package client.api.item.domain;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品状态批量更新任务表
 * Created by qinhailong on 17-1-10.
 */
@Data
public class ProductStatusPlan implements Serializable {

    private Long id; // 主键

    private String planName; // 任务名

    private String synPlanDataTime; // 同步时间

    private Integer status; // 0/1/2; 正常销售/下架停售/未审核

    private String remarker; // 备注

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @LastModifiedDate
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间
}
