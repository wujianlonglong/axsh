package client.api.customerComplain.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by byinbo on 2017/3/16.
 */
@Data
public class CustomerComplainWxModel implements Serializable {

    /**
     * 投诉编号
     */
    private Long id;

    /**
     * 受理部门
     */
    private String receiveDept;

    /**
     * 受理部门编号
     */
    private String receiveDeptNum;

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
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime complainTime;

    private String complainTimeStr;

    /**
     * 受理时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime acceptTime;

    private String acceptTimeStr;

    /**
     * 投诉内容
     */
    private String complainContent;

    /**
     * 投诉状态
     */
    private int complainStat;

    /**
     * 照片集
     */
    private String imagePaths;

    private List<Image> imagePathLists;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 是否会员 1:会员 2：非会员
     */
    private int member;

}
