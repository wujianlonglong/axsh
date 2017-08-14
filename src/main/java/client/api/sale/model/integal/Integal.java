package client.api.sale.model.integal;

import client.api.sale.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 积分对象
 * Created by kimiyu on 15/9/14.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Integal extends BaseModel implements Serializable {

    private Integer limitTradeVolume;   // 换卷次数上限
    private Integer integalScore;   // 积分
    private List<String> benefitVolumes;    // 优惠劵码

    public String getEffectiveDate() {
        String effectiveDate = null;
        if (getStartDate() != null && getEndDate() != null) {
            effectiveDate = getStartDate().toString().split("T")[0] + "至" + getEndDate().toString().split("T")[0];
        }
        return effectiveDate;
    }

}
