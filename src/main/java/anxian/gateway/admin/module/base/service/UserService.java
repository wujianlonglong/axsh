package anxian.gateway.admin.module.base.service;

import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.model.UserModel;
import anxian.gateway.admin.module.base.repository.UserRepository;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private NewRoleService newRoleService;

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

    /**
     * 获取用户列表
     *
     * @param workerId
     * @param pageable
     * @return
     */
    public ResponseMessage<Page<User>> list(String workerId, Pageable pageable) {
        if (StringUtils.isEmpty(workerId)) {
            return ResponseMessage.success(userRepository.findAll(pageable));
        }
        return ResponseMessage.success(userRepository.findByUsernameLike(workerId, pageable));
    }

    /**
     * 根据id获取用户信息
     *
     * @param id
     * @return
     */
    public ResponseMessage<User> getById(String id) {

        User user = userRepository.findOne(id);

        return null == user ? ResponseMessage.error("没有获取对应的用户") : ResponseMessage.success(user);
    }

    /**
     * 保存用户
     *
     * @param userModel
     * @return
     */
    public ResponseMessage save(UserModel userModel, String username) {

        String id = userModel.getId();
        User user = null;
        if (StringUtils.isEmpty(id)) {
            user = new User();
            user.setCreater(username);
            user.setCreateDateTime(LocalDateTime.now());
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        } else {
            user = userRepository.findOne(id);
            if (null == user) {
                userModel.setId(null);
            }
            user.setUpdater(username);
            user.setUpdateDateTime(LocalDateTime.now());
        }

        user.setShopId(userModel.getShopId());
        user.setFullName(userModel.getFullName());
        user.setNewRole(newRoleService.getOne(userModel.getRoleId()));
        user.setIsValid(userModel.getIsValid());
        User result = userRepository.save(user);
        return null == result ? ResponseMessage.error("保存失败！") : ResponseMessage.success("保存成功！");
    }
}
