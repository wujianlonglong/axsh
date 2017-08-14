package client.api.card.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

/**
 * 会员订单查询
 * Created by kimiyu on 16/12/20.
 */
@Data
@ToString
public class CardOrderAdminDTO implements Serializable {

    private Long orderId;

    private String cardNumber;

    private Integer orderStatus;

    private Date startDateTime;

    private Date endDateTime;

    private String type;

    private int page = 1;

    private int size = 10;
}
