package anxian.gateway.admin.module.business.controller.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单支付信息
 * Created by jiangzhe on 15-11-27.
 */
@Data
public class PayAmount implements Serializable {
    private static final long serialVersionUID = -8332823302449170467L;
    private String key;
    private String value;
}
