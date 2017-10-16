package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class CouponParamDTO implements Serializable {

    private String id;

    private String usedExplanation;

    private String operator;
}
