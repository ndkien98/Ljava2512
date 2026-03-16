package com.t3h.uniqlo;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

/**
 @WebServlet:
    - Annotation sử dụng để khai báo class này là một servlet, để dispatcher servelet có thể nhận biết và forward request đến đúng servlet được chỉ định
     thông qua đường vẫn trong thuộc tính value.
    - name: Tên của servlet, có thể được sử dụng để tham chiếu đến servlet này trong các cấu hình khác hoặc trong mã Java.
    - value: Đường dẫn URL mà servlet sẽ xử lý. Khi một yêu cầu đến URL này, servlet sẽ được kích hoạt để xử lý yêu cầu đó.

 Ví dụ, nếu value = /hello-servlet, và application chạy trên domain uniqlo.com
 khi đó user truy câp vào url: uniqlo.com/hello-servlet thì servlet này sẽ được kích hoạt và thực hiện các logic xử lý
 trong phương thức doGet hoặc doPost tùy thuộc vào loại yêu cầu HTTP mà user gửi đến.
 */
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World! Welcome to Java Servlets.";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}