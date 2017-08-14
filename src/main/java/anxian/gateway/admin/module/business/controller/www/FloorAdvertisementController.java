package anxian.gateway.admin.module.business.controller.www;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.www.feign.FloorAdvertisementFeign;
import client.api.www.model.FloorAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 16-1-4.
 */
@RestController
@RequestMapping("floorAdvertisement")
public class FloorAdvertisementController {

    @Autowired
    private FloorAdvertisementFeign floorAdvertisementFeign;

    /**
     * 新增楼层广告
     *
     * @param floorAdvertisement 楼层广告
     * @return 楼层广告
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(FloorAdvertisement floorAdvertisement) {
        floorAdvertisementFeign.save(floorAdvertisement);
        return JsonMsg.success("保存成功!");
    }

    /**
     * 更新楼层广告信息
     *
     * @param floorAdvertisement 楼层广告
     * @return 修改数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(FloorAdvertisement floorAdvertisement) {
        floorAdvertisementFeign.update(floorAdvertisement);
        return JsonMsg.success("修改成功!");
    }

    /**
     * 更具id删除指定的楼层广告信息
     *
     * @param id 主键
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long id) {
        floorAdvertisementFeign.delete(id);
        return JsonMsg.success("删除成功!");
    }

}
