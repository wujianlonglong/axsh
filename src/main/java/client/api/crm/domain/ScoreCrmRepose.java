package client.api.crm.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * Created by gaoqichao on 16-1-4.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ScoreCrmRepose {
    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 提示信息
     */
    private String message;
    /**
     * 积分总额
     */
    private BigDecimal value;
}
