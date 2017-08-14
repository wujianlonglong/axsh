package client.api.batch.feign;

import anxian.gateway.admin.module.common.domain.ResponseMessage;
import client.api.batch.model.SavePriceLog;
import client.api.constants.Constants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qinhailong on 16-6-20.
 */
@FeignClient(Constants.SJES_BATCH_ITEM)
@RequestMapping(value = "priceSynLogs")
public interface PriceSynLogFeign {

    /**
     * 新增 生鲜价格更新 商品
     *
     * @param savePriceLog
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseMessage savePriceLog(SavePriceLog savePriceLog);
}
