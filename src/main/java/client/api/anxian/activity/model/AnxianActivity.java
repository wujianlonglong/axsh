package client.api.anxian.activity.model;

import client.api.sale.model.JoinedGateShop;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangdinglan on 2017/10/16
 */
@Data
public class AnxianActivity implements Serializable {
    /**
     * id
     */
    @Id
    private String id;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 参与门店状态: 1 所有门店;2 部分门店
     */
    private Integer joinGateShopType = 1;

    /**
     * 参与门店列表
     */
    private List<JoinedGateShop> gateShops = new ArrayList<>();

    /**
     * 开始时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    /**
     * 结束时间
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    private String startDateStr;

    private String endDateStr;

    /**
     * 未开始5; 进行中10; 强停15; 已结束20;
     */
    private Integer status = 5;

    /**
     * 页面背景颜色
     */
    private String pageBackgroundColor;

    /**
     * 页面背景图片
     */
    private String pageBackgroundImage;

    /**
     * 页面头图
     */
    private String pageHeaderImage;

    /**
     * 创建日期
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdDate;

    /**
     * 更新日期
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedDate;

    @DBRef
    private List<AnxianActivityCoupon> activityCouponMongos;

    /**
     * 楼层数量
     */
    private Integer floorNum;

    /**
     * 楼层
     */
    private List<ActivityFloor> activityFloors;

}
