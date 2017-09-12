package client.api.sale.model.secKill;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 促销管理查询条件对象 Created by kimiyu on 15/9/7.
 */
@Data
public class SaleManageCondition implements Serializable {

    private String promotionName;   // 促销名称
    private Integer promotionType = 0;  // 促销类型
    private Integer promotionStatus = 0;// 促销状态
    private LocalDateTime promotionStartDate;// 促销开始时间
    private LocalDateTime promotionEndDate;  // 促销结束时间
    private Long itemId;          // 商品ID
    private Integer size;
    private Integer page;
}
