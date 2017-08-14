package client.api.sale.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by kimiyu on 16/1/5.
 */
@Data
public class JoinedVolume implements Serializable {

    @Id
    private String id;

    /**
     * 订单满多少元
     */
    private Double orderMoney;

    /**
     * 订单满减XX元
     */
    private Double reduceMoney;

    /**
     * 现金券
     */
    private Double cash;

    /**
     * 卷数量
     */
    private Integer totalNumber;

    /**
     * 券等级
     */
    private Integer volumeLevel = 0;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 促销类型
     */
    private Integer saleType;

}
