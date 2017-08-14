package client.api.category.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mac on 15/9/19.
 */
@Data
public class CategoryAttributeGroup implements Serializable {

    private Long id; // 主键

    private Long attributeGroupId;  // 属性组Id

    private Long categoryId; // 分类id

}
