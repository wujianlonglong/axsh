package anxian.gateway.admin.module.business.controller.anxian.app;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.anxian.app.AnXianAppVersionFeign;
import client.api.anxian.app.model.VersionAnxian;
import client.api.user.utils.page.SjesPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Date;

/**
 * Created by gaoqichao on 16-3-8.
 */
@Controller
@RequestMapping(value = "anxian/versions")
public class AnxianVersionController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(AnxianVersionController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AnXianAppVersionFeign anXianAppVersionFeign;


    /**
     * 新增APP版本信息
     *
     * @param version
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public JsonMsg add(VersionAnxian version) {
        try {
            version.setCreatedDate(new Date());
            version.setLastModifiedDate(new Date());
            anXianAppVersionFeign.insert(version);
        } catch (Exception e) {
            log.error("新增APP版本发生异常！", e);
            return JsonMsg.failure("新增版本信息发生异常!");
        }

        return JsonMsg.success("新增版本成功！");
    }

    /**
     * @param version
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public JsonMsg update(VersionAnxian version) {
        try {
            VersionAnxian oldVersion = anXianAppVersionFeign.findOne(version.getId());
            version.setCreatedDate(oldVersion.getCreatedDate());
            version.setLastModifiedDate(new Date());
            anXianAppVersionFeign.save(version);
        } catch (Exception e) {
            log.error("更新APP版本信息内容发生异常", e);

            return JsonMsg.failure("更新APP版本信息内容发生异常");
        }

        return JsonMsg.success("更新APP版本信息成功");
    }

    /**
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonMsg findCategoryById(@PathVariable("id") Long id) {
        return new JsonMsg(true, false, anXianAppVersionFeign.findOne(id), null, null);
    }

    /**
     * 删除APP版本信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public JsonMsg delete(@PathVariable("id") Long id) {
        try {
            anXianAppVersionFeign.delete(id);
        } catch (Exception e) {
            log.error("删除版本信息失败", e);

            return JsonMsg.failure("删除版本信息失败");
        }
        return JsonMsg.success("删除版本信息成功");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String versions(Model model, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);

        return "anXian-APP/version";
    }

    @RequestMapping("/ajaxVersion")
    public String ajaxVersion(Model model, int page, int limit, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        SjesPage<VersionAnxian> sjesPage = anXianAppVersionFeign.pageGetVersionList(page, limit);
        model.addAttribute("page", page + 1);
        model.addAttribute("totalPages",sjesPage.getTotalPages());
        model.addAttribute("versions", sjesPage);
        return "anXian-APP/version-ajax";
    }


}
