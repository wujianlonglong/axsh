package client.api.sale;

import client.api.sale.model.SaleManageCondition;
import client.api.sale.model.preSell.PreSell;
import client.api.sale.util.SaleResponseMessage;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kimiyu on 2017/2/22.
 */
@FeignClient("sjes-api-sale")
public interface PreSellApiClient {

    @RequestMapping(value = "/sales/preSell/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    SaleResponseMessage<PreSell> getById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/sales/preSell/save")
    SaleResponseMessage<PreSell> savePreSell(PreSell preSell);

    /**
     * 促销管理
     *
     * @param saleManageCondition 促销管理查询条件
     */
    @RequestMapping(value = "/sales/preSell/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SjesPage<PreSell> list(SaleManageCondition saleManageCondition);

    /**
     * 商品预售活动删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sales/preSell/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    SaleResponseMessage deleteById(@PathVariable("id") String id);

    /**
     * 活动强停
     *
     * @param preSell
     * @return
     */
    @RequestMapping(value = "/sales/preSell/manualStop", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SaleResponseMessage<PreSell> manualStop(PreSell preSell);
}
