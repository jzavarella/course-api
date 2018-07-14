package ca.jackzavarella.courses.pdf.filters;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Jack Zavarella on 7/14/2018. :)
 */
@Order(1)
public class ParseFilter implements Filter {

    private final boolean allowParsing;

    public ParseFilter(boolean allowParsing) {
        this.allowParsing = allowParsing;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (!this.allowParsing) {
            throw new ServletException("PDF Parsing has been disabled by the administrator...");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
