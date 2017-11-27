package anxian.gateway.admin.module.business.service;

import client.api.item.AnXianItemPriceFeign;
import client.api.item.domain.ItemPriceShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qinhailong on 16-11-1.
 */
@Service
public class AnXianProductService {

    @Autowired
    private AnXianItemPriceFeign anXianItemPriceFeign;

    public List<ItemPriceShow> getItemPriceShowList(Long erpGoodsId) {
        return anXianItemPriceFeign.getItemPriceShowList(erpGoodsId);
    }


}
