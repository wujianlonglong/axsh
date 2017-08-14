package client.api.sale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 优惠劵对象
 * Created by kimiyu on 15/9/9.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BenefitVolume extends BaseModel implements Serializable {

    /**
     * 优惠阶梯层级
     */
    private Integer benefitLevel = 0;
    /**
     * 参与的优惠券
     */
    private List<JoinedVolume> joinedVolumes;
    private Integer participationMode; // 参与方式
    private List<JoinedCategory> joinedCategory;    // 商品分类
    private List<JoinedItem> joinedItem;   // 参与活动商品
    private List<JoinedBrand> joinedBrands;       // 参与活动的品牌ID
    private String usedExplanation;     // 使用说明
    private Integer getVolumeType;      // 领卷方式

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime getVolumeStartDate;    // 领卷开始日期

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime getVolumeEndDate;      // 领卷结束日期

    private Integer limitGetTotalVolume;// 用户总共限领数量
    private Integer limitDayTotalNumber;    // 用户每天限领总数
    private Integer perDayTotalExchangeAmount;  // 用户每人每天限兑换张数
    private Integer dayGetTotalVolume;  // 每天每阶梯限几张

    private String joinVolumeContent; // 参与的梯级优惠券内容
    private String saleId; //优惠券id

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime dayGetStartDate;       // 每天开始时间

    public String getExtViewStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getStartDate() != null ? formatter.format(getStartDate()) : "";
    }

    public String getExtViewEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getEndDate() != null ? formatter.format(getEndDate()) : "";
    }


}
