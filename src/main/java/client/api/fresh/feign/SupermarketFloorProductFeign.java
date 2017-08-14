package client.api.fresh.feign;

import client.api.constants.Constants;
import client.api.fresh.model.SupermarketFloorProduct;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mac on 15/9/24.
 */
@FeignClient(Constants.SJES_API_FRESH)
@RequestMapping("supermarketFloorProducts")
public interface SupermarketFloorProductFeign {

    /**
     * 批量新增超市页楼层商品关系
     *
     * @param supermarketFloorProducts 超市页楼层商品关系列表
     * @return 超市页楼层商品关系列表
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<SupermarketFloorProduct> saveBat(List<SupermarketFloorProduct> supermarketFloorProducts);

    /**
     * 根据主键id删除
     *
     * @param id 删除
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);

    /**
     * 更新
     *
     * @param supermarketFloorProduct
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(SupermarketFloorProduct supermarketFloorProduct);

    /**
     * 根据超市页楼层Id查询列表
     *
     * @param supermarketFloorId 超市页楼层Id
     * @return 超市页楼层商品关系列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<SupermarketFloorProduct> listBySupermarketFloorId(@RequestParam("supermarketFloorId") Long supermarketFloorId);
}
