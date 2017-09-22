package client.api.sale;

import client.api.sale.model.CouponMongo;
import client.api.sale.model.CouponParamDTO;
import client.api.sale.model.ResponseMessage;
import client.api.sale.model.VolumeCondition;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wangdinglan on 2017/09/04
 */
@FeignClient("sjes-api-sale")
@RequestMapping(value = "/anxian/coupons/admin")
public interface CouponApiClient {
    /**
     * 保存满减/新人优惠劵信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    ResponseMessage<CouponMongo> save(@RequestBody CouponParamDTO couponParamDTO);
    /**
     * 显示优惠劵列表
     *
     * @param volumeCondition 优惠查询对象
     */
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    ResponseMessage<SjesPage<CouponMongo>> list(@RequestBody VolumeCondition volumeCondition);

    /**
     * 根据id获取优惠劵活动
     *
     * @param id 主键
     * @return 返回优惠劵活动那个
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseMessage<CouponMongo> getById(@PathVariable("id") String id);
}
