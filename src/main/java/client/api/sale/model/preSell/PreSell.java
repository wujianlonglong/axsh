package client.api.sale.model.preSell;

import client.api.sale.model.BaseModel;
import client.api.sale.model.JoinedItem;
import client.api.sale.model.SaleConstant;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 商品预售模型
 * Created by kimiyu on 2017/2/20.
 */
@Data
@ToString
public class PreSell extends BaseModel implements Serializable {

    /**
     * 配送周期
     */
    private Integer deliveryCycle;

    /**
     * 每天配送
     */
    private boolean deliveryEveryDay;

    /**
     * 每周配送日期
     */
    private List<Integer> deliveryWeekDays;

    /**
     * 强停理由
     */
    private String promotionStopReason;

    /**
     * 商品爆炸贴
     */
    private String itemDetonation;
    /**
     * 参与的商品
     */
    private List<JoinedItem> joinedItems;

    /**
     * 大库到门店时间
     */
    private Integer stockToShopTime = 1;

    private String startDateStr;

    private String endDateStr;

    public Integer getJoinedItemType() {
        return 2;
    }

    /**
     * 页面显示用
     */
    public String getViewStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return getStartDate() != null ? formatter.format(getStartDate()) : "";
    }

    /**
     * ExtJs中修改页面显示用
     */
    public String getViewEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return getEndDate() != null ? formatter.format(getEndDate()) : "";
    }

    public String getMemberLevelStr() {
        StringBuilder build = new StringBuilder();
        if (getMemberLevel().contains(SaleConstant.member)){
            build.append("三江会员");
        }
        if (getMemberLevel().contains(SaleConstant.benefitMember)) {
            build.append("  三江惠用户");
        }
        return build.toString();
    }
}
