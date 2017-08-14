package client.api.sale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by kimiyu on 15/9/14.
 */
@Data
public class BaseViewModel implements Serializable {

    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 开始时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime startDate;
    /**
     * 结束时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime endDate;
    /**
     * 使用场景
     */
    private List<Integer> envType;
    /**
     * 会员等级
     */
    private List<Integer> memberLevel;
    private Integer joinGateShopType;   // 参与门店: 1 所有门店;2 部分门店
    private List<JoinedGateShop> gateShops; // 参与的门店
    private String memo;            // 备注
    private Integer limitVolumeNum = 0; // 换购优惠劵数量
    private String telphoneStr;     // 手机号
    private String smsContent;      // 消息发送的内容
    private Long userId;          // 用户ID
}
