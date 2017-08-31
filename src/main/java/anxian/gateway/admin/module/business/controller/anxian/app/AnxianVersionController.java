package anxian.gateway.admin.module.business.controller.anxian.app;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.app.version.VersionApiClient;
import client.api.app.version.domain.Version;
import client.api.user.utils.page.PageForAdmin;
import client.api.user.utils.page.SjesPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by gaoqichao on 16-3-8.
 */
@Controller
@RequestMapping(value = "anxian/versions")
public class AnxianVersionController {
    private static final Logger log = LoggerFactory.getLogger(AnxianVersionController.class);

    @Autowired
    private VersionApiClient versionApiClient;


    /**
     * 取得APP版本信息列表
     *
     * @param page  页码
     * @param limit 每页展示的最大数目
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public PageForAdmin<Version> pageList(int page, int limit) {
        SjesPage<Version> sjesPage = versionApiClient.pageGetVersionList(page - 1, limit);


        PageForAdmin<Version> pageForAdmin = new PageForAdmin<>();
        pageForAdmin.setTotalCount(sjesPage.getTotalElements());
        pageForAdmin.setList(sjesPage.getContent());

        return pageForAdmin;
    }

    /**
     * 新增APP版本信息
     *
     * @param version
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public JsonMsg add(Version version) {
        try {
            version.setCreatedDate(new Date());
            version.setLastModifiedDate(new Date());
            version = versionApiClient.insert(version);
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
    public JsonMsg update(Version version) {
        try {
            Version oldVersion = versionApiClient.findOne(version.getId());

            version.setCreatedDate(oldVersion.getCreatedDate());
            version.setLastModifiedDate(new Date());
            versionApiClient.save(version);
        } catch (Exception e) {
            log.error("更新APP版本信息内容发生异常", e);

            return JsonMsg.failure("更新APP版本信息内容发生异常");
        }

        return JsonMsg.success("更新APP版本信息成功");
    }

    /**
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonMsg findCategoryById(@PathVariable("id") Long id) {
        return new JsonMsg(true, false, versionApiClient.findOne(id), null, null);
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
            versionApiClient.delete(id);
        } catch (Exception e) {
            log.error("删除版本信息失败", e);

            return JsonMsg.failure("删除版本信息失败");
        }

        return JsonMsg.success("删除版本信息成功");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String versions(Model model) {
        return "anXian-APP/version";
    }

    @RequestMapping("/ajaxVersion")
    public String ajaxVersion(Model model, int page, int limit) {
        SjesPage<Version> sjesPage = versionApiClient.pageGetVersionList(page, limit);
        model.addAttribute("page", page + 1);
        model.addAttribute("versions", sjesPage);
        return "anXian-APP/version-ajax";
    }


}
