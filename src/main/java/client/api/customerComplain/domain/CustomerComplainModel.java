package client.api.customerComplain.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by byinbo on 2017/3/16.
 */
@Data
public class CustomerComplainModel implements Serializable {

    /**
     * 投诉编号
     */
    private Long id;

    /**
     * 受理部门
     */
    private String receiveDept;

    /**
     * 购买平台
     */
    private String platform;

    /**
     * 购买门店
     */
    private String gateShop;

    /**
     * 客户电话
     */
    private String mobile;

    /**
     * 投诉类型
     */
    private String complainType;

    /**
     * 投诉时间
     */
    private String complainTime;

    /**
     * 处理完成时间
     */
    private String finishTime;

    /**
     * 是否超时
     */
    private String overTime;

    /**
     * 投诉内容
     */
    private String complainContent;

    /**
     * 投诉状态
     */
    private String complainStat;

    /**
     * 定责状态
     */
    private String responStat;

    /**
     * 定责内容
     */
    private String responStr;

    /**
     * 是否会员 1:会员 2：非会员
     */
    private String memberStr;

}
