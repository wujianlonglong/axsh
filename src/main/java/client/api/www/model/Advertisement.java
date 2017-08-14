package client.api.www.model;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by mac on 15/9/16.
 */
@Data
public class Advertisement implements Serializable {

    private Long id; // 主键

    private Integer status; // 状态

    private Integer position; // 广告位置

    private Integer orders; // 排序

    private String backgroundColor; // 背景

    private String remarker; // 备注

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime startDate; // 开始时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime endDate; // 结束时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

    public String getFormatStartDate() {
        return getStartDate() != null ? getStartDate().toString().split("T")[0] : "";
    }

    public String getFormatEndDate() {
        return getEndDate() != null ? getEndDate().toString().split("T")[0] : "";
    }
}
