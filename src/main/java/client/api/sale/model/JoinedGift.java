package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 参与的赠品对象
 * Created by kimiyu on 15/9/6.
 */
@Data
public class JoinedGift implements Serializable {
    private String id;
    /**
     * 添加人ID
     */
    private Long userId;
    /**
     * 赠品编码
     */
    private String giftCode;
    /**
     * 赠品名称
     */
    private String giftName;
    /**
     * 赠品总量
     */
    private Integer giftAmount;
    /**
     * 每单赠送数量
     */
    private Integer sendNumber;
    /**
     * 赠品状态
     */
    private Integer giftStatus;
    /**
     * 赠品层级
     */
    private Integer giftLevel;
}
