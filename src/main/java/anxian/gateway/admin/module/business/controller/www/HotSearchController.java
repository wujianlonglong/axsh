package anxian.gateway.admin.module.business.controller.www;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.www.feign.HotSearchFeign;
import client.api.www.model.HotSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by qinhailong on 15-12-31.
 */
@RestController
@RequestMapping("hotSearch")
public class HotSearchController {

    @Autowired
    private HotSearchFeign hotSearchFeign;

    /**
     * 查询热门搜索词列表
     *
     * @return 热门搜索词列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<HotSearch> list(int page, int limit) {
        List<HotSearch> list = hotSearchFeign.list();
        return new PageModel<>(list, list.size(), new Pageable(page, limit));
    }

    /**
     * 保存热门搜索词
     *
     * @param hotSearch 热门搜索词
     * @return 热门搜索词
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(HotSearch hotSearch) {
        hotSearchFeign.save(hotSearch);
        return JsonMsg.success("保存成功");
    }

    /**
     * 保存热门搜索词
     *
     * @param hotSearch 热门搜索词
     * @return JsonMsg
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(HotSearch hotSearch) {
        hotSearchFeign.update(hotSearch);
        return JsonMsg.success("修改成功");
    }

    /**
     * 删除热门搜索词
     *
     * @param id 主键id
     * @return JsonMsg
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long id) {
        hotSearchFeign.delete(id);
        return JsonMsg.success("删除成功");
    }


}
