package client.api.item.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by mac on 15/9/15.
 */
@Data
public class ProductCategory implements Serializable {

    @Id
    private Long id; // 主键

    private Long productId; // 单品id

    private Long categoryId; // 分类id

}
