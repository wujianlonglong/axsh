package client.api.sale.model.turnTable;

import lombok.Data;

import java.io.Serializable;

/**
 * 奖项
 * Created by kimiyu on 15/9/24.
 */
@Data
public class Prize implements Serializable {

    /**
     * 奖项等级
     */
    private Integer level;
    /**
     * 奖项名称
     */
    private String name;
    /**
     * 奖品数量
     */
    private Integer number;
    /**
     * 奖品类型
     */
    private Integer prizeType;
    /**
     * 优惠券ID
     */
    private String benefitId;
    /**
     * 中奖概率
     */
    private Double prizeRate;
    /**
     * 中奖提示
     */
    private String prizeTip;

    /**
     * 已中奖数量,中奖列表中显示用
     */
    private Integer winNumber = 0;
}
