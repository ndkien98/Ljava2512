package com.t3h.uniqlo.service;

import com.t3h.uniqlo.dao.CategoryDao;
import com.t3h.uniqlo.dao.ColorDao;
import com.t3h.uniqlo.dao.IProductionDao;
import com.t3h.uniqlo.dao.ProductionsDao;
import com.t3h.uniqlo.dao.impl.ProductionDaoPosgreSqlImpl;
import com.t3h.uniqlo.model.dto.CategoryDTO;
import com.t3h.uniqlo.model.dto.ColorDTO;
import com.t3h.uniqlo.model.dto.ProductionDTO;
import com.t3h.uniqlo.model.response.ProductionResponse;

import java.util.List;

/**
  So sánh giữa 2 class
    1. ProductionDTO
    2. ProductionService, ProductionsDao

 - Chức năng:
    1.
        - Đóng gói data từ repo đi qua các service, controller rồi trảvề client
        - Giảm bớt các thông tin thừa trong db
        - Che giấu thiết kế db, chỉ expose những trường cần thiết cho client
    => DTO sử dụng để chưa dữ liệu.
    2.
 ProductionService
        - Sử dụng để xử lý logic nghiệp vụ liên quan sảm phẩm
        - Tương tác với các dao để lấy dữ liệu, sau đó có thể xử lý thêm (nếu cần) rồi trả về cho controller
        - Là layer ở giữa giúp controller tương tác lấy ra model dữ liệu từ dao và controller sẽ gắn dữ liệu đó vào view (jsp) để hiển thị cho người dùng
 ProductionsDao
        - Layer chuyên giao tiếp với database, thực hiện các câu lệnh SQL để lấy dữ liệu, đếm số lượng, thêm/sửa/xóa sản phẩm trong database
        - Chỉ tập trung vào việc truy xuất dữ liệu, không chứa logic nghiệp vụ phức tạp nào khác, đảm bảo nguyên tắc Single Responsibility Principle (SRP) trong thiết kế phần mềm
    => Sử dụng để cung cấp các method xử lý dữ liệu của các layer, sử dụng model(dto,entity,response,request) để trao đổi dữ liệu giữa các layer, có thể gọi đến các dao để lấy dữ liệu từ database rồi trả về cho controller
    và nó không cần phải chua dữ liệu.

 ?: Có cần tạo quá > 1 đối tượng của layer dao, service không ?
 tl :không
 vì:
    - với model(dto,entity,response,request) cần tạo ra đối tượng(bean) bằng tu khóa new cho mỗi lần sử dụng vì nó phải chứa dữ liệu để xử lý, nếu không tạo ra đối tượng mới với
    các ô nhớ mới => có thể bị ghi đè lẫn nhau dẫn đến sai dữ liệu giữa các request hoặc các method
    - còn với các layer dao,service,controller... các layuer chỉ cung cấp method để xử lý nghiệp vụ dữ liệu sẽ không cần thiết tạo quá nhiều object mỗi khi xử dụng
    vì chúng không chứa dữ liệu mà chỉ cung cấp các method để xử lý dữ liệu, xử lý nghiệp vụ.
    => hiện tại trong project này để sử duung được các layer service,dao thì vẫn phải khơi tạo các đối tượng của layer đó,
    có thể cải thiện bằng cách áp dụng design pattern singleton để giới hạn chỉ tạo ra 1 instance duy nhất cho các layer dao,service,controller... hoặc sử dụng framework spring để quản lý các bean này, khi đó spring sẽ đảm nhiệm việc khởi tạo và quản lý vòng đời của các bean này, chúng ta chỉ cần khai báo các bean này là spring sẽ tự động khởi tạo và quản lý chúng.


 Singleton: Giới hạn số lượng của 1 class, chỉ cho phép tạo ra 1 instance duy nhất của class đó.
 Để thực hiện:
 1. tạo ra constructor private của các class đó để không cho phép tạo đối tượng bằng từ khóa new
 2. tạo ra 1 biến static private để lưu trữ instance duy nhất của class đó
 3. tạo ra 1 phương thức static public để cung cấp instance duy nhất đó, trong method này
 xử lý nếu instance chưa tồn tại thì tạo mới, nếu đã tồn tại thì trả về instance đã có sẵn

 => Đó là cách xử lý vớ servlet

 Khi sử dụng spring framework tất cả các object của layer layer dao, servce, controller đều sẽ được spring quản lý tự động dưới dạng các bean
 và được config mặc đjnh sẽ sử dụng singleton scope, tức là chỉ tạo ra 1 instance duy nhất cho mỗi bean đó,
 khi đó chúng ta không cần phải lo lắng về việc tạo quá nhiều object của các layer này nữa, spring sẽ đảm nhiệm việc đó cho chúng ta.

 vậy để quản lý tự động object(bean) của các layer thì spring sẽ sử dụng 2 cơ chế chính:
    IOC (Inversion of Control) và DI (Dependency Injection)

 */
public class ProductionService {

    private final IProductionDao productionsDao2;
    private final ProductionsDao productionsDao;
    private static ProductionService productionService;
    private ProductionService(IProductionDao productionsDao2,ProductionsDao productionsDao) {
        this.productionsDao2 = productionsDao2;
        this.productionsDao = productionsDao;
    }

    public static ProductionService getInstance(ProductionsDao productionsDao) {
        if (productionService == null) {
            productionService = new ProductionService(new ProductionDaoPosgreSqlImpl(),productionsDao);
        }
        return productionService;
    }

    private final CategoryDao categoryDao = new CategoryDao();
    private final ColorDao colorDao = new ColorDao();

    public ProductionResponse findByCondition(int pageSize, int pageIndex, String keySearch, Integer colorId, Integer categoryId) {
        ProductionResponse response = new ProductionResponse();

        Integer totalElements = productionsDao2.countProductions(keySearch, colorId, categoryId);
        int totalPages = totalElements / pageSize;
        if (totalElements % pageSize != 0) {
            totalPages++;
        }
        if (totalPages == 0) totalPages = 1;

        List<ProductionDTO> data = productionsDao.findByCondition(pageSize, (pageIndex - 1) * pageSize, keySearch, colorId, categoryId);
        response.setData(data);
        response.setTotalPages(totalPages);
        response.setCurrentPage(pageIndex);
        response.setPageSize(pageSize);
        return response;
    }

    public ProductionDTO findById(int id) {
        return productionsDao.findById(id);
    }

    public void save(String name, Integer categoryId, String description, String materialInfo, String avatar, Integer id) {
        if (id == null || id <= 0) {
            productionsDao.insert(name, categoryId, description, materialInfo, avatar);
        } else {
            productionsDao.updateProduct(id, name, categoryId, description, materialInfo, avatar);
        }
    }

    public void delete(int id) {
        productionsDao.deleteProduct(id);
    }

    public List<CategoryDTO> getCategories() {
        return categoryDao.findAll();
    }

    public List<ColorDTO> getColors() {
        return colorDao.findAll();
    }
}
