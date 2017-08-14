package client.api.order.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 拣货员查询条件 Created by kimiyu on 16/2/6.
 */
@Data
public class PickingCondition implements Serializable {
    /**
     * 拣货员对应的shopId
     */
    private String shopId;
    /**
     * 订单号
     */
    private String orderId;
    /**
     * 关联订单号
     */
    private Long parentId;
    /**
     * 手机号
     */
    private String telphone;
    /**
     * 是否打印(0,1,2)
     */
    private int checkPrint = 0;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 成交开始时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startDateTime;
    /**
     * 成交结束时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDateTime;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 收货方式.
     */
    private Integer consigneeType;

    /**
     * 当前页码
     */
    private int page;

    /**
     * 一页显示几行
     */
    private int size;

    /**
     * 查询类型(1:待拣货;2:已拣货)
     */
    private int searchType = SearchType.treatGoods;
}
