package client.api.crm;

import client.api.crm.domain.ScoreCrmRepose;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by byinbo on 2017/5/16.
 */
@FeignClient("sjes-api-crm")
@RequestMapping("/crm")
public interface CrmApiClient {

    /**
     * 根据会员卡号取得积分总额
     *
     * @param cardNum 会员卡号
     * @return 积分总额信息
     */
    @RequestMapping(method = RequestMethod.GET, value = "/score/total/{cardNum}")
    ScoreCrmRepose getTotalScore(@PathVariable("cardNum") String cardNum);
}
