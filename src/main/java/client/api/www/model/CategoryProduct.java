package client.api.www.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mac on 15/9/22.
 */
@Data
public class CategoryProduct implements Serializable {

    private Long id; // 主键

    private Long categoryId; // 顶层分类id

    private Long productId; // 商品Id,

    private Integer orders; // 排序

}
