package com.t3h.uniqlo.controller;

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
import java.util.List;

@Slf4j
@WebServlet(name = "ProductionsServlet", urlPatterns = "/productions")
public class ProductionsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductionService productionService = new ProductionService();

        Integer pageSize = StringUtils.getInteger(req.getParameter("pageSize"));
        Integer pageIndex = StringUtils.getInteger(req.getParameter("currentPage"));
        String keyword = req.getParameter("keySearch") != null ? req.getParameter("keySearch") : "";
        Integer categoryId = StringUtils.getInteger(req.getParameter("categoryId"));
        Integer colorId = StringUtils.getInteger(req.getParameter("colorId"));
        if (pageIndex == -1){
            pageIndex = Constant.DEFAULT_PAGE;
        }
        if (pageSize == -1){
            pageSize = Constant.DEFAULT_PAGE_SIZE;
        }

        ProductionResponse response = productionService.findByCondition(pageSize,pageIndex, keyword, colorId, categoryId);

        req.setAttribute("productions", response.getData()); // gắn danh sách dữ liệu vào request bằng thuộc tính Attribute với key=productions, value = danh sách sản phẩm
        req.setAttribute("keyword", keyword);
        req.setAttribute("categoryId", categoryId);
        req.setAttribute("colorId", colorId);
        req.setAttribute("totalPages", response.getTotalPages());
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("currentPage", pageIndex);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/productions.jsp");
        rd.forward(req, resp);
    }
}
