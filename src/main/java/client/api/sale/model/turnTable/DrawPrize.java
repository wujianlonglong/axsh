package client.api.sale.model.turnTable;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户中奖模型
 * Created by kimiyu on 15/9/27.
 */
@Data
public class DrawPrize {

    private String id;
    /**
     * 大转盘ID
     */
    private String turnTableId;
    /**
     * 奖品ID
     */
    private String prizeId;
    /**
     * 奖品名称
     */
    private String prizeName;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 中奖时间
     */
    private LocalDateTime drawDate;
}
