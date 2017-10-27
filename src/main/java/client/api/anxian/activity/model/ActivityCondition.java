package client.api.anxian.activity.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by wangdinglan on 2017/10/16
 */
@Data
public class ActivityCondition implements Serializable {
    private String activityName;      // 活动名称
    private Integer status = 0;   // 活动状态
    private String startDateStr;
    private String endDateStr;
    private LocalDate startDate;  // 有效开始时间
    private LocalDate endDate;    // 有效结束时间
    private Integer page;
    private Integer size;
}
