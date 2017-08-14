package anxian.gateway.admin.module.business.model.activity;

import lombok.Data;

/**
 * Created by gaoqichao on 16-1-15.
 */
@Data
public class ActivityViewModel {
    /**
     * id
     */
    private String id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动名称
     */
    private String zoneId;

    /**
     * 活动日期
     */
    private String activityDate;

    /**
     * 状态
     */
    private int flag;
}
