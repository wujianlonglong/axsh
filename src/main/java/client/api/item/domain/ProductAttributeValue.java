package client.api.item.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mac on 15/8/31.
 */
@Data
public class ProductAttributeValue implements Serializable {

    private Long id; // 主键

    private Long productId; // 单品Id

    private Long attributeId; // 属性Id

    private Long attributeOptionId; // 属性选项值

    private String attributeValue; // 属性选项值

    private Integer type; // 属性类型: 0: 下拉属性值； 1：可填写属性

}
