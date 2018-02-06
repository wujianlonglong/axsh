package client.api.anxian.app;

import client.api.anxian.app.model.AdItemTempleteAnxian;
import client.api.app.floor.model.FloorContentModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by wangdinglan on 2017/09/14
 */
@FeignClient(name = "sjes-api-app")
@RequestMapping("/anxian/appHotgoods")
public interface AnXianAppHotGoodsFeign {
    /**
     * 根据主键取得商品模板
     *
     * @param id 主键id
     * @return 商品模板对象;无则返回null
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    AdItemTempleteAnxian findOne(@PathVariable("id") long id);

    /**
     * 根据zoneId取得AdItemTemplete
     *
     * @param zoneId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/app/{zoneId}")
    List<AdItemTempleteAnxian> getByZoneId(@PathVariable("zoneId") String zoneId);

    /**
     * 修改热销商品
     *
     * @param id
     * @param sns
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    void update(@RequestParam("id") Long id, @RequestParam("sns") String sns, @RequestParam("shopId") String shopId, @RequestParam("shopName") String shopName);


    /**
     * 新增热销商品
     * @param sns
     * @param shopId
     * @param shopName
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    void add(@RequestParam("sns") String sns, @RequestParam(name = "shopId", required = false) String shopId, @RequestParam(name = "shopName", required = false) String shopName);

    /**
     * 删除热销商品
     * @param id
     */
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
    void delete(@PathVariable("id") Long id);
}
