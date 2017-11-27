package client.api.item;

import anxian.gateway.admin.module.common.domain.ResponseMessage;
import client.api.constants.Constants;
import client.api.item.domain.ItemPriceShow;
import client.api.item.model.GoodsStatus;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by mac on 15/8/28.
 */
@FeignClient(value = Constants.ANXIAN_SJES_API_ITEM)
@RequestMapping("itemPrices/anxian")
public interface AnXianItemPriceFeign {
    /**
     * 后台获取门店价格列表
     */
    @RequestMapping(value = "getItemPriceShowList", method = RequestMethod.POST)
    List<ItemPriceShow> getItemPriceShowList(@RequestParam("erpGoodsId") Long erpGoodsId);

    @RequestMapping(value = "batUpdateStatusNew/{status}", method = RequestMethod.PUT)
    ResponseMessage batUpdateStatusNew(@RequestBody List<GoodsStatus> goodsStatusList, @PathVariable("status") Integer status);

    @RequestMapping(value = "updateStatus", method = RequestMethod.PUT)
    int updateStatus(@RequestParam("erpGoodsId") Long erpGoodsId,@RequestParam("shopId") String shopId,@RequestParam("status") Integer status,@RequestParam("message") String message);


}
