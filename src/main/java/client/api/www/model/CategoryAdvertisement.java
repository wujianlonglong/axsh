package client.api.www.model;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by mac on 15/9/17.
 */
@Data
public class CategoryAdvertisement implements Serializable {

    private Long id; // 主键

    private Long categoryId; // 分类id

    private Integer orders; // 排序

    private Integer type; // 分类广告类型，0:品牌广告，1:大图广告

    private String imageUrl; // 图片路径

    private String hrefUrl; // 品牌链接路径

    private String remarker; // 广告描述

    private Boolean isLong; // 是否长期

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

    public String getExtViewCreateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getCreateDate() != null ? formatter.format(createDate) : "";
    }

    public String getExtViewUpdateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getUpdateDate() != null ? formatter.format(updateDate) : "";
    }

    public String getStartDateFormat() {
        return startDate != null ? DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(startDate) : "";
    }

    public String getEndDateFormat() {
        return endDate != null ? DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(endDate) : "";
    }

}
