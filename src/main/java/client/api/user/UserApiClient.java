package client.api.user;

import client.api.user.domain.User;
import client.api.user.utils.page.PageForAdmin;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by gaoqichao on 15-12-1.
 */
@FeignClient(name="sjes-api-user")
@RequestMapping("/users/anxian")
public interface UserApiClient {

    /**
     * 　根据用户id取得用户信息
     *
     * @param id 用户id
     * @return 用户对象
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    User findById(@PathVariable("id") Long id);

    /**
     * 　根据登录名取得用户信息
     *
     * @param name 用户名
     * @return 用户对象
     */
    @RequestMapping(method = RequestMethod.GET, value = "/findByLogin")
    User findUser(@RequestParam("name") String name);

    /**
     * x
     * 根据用户名/手机号/邮箱/会员卡号分页取的用户信息列表
     *
     * @param username 用户名
     * @param mobile   手机号
     * @param email    邮箱
     * @param cardNum  会员卡号
     * @return 分页信息列表
     */
    @RequestMapping(method = RequestMethod.POST, value = "/page")
    PageForAdmin pageGetUserList(@RequestParam("username") String username,
                                 @RequestParam("mobile") String mobile,
                                 @RequestParam("email") String email,
                                 @RequestParam("cardNum") String cardNum,
                                 @RequestParam("page") int page, @RequestParam("size") int size);

    /**
     * 　根据登录名取得用户信息
     *
     * @param username 　用户名
     * @return 用户对象
     */
    @RequestMapping(method = RequestMethod.GET, value = "/findByUsername")
    User findByUsername(@RequestParam("username") String username);

    /**
     * 更新用户信息
     *
     * @param user 　用户对象
     * @return　用户对象
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    User updateUser(User user);

    @RequestMapping(method = RequestMethod.PUT, value = "/updateMobileAndEmailAndCardNum")
    Integer updateMobileAndEmailAndCardNum(@RequestParam("id") Long id,
                                           @RequestParam("username") String username,
                                           @RequestParam("mobile") String mobile,
                                           @RequestParam("email") String email,
                                           @RequestParam("cardNum") String cardNum);
}
