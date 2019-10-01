package be.afelio.babell.tp_babell.api.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AngularCorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String origin = httpRequest.getHeader("Origin");
        httpResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
        httpResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET,POST");
        httpResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Authorization,Content-type");
        httpResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        httpResponse.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization");
        httpResponse.setHeader(HttpHeaders.ALLOW, "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        httpResponse.setHeader(HttpHeaders.VARY, "*");

        if (HttpMethod.OPTIONS.matches(httpRequest.getMethod())) {
            httpResponse.setStatus(200);
            httpResponse.flushBuffer();
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
