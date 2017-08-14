package client.api.item.domain;

import lombok.Data;

/**
 * Created by qinhailong on 16-2-17.
 */
@Data
public class ProductPrice extends Product {

    private ModifyPriceLog saleModifyPriceLog; // 销售价修改日志

    private ModifyPriceLog memberModifyPriceLog;  // 会员价修改日志
}
