package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 已审核订单的查询条件
 * Created by kimiyu on 15/9/1.
 */
@Data
public class VerficationCondition implements Serializable {

    private String verficater;          // 审核人
    private Integer verficateResult;     // 审核结果:1 通过;2 不通过
    private Date verficateStartDate;    // 审核开始时间
    private Date verficateEndDate;      // 审核结束时间
    private Integer page;
    private Integer size;
    private Integer payType;
}
