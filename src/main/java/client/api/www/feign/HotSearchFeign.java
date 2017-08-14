package client.api.www.feign;

import client.api.constants.Constants;
import client.api.www.model.HotSearch;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mac on 15/9/17.
 */
@FeignClient(Constants.SJES_API_WWW)
@RequestMapping("hotSearchs")
public interface HotSearchFeign {

    /**
     * 保存热门搜索词
     *
     * @param hotSearch 热门搜索词
     * @return 热门搜索词
     */
//    @PreAuthorize("hasAuthority('HOTSEARCH_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    HotSearch save(HotSearch hotSearch);

    /**
     * 更新热门搜索词
     *
     * @param hotSearch 热门搜索词
     * @return 热门搜索词
     */
//    @PreAuthorize("hasAuthority('HOTSEARCH_SAVE')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(HotSearch hotSearch);

    /**
     * 查询热门搜索词列表
     *
     * @return 热门搜索词列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<HotSearch> list();

    /**
     * 删除指定热门搜索词
     *
     * @param id 主键
     */
//    @PreAuthorize("hasAuthority('HOTSEARCH_DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);
}
