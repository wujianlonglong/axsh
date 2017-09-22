package client.api.user;

import client.api.user.domain.Blacklist;
import client.api.user.utils.page.PageForAdmin;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gaoqichao on 16-2-18.
 */
@FeignClient("sjes-api-user")
@RequestMapping("/blackList/anxian")
public interface BlacklistApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/id")
    Blacklist findById(@RequestParam("id") long id);

    /**
     * 新增/修改黑名单
     *
     * @param blackList 黑名单对象实例
     * @return 黑名单对象实例
     */
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    Blacklist save(Blacklist blackList);

    /**
     * 删除给定的黑名单
     *
     * @param idList 主键列表
     */
    @RequestMapping(method = RequestMethod.POST, value = "/findByIdList", consumes = "application/json")
    List<Blacklist> findByIdList(@RequestBody List<Long> idList);

    /**
     * 批量更新黑名单数据
     *
     * @param blacklists
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/batchUpdate", consumes = "application/json")
    List<Blacklist> batchUpdate(@RequestBody List<Blacklist> blacklists);

    /**
     * 根据用户id查询黑名单信息
     *
     * @param userId 用户id
     * @return 黑名单对象实例，无结果返回null
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    Blacklist findByUserId(@PathVariable("userId") Long userId);

    /**
     * 分页取得黑名单列表
     *
     * @param username 用户名
     * @param mobile   手机号
     * @param email    邮箱
     * @param custNum  会员卡号
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/pageGetBlackLists")
    PageForAdmin<Blacklist> pageGetBlackLists(@RequestParam("username") String username,
                                              @RequestParam("mobile") String mobile,
                                              @RequestParam("email") String email,
                                              @RequestParam("custNum") String custNum,
                                              @RequestParam("page") int page,
                                              @RequestParam("size") int size);

}
