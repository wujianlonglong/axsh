package anxian.gateway.admin.module.security.service;

import anxian.gateway.admin.module.security.UserContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by jiangzhe on 15-11-17.
 */
public class UserContextService implements UserDetailsService {

    private anxian.gateway.admin.module.security.service.SecurityManager securityManager;

    public anxian.gateway.admin.module.security.service.SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(anxian.gateway.admin.module.security.service.SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserContext userContext = this.securityManager.getUserContext(username);

        if (userContext == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return userContext;
    }
}
