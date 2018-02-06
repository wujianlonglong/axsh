package anxian.gateway.admin.module.business.controller.anxian.app;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.anxian.app.AnXianAppHotGoodsFeign;
import client.api.anxian.app.model.AdItemTempleteAnxian;
import client.api.app.floor.model.AdItemTempleteModel;
import client.api.constants.Constants;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by byinbo on 2016/12/6.
 */
@Controller
@RequestMapping("anxian/appHotgood")
public class AnxianAppHotgoodsController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AnXianAppHotGoodsFeign anXianAppHotGoodsFeign;

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
        AdItemTempleteAnxian adItemTemplete = anXianAppHotGoodsFeign.findOne(id);
        jsonMsg.setData(adItemTemplete);
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

//    /**
//     * 热销列表
//     *
//     * @return 热销列表
//     */
//    @ResponseBody
//    @RequestMapping(method = RequestMethod.GET)
//    public PageModel<AdItemTempleteModel> list(int page, int limit) {
//        AdItemTempleteAnxian adItemTemplete = anXianAppHotGoodsFeign.getByZoneId(Constants.APP_HOTGOODS_ZONEID);
//        List<AdItemTempleteModel> list = Lists.newArrayList();
//        if (adItemTemplete != null) {
//            AdItemTempleteModel adItemTempleteModel = new AdItemTempleteModel();
//            adItemTempleteModel.setId(adItemTemplete.getId());
//            adItemTempleteModel.setSns(adItemTemplete.getSns());
//            adItemTempleteModel.setTempleteName(adItemTemplete.getTempleteName());
//            adItemTempleteModel.setZoneId(adItemTemplete.getZoneId());
//            list.add(adItemTempleteModel);
//        }
//        return new PageModel<>(list, list.size(), new Pageable(page, limit));
//    }


    /**
     * 新增/修改热销商品
     *
     * @param adItemTempleteModel 热销信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(AdItemTempleteModel adItemTempleteModel) {
        if (StringUtils.isEmpty(adItemTempleteModel.getId())) {
            anXianAppHotGoodsFeign.add(adItemTempleteModel.getSns(), adItemTempleteModel.getShopId(), adItemTempleteModel.getShopName());
        } else {
            anXianAppHotGoodsFeign.update(adItemTempleteModel.getId(), adItemTempleteModel.getSns(), adItemTempleteModel.getShopId(), adItemTempleteModel.getShopName());
        }
        return JsonMsg.success("成功");
    }

    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String hotGood(Model model, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "anXian-APP/sell-hot";
    }


    @RequestMapping("/ajaxHot")
    public String ajaxHotGood(Model model, int page, int limit, String shopId, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        List<AdItemTempleteAnxian> adItemTemplete = anXianAppHotGoodsFeign.getByZoneId(Constants.APP_HOTGOODS_ZONEID);
        List<AdItemTempleteModel> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(adItemTemplete)) {
            adItemTemplete.forEach(adItemTempleteAnxian -> {
                //todo 筛选门店
                String shopIds = adItemTempleteAnxian.getShopId();
                if (!StringUtils.isEmpty(shopId) && (StringUtils.isEmpty(shopIds) || (!shopIds.equals("all") && !shopIds.contains(shopId))))
                    return;
                AdItemTempleteModel adItemTempleteModel = new AdItemTempleteModel();
                adItemTempleteModel.setId(adItemTempleteAnxian.getId());
                adItemTempleteModel.setSns(adItemTempleteAnxian.getSns());
                adItemTempleteModel.setTempleteName(adItemTempleteAnxian.getTempleteName());
                adItemTempleteModel.setZoneId(adItemTempleteAnxian.getZoneId());
                adItemTempleteModel.setShopId(adItemTempleteAnxian.getShopId());
                adItemTempleteModel.setShopName(adItemTempleteAnxian.getShopName());
                list.add(adItemTempleteModel);
            });
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
        model.addAttribute("totalPages", hotGoods.getTotalPages());
        model.addAttribute("hotGoods", hotGoods);
        return "anXian-APP/sell-hot-ajax";
    }

    /**
     *  删除热销
     * @param hotId
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonMsg deleteMenu(@RequestParam(value = "hotId") Long hotId) {
        try {
              anXianAppHotGoodsFeign.delete(hotId);
        }catch (Exception e){
             return JsonMsg.failure("删除菜单失败");
        }
        return JsonMsg.success("删除菜单成功");
    }
}
