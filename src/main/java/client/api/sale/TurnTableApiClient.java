package client.api.sale;

import client.api.sale.model.ResponseMessage;
import client.api.sale.model.turnTable.TurnTable;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by kimiyu on 16/1/17.
 */
@FeignClient("sjes-api-sale")
@RequestMapping(value = "/sales/turntables", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface TurnTableApiClient {
    /**
     * 保存或更新大转盘
     *
     * @param turnTable
     * @return
     */
    @PreAuthorize("hasAuthority('TURNTABLE_SAVE')")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    ResponseMessage<TurnTable> save(TurnTable turnTable);

    /**
     * 查看大转盘明细
     *
     * @param id 大转盘ID
     * @return 指定ID的大转盘
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    TurnTable get(@PathVariable("id") String id);

    /**
     * 删除指定的大转盘
     *
     * @param id 大转盘ID
     */
    @PreAuthorize("hasAuthority('TURNTABLE_DELETE')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    void del(@PathVariable("id") String id);

    /**
     * 查询
     *
     * @return 返回分页数据
     */
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    SjesPage<TurnTable> findTurnTables(@RequestParam(value = "startDate") String startDate,
                                       @RequestParam(value = "endDate") String endDate,
                                       Pageable pageable);
}
