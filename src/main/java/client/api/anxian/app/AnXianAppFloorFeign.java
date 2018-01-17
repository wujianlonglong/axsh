package client.api.anxian.app;

import client.api.app.floor.model.AppFloorDetailModel;
import client.api.app.floor.model.AppFloorModel;
import client.api.app.floor.model.FloorContentModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangdinglan on 2017/09/14
 */
@FeignClient(name="sjes-api-app")
@RequestMapping("anxian/appFloors")
public interface AnXianAppFloorFeign {
    /**
     * 编辑楼层信息
     *
     * @param appFloorModel 楼层信息
     * @return 楼层信息
     */
    @RequestMapping(method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    void addAppFloor(@RequestBody AppFloorModel appFloorModel);

    /**
     * 更新楼层
     * @param appFloorModel
     */
    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateAppFloor(@RequestBody AppFloorModel appFloorModel);
    /**
     * 楼层列表
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    List<AppFloorModel> list(@RequestParam("shopId") String shopId);

    /**
     * 删除指定id的楼层
     * @param floorId 楼层id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("floorId") Long floorId, @RequestParam("zoneId") String zoneId);


    /**
     * 根据id得到指定的 FloorDetailModel
     * @param floorId 主键id
     * @return FloorDetailModel
     */
    @RequestMapping(value = "{floorId}", method = RequestMethod.GET)
    public AppFloorDetailModel getAppFloorDetailModel(@PathVariable("floorId") Long floorId);

    /**
     * 添加楼层内容
     * @param floorContentModel
     */
    @RequestMapping(value = "/content/add", method = RequestMethod.POST)
    void addContent(@RequestBody FloorContentModel floorContentModel);

    /**
     * 更新楼层内容
     * @param floorContentModel
     */
    @RequestMapping(value = "/content/update", method = RequestMethod.POST)
    void updateContent(@RequestBody FloorContentModel floorContentModel);

    /**
     * 根据d删除指定的楼层内容
     * @param id 主键
     */
    @RequestMapping(value = "/content/delete", method = RequestMethod.POST)
    void deleteContent(@RequestParam("id") Long id);
}
