package client.api.anxian.activity;

import client.api.anxian.activity.model.ActivityCondition;
import client.api.anxian.activity.model.AnxianActivity;
import client.api.sale.model.ResponseMessage;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wangdinglan on 2017/10/16
 */
@FeignClient("sjes-api-sale")
@RequestMapping("/anxian/activity/admin")
public interface AnxianActivityApiClient {
    /**
     * 显示活动页列表
     *
     * @param activityCondition
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    ResponseMessage<SjesPage<AnxianActivity>> list(@RequestBody ActivityCondition activityCondition);

    /**
     * 根据id获取活动
     *
     * @param id 主键
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseMessage<AnxianActivity> getById(@PathVariable("id") String id);

    /**
     * 保存活动/更新
     */
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    ResponseMessage<AnxianActivity> save(@RequestBody AnxianActivity anxianActivity);

    /**
     * 获得优惠券
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getCoupons")
    ResponseMessage getCoupons();

    /**
     * 获得优惠券
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getCouponById/{promotionId}")
    ResponseMessage getCouponById(@PathVariable("promotionId") String promotionId);

    /**
     * 删除活动
     *
     * @param id 主键
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    ResponseMessage delete(@PathVariable("id") String id);

    /**
     * 强停活动
     *
     * @param id 主键
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/stop/{id}")
    ResponseMessage stop(@PathVariable("id") String id);
}
