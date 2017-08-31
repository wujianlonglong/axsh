package anxian.gateway.admin.module.business.controller.anxian.app;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.app.floor.feign.AppFloorFeign;
import client.api.app.floor.model.AppFloorDetailModel;
import client.api.app.floor.model.AppFloorModel;
import client.api.app.floor.model.FloorContentModel;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by byinbo on 2016/11/30.
 */
@Controller
@RequestMapping("anxian/appFloor")
public class AnxianAppFloorController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppFloorFeign appFloorFeign;

    /**
     * 楼层列表
     *
     * @return 楼层列表
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<AppFloorModel> list(int page, int limit) {
        List<AppFloorModel> list = appFloorFeign.list();
        return new PageModel<>(list, list.size(), new Pageable(page, limit));
    }

    /**
     * 保存新楼层
     *
     * @param appFloorModel
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg add(AppFloorModel appFloorModel) {
        appFloorModel.setZoneId(String.valueOf(System.currentTimeMillis()));
        appFloorFeign.addAppFloor(appFloorModel);
        return JsonMsg.success("保存成功");
    }

    /**
     * 修改楼层
     *
     * @param appFloorModel 楼层信息
     * @return 修改数目
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(AppFloorModel appFloorModel) {
        appFloorFeign.updateAppFloor(appFloorModel);
        return JsonMsg.success("修改成功");
    }

    /**
     * 删除指定id的楼层
     *
     * @param floorId 楼层id
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonMsg deleteFloor(@RequestParam(value = "floorId") Long floorId) {
        AppFloorDetailModel appFloorDetailModel = appFloorFeign.getAppFloorDetailModel(floorId);
        if (null != appFloorDetailModel) {
            if (CollectionUtils.isNotEmpty(appFloorDetailModel.getFloorContents())) {
                return JsonMsg.failure("该楼层下存在内容, 不能删除！");
            }

            appFloorFeign.delete(floorId, appFloorDetailModel.getZoneId());
            return JsonMsg.success("删除成功！");
        } else {
            return JsonMsg.failure("不存在该楼层！");
        }
    }

    /**
     * 根据id得到指定的 FloorDetailModel
     *
     * @param floorId 主键id
     * @return FloorDetailModel
     */
    @ResponseBody
    @RequestMapping(value = "{floorId}", method = RequestMethod.GET)
    public JsonMsg getAppFloorDetailModel(@PathVariable("floorId") Long floorId) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(appFloorFeign.getAppFloorDetailModel(floorId));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 新增楼层内容信息
     *
     * @param floorContentModel ；楼层内容
     * @return 修改数目
     */
    @ResponseBody
    @RequestMapping(value = "/content/add", method = RequestMethod.POST)
    public JsonMsg addContent(FloorContentModel floorContentModel) {
        appFloorFeign.addContent(floorContentModel);
        return JsonMsg.success("添加成功!");
    }

    /**
     * 更新楼层内容信息
     *
     * @param floorContentModel ；楼层内容
     * @return 修改数目
     */
    @ResponseBody
    @RequestMapping(value = "/content/update", method = RequestMethod.POST)
    public JsonMsg updateContent(FloorContentModel floorContentModel) {
        appFloorFeign.updateContent(floorContentModel);
        return JsonMsg.success("修改成功!");
    }

    /**
     * 根据id删除指定的楼层内容
     *
     * @param id 主键
     */
    @ResponseBody
    @RequestMapping(value = "/content/delete", method = RequestMethod.POST)
    public JsonMsg delete(@RequestParam("id") Long id) {
        appFloorFeign.deleteContent(id);
        return JsonMsg.success("删除成功!");
    }


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String floor(Principal principal, Model model) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "anXian-APP/floor";
    }

    @RequestMapping("/ajaxFloor")
    public String ajaxFloor(Principal principal, Model model, int page, int limit) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        List<AppFloorModel> list = appFloorFeign.list();
        PageModel<AppFloorModel> floors = new PageModel<>(list, list.size(), new Pageable(page, limit));
        List<AppFloorModel> content = new ArrayList<>();
        int currIdx = page * limit;
        for (int i = 0; i < limit && i < list.size() - currIdx; i++) {
            AppFloorModel floor = list.get(currIdx + i);
            content.add(floor);
        }
        floors.getContent().clear();
        floors.getContent().addAll(content);
        model.addAttribute("page", page + 1);
        model.addAttribute("floors", floors);
        return "anXian-APP/floor-ajax";
    }

    @RequestMapping(value = "/editFloor", method = RequestMethod.POST)
    public String editFloor(Model model, Long id, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        AppFloorDetailModel floor = appFloorFeign.getAppFloorDetailModel(id);
        model.addAttribute("floor", floor);
        return "anXian-APP/edit-floor";
    }

}
