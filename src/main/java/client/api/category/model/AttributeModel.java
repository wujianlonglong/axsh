package client.api.category.model;

import client.api.category.domain.Attribute;
import client.api.category.domain.AttributeOption;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * Created by mac on 15/9/8.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AttributeModel extends Attribute {

    /**
     * 分类Id
     */
    private Long categoryId;

    /**
     * 属性组Id
     */
    private Long attributeGroupId;

    /**
     * 属性值
     */
    private String value;

    /**
     * 属性选项列表
     */
    private List<AttributeOption> attributeOptions;

}
