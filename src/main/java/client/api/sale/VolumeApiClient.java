package client.api.sale;

import client.api.sale.model.*;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by Jianghe on 16/1/16.
 */
@FeignClient("sjes-api-sale")
@RequestMapping(value = "/sales/volumes", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface VolumeApiClient {

    /**
     * 显示优惠劵列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/list")
    SjesPage<BenefitListView> getList(VolumeCondition volumeCondition);

    /**
     * 获得积分兑换券-各级
     *
     * @param volumeCondition
     * @return
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getIntegalVolumeList")
    SjesPage<JoinedVolumeViewModel> getJoinVolumeList(@RequestBody VolumeCondition volumeCondition);

    /**
     * 保存现金/满减优惠劵信息
     *
     * @param volumeViewModel
     */
    @PreAuthorize("hasAuthority('COUPON_SAVE')")
    @RequestMapping(method = RequestMethod.POST, value = "/saveCash", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseMessage<BenefitVolume> saveCashVolume(VolumeViewModel volumeViewModel);


    /**
     * 更新优惠券活动
     *
     * @param saleId
     * @param name
     * @param usedExplanation
     * @return
     */
    @PreAuthorize("hasAuthority('COUPON_SAVE')")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    ResponseMessage<BenefitVolume> updateBenefitVolume(@RequestParam("saleId") String saleId,
                                                       @RequestParam("name") String name,
                                                       @RequestParam("usedExplanation") String usedExplanation);


    /**
     * 导出txt文件[微信活动才能导出]
     *
     * @param id 促销ID
     */
    @PreAuthorize("hasAuthority('COUPON_EXPORT')")
    @RequestMapping(method = RequestMethod.POST, value = "/export")
    ResponseMessage<List<VolumeModel>> exportTxt(@RequestParam("id") String id,
                                                 @RequestParam("userId") Long userId);

    /**
     * 删除优惠券
     *
     * @param saleId
     * @return
     */
    @PreAuthorize("hasAuthority('COUPON_DELETE')")
    @RequestMapping(value = "/{saleId}", method = RequestMethod.DELETE)
    ResponseMessage delBySaleModel(@PathVariable("saleId") String saleId);


    /**
     * 根据id获取优惠劵活动
     *
     * @param id 主键
     * @return 返回优惠劵活动那个
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    BenefitVolume getById(@PathVariable("id") String id);
}
