package client.api.user;

import client.api.user.domain.Feedback;
import client.api.user.utils.page.PageForAdmin;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by gaoqichao on 16-3-30.
 */
@FeignClient("sjes-api-user")
@RequestMapping("/feedbacks")
public interface FeedbackApiClient {
    /**
     * 根据id取得指定的个人反馈信息
     *
     * @param id id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    Feedback findById(@PathVariable("id") Long id);


    /**
     * 根据查询条件取得在线反馈列表
     *
     * @param userId    用户id
     * @param mobile    手机号
     * @param isSolved  解决状态
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param page      页面
     * @param size      每页显示的条目数
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/findByCond")
    PageForAdmin<Feedback> findByCondition(@RequestParam("userId") Long userId,
                                           @RequestParam("mobile") String mobile,
                                           @RequestParam("isSolved") String isSolved,
                                           @RequestParam("startDate") String startDate,
                                           @RequestParam("endDate") String endDate,
                                           @RequestParam("page") int page,
                                           @RequestParam("size") int size);

    /**
     * 新增/修改在线反馈
     *
     * @param feedBack 在线反馈
     * @return 在线反馈实体类
     */
    @RequestMapping(method = RequestMethod.POST, value = "/", consumes = "application/json")
    Feedback save(Feedback feedBack);
}
