package client.api.category.domain;


import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品分类表
 *
 * @author qinhailong
 */
@Data
public class Category implements Serializable {

    private Long id; // 主键

    private String name; // 分类名称

    private Long parentId; // 父分类ID

    private Integer grade; // 级别

    private String treePath; // 路径

    private Integer orders; // 排序

    private Integer type; // 类型

    private Boolean display; // 是否显示

    private String speciHref; // 专题页链接

    private Boolean isRedLabel; // 文本标红

    private String tagName; // 分类标签名

    private String appCategoryName; //APP分类名称

    private String appImgSrc; // APP第3层分类图标

    private String classes; // 样式

    private Long seoId; // seoId

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

    private String classification;

}
