package anxian.gateway.admin.module.business.controller.fresh;

import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.fresh.feign.SupermarketAdvertisementFeign;
import client.api.fresh.model.SupermarketAdvertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 16-1-4.
 */
@RestController
@RequestMapping("supermarketAdvertisement")
public class SupermarketAdvertisementController extends BaseController {

    @Autowired
    private SupermarketAdvertisementFeign supermarketAdvertisementFeign;

    /**
     * 根据id得到
     *
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public SupermarketAdvertisement get(@PathVariable("id") Long id) {
        return supermarketAdvertisementFeign.get(id);
    }

    /**
     * 新增超市页焦点图
     *
     * @param supermarketAdvertisement 超市页焦点图
     * @return 超市页焦点图
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(SupermarketAdvertisement supermarketAdvertisement) {
//        Integer position = supermarketAdvertisement.getPosition();
//        if (position == AdvertisementPositionEnum.right.getValue() || position == AdvertisementPositionEnum.speci_left.getValue()) {
//            Boolean bool = supermarketAdvertisementFeign.isExist(supermarketAdvertisement.getSupermarketId(), position);
//            if (BooleanUtils.isTrue(bool)) {
//                switch (position) {
//                    case 4: return  JsonMsg.failure("右焦已经存在!");
//                    case 7: return  JsonMsg.failure("每日特价焦点图已经存在!");
//                }
//            }
//        }
        supermarketAdvertisementFeign.save(supermarketAdvertisement);
        return JsonMsg.success("保存成功");
    }

    /**
     * 更新超市页焦点图
     *
     * @param supermarketAdvertisement 超市页焦点图
     * @return 修改数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(SupermarketAdvertisement supermarketAdvertisement) {
        supermarketAdvertisementFeign.update(supermarketAdvertisement);
        return JsonMsg.success("更新成功");
    }

    /**
     * 根据指定id删除超市页焦点图
     *
     * @param id 主键id
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long id) {
        supermarketAdvertisementFeign.delete(id);
        return JsonMsg.success("删除成功");
    }

}
