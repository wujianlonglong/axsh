package client.api.item.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mac on 15/9/11.
 */
@Data
public class ProductSpecificationValue implements Serializable {

    private Long id; // 主键

    private String sn; // 商品编码

    private Long specificationId; // 规格id

    private Long specificationValueId; // 规格值id

}
