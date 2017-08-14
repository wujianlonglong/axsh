package client.api.www.feign;

import client.api.constants.Constants;
import client.api.www.model.Floor;
import client.api.www.model.FloorDetailModel;
import client.api.www.model.FloorModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mac on 15/9/23.
 */
@FeignClient(Constants.SJES_API_WWW)
@RequestMapping("floors")
public interface FloorFeign {

    /**
     * 添加楼层
     *
     * @param floor 楼层信息
     * @return 楼层信息
     */
//    @PreAuthorize("hasAuthority('FLOOR_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Floor add(Floor floor);

    /**
     * 修改楼层
     *
     * @param floor 楼层信息
     * @return 修改数目
     */
//    @PreAuthorize("hasAuthority('FLOOR_SAVE')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(Floor floor);

    /**
     * 根据id得到指定的 FloorDetailModel
     *
     * @param floorId 主键id
     * @return FloorDetailModel
     */
    @RequestMapping(value = "{floorId}", method = RequestMethod.GET)
    FloorDetailModel getFloorDetailModel(@PathVariable("floorId") Long floorId);

    /**
     * 楼层列表
     *
     * @return 楼层列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    List<FloorModel> list();

    /**
     * 删除指定id的楼层
     *
     * @param floorId 楼层id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("floorId") Long floorId);

}
