package anxian.gateway.admin.module.security;


import anxian.gateway.admin.module.base.domain.AclUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * 获取登陆后用户信息的工具类
 */
public class UserContextHelper {

    public static UserContext getUserContext() {
        return ((getAuthentication() != null) && (getAuthentication().getPrincipal() instanceof UserContext)) ? (UserContext) getAuthentication().getPrincipal() : null;
    }

    public static SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    public static Authentication getAuthentication() {
        return (getContext() == null) ? null : getContext().getAuthentication();
    }

    public static AclUser getUser() {
        return (getUserContext() == null) ? null : getUserContext().getUser();
    }

    public static String getSessionId() {
        WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) UserContextHelper.getAuthentication().getDetails();
        return webAuthenticationDetails.getSessionId();
    }
}
