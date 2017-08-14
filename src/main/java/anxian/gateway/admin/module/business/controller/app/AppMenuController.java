package anxian.gateway.admin.module.business.controller.app;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.app.floor.feign.AppMenuFeign;
import client.api.app.floor.model.EntryIconModel;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by byinbo on 2016/12/6.
 */
@RestController
@RequestMapping("appMenu")
public class AppMenuController {

    @Autowired
    AppMenuFeign appMenuFeign;

    /**
     * 楼层列表
     *
     * @return 楼层列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<EntryIconModel> list(int page, int limit) {
        List<EntryIconModel> list = appMenuFeign.list();
        return new PageModel<>(list, list.size(), new Pageable(page, limit));
    }

    /**
     * 根据id得到指定的 EntryIconModel
     *
     * @param id 主键id
     * @return EntryIconModel
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonMsg getEntryIconModel(@PathVariable("id") Long id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(appMenuFeign.getEntryIconModel(id));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 修改楼层
     *
     * @param entryIconModel 楼层信息
     * @return 修改数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(EntryIconModel entryIconModel) {
        appMenuFeign.updateEntryIcon(entryIconModel);
        return JsonMsg.success("修改成功");
    }
}
