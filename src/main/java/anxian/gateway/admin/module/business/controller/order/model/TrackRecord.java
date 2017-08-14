package anxian.gateway.admin.module.business.controller.order.model;

import lombok.Data;

/**
 * 跟踪记录
 * Created by Jianghe on 16/1/25.
 */
@Data
public class TrackRecord {

    /**
     * 表示已经达到的阶段需要在页面上字段标红显示
     */
    private boolean isRed;

    /**
     * 跟踪记录节点名称
     */
    private String trackValue;
}
