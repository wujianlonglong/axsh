package anxian.gateway.admin.module.business.controller.order.model;

import client.api.order.model.TrackModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 物流跟踪
 * Created by Jianghe on 16/1/25.
 */
@Data
public class LogisticsTracking implements Serializable {

    private static final long serialVersionUID = 5682018022916050427L;

    /**
     * 配送类型
     */
    private String distributionType;

    /**
     * 所属商场
     */
    private String shopName;

    /**
     * 自提商场
     */
    private String pickShopId;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 手机号
     */
    private String telphone;

    /**
     * 收货地址
     */
    private String sendAddress;

    /**
     * 跟踪记录
     */
    List<TrackRecord> trackRecords;

    /**
     * 物流明细
     */
    List<TrackModel> orderTracks;
}
