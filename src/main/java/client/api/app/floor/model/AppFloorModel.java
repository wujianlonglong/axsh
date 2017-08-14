package client.api.app.floor.model;

import lombok.Data;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by byinbo on 16-11-30
 */
@Data
public class AppFloorModel extends Floor implements Serializable {
    @Transient
    private String startDateStr; // 开始时间字符串
    @Transient
    private String endDateStr; // 结束时间字符串
    private String sns;
}
