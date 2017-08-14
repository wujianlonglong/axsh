package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kimiyu on 16/1/20.
 */
@Data
public class PayDetail implements Serializable {
    /**
     * 支付类型
     */
    private String payType;
    /**
     * 支付来源
     */
    private String paySource;
    /**
     * 支付平台
     */
    private String platForm;
    /**
     * 支付金额
     */
    private BigDecimal actualAmount;
    /**
     * 现金券列表
     */
    private List<VolumeView> volumeViews;
}
