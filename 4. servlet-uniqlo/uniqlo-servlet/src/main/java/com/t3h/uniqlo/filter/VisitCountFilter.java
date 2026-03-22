package com.t3h.uniqlo.filter;

import com.t3h.uniqlo.dao.VisitCountDao;
import jakarta.servlet.*;

import java.io.IOException;

/**
 * VisitCountFilter – XML Config (đăng ký trong web.xml, KHÔNG dùng @WebFilter)
 * Đếm tổng số lượt truy cập website và lưu vào ServletContext.
 * Demo FilterConfig: đọc init-param "excludePrefix" từ web.xml.
 */
public class VisitCountFilter implements Filter {

    private FilterConfig    filterConfig;
    private String          excludePrefix;
    private final VisitCountDao visitCountDao = new VisitCountDao();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig  = filterConfig;
        // Đọc init-param từ web.xml (demo FilterConfig)
        this.excludePrefix = filterConfig.getInitParameter("excludePrefix");
        if (this.excludePrefix == null) this.excludePrefix = "/assets";

        // Khởi tạo visit count trong ServletContext
        // Bọc try/catch: nếu DB chưa sẵn sàng (bảng chưa tồn tại) app vẫn start bình thường
        try {
            long currentCount = visitCountDao.getCount();
            filterConfig.getServletContext().setAttribute("visitCount", currentCount);
            filterConfig.getServletContext().log(
                "[VisitCountFilter] init – excludePrefix=" + excludePrefix
                + " | currentCount=" + currentCount);
        } catch (Exception e) {
            filterConfig.getServletContext().setAttribute("visitCount", 0L);
            filterConfig.getServletContext().log(
                "[VisitCountFilter] init WARN – cannot load visitCount from DB: " + e.getMessage());
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Lấy request URI để bỏ qua asset files
        String uri = ((jakarta.servlet.http.HttpServletRequest) request).getRequestURI();

        // Chỉ đếm các request KHÔNG phải static asset
        if (uri != null && !uri.contains(excludePrefix)) {
            try {
                visitCountDao.increment();
                long count = visitCountDao.getCount();
                filterConfig.getServletContext().setAttribute("visitCount", count);
            } catch (Exception e) {
                // DB lỗi → không dừng request, tiếp tục bình thường
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        filterConfig.getServletContext().log("[VisitCountFilter] destroy");
    }
}
