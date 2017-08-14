package anxian.gateway.admin.module.business.controller.www;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.www.feign.FloorFeign;
import client.api.www.model.Floor;
import client.api.www.model.FloorDetailModel;
import client.api.www.model.FloorModel;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by qinhailong on 16-1-4.
 */
@RestController
@RequestMapping("floor")
public class FloorController {

    @Autowired
    private FloorFeign floorFeign;

    /**
     * 添加楼层
     *
     * @param floor 楼层信息
     * @return 楼层信息
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg add(Floor floor) {
        floorFeign.add(floor);
        return JsonMsg.success("保存成功");
    }

    /**
     * 修改楼层
     *
     * @param floor 楼层信息
     * @return 修改数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(Floor floor) {
        floorFeign.update(floor);
        return JsonMsg.success("修改成功");
    }

    /**
     * 楼层列表
     *
     * @return 楼层列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<FloorModel> list(int page, int limit) {
        List<FloorModel> list = floorFeign.list();
        return new PageModel<>(list, list.size(), new Pageable(page, limit));
    }

    /**
     * 根据id得到指定的 FloorDetailModel
     *
     * @param floorId 主键id
     * @return FloorDetailModel
     */
    @RequestMapping(value = "{floorId}", method = RequestMethod.GET)
    public JsonMsg getFloorDetailModel(@PathVariable("floorId") Long floorId) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(floorFeign.getFloorDetailModel(floorId));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 删除指定id的楼层
     *
     * @param floorId 楼层id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonMsg delete(Long floorId) {
        FloorDetailModel floorDetailModel = floorFeign.getFloorDetailModel(floorId);
        if (null != floorDetailModel) {
            if (CollectionUtils.isNotEmpty(floorDetailModel.getLeftFloorCategorys())) {
                return JsonMsg.failure("该楼层存在左侧分类, 不能删除！");
            }
            if (CollectionUtils.isNotEmpty(floorDetailModel.getLeftFloorKeyword())) {
                return JsonMsg.failure("该楼层存在左侧关键词, 不能删除！");
            }
            if (CollectionUtils.isNotEmpty(floorDetailModel.getTopFloorCategorys())) {
                return JsonMsg.failure("该楼层存在顶层分类, 不能删除！");
            }
            if (CollectionUtils.isNotEmpty(floorDetailModel.getRightFloorAdvertisements())) {
                return JsonMsg.failure("该楼层存在右侧广告, 不能删除！");
            }
            if (CollectionUtils.isNotEmpty(floorDetailModel.getBottomFloorAdvertisements())) {
                return JsonMsg.failure("该楼层存在底部品牌广告, 不能删除！");
            }
            floorFeign.delete(floorId);
            return JsonMsg.success("删除成功！");
        } else {
            return JsonMsg.failure("不存在该楼层！");
        }
    }

}
