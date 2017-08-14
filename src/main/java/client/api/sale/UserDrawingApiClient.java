package client.api.sale;

import client.api.sale.model.turnTable.DrawPrize;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Jianghe on 16/2/5.
 */
@FeignClient("sjes-api-sale")
@RequestMapping(value = "/userdrawings")
public interface UserDrawingApiClient {

    /**
     * 显示用户中奖列表
     *
     * @param turnTableId 大转盘ID
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/drawprizeList/{turnTableId}")
    List<DrawPrize> getDrawPrizeList(@PathVariable("turnTableId") String turnTableId);
}
