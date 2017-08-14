package client.api.www.feign;

import client.api.constants.Constants;
import client.api.www.model.TopNavigation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by qinhailong on 15-11-20.
 */
@FeignClient(Constants.SJES_API_WWW)
@RequestMapping("topNavigations")
public interface TopNavigationFeign {

    /**
     * 保存首行
     *
     * @param topNavigation 首行
     * @return 首行
     */
//    @PreAuthorize("hasAuthority('TOPNAVIGATION_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    TopNavigation save(TopNavigation topNavigation);

    /**
     * 更新首行
     *
     * @param topNavigation 首行
     * @return 更新数目
     */
//    @PreAuthorize("hasAuthority('TOPNAVIGATION_SAVE')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(TopNavigation topNavigation);

    /**
     * 删除指定id的首行
     *
     * @param id 主键
     */
//    @PreAuthorize("hasAuthority('TOPNAVIGATION_DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);

    /**
     * 查询所有首行信息
     *
     * @return 首行信息
     */
    @RequestMapping(method = RequestMethod.GET)
    List<TopNavigation> listAll();
}
