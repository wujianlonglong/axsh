package client.api.sale.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by gaoqichao on 15-9-19.
 * 活动查询参数对象，确保属性映射与活动实体类中名称一致
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityParam {
    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动状态： -1 --所有状态； 0 -- 尚未开始； 1 -- 已开始;2 --已结束
     */
    private Integer flag = -1;

    /**
     * 开始时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate beginTime;

    /**
     * 结束时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endTime;
}
