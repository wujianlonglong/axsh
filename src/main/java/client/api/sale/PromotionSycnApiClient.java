package client.api.sale;

import anxian.gateway.admin.module.business.model.promotionSycn.PromotionSycnView;
import client.api.sale.model.PromotionSycnViewModel;
import client.api.sale.model.PromotionSync;
import client.api.sale.model.PromotionSyncModel;
import client.api.sale.model.ResponseMessage;
import client.api.sale.util.ErpSaleDTO;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by byinbo on 2016/12/21.
 */
@FeignClient(name="sjes-api-sale")
//@RequestMapping(value = "/sales/erp", consumes = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value = "/anxian/erpSale", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface PromotionSycnApiClient {

    /**
     * ERP促销活动列表
     *
     * @param erpSaleDTO
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    SjesPage<PromotionSycnView> getPages(ErpSaleDTO erpSaleDTO);

    /**
     * 根据ERP_ID获取活动model
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    ResponseMessage<PromotionSycnViewModel> getById(@PathVariable("id") String id);

    /**
     * 同步促销活动
     */
    @RequestMapping(value = "/promotionSycn", method = RequestMethod.POST)
    ResponseMessage<PromotionSync> savePromotionSycn(PromotionSyncModel promotionSyncModel);

    /**
     * 删除促销同步
     *
     * @param sheetId
     * @return
     */
    @RequestMapping(value = "/delete/{sheetId}", method = RequestMethod.DELETE)
    ResponseMessage delBySheetId(@PathVariable("sheetId") String sheetId);
}
