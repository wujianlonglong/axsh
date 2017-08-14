package client.api.www.model;

import lombok.Data;

/**
 * Created by mac on 15/9/23.
 */
@Data
public class FloorModel extends Floor {

    private String leftCategoryNames; // 左侧分类

    private String leftKeywordNames; // 左侧关键字

}
