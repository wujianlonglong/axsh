package anxian.gateway.admin.module.business.controller.www;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.www.feign.TopNavigationFeign;
import client.api.www.model.TopNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by qinhailong on 16-1-4.
 */
@RestController
@RequestMapping("topNavigation")
public class TopNavigationController {

    @Autowired
    private TopNavigationFeign topNavigationFeign;

    /**
     * 保存首行
     *
     * @param topNavigation 首行
     * @return 首行
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(TopNavigation topNavigation) {
        topNavigationFeign.save(topNavigation);
        return JsonMsg.success("保存成功");
    }

    /**
     * 更新首行
     *
     * @param topNavigation 首行
     * @return 更新数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(TopNavigation topNavigation) {
        topNavigationFeign.update(topNavigation);
        return JsonMsg.success("修改成功");
    }

    /**
     * 删除指定id的首行
     *
     * @param id 主键
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long id) {
        topNavigationFeign.delete(id);
        return JsonMsg.success("删除成功");
    }

    /**
     * 查询所有首行信息
     *
     * @return 首行信息
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<TopNavigation> listAll(int page, int limit) {
        List<TopNavigation> topNavigations = topNavigationFeign.listAll();
        return new PageModel<>(topNavigations, topNavigations.size(), new Pageable(page, limit));
    }

}
