package anxian.gateway.admin.module.business.controller.app;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.app.version.VersionApiClient;
import client.api.app.version.domain.Version;
import client.api.user.utils.page.PageForAdmin;
import client.api.user.utils.page.SjesPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by gaoqichao on 16-3-8.
 */
@RestController
@RequestMapping(value = "/versions")
public class VersionController {
    private static final Logger log = LoggerFactory.getLogger(VersionController.class);

    @Autowired
    private VersionApiClient versionApiClient;


    /**
     * 取得APP版本信息列表
     *
     * @param page  页码
     * @param limit 每页展示的最大数目
     * @return
     */
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
     * 删除APP版本信息
     *
     * @param id
     * @return
     */
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
}
