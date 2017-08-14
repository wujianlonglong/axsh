package anxian.gateway.admin.module.business.service;

import anxian.gateway.admin.utils.DateTimeUtils;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.order.OrderPickerFeign;
import client.api.order.model.OrderPicker;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qinhailong on 16-11-1.
 */
@Service
public class OrderPickerService {

    @Autowired
    private OrderPickerFeign orderPickerFeign;

    /**
     * 分页查询拣货员列表
     *
     * @param orderPicker 分页查询条件
     * @return 分页列表
     */
    public PageModel<OrderPicker> search(OrderPicker orderPicker, int page, int limit) {
        SearchCoditionModel<OrderPicker> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(orderPicker);
        PageModel<OrderPicker> pageModel = orderPickerFeign.search(searchCoditionModel);
        List<OrderPicker> orderPickers = pageModel.getContent();
        if (CollectionUtils.isNotEmpty(orderPickers)) {
            orderPickers.forEach(picker -> {
                picker.setCreateDateStr(DateTimeUtils.formatDateTime(picker.getCreateDate()));
                picker.setUpdateDateStr(DateTimeUtils.formatDateTime(picker.getUpdateDate()));
            });
        }
        return pageModel;
    }

    /**
     * 保存拣货员信息
     *
     * @param orderPicker 拣货员信息
     * @return 拣货员
     */
    public JsonMsg save(OrderPicker orderPicker) {
        OrderPicker retOrderPicker = orderPickerFeign.save(orderPicker);
        if (null != retOrderPicker) {
            return JsonMsg.success("新增拣货员成功！");
        } else {
            return JsonMsg.failure("新增拣货员失败！");
        }
    }

    /**
     * 跟新拣货员信息
     *
     * @param orderPicker 拣货员信息
     */
    public JsonMsg update(OrderPicker orderPicker) {
        int retVal = orderPickerFeign.update(orderPicker);
        if (retVal > 0) {
            return JsonMsg.success("更新拣货员成功！");
        } else {
            return JsonMsg.failure("更新拣货员失败！");
        }
    }

    /**
     * 删除指定id的拣货员
     *
     * @param id 主键id
     */
    public void delete(Long id) {
        orderPickerFeign.delete(id);
    }

}
