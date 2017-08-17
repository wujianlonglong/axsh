package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    public User getByUserName(String username) {

        return userRepository.findByUsername(username);
    }

    // TODO 用户维护，修改密码
}
