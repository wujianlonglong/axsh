package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.repository.UserRepository;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import client.api.item.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */
    public User getByUserName(String username) {

        return userRepository.findByUsername(username);
    }

    /**
     * 密码修改
     *
     * @param newPwd
     * @param oldPwd
     * @param username
     * @return
     */
    public ResponseMessage updatePassword(String newPwd, String oldPwd, String username) {

        User user = userRepository.findByUsername(username);
        String password = user.getPassword();

        boolean checkResult = passwordEncoder.matches(oldPwd, password);

        if (!checkResult) {
            return ResponseMessage.error("输入原先的密码有误！");
        }

        String newEncoderPwd = passwordEncoder.encode(newPwd);
        user.setPassword(newEncoderPwd);
        userRepository.save(user);
        return ResponseMessage.success("密码修改成功！");
    }
}
