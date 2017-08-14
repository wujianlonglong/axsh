package anxian.gateway.admin.module.business.model.promotionSycn;

import lombok.Data;

/**
 * Created by byinbo on 2016/12/20.
 */
@Data
public class PromotionSycnView {
    private String id;

    private String promotionId;

    private String promotionName;

    private String promotionType;

    private int syncStatus;

    private String promotionDate;
}
