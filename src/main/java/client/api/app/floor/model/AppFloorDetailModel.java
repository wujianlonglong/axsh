package client.api.app.floor.model;

import client.api.customerComplain.domain.ShopInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by byinbo on 2016/11/30.
 */
@Data
public class AppFloorDetailModel extends AppFloorModel implements Serializable {

    private List<FloorContentModel> floorContents; // 楼层内容

    private List<ShopInfo> shopList=new ArrayList<>();
}
