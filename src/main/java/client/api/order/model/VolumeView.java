package client.api.order.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by kimiyu on 16/1/20.
 */
@Data
public class VolumeView implements Serializable {
    /**
     * 优惠券类型
     */
    private String volumeType;
    /**
     * 优惠劵金额
     */
    private double volumeMondy;
    /**
     * 优惠券编号
     */
    private String volumeNumber;
}
