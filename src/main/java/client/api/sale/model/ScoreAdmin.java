package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by kimiyu on 2017/5/10.
 */
@Data
public class ScoreAdmin extends BaseModel implements Serializable {

    // 管理编码
    private Long erpGoodsId;

    // 会员金额
    private BigDecimal memberPrice;

    // 所需积分
    private Long score;

}
