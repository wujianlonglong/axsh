package anxian.gateway.admin.module.business.controller.app;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.app.floor.feign.AppFloorFeign;
import client.api.app.floor.model.AppFloorDetailModel;
import client.api.app.floor.model.AppFloorModel;
import client.api.app.floor.model.FloorContentModel;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by byinbo on 2016/11/30.
 */
@RestController
@RequestMapping("appFloor")
public class AppFloorController {

    @Autowired
    private AppFloorFeign appFloorFeign;

    /**
     * 楼层列表
     *
     * @return 楼层列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<AppFloorModel> list(int page, int limit) {
        List<AppFloorModel> list = appFloorFeign.list();
        return new PageModel<>(list, list.size(), new Pageable(page, limit));
    }

    /**
     * 保存新楼层
     *
     * @param appFloorModel
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg add(AppFloorModel appFloorModel) {
        appFloorModel.setZoneId(String.valueOf(System.currentTimeMillis()));
        appFloorFeign.addAppFloor(appFloorModel);
        return JsonMsg.success("保存成功");
    }

    /**
     * 修改楼层
     *
     * @param appFloorModel 楼层信息
     * @return 修改数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(AppFloorModel appFloorModel) {
        appFloorFeign.updateAppFloor(appFloorModel);
        return JsonMsg.success("修改成功");
    }

    /**
     * 删除指定id的楼层
     *
     * @param floorId 楼层id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonMsg deleteFloor(Long floorId) {
        AppFloorDetailModel appFloorDetailModel = appFloorFeign.getAppFloorDetailModel(floorId);
        if (null != appFloorDetailModel) {
            if (CollectionUtils.isNotEmpty(appFloorDetailModel.getFloorContents())) {
                return JsonMsg.failure("该楼层下存在内容, 不能删除！");
            }

            appFloorFeign.delete(floorId, appFloorDetailModel.getZoneId());
            return JsonMsg.success("删除成功！");
        } else {
            return JsonMsg.failure("不存在该楼层！");
        }
    }

    /**
     * 根据id得到指定的 FloorDetailModel
     *
     * @param floorId 主键id
     * @return FloorDetailModel
     */
    @RequestMapping(value = "{floorId}", method = RequestMethod.GET)
    public JsonMsg getAppFloorDetailModel(@PathVariable("floorId") Long floorId) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(appFloorFeign.getAppFloorDetailModel(floorId));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 新增楼层内容信息
     *
     * @param floorContentModel ；楼层内容
     * @return 修改数目
     */
    @RequestMapping(value = "/content/add", method = RequestMethod.POST)
    public JsonMsg addContent(FloorContentModel floorContentModel) {
        appFloorFeign.addContent(floorContentModel);
        return JsonMsg.success("添加成功!");
    }

    /**
     * 更新楼层内容信息
     *
     * @param floorContentModel ；楼层内容
     * @return 修改数目
     */
    @RequestMapping(value = "/content/update", method = RequestMethod.POST)
    public JsonMsg updateContent(FloorContentModel floorContentModel) {
        appFloorFeign.updateContent(floorContentModel);
        return JsonMsg.success("修改成功!");
    }

    /**
     * 根据id删除指定的楼层内容
     *
     * @param id 主键
     */
    @RequestMapping(value = "/content/delete", method = RequestMethod.POST)
    public JsonMsg delete(@RequestParam("id") Long id) {
        appFloorFeign.deleteContent(id);
        return JsonMsg.success("删除成功!");
    }
}
