package client.api.app.version;

import client.api.app.version.domain.Version;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by gaoqichao on 16-3-8.
 */
@FeignClient("sjes-api-app")
@RequestMapping(value = "/version", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface VersionApiClient {
    /**
     * 新增APP版本
     *
     * @param version APP版本对象
     * @return app版本对象
     */
    @RequestMapping(method = RequestMethod.POST)
    Version insert(Version version);

    /**
     * 修改APP版本
     *
     * @param version APP版本对象
     * @return 修改后的app版本对象
     */
    @RequestMapping(method = RequestMethod.PUT)
    Version save(Version version);

    /**
     * 分页取得版本信息列表
     *
     * @param page 页码
     * @param size 每页显示的最大条目
     * @return 版本列表分页信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/pageList")
    SjesPage<Version> pageGetVersionList(@RequestParam("page") int page, @RequestParam("size") int size);

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
    Version findOne(@PathVariable("id") Long id);
}
