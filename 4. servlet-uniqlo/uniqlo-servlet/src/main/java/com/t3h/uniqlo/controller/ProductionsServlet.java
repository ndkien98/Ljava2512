package com.t3h.uniqlo.controller;

import com.t3h.uniqlo.model.ProductionDTO;
import com.t3h.uniqlo.service.ProductionService;
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

        List<ProductionDTO> productionDTOS = productionService.findByCondition();
        productionDTOS.forEach(data -> System.out.println(data.toString()));
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/productions.jsp");
        rd.forward(req, resp);
    }
}
