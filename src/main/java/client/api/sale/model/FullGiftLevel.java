package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by kimiyu on 15/11/6.
 */
@Data
public class FullGiftLevel implements Serializable {

    /**
     * 会员满
     */
    private Double memberMoreThan = 0.00;

    /**
     * 惠用户满
     */
    private Double benefitUserMoreThan = 0.00;
}
