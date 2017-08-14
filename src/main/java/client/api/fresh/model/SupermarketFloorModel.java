package client.api.fresh.model;

import lombok.Data;

import java.util.List;

/**
 * Created by qinhailong on 15-11-13.
 */
@Data
public class SupermarketFloorModel extends SupermarketFloor {

    private List<SupermarketFloorProduct> supermarketFloorProducts;
}
