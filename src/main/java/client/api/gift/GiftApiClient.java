package client.api.gift;

import client.api.gift.model.Gift;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kimiyu on 16/1/8.
 */
@FeignClient("sjes-api-giftstock")
@RequestMapping(value = "/gifts", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface GiftApiClient {
    /**
     * 根据赠品编码的降序排序
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    SjesPage<Gift> findAll(@RequestParam("page") Integer page,
                           @RequestParam("size") Integer size);


    /**
     * 新增赠品
     *
     * @param gift 赠品对象
     * @return gift
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    Gift createGift(Gift gift);

    /**
     * 修改商品
     *
     * @param gift 赠品对象
     * @return Gift
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    Gift updateGift(Gift gift);

    /**
     * 根据商品编码或商品名称动态查询赠品
     *
     * @param page 页数
     * @param size 展示条数
     * @param name 赠品名称
     * @param code 赠品编码
     * @return Page
     */
    @RequestMapping(method = RequestMethod.GET, value = "/findByNameOrCode")
    SjesPage<Gift> findByNameOrCode(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code);

    /**
     * 根据商品编码或商品名称动态查询赠品
     *
     * @param page 页数
     * @param size 展示条数
     * @param name 赠品名称
     * @param code 赠品编码
     * @return Page
     */
    @RequestMapping(method = RequestMethod.GET, value = "/findByNameOrCodeForFullGift")
    SjesPage<Gift> findByNameOrCodeForFullGift(
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "code", required = false) String code);

    /**
     * 根据赠品编号获取赠品对象
     *
     * @param giftCode
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{giftCode}")
    Gift getGift(@PathVariable("giftCode") String giftCode);
}
