package client.api.anxian.app;

import client.api.anxian.app.model.VersionAnxian;
import client.api.app.version.domain.Version;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangdinglan on 2017/09/14
 */
@FeignClient("sjes-api-app")
@RequestMapping("/anxian/version")
public interface AnXianAppVersionFeign {
    /**
     * 新增APP版本
     *
     * @param version APP版本对象
     * @return app版本对象
     */
    @RequestMapping(method = RequestMethod.POST)
    VersionAnxian insert(VersionAnxian version);

    /**
     * 修改APP版本
     *
     * @param version APP版本对象
     * @return 修改后的app版本对象
     */
    @RequestMapping(method = RequestMethod.PUT)
    VersionAnxian save(VersionAnxian version);

    /**
     * 分页取得版本信息列表
     *
     * @param page 页码
     * @param size 每页显示的最大条目
     * @return 版本列表分页信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/pageList")
    SjesPage<VersionAnxian> pageGetVersionList(@RequestParam("page") int page, @RequestParam("size") int size);

    /**
     * 根据主键id删除对应的版本信息
     *
     * @param id 主键id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    void delete(@PathVariable("id") Long id);

    /**
     * 根据id取得版本信息详情
     *
     * @param id 主键id
     * @return 版本信息详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    VersionAnxian findOne(@PathVariable("id") Long id);
}
