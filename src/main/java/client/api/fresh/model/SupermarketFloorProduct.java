package client.api.fresh.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by mac on 15/9/23.
 */
@Data
public class SupermarketFloorProduct implements Serializable {

    private Long id; // 主键

    private Long supermarketFloorId; // 超市页id

    private Long productId; // 超市页id

    private Integer orders; // 排序
}
