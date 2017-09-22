package anxian.gateway.admin.config;

import anxian.gateway.admin.module.base.domain.NewAuthority;
import anxian.gateway.admin.module.base.domain.NewRole;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jiangzhe on 15-11-17.
 */
public class NewSecurityManager implements SecurityManager {

    @Autowired
    private UserRepository userRepository;


    private User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public NewUserContext getNewUserContext(String username) {
        User user = getUser(username);

        if (user == null) {
            return null;
        }
        NewUserContext userContext = new NewUserContext(user);
        userContext.setUserAuthorities(getUserAuthorities(user));
        userContext.setRole(user.getNewRole());
        return userContext;
    }

    /**
     * 返回当前用户的全局权限
     *
     * @param user
     * @return
     */
    private List<NewAuthority> getUserAuthorities(User user) {

        NewRole role = user.getNewRole();
        if (null == role) {
            // TODO 未绑定用户异常
            return null;
        }

        return role.getNewAuthorities();
    }
}
