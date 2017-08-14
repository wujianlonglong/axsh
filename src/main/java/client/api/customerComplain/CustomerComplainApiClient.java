package client.api.customerComplain;

import client.api.customerComplain.domain.*;
import client.api.sale.model.ResponseMessage;
import client.api.user.utils.page.PageForAdmin;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by byinbo on 2017/3/20.
 */
@FeignClient("sjes-api-user")
@RequestMapping("/complains")
public interface CustomerComplainApiClient {

    @RequestMapping(method = RequestMethod.POST, value = "/page")
    PageForAdmin<CustomerComplain> pageGetCustomerComplainList(@RequestParam(name = "receiveDept", required = false) String receiveDept,
                                                               @RequestParam(name = "complainType", required = false) String complainType,
                                                               @RequestParam(name = "complainStat", required = false) Integer complainStat,
                                                               @RequestParam(name = "responStat", required = false) Integer responStat,
                                                               @RequestParam(name = "overTime", required = false) Integer overTime,
                                                               @RequestParam(name = "searchStr", required = false) String searchStr,
                                                               @RequestParam(name = "startDate", required = false) String startDate,
                                                               @RequestParam(name = "endDate", required = false) String endDate,
                                                               @RequestParam(name = "memberStat", required = false) Integer memberStat,
                                                               @RequestParam(name = "orgNum", required = false) String orgNum,
                                                               @RequestParam(name = "clientType", required = false) Integer clientType,
                                                               @RequestParam("page") int page, @RequestParam("size") int size

    );

    /**
     * 根据投诉编号获取处理结果
     *
     * @param complainId
     * @return
     */
    @RequestMapping(value = "/result/{complainId}", method = RequestMethod.GET)
    List<ComplainResult> findComplainResult(@PathVariable("complainId") Long complainId);

    /**
     * 显示所有门店信息
     *
     * @param shopSearch 参与门店查询条件
     * @return 返回查询结果
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SjesPage<ShopInfo> getShopList(ShopSearch shopSearch);

    /**
     * 提交处理结果
     *
     * @param type
     * @param id
     * @param result
     * @param turn
     * @param shopId
     * @param shopName
     * @return
     */
    @RequestMapping(value = "/result/submit", method = RequestMethod.POST)
    @Transactional
    ResponseMessage submit(@RequestParam("type") int type,
                           @RequestParam("complainId") Long id,
                           @RequestParam("result") String result,
                           @RequestParam(value = "turn", required = false) boolean turn,
                           @RequestParam(value = "shopId", required = false) String shopId,
                           @RequestParam(value = "shopName", required = false) String shopName);


    @RequestMapping(method = RequestMethod.POST, value = "/duty/page")
    PageForAdmin<CustomerComplain> pageGetComplainDutyList(@RequestParam(name = "responStat", required = false) Integer responStat,
                                                           @RequestParam(name = "searchStr", required = false) String searchStr,
                                                           @RequestParam(name = "memberStat", required = false) Integer memberStat,
                                                           @RequestParam(name = "orgNum", required = false) String orgNum,
                                                           @RequestParam("page") int page, @RequestParam("size") int size
    );

    /**
     * 提交处理结果
     *
     * @param responStrs
     * @param id
     * @return
     */
    @RequestMapping(value = "/duty/submit", method = RequestMethod.POST)
    @Transactional
    ResponseMessage dutySubmit(@RequestParam("responStrs") int[] responStrs,
                               @RequestParam("complainId") Long id);

    /**
     * 根据投诉编号id查询投诉
     *
     * @param complainId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/complain/{complainId}")
    CustomerComplainWxModel getComplainById(@PathVariable("complainId") Long complainId);

    /**
     * 根据区总编码取得所属门店编码
     *
     * @param org 区总编码
     * @return
     */
    @RequestMapping(value = "/listOrgNums/{org}", method = RequestMethod.GET)
    List<String> getOrgNums(@PathVariable("org") String org);

    /**
     * 根据区总编码取得所属门店名称
     *
     * @param org 区总编码
     * @return
     */
    @RequestMapping(value = "/listShopNames/{org}", method = RequestMethod.GET)
    List<String> getShopNames(@PathVariable("org") String org);

    /**
     * 客诉处理中
     *
     * @param complainId
     * @return
     */
    @RequestMapping(value = "/handle/{complainId}", method = RequestMethod.GET)
    ResponseMessage handle(@PathVariable("complainId") Long complainId);

    /**
     * 客诉状态统计
     *
     * @param orgNum
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/count")
    List<CustomerComplain> countCustomerComplainList(@RequestParam(name = "orgNum", required = false) String orgNum);
}
