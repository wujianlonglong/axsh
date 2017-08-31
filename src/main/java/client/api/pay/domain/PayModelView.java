package client.api.pay.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by kimiyu on 16/7/8.
 */
@Data
public class PayModelView implements Serializable {

    private Long orderId;

    private BigDecimal actualAmount;

    private String payDate;

    private String shopId;

    private String platForm;

    private String payMethod;

    private String platformId;
}
