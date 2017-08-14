package anxian.gateway.admin.module.business.controller.batch;

import anxian.gateway.admin.module.common.domain.ResponseMessage;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.batch.feign.PriceSynLogFeign;
import client.api.batch.model.SavePriceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 16-6-20.
 */
@RestController
@RequestMapping("priceSynLog")
public class PriceSynLogController {

    @Autowired
    private PriceSynLogFeign priceSynLogFeign;

    /**
     * 新增 生鲜价格更新 商品
     *
     * @param savePriceLog
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg savePriceLog(SavePriceLog savePriceLog) {
        ResponseMessage responseMessage = priceSynLogFeign.savePriceLog(savePriceLog);
        if (ResponseMessage.isSuccess(responseMessage)) {
            return JsonMsg.success(responseMessage.getContent());
        }
        return JsonMsg.failure(responseMessage.getContent());
    }


}
