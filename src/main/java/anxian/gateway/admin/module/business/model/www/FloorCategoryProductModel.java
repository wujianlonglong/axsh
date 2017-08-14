package anxian.gateway.admin.module.business.model.www;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by qinhailong on 16-1-7.
 */
@Data
public class FloorCategoryProductModel implements Serializable {

    private Long id; // 商品Id

    private Integer orders; // 排序

    private String sn; // 商品编码

    private String name; // 商品名称

    private Integer status; // 商品状态

}
