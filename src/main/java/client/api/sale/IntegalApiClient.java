package client.api.sale;

import client.api.sale.model.IntegalCondition;
import client.api.sale.model.ResponseMessage;
import client.api.sale.model.integal.Integal;
import client.api.sale.model.integal.IntegalViewModel;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jianghe on 16/1/25.
 */
@FeignClient("sjes-api-sale")
@RequestMapping("/sales/integals")
public interface IntegalApiClient {
    /**
     * 新增
     *
     * @param integalViewModel
     */
    @PreAuthorize("hasAuthority('INTEGAL_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseMessage<Integal> save(IntegalViewModel integalViewModel);

    /**
     * 更新积分对象
     *
     * @param integalViewModel
     * @return
     */
    @PreAuthorize("hasAuthority('INTEGAL_SAVE')")
    @RequestMapping(value = "/updateIntegal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseMessage<Integal> updateInteal(IntegalViewModel integalViewModel);

    /**
     * 删除积分活动
     *
     * @param id 主键
     */
    @PreAuthorize("hasAuthority('INTEGAL_DELETE')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    Boolean delete(@PathVariable("id") String id);

    /**
     * 显示积分列表
     *
     * @param integalCondition 分页对象
     * @return 积分列表
     */
    @RequestMapping(method = RequestMethod.POST, value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    SjesPage<Integal> getList(IntegalCondition integalCondition);

    /**
     * 根据促销ID返回促销对象
     *
     * @param saleId 促销ID
     * @return
     */
    @RequestMapping(value = "/{saleId}", method = RequestMethod.GET)
    ResponseMessage<Integal> getIntegalById(@PathVariable("saleId") String saleId);

    /**
     * 强停积分活动
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/stop/{id}")
    Boolean stopIntegal(@PathVariable("id") String id);

}
