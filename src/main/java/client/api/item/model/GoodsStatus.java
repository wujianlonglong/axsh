package client.api.item.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class GoodsStatus implements Serializable {

    private String shopId;

    private Long erpGoodsId;

    private Integer status;

}