package anxian.gateway.admin.module.business.controller.GiftStock.viewmodel;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by kimiyu on 16/1/11.
 */
@Data
public class GiftViewModel implements Serializable {

    /**
     * '赠品编码'
     */
    private String code;
    /**
     * ''赠品名称''
     */
    private String name;
    /**
     * '赠品来源，1表示大库，2表示采购（供应商），3表示外采，4表示内采'
     */
    private String sorce;
    /**
     * 赠品单位
     */
    private String unitName;
    /**
     * 商品价值
     */
    private Double price;
    /**
     * 入库时间
     */
    private String shelflife;
    /**
     * 库存
     */
    private Integer storagenu;
    /**
     * 锁定库存
     */
    private Integer locknu;
    /**
     * 备注
     */
    private String remarks;
}
