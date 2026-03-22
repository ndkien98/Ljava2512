<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- Redirect thông minh: đã login → dashboard, chưa login → login --%>
<%
    Object loggedUser = session.getAttribute("loggedUser");
    if (loggedUser != null) {
        response.sendRedirect(request.getContextPath() + "/dashboard");
    } else {
        response.sendRedirect(request.getContextPath() + "/login");
    }
%>