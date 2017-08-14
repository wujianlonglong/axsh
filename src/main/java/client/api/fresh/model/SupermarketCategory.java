package client.api.fresh.model;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by mac on 15/9/23.
 */
@Data
public class SupermarketCategory implements Serializable {

    private Long id; // 主键

    private Long supermarketId; // 超市页id

    private Long parentId; // 父分类ID

    private String name; // 分类名称

    private String href; // 链接

    private Integer grade; // 级别

    private Integer orders; // 排序

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

}
