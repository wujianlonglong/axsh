package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kimiyu on 16/1/22.
 */
@Data
public class VerficationView implements Serializable {
    /**
     * 是否通过[默认为通过!]
     */
    private Boolean verficateResult = Boolean.TRUE;
    /**
     * 是否加入黑名单
     */
    private Boolean isAddBlack;
    /**
     * 取消理由
     */
    private Integer cancelReason;
    /**
     * 取消理由内容
     */
    private String cancelReasonContent;
    /**
     * 备注
     */
    private String memo = "";
    /**
     * 审核人ID
     */
    private Long userId;
    /**
     * 订单ID
     */
    private List<Long> orderIds;

    private List<String> orderIdsStr;
}
