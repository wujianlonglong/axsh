package client.api.fresh.feign;

import client.api.constants.Constants;
import client.api.fresh.model.SupermarketFloor;
import client.api.fresh.model.SupermarketFloorModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mac on 15/9/24.
 */
@FeignClient(Constants.SJES_API_FRESH)
@RequestMapping("supermarketFloors")
public interface SupermarketFloorFeign {

    /**
     * 根据id得到 SupermarketFloorModel
     *
     * @param id 超市页楼层id
     * @return SupermarketFloorModel
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    SupermarketFloorModel get(@PathVariable("id") Long id);

    /**
     * 新增超市页楼层
     *
     * @param supermarketFloorModel 超市页楼层
     * @return 超市页楼层
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SupermarketFloor save(SupermarketFloorModel supermarketFloorModel);

    /**
     * 修改超市页楼层
     *
     * @param supermarketFloorModel 超市页楼层
     * @return 修改数目
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    void update(SupermarketFloorModel supermarketFloorModel);

    /**
     * 根据主键id删除超市页
     *
     * @param id 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);


    /**
     * 根据超市页Id查询楼层列表
     *
     * @param supermarketId 超市页Id
     * @param status        状态
     * @return 楼层列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<SupermarketFloor> listBySupermarketId(@RequestParam("supermarketId") Long supermarketId, @RequestParam("status") Integer status);
}
