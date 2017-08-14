package client.api.order;

import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.order.model.OrderPicker;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qinhailong on 16-11-1.
 */
@FeignClient("sjes-api-order")
@RequestMapping(value = "orderPickers")
public interface OrderPickerFeign {

    /**
     * 分页查询拣货员列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<OrderPicker> search(SearchCoditionModel<OrderPicker> searchCoditionModel);

    /**
     * 保存拣货员信息
     *
     * @param orderPicker 拣货员信息
     * @return 拣货员
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    OrderPicker save(OrderPicker orderPicker);

    /**
     * 跟新拣货员信息
     *
     * @param orderPicker 拣货员信息
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(OrderPicker orderPicker);

    /**
     * 删除指定id的拣货员
     *
     * @param id 主键id
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") Long id);
}
