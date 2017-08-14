package client.api.sale.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by kimiyu on 2017/5/12.
 */
@Data
public class ScoreSearch {

    private LocalDateTime beginDateTime;

    private LocalDateTime endDateTime;

    private Long erpGoodsId;

    private Integer status;

    private Integer page = 0;

    private Integer size = 10;
}
