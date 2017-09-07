package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class EpbVolume implements Serializable {

    /**
     * 订单满
     */
    private BigDecimal orderMoney;

    /**
     * 减
     */
    private BigDecimal reduceMoney;

    /**
     * 券总量
     */
    private Integer totalNumber;

    /**
     * 券阶梯
     */
    private Integer couponLevel;

}
