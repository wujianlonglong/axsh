package client.api.sale;

import client.api.sale.model.ResponseMessage;
import client.api.sale.model.ScoreAdmin;
import client.api.sale.model.ScoreItem;
import client.api.sale.model.ScoreSearch;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by gaoqichao on 16-1-15.
 */
@FeignClient("sjes-api-sale")
@RequestMapping(value = "/admin/scores", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface ScoreApiClient {
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    ResponseMessage save(ScoreAdmin scoreAdmin);

    /**
     * 根据id获取积分信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    ResponseMessage<ScoreItem> getOne(@RequestParam("id") String id);

    /**
     * 强停积分商品
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/manualStop", method = RequestMethod.DELETE)
    ResponseMessage manualStop(@RequestParam("id") String id);

    /**
     * 查询
     *
     * @param scoreSearch
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    ResponseMessage<SjesPage<ScoreItem>> search(ScoreSearch scoreSearch);
}
