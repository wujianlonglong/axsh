package client.api.item.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by qinhailong on 16-2-17.
 */
@Data
public class ModifyPriceLog implements Serializable {

    private String id;

    private Long productId; // 商品id

    private BigDecimal price; // 现价格

    private BigDecimal historyPrice; // 历史价格

    private String modifyUserName; // 修改人用户名

    private Integer type; // 价格类型

    private LocalDateTime createDate; // 创建时间

}
