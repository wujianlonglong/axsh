package client.api.order;

import client.api.order.model.ScoreConvertNote;
import client.api.order.model.SearchCondition;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by zhangyunan on 2017/8/2.
 */
@FeignClient("sjes-api-order")
@RequestMapping(value = "/scoreConvert", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface ScoreConvertNoteApiClient {
    /**
     * 积分兑换记录查询
     *
     * @return
     * @@param searchCondition
     */
    @RequestMapping(value = "/scoreConvertNote/list", method = RequestMethod.POST)
    SjesPage<ScoreConvertNote> scoreConvertList(@RequestBody SearchCondition searchCondition);

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    List<ScoreConvertNote> exportscoreConvertList(@RequestBody SearchCondition searchCondition);
}
