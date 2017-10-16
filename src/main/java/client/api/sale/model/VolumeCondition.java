package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 优惠劵查询条件
 * Created by kimiyu on 15/9/10.
 */
@Data
public class VolumeCondition implements Serializable {

    private String volumeName;      // 优惠劵名称
    private Integer volumeStatus = 0;   // 优惠劵状态
    private Integer volumeType = 0;     // 优惠劵类型
    private Integer getVolumeType = 0;  // 领卷方式
    private LocalDateTime startDate;  // 有效开始时间
    private LocalDateTime endDate;    // 有效结束时间
    private Integer page;
    private Integer size;
    private Boolean isSync;   // 是否同步
    private String startDateStr;
    private String endDateStr;
}
