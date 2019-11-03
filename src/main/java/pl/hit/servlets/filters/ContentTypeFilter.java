package pl.hit.servlets.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class ContentTypeFilter extends HttpFilter {

    private static final String DEFAULT_CONTENT_TYPE = "text/html;charset=UTF-8";

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        res.setContentType(DEFAULT_CONTENT_TYPE);

        chain.doFilter(req, res);
    }
}