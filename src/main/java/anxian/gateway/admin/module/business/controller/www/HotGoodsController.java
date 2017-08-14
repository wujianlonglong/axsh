package anxian.gateway.admin.module.business.controller.www;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.www.feign.HotGoodsFeign;
import client.api.www.model.HotGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 15-12-31.
 */
@RestController
@RequestMapping("hotGoods")
public class HotGoodsController {

    @Autowired
    private HotGoodsFeign hotGoodsFeign;

    /**
     * 新增热销好货
     *
     * @param hotGoods 热销好货
     * @return 热销好货
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(HotGoods hotGoods) {
        hotGoodsFeign.save(hotGoods);
        return JsonMsg.success("保存成功");
    }


    /**
     * 新增热销好货
     *
     * @param hotGoods 热销好货
     * @return 热销好货
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(HotGoods hotGoods) {
        hotGoodsFeign.update(hotGoods);
        return JsonMsg.success("修改成功");
    }

    /**
     * 删除热销好货
     *
     * @param id 主键Id
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long id) {
        hotGoodsFeign.delete(id);
        return JsonMsg.success("删除成功");
    }

    /**
     * 分页查询热销好货
     *
     * @param hotGoods 查询条件
     * @return 分页信息
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public PageModel<HotGoods> search(HotGoods hotGoods, int page, int limit) {
        SearchCoditionModel<HotGoods> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setSearchCodition(hotGoods);
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        return hotGoodsFeign.search(searchCoditionModel);
    }
}
