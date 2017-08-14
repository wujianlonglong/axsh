package client.api.sale.model.integal;

import client.api.sale.model.BaseViewModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kimiyu on 15/9/14.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IntegalViewModel extends BaseViewModel implements Serializable {
    private Integer limitTradeVolume;   // 换卷次数上限
    private Integer integalScore;   // 积分
    private List<String> benefitVolumes;    // 优惠劵码
}
