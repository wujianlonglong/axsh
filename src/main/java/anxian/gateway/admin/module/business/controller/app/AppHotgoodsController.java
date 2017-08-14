package anxian.gateway.admin.module.business.controller.app;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.app.floor.feign.AppHotgoodsFeign;
import client.api.app.floor.model.AdItemTemplete;
import client.api.app.floor.model.AdItemTempleteModel;
import client.api.constants.Constants;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import com.google.common.collect.Lists;
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
@RequestMapping("appHotgood")
public class AppHotgoodsController {

    @Autowired
    AppHotgoodsFeign appHotgoodsFeign;

    /**
     * 根据主键取得商品模板
     *
     * @param id 主键id
     * @return 商品模板对象;无则返回null
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonMsg getAppHotgoodsModel(@PathVariable("id") long id) {
        JsonMsg jsonMsg = new JsonMsg();
        AdItemTemplete adItemTemplete = appHotgoodsFeign.findOne(id);
        AdItemTempleteModel adItemTempleteModel = new AdItemTempleteModel();
        adItemTempleteModel.setZoneId(adItemTemplete.getZoneId());
        adItemTempleteModel.setTempleteName(adItemTemplete.getTempleteName());
        adItemTempleteModel.setSns(adItemTemplete.getSns());
        adItemTempleteModel.setId(adItemTemplete.getId());
        jsonMsg.setData(adItemTempleteModel);
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 热销列表
     *
     * @return 热销列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<AdItemTempleteModel> list(int page, int limit) {
        AdItemTemplete adItemTemplete = appHotgoodsFeign.getByZoneId(Constants.APP_HOTGOODS_ZONEID);
        List<AdItemTempleteModel> list = Lists.newArrayList();
        if (adItemTemplete != null) {
            AdItemTempleteModel adItemTempleteModel = new AdItemTempleteModel();
            adItemTempleteModel.setId(adItemTemplete.getId());
            adItemTempleteModel.setSns(adItemTemplete.getSns());
            adItemTempleteModel.setTempleteName(adItemTemplete.getTempleteName());
            adItemTempleteModel.setZoneId(adItemTemplete.getZoneId());
            list.add(adItemTempleteModel);
        }
        return new PageModel<>(list, list.size(), new Pageable(page, limit));
    }

    /**
     * 修改热销商品
     *
     * @param adItemTempleteModel 热销信息
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(AdItemTempleteModel adItemTempleteModel) {
        appHotgoodsFeign.update(adItemTempleteModel.getId(), adItemTempleteModel.getSns());
        return JsonMsg.success("修改成功");
    }
}
