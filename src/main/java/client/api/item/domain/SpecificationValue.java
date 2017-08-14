package client.api.item.domain;


import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity -- 规格值表
 * Created by qinhailong on 15-8-22.
 */
@Data
public class SpecificationValue implements Serializable {

    @Id
    private Long id; // 主键

    private Long specificationId; // 规格id

    private Boolean isColor; // 是否颜色显示

    private String image; // 规格背景图像

    private String name; // 规格名称

    private Integer orders; // 排序

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间
}
