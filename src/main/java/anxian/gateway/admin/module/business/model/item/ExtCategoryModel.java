package anxian.gateway.admin.module.business.model.item;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类Model,存放处理过后的格式
 * Created by jiangzhe on 15-12-4.
 */
@Data
public class ExtCategoryModel {
    private String id;

    private String text;

    private Integer type;

    private Integer grade; // 级别

    List<ExtCategoryModel> items = new ArrayList<>();
}
