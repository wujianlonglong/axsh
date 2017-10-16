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
@FeignClient("sjes-api-app")
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
     * @param zoneId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/app/{zoneId}")
    AdItemTempleteAnxian getByZoneId(@PathVariable("zoneId") String zoneId);

    /**
     * 修改热销商品
     * @param id
     * @param sns
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    void update(@RequestParam("id") Long id, @RequestParam("sns") String sns);

}
