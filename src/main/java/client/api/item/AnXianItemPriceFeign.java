package client.api.item;

import client.api.constants.Constants;
import client.api.item.domain.ItemPriceShow;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
}
