package anxian.gateway.admin.module.business.controller.fresh;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.fresh.feign.SupermarketFeign;
import client.api.fresh.model.Supermarket;
import client.api.fresh.model.Supermarkets;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
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
@RequestMapping("supermarket")
public class SupermarketController {

    @Autowired
    private SupermarketFeign supermarketFeign;

    /**
     * 根据主键id查询SupermarketModel
     *
     * @param id 主键id
     * @return SupermarketModel
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonMsg get(@PathVariable("id") Long id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(supermarketFeign.get(id));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 新增超市页
     *
     * @param supermarket 超市页信息
     * @return 超市页信息
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(Supermarket supermarket) {
        supermarketFeign.save(supermarket);
        return JsonMsg.success("保存成功");
    }

    /**
     * 更新超市页
     *
     * @param supermarket 超市页
     * @return 修改数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(Supermarket supermarket) {
        supermarketFeign.update(supermarket);
        return JsonMsg.success("修改成功");
    }

    /**
     * 根据指定主键id删除
     *
     * @param id 主键
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long id) {
        supermarketFeign.delete(id);
        return JsonMsg.success("删除成功");
    }

    /**
     * 超市页列表
     *
     * @return 超市业列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<Supermarkets> list(int page, int limit) {
        List<Supermarkets> list = supermarketFeign.list();
        return new PageModel<>(list, list.size(), new Pageable(page, limit));
    }


}
