package pl.hit.servlets.jsp;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet (urlPatterns = "/jsp/empty-page-servlet")
public class EmptyPageServlet extends HttpServlet {

    public void service(ServletRequest req, ServletResponse res) throws IOException {
        res.setContentType("UTF-8");

        PrintWriter writer = res.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Pusta strona</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<div>");
        writer.println("<h1>Pusta strona</h1>");
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");

    }
}
