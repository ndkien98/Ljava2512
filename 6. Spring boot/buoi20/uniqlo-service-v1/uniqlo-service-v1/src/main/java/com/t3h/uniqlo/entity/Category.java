package com.t3h.uniqlo.entity;

import com.t3h.uniqlo.repository.CategoryRepository;
import com.t3h.uniqlo.repository.impl.CategoryRepositoryImpl;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String name;
    /*
    1 category cha có thể có nhiều category con,
    nhưng 1 category con chỉ có 1 category cha.
    => Sử dụng @ManyToOne ở đây để ánh xạ quan hệ cha-con

    fetch: có 2 loại
        1. EAGER: khi load category con thì tự động load category cha (join query)
           => khi query lấy ra category thì filed parent cũng tự động có giá trị, của bản ghi cha,
           không cần gọi getParent() nữa
        2. LAZY: khi load category con thì không load category cha, chỉ khi nào gọi getParent() mới load category cha (select query riêng)
            => khi query lấy ra category thì filed parent không có giá trị, của bản ghi cha,
           chỉ gó giá trị khi gọi method getParent() để load category cha lên,
           lúc đó mới có giá trị của bản ghi cha
           lưu ý:
            1. nêu dùng LAZY thì khi gọi getParent() để load category cha lên, mà session đã đóng thì sẽ bị lỗi LazyInitializationException
            => phải gọi method getParent() trong session đang connect.
            2. có thể bị lỗi n+1 query nếu trường hợp có nhiều category con trong children
                n+1 query: khi query lấy ra category thì danh sách children cũng sẽ được lấy ra nhưng sẽ lấy ra từ category con
                trong vòng for -> sẽ query = children.length lần để ly ra toàn bộ category con của category đó
              vấn đề có n+1 query có thể sảy ra tương tự với thuộc tính products
              => qery lấy ra category sẽ có 1 query để lấy ra category đó, sau đó sẽ có n query để lấy ra toàn bộ category con của category đó, và m=products.length query để lấy ra toàn bộ product của category đó
            => giai pháp:
                1. @EntityGraph để cấu hình để khi query lấy ra category thì tự động
                join query để lấy ra toàn bộ category con và product của category đó, tránh n+1 query
                2. Sử dụng DTO để custom các field được lấy ra
                3. sử dụng join fetch trong query để lấy ra category con và product của category đó, tránh n+1 query

     cascade: sử dụng để config tác động khi thao tác với entity(category) sẽ ảnh hưởng như thế nào đến dữ liệu các table
        có quan hệ với entity hiện tại ( trong bối cảnh này chính là config thể hiện khi tác động vào entity category
        sẽ ảnh hưởng như thế nào đến dữ liệu của table categories (bản ghi cha) và table products (bản ghi con) thông qua các thuộc tính parent và products)
        1. CascadeType.ALL: khi thao tác với entity category thì sẽ tác động đến tất cả các table có quan hệ với entity category thông qua các thuộc tính parent và products,
        cụ thể:
            - khi tạo mới category con và gán category cha cho category con đó, thì khi save category con thì sẽ tự động save category cha nếu category cha chưa tồn tại trong database
            - khi xóa category cha thì sẽ tự động xóa tất cả category con của category
                cũng sẽ bị xóa theo, nếu không có cascade thì khi xóa category cha mà vẫn còn category con thì sẽ bị lỗi do ràng buộc khóa ngoại (foreign key constraint) của database, vì category con vẫn còn tham chiếu đến category cha đã bị xóa
            - Tương tự với thuộc tính products, khi thao tác với entity category thì sẽ tác động đến tất cả các table có quan hệ với entity category thông qua thuộc tính products, cụ thể:
                + khi xóa category thì sẽ tự động xóa tất cả product của category đó,
                nếu không có cascade thì khi xóa category mà vẫn còn product của category đó thì sẽ bị lỗi do ràng buộc khóa ngoại (foreign key constraint) của database, vì product vẫn còn tham chiếu đến category đã bị xóa
        2. CascadeType.PERSIST: khi tạo mới category con và gán category cha cho category con đó, thì khi save category con thì sẽ tự động save category cha nếu category cha chưa tồn tại trong database, nhưng khi xóa category cha thì sẽ không tự động xóa tất cả category con của category đó, nếu muốn xóa category cha thì phải xóa tất cả category con của category đó trước, nếu không sẽ bị lỗi do ràng buộc khóa ngoại (foreign key constraint) của database, vì category con vẫn còn tham chiếu đến category cha đã bị xóa
        3. CascadeType.REMOVE: khi tạo mới category con và gán category cha cho category con đó, thì khi save category con thì sẽ không tự động save category cha nếu category cha chưa tồn tại trong database, nhưng khi xóa category cha thì sẽ tự động xóa tất cả category con của category đó, nếu không có cascade thì khi xóa category cha mà vẫn còn category con thì sẽ bị lỗi do ràng buộc khóa ngoại (foreign key constraint) của database, vì category con vẫn còn tham chiếu đến category cha đã bị xóa
        4. CascadeType.MERGE: khi tạo mới category con và gán category cha cho category con đó, thì khi save category con thì sẽ không tự động save category cha nếu category cha chưa tồn tại trong database, nhưng khi update entity category thì sẽ tự động update entity các table có quan hệ với entity đó thông qua các thuộc tính parent và products, cụ thể:
            - khi update entity category thì sẽ tự động update entity các table có quan hệ với entity đó thông qua thuộc tính parent và products, cụ thể:
                + khi update entity category mà có thay đổi về parent thì sẽ tự động update entity của table categories (bản ghi cha) tương ứng với parent mới của entity đó, nếu parent mới chưa tồn tại trong database thì sẽ tự động tạo mới bản ghi cha đó trong database
                + khi update entity category mà có thay đổi về products thì sẽ tự động update entity của table products (bản ghi con) tương ứng với products mới của entity đó, nếu product mới chưa tồn tại trong database thì sẽ tự động tạo mới bản ghi con đó trong database
        5. CascadeType.DETACH: khi tạo mới category con và gán category cha cho category con đó, thì khi save category con thì sẽ không tự động save category cha nếu category cha chưa tồn tại trong database, nhưng khi detach entity category thì sẽ tự động detach entity các table có quan hệ với entity đó thông qua các thuộc tính parent và products, cụ thể:
            - khi detach entity category thì sẽ tự động detach entity các table có quan hệ với entity đó thông qua thuộc tính parent và products, cụ thể:
                + khi detach entity category thì sẽ tự động detach entity của table categories (bản ghi cha) tương ứng với parent của entity đó
                + khi detach entity category thì sẽ tự động detach entity của table products (bản ghi con) tương ứng với products của entity đó
        6. CascadeType.REFRESH: khi tạo mới category con và gán category cha cho category con đó, thì khi save category con thì sẽ không tự động save category cha nếu category cha chưa tồn tại trong database, nhưng khi refresh entity category thì sẽ tự động refresh entity các table có quan hệ với entity đó thông qua các thuộc tính parent và products, cụ

     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(
            mappedBy = "parent", // mappedBy trỏ đến tên thuộc tính parent trong entity Category, để xác định quan hệ cha-con giữa 2 entity Category với nhau
            fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private java.util.List<Category> children;

    @OneToMany(mappedBy = "category", // mappedBy trỏ đến tên thuộc tính category trong entity Product, để xác định quan hệ giữa 2 entity Category và Product với nhau
             cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Product> products;


    public static void main(String[] args) {

        Category category = new Category();
        Category parent = new Category();
        Product product = new Product();
        category.setParent(parent);
        category.setProducts(Arrays.asList(product));
        CategoryRepository categoryRepository = new CategoryRepositoryImpl();
        categoryRepository.save(category);
        /**
         CascadeType.ALL:
            - khi save category con thì
                + sẽ tự động save category cha nếu category cha chưa tồn tại trong database
                + tự động thêm mới product nếu product chưa tồn tại trong database, và tự động gán category cho product đó là category hiện tại, nếu product đã tồn tại trong database thì sẽ tự động cập nhật category của product đó thành category hiện tại
           nếu không có cascade thì khi save category con mà category cha chưa tồn tại trong database thì sẽ bị lỗi do ràng buộc khóa ngoại (foreign key constraint) của database,
         */

        categoryRepository.delete(parent);
         /**
         CascadeType.ALL:
            khi xóa category cha thì sẽ tự động xóa tất cả category con của category cũng sẽ bị xóa theo, nếu không có cascade thì khi xóa category cha mà vẫn còn category con thì sẽ bị lỗi do ràng buộc khóa ngoại (foreign key constraint) của database, vì category con vẫn còn tham chiếu đến category cha đã bị xóa
            tương tự với thuộc tính products, khi thao tác với entity category thì sẽ tác động đến tất cả các table có quan hệ với entity category thông qua thuộc tính products, cụ thể:
                + khi xóa category thì sẽ tự động xóa tất cả product của category đó, nếu không có cascade thì khi xóa category mà vẫn còn product của category đó thì sẽ bị lỗi do ràng buộc khóa ngoại (foreign key constraint) của database, vì product vẫn còn tham chiếu đến category đã bị xóa)

         */
    }
}
