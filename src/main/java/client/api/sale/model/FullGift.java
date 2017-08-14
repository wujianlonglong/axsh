package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kimiyu on 15/12/22.
 */
@Data
public class FullGift implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 促销ID
     */
    private String saleId;
    /**
     * 会员满
     */
    private BigDecimal memberMoreThan = new BigDecimal(0.00);

    /**
     * 惠用户满
     */
    private BigDecimal benefitUserMoreThan = new BigDecimal(0.00);
    /**
     * 促销上限
     */
    private Integer limitNumber;
    /**
     * 加入的赠品列表
     */
    private List<JoinedGift> joinedGiftList;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 满赠阶梯
     */
    private Integer giftLevel;

}
