package anxian.gateway.admin.module.business.controller.fresh;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.fresh.feign.SupermarketFloorProductFeign;
import client.api.fresh.model.SupermarketFloorProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by qinhailong on 16-1-4.
 */
@RestController
@RequestMapping("supermarketFloorProduct")
public class SupermarketFloorProductController {

    @Autowired
    private SupermarketFloorProductFeign supermarketFloorProductFeign;

    /**
     * 批量新增超市页楼层商品关系
     *
     * @param supermarketFloorProducts 超市页楼层商品关系列表
     * @return 超市页楼层商品关系列表
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg saveBat(List<SupermarketFloorProduct> supermarketFloorProducts) {
        supermarketFloorProductFeign.saveBat(supermarketFloorProducts);
        return JsonMsg.success("保存成功");
    }

    /**
     * 更新
     *
     * @param supermarketFloorProduct
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public JsonMsg update(SupermarketFloorProduct supermarketFloorProduct) {
        supermarketFloorProductFeign.update(supermarketFloorProduct);
        return JsonMsg.success("修改成功");
    }

    /**
     * 根据主键id删除
     *
     * @param id 删除
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonMsg delete(Long id) {
        supermarketFloorProductFeign.delete(id);
        return JsonMsg.success("删除成功");
    }

}
