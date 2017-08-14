package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 导出对象
 * Created by kimiyu on 15/9/1.
 */
@Data
public class ExportViewModel implements Serializable {

    private Order order;
    private List<OrderItem> orderItems;
}
