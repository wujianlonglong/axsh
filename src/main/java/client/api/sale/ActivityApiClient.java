package client.api.sale;

import anxian.gateway.admin.module.business.model.activity.ActivityViewModel;
import client.api.sale.model.act.Activity;
import client.api.sale.util.ActivityParam;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by gaoqichao on 16-1-15.
 */
@FeignClient("sjes-api-sale")
@RequestMapping(value = "/act", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface ActivityApiClient {
    /**
     * 插入新增活动
     *
     * @param activity 活动对象
     * @return 插入后的活动对象
     */
    @PreAuthorize("hasAuthority('ACTIVITY_SAVE')")
    @RequestMapping(method = RequestMethod.POST)
    Activity insert(Activity activity);


    /**
     * 更新活动对象
     *
     * @param activity 给定的修改的活动对象
     * @return 修改后的活动对象
     */
    @PreAuthorize("hasAuthority('ACTIVITY_SAVE')")
    @RequestMapping(method = RequestMethod.PUT)
    Activity save(Activity activity);

    /**
     * 根据主键取得活动
     *
     * @param activityId 主键id
     * @return 活动详情
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{activityId}")
    Activity findActivity(@PathVariable("activityId") String activityId);

    /**
     * 取得指定专区对应的活动模板
     *
     * @param zoneId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/zone/{zoneId}")
    Activity findByZoneId(@PathVariable("zoneId") String zoneId);

    /**
     * 根据id删除对应活动
     *
     * @param id 活动id
     */
    @PreAuthorize("hasAuthority('ARTICLE_DELETE')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    void delete(@PathVariable("id") String id);

    /**
     * 根据查询条件分页取得活动列表
     *
     * @param activityParam 查询参数
     * @return 分页活动信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/pageList")
    SjesPage<ActivityViewModel> pageGetActivityList(ActivityParam activityParam,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("size") int size);
}
