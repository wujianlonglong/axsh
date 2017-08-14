package anxian.gateway.admin.module.business.controller.sale.model;

import lombok.Data;

/**
 * 活动模板楼层商品模板
 * Created by gaoqichao on 16-5-20.
 */
@Data
public class ActFloorProduct {
    /**
     * 商品SN
     */
    private String sn;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品状态
     */
    private Integer status;
}
