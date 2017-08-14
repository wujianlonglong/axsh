package anxian.gateway.admin.module.business.controller.www;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.www.feign.FloorKeywordFeign;
import client.api.www.model.FloorKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 16-1-4.
 */
@RestController
@RequestMapping("floorKeyword")
public class FloorKeywordController {

    @Autowired
    private FloorKeywordFeign floorKeywordFeign;

    /**
     * 新增楼层关键词
     *
     * @param floorKeyword 楼层关键词
     * @return 楼层关键词
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(FloorKeyword floorKeyword) {
        floorKeywordFeign.save(floorKeyword);
        return JsonMsg.success("保存成功");
    }

    /**
     * 更新楼层关键词
     *
     * @param floorKeyword 楼层关键字
     * @return 修改数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(FloorKeyword floorKeyword) {
        floorKeywordFeign.update(floorKeyword);
        return JsonMsg.success("更新成功");
    }

    /**
     * 根据id删除指定的关键词
     *
     * @param id 主键
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonMsg delete(Long id) {
        floorKeywordFeign.delete(id);
        return JsonMsg.success("删除成功");
    }


}
