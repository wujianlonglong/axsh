package client.api.sale.model.turnTable;

import client.api.sale.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 大转盘对象
 * Created by kimiyu on 15/9/24.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TurnTable extends BaseModel implements Serializable {

    /**
     * 抽奖次数达到几个
     */
    private Integer usedTotalNumber = 0;
    /**
     * 会员免费总抽奖次数
     */
    private Integer freeMemberTotalNumber = 0;
    /**
     * 惠用户免费总抽奖次数
     */
    private Integer freeUserTotalNumber = 0;
    /**
     * 会员免费每天抽奖次数
     */
    private Integer freeMemberDayNumber = 0;
    /**
     * 惠用户免费每天抽奖次数
     */
    private Integer freeUserDayNumber = 0;
    /**
     * 消耗积分
     */
    private Integer payIntegral = 0;
    /**
     * 用户积分兑换获得抽奖次数
     */
    private Integer exchangeNumber = 0;
    /**
     * 妥投单满多少金额抽一次
     */
    private Double amount;
    /**
     * 妥投单总共抽奖次数
     */
    private Integer totalAmountNumber = 0;
    /**
     * 奖项列表
     */
    private List<Prize> prizeList;

    /**
     * 中奖情况
     */
    private List<Prize> drawPrizeList;

    /**
     * 领取地点
     */
    private String getAddress;
    /**
     * 领取时间
     */
    private String getDateTime;
    /**
     * 领取条件
     */
    private String neckLine;


    /**
     * 列表中显示有效日期
     */
    public String getEffectiveDate() {
        return !getExtViewStartDate().equals("") ? getExtViewStartDate() + "至" + getExtViewEndDate() : "";
    }

    /**
     * ExtJs中修改页面显示用
     *
     * @return
     */
    public String getExtViewStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getStartDate() != null ? formatter.format(getStartDate()) : "";
    }

    /**
     * ExtJs中修改页面显示用
     *
     * @return
     */
    public String getExtViewEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getEndDate() != null ? formatter.format(getEndDate()) : "";
    }

}
