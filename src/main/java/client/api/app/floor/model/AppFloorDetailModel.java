package client.api.app.floor.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by byinbo on 2016/11/30.
 */
@Data
public class AppFloorDetailModel extends AppFloorModel implements Serializable {

    private List<FloorContentModel> floorContents; // 楼层内容
}
