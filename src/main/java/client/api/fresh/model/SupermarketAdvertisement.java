package client.api.fresh.model;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mac on 15/9/23.
 */
@Data
public class SupermarketAdvertisement implements Serializable {

    private Long id; // 主键

    private Long supermarketId; // 超市页id

    private Integer position; // 广告位置

    private Integer status; // 状态

    private String imageUrl; // 图片路径

    private String hrefUrl; // 链接路径

    private Integer orders; // 排序

    private String remarker; // 描述

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

    public String getExtViewStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return getStartDate() != null ? formatter.format(startDate) : "";
    }

    public String getExtViewEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return getEndDate() != null ? formatter.format(endDate) : "";
    }

}
