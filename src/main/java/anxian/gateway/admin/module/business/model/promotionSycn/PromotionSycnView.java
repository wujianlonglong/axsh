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

    private int syncStatus;//1:待同步 2：已同步

    private String promotionDate;

    private Integer status;


//    public String getStatusText() {
//        switch (status) {
//            case 10:
//                return "进行中";
//            case 15:
//                return "强停";
//            case 20:
//                return "已结束";
//            default:
//                return "未开始";
//        }
//    }
}

