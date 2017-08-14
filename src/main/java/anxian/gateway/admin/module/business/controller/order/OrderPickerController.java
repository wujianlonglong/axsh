package anxian.gateway.admin.module.business.controller.order;

import anxian.gateway.admin.module.business.service.OrderPickerService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.order.model.OrderPicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 16-11-1.
 */
@RestController
@RequestMapping("orderPicker")
public class OrderPickerController {

    @Autowired
    private OrderPickerService orderPickerService;

    /**
     * 分页查询拣货员列表
     *
     * @param orderPicker 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search")
    public PageModel<OrderPicker> search(OrderPicker orderPicker, int page, int limit) {
        return orderPickerService.search(orderPicker, page, limit);
    }

    /**
     * 更新拣货员信息
     *
     * @param orderPicker 拣货员信息
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public JsonMsg saveOrUpdate(OrderPicker orderPicker) {
        if (null == orderPicker.getId()) {
            return orderPickerService.save(orderPicker);
        } else {
            return orderPickerService.update(orderPicker);
        }
    }

    /**
     * 删除指定id的拣货员
     *
     * @param id 主键id
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonMsg delete(@PathVariable("id") Long id) {
        orderPickerService.delete(id);
        return JsonMsg.success("删除拣货员成功！");
    }

}
