package anxian.gateway.admin.config;

import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录失败处理
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private UserService userService;

    public CustomAuthenticationFailureHandler() {
    }

    public CustomAuthenticationFailureHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession httpSession = request.getSession();
        // 用户名为空
        if (StringUtils.isEmpty(username)) {
            SecurityContextHolder.getContext().setAuthentication(null);
            httpSession.setAttribute("message", "用户名不能为空！");
            response.sendRedirect("/login");
            return;
        }

        // 密码不能为空
        if (StringUtils.isEmpty(password)) {
            SecurityContextHolder.getContext().setAuthentication(null);
            httpSession.setAttribute("message", "密码不能为空！");
            response.sendRedirect("/login");
            return;
        }

        // 处理用户名、密码错误的情况

        if (exception instanceof BadCredentialsException) {
            httpSession.setAttribute("message", "用户名或密码错误");
            response.sendRedirect("/login");
            return;
        }

        User user = userService.getByUserName(username);
        if (null == user) {
            SecurityContextHolder.getContext().setAuthentication(null);
            httpSession.setAttribute("message", "没有找到" + username + "对应的用户");
            response.sendRedirect("/login");
        }
    }
}
