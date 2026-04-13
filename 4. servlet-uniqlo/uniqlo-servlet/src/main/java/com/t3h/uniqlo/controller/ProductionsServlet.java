package com.t3h.uniqlo.controller;

import com.t3h.uniqlo.dao.ProductionsDao;
import com.t3h.uniqlo.dao.impl.ProductionDaoMysqlImpl;
import com.t3h.uniqlo.dao.impl.ProductionDaoPosgreSqlImpl;
import com.t3h.uniqlo.model.dto.ProductionDTO;
import com.t3h.uniqlo.model.response.ProductionResponse;
import com.t3h.uniqlo.service.ProductionService;
import com.t3h.uniqlo.utils.Constant;
import com.t3h.uniqlo.utils.StringUtils;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "ProductionsServlet", urlPatterns = "/productions")
public class ProductionsServlet extends HttpServlet {

    private final ProductionService productionService;

    public ProductionsServlet() {
        productionService = ProductionService.getInstance(ProductionsDao.getInstance());
    }


    // ===================== GET: hiển thị danh sách / form sửa =====================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        // Nếu action = edit -> load sản phẩm theo id để điền vào modal
        if ("edit".equals(action)) {
            Integer id = StringUtils.getInteger(req.getParameter("id"));
            if (id != null && id > 0) {
                ProductionDTO editProduct = productionService.findById(id);
                req.setAttribute("editProduct", editProduct);
            }
        }

        // Lấy tham số phân trang / tìm kiếm
        Integer pageSize  = StringUtils.getInteger(req.getParameter("pageSize"));
        Integer pageIndex = StringUtils.getInteger(req.getParameter("currentPage"));
        String  keyword   = req.getParameter("keySearch") != null ? req.getParameter("keySearch").trim() : "";
        Integer categoryId = StringUtils.getInteger(req.getParameter("categoryId"));
        Integer colorId    = StringUtils.getInteger(req.getParameter("colorId"));

        if (pageIndex == -1) pageIndex = Constant.DEFAULT_PAGE;
        if (pageSize  == -1) pageSize  = Constant.DEFAULT_PAGE_SIZE;

        // Lấy danh sách sản phẩm
        ProductionResponse response = productionService.findByCondition(pageSize, pageIndex, keyword, colorId, categoryId);

        // Bind attributes vào request
        req.setAttribute("productions",  response.getData());
        req.setAttribute("keySearch",    keyword);
        req.setAttribute("categoryId",   categoryId);
        req.setAttribute("colorId",      colorId);
        req.setAttribute("totalPages",   response.getTotalPages());
        req.setAttribute("pageSize",     pageSize);
        req.setAttribute("currentPage",  pageIndex);

        // Dropdown categories và colors từ DB
        req.setAttribute("categories", productionService.getCategories());
        req.setAttribute("colors",     productionService.getColors());

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/productions.jsp");
        rd.forward(req, resp);
    }

    // ===================== POST: xử lý thêm / sửa / xóa =====================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        String contextPath = req.getContextPath();

        if ("create".equals(action)) {
            String  name         = req.getParameter("name");
            Integer categoryId   = StringUtils.getInteger(req.getParameter("category_id"));
            String  description  = req.getParameter("description");
            String  materialInfo = req.getParameter("material_info");
            String  avatar       = req.getParameter("avatar");
            if (avatar == null || avatar.isBlank()) {
                avatar = "https://placehold.co/400x500/EEE/333?text=No+Image";
            }

            try {
                productionService.save(name, categoryId, description, materialInfo, avatar, null);
                resp.sendRedirect(contextPath + "/productions?success=created");
            } catch (Exception e) {
                log.error("Lỗi khi tạo sản phẩm", e);
                resp.sendRedirect(contextPath + "/productions?error=create_failed");
            }

        } else if ("update".equals(action)) {
            Integer id           = StringUtils.getInteger(req.getParameter("id"));
            String  name         = req.getParameter("name");
            Integer categoryId   = StringUtils.getInteger(req.getParameter("category_id"));
            String  description  = req.getParameter("description");
            String  materialInfo = req.getParameter("material_info");
            String  avatar       = req.getParameter("avatar");

            try {
                productionService.save(name, categoryId, description, materialInfo, avatar, id);
                resp.sendRedirect(contextPath + "/productions?success=updated");
            } catch (Exception e) {
                log.error("Lỗi khi cập nhật sản phẩm id={}", id, e);
                resp.sendRedirect(contextPath + "/productions?error=update_failed");
            }

        } else if ("delete".equals(action)) {
            Integer id = StringUtils.getInteger(req.getParameter("id"));
            try {
                productionService.delete(id);
                resp.sendRedirect(contextPath + "/productions?success=deleted");
            } catch (Exception e) {
                log.error("Lỗi khi xóa sản phẩm id={}", id, e);
                resp.sendRedirect(contextPath + "/productions?error=delete_failed");
            }

        } else {
            resp.sendRedirect(contextPath + "/productions");
        }
    }
}
