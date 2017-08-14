package anxian.gateway.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gaoqichao on 15-12-8.
 */
@Component
public class CORSFilter implements Filter {
    private static Logger LOG = LoggerFactory.getLogger(CORSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "OPTIONS,POST, PUT, GET, DELETE");
        response.setHeader("Access-Control-Max-Age", "1800");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,x-auth-token,terminal");
        response.setHeader("Access-Control-Expose-Headers", "x-auth-token,terminal");

        if (((HttpServletRequest) request).getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(HttpStatus.OK.value());
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
