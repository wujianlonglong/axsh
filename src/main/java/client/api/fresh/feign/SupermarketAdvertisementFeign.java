package client.api.fresh.feign;

import client.api.constants.Constants;
import client.api.fresh.model.SupermarketAdvertisement;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mac on 15/9/24.
 */
@FeignClient(Constants.SJES_API_FRESH)
@RequestMapping("supermarketAdvertisements")
public interface SupermarketAdvertisementFeign {

    /**
     * 根据id得到
     *
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    SupermarketAdvertisement get(@PathVariable("id") Long id);

    /**
     * 新增超市页焦点图
     *
     * @param supermarketAdvertisement 超市页焦点图
     * @return 超市页焦点图
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SupermarketAdvertisement save(SupermarketAdvertisement supermarketAdvertisement);

    /**
     * 更新超市页焦点图
     *
     * @param supermarketAdvertisement 超市页焦点图
     * @return 修改数目
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(SupermarketAdvertisement supermarketAdvertisement);

    /**
     * 根据指定id删除超市页焦点图
     *
     * @param id 主键id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);

    /**
     * 判断指定position焦点图是否已经存在
     *
     * @param supermarketId 超市Id
     * @param position      焦点图位置
     * @return 是否已经存在
     */
    @RequestMapping(value = "isExist", method = RequestMethod.GET)
    Boolean isExist(@RequestParam("supermarketId") Long supermarketId, @RequestParam("position") Integer position);

}
