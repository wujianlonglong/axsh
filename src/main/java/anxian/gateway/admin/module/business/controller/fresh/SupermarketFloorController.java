package anxian.gateway.admin.module.business.controller.fresh;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.fresh.feign.SupermarketFloorFeign;
import client.api.fresh.model.SupermarketFloorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 16-1-4.
 */
@RestController
@RequestMapping("supermarketFloor")
public class SupermarketFloorController {

    @Autowired
    private SupermarketFloorFeign supermarketFloorFeign;

    /**
     * 根据id得到 SupermarketFloorModel
     *
     * @param id 超市页楼层id
     * @return SupermarketFloorModel
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public JsonMsg get(@PathVariable("id") Long id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setData(supermarketFloorFeign.get(id));
        return jsonMsg;
    }


    /**
     * 新增超市页楼层
     *
     * @param supermarketFloorModel 超市页楼层
     * @return 超市页楼层
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(SupermarketFloorModel supermarketFloorModel) {
        supermarketFloorFeign.save(supermarketFloorModel);
        return JsonMsg.success("保存成功");
    }

    /**
     * 修改超市页楼层
     *
     * @param supermarketFloorModel 超市页楼层
     * @return 修改数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(SupermarketFloorModel supermarketFloorModel) {
        supermarketFloorFeign.update(supermarketFloorModel);
        return JsonMsg.success("更新成功");
    }

    /**
     * 根据主键id删除超市页
     *
     * @param id 主键
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long id) {
        supermarketFloorFeign.delete(id);
        return JsonMsg.success("删除成功");
    }

}
