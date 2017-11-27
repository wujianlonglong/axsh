package client.api.item.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 给安鲜后台展示
 */
@Data
public class ItemPriceShow implements Serializable {

    private Long id; // 主键

    private Long erpGoodsId; // 对应ERP GoodsID

    private String shopId; // 门店id

    private BigDecimal salePrice; // 销售价

    private BigDecimal originalSalePrice; // 原销售价

    private BigDecimal memberPrice; // 会员价

    private BigDecimal originalMemberPrice; // 原会员价

    private String createDate; // 创建时间

    private String updateDate; // 更新时间

    private Integer status;

    private Integer erpStatus;

    private String remark;

    private String statusUpdateDate;

    private String shopName;

    public String getOriginalMemberPrice() {
        if (originalMemberPrice == null) {
            return "";
        } else {
            return originalMemberPrice.toString();
        }
    }

    public String getStatusMean() {
        String mean = "";
        if (status == null) {
            return mean = "未知";
        }
        if (status == 0) {
            return mean = "正常销售";
        }
        if (status == 1) {
            return mean = "下架停售";
        }
        if (status == 2) {
            return mean = "未审核";
        }
        return mean;
    }

}
