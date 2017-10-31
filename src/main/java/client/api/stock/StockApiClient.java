package client.api.stock;

import client.api.item.model.Pageable;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by wangdinglan on 2017/10/28
 */
@FeignClient(value = "sjes-hub-api",url="http://193.0.1.122:20002")
@RequestMapping(value = "/platformStock")
public interface StockApiClient {

    @RequestMapping(method = RequestMethod.POST, value = "/stockSyncShow")
    Map<String, Object> stockSyncShow(@RequestParam(name = "shopId") String shopId);


    @RequestMapping(method = RequestMethod.POST, value = "/stockSyncAjax")
    Map<String, Object> syncStock(@RequestParam(name = "sjShopCode") String sjShopCode,
                                  @RequestParam(name = "sjGoodsCode") String sjGoodsCode,
                                  @RequestParam(name = "sjGoodsName") String sjGoodsName,
                                  @RequestParam(name = "page") int page,
                                  @RequestParam(name = "size") int size,
                                  @RequestParam(name = "minNum")  String minNum,
                                  @RequestParam(name = "maxNum") String maxNum,
                                  @RequestParam(name = "lockMinNum") String lockMinNum,
                                  @RequestParam(name = "lockMaxNum") String lockMaxNum,
                                  @RequestParam(name = "shopId") String shopId);


    @RequestMapping(method = RequestMethod.POST, value = "/modifyLockStock")
    @ResponseBody
    String modifyLockStock(@RequestParam(name = "operateName") String operateName,
                           @RequestParam(name = "numVir") String numVir,
                           @RequestParam(name = "id") String id,
                           @RequestParam(name = "shopCode") String shopCode,
                           @RequestParam(name = "goodsCode") String goodsCode);


}
