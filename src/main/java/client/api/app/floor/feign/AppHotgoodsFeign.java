package client.api.app.floor.feign;

import client.api.app.floor.model.AdItemTemplete;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by byinbo on 2016/12/6.
 */
@FeignClient("sjes-api-app")
@RequestMapping("appHotgoods")
public interface AppHotgoodsFeign {

    /**
     * 根据主键取得商品模板
     *
     * @param id 主键id
     * @return 商品模板对象;无则返回null
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    AdItemTemplete findOne(@PathVariable("id") long id);

    /**
     * 根据zoneId取得AdItemTemplete
     *
     * @param zoneId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/app/{zoneId}")
    AdItemTemplete getByZoneId(@PathVariable("zoneId") String zoneId);

    /**
     * 修改热销商品
     *
     * @param id
     * @param sns
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    void update(@RequestParam("id") Long id, @RequestParam("sns") String sns);
}
