package anxian.gateway.admin.module.business.model.item;

import lombok.Data;

/**
 * Created by Jianghe on 15/12/16.
 */
@Data
public class AttributeMaintainModel {

    private Long id;

    private String name;// 属性名称

    private Long categoryAttributeGroupId; // 属性组Id

    private Long attributeGroupId;

    private String attributeGroup;//属性组

    private Integer orders; // 排序

    private Integer type; // 属性类型: 0: 下拉属性值； 1：可填写属性

    private String unit; // 属性单位

    private Boolean isFilter; // 是否加入分类筛选项

    private String attributeOptions;//属性值

    private Long categoryId;

}
