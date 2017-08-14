package client.api.www.model;

import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-11-12.
 */
@Data
public class HotGoodsModel extends HotGoods {

    private List<HotGoods> hotGoods;
}
