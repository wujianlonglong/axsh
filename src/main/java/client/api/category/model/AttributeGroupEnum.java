package client.api.category.model;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mac on 15/9/8.
 */
public enum AttributeGroupEnum {


    place("产地", 1L), profiles("基本信息", 2L), specifications("规格", 3L),
    packaging("包装", 4L), securityInfos("安全信息", 5L), subjects("主体", 6L),
    functions("功能特点", 7L), techParams("技术参数", 8L), featrues("特征", 9L);

    private String name;

    private Long value;


    AttributeGroupEnum(String name, Long value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Long getValue() {
        return value;
    }

    public static AttributeGroupEnum getAttributeGroupEnum(Long value) {
        if (null == value) {
            return null;
        }
        List<AttributeGroupEnum> attributeGroupEnums = Lists.newArrayList(AttributeGroupEnum.values());
        for (AttributeGroupEnum attributeGroupEnum : attributeGroupEnums) {
            if (value.longValue() == attributeGroupEnum.getValue().longValue()) {
                return attributeGroupEnum;
            }
        }
        return null;
    }

    public static AttributeGroupEnum getAttributeGroupEnum(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        List<AttributeGroupEnum> attributeGroupEnums = Lists.newArrayList(AttributeGroupEnum.values());
        for (AttributeGroupEnum attributeGroupEnum : attributeGroupEnums) {
            if (name.equals(attributeGroupEnum.getName())) {
                return attributeGroupEnum;
            }
        }
        return null;
    }

    public static List<Map> getAttributeGroupList() {
        List<Map> getAttributeGroupList = Lists.newArrayList();
        List<AttributeGroupEnum> attributeGroupEnums = Lists.newArrayList(AttributeGroupEnum.values());
        for (AttributeGroupEnum attributeGroupEnum : attributeGroupEnums) {
            Map map = new HashMap();
            map.put("id", attributeGroupEnum.getValue());
            map.put("value", attributeGroupEnum.getName());
            getAttributeGroupList.add(map);
        }
        return getAttributeGroupList;
    }

}
