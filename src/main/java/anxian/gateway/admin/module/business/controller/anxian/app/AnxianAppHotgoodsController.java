package anxian.gateway.admin.module.business.controller.anxian.app;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.app.floor.feign.AppHotgoodsFeign;
import client.api.app.floor.model.AdItemTemplete;
import client.api.app.floor.model.AdItemTempleteModel;
import client.api.app.floor.model.AppFloorModel;
import client.api.app.version.domain.Version;
import client.api.constants.Constants;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.user.utils.page.SjesPage;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byinbo on 2016/12/6.
 */
@Controller
@RequestMapping("anxian/appHotgood")
public class AnxianAppHotgoodsController {

    @Autowired
    AppHotgoodsFeign appHotgoodsFeign;

    /**
     * 根据主键取得商品模板
     *
     * @param id 主键id
     * @return 商品模板对象;无则返回null
     */
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(AdItemTempleteModel adItemTempleteModel) {
        appHotgoodsFeign.update(adItemTempleteModel.getId(), adItemTempleteModel.getSns());
        return JsonMsg.success("修改成功");
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String hotGood(Model model) {
        return "anXian-APP/sell-hot";
    }

    @RequestMapping("/ajaxHot")
    public String ajaxHotGood(Model model, int page, int limit) {
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
        PageModel<AdItemTempleteModel> hotGoods = new PageModel<>(list, list.size(), new Pageable(page, limit));
        List<AdItemTempleteModel> content = new ArrayList<>();
        int currIdx = page * limit;
        for (int i = 0; i < limit && i < list.size() - currIdx; i++) {
            AdItemTempleteModel good = list.get(currIdx + i);
            content.add(good);
        }
        hotGoods.getContent().clear();
        hotGoods.getContent().addAll(content);
        model.addAttribute("page", page + 1);
        model.addAttribute("hotGoods", hotGoods);
        return "anXian-APP/sell-hot-ajax";
    }



}
