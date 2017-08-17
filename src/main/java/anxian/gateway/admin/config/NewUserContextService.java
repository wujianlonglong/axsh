package anxian.gateway.admin.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by jiangzhe on 15-11-17.
 */
public class NewUserContextService implements UserDetailsService {

    private SecurityManager securityManager;

    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NewUserContext newUserContext = this.securityManager.getNewUserContext(username);

        if (newUserContext == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        return newUserContext;
    }
}
