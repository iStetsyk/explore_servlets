package pl.hit.servlets.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class ResponseInfoFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(req, res);

        System.out.println("ResponseInfoFilter.doFilter \t Odpowiedź");
        System.out.println("ResponseInfoFilter.doFilter \t Status: " + res.getStatus());
        System.out.println("ResponseInfoFilter.doFilter \t Typ treści: " + res.getContentType());
        System.out.println("ResponseInfoFilter.doFilter \t Kodowanie: " + res.getCharacterEncoding());
        System.out.println("ResponseInfoFilter.doFilter \t Nagłówki: ");
        res.getHeaderNames().forEach(header ->
                System.out.println("ResponseInfoFilter.doFilter \t\t" + header + " : " + res.getHeaders(header)));
    }
}