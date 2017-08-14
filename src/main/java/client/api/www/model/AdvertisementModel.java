package client.api.www.model;

import lombok.Data;

import java.util.List;

/**
 * Created by mac on 15/9/17.
 */
@Data
public class AdvertisementModel extends Advertisement {

    private List<AdvertisementDevice> advertisementDevice;

}
