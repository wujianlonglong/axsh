package client.api.www.feign;

import client.api.constants.Constants;
import client.api.www.model.FloorAdvertisement;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by mac on 15/9/23.
 */
@FeignClient(Constants.SJES_API_WWW)
@RequestMapping("floorAdvertisements")
public interface FloorAdvertisementFeign {

    /**
     * 新增楼层广告
     *
     * @param floorAdvertisement 楼层广告
     * @return 楼层广告
     */
//    @PreAuthorize("hasAuthority('FLOOR_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    FloorAdvertisement save(FloorAdvertisement floorAdvertisement);

    /**
     * 更新楼层广告信息
     *
     * @param floorAdvertisement 楼层广告
     * @return 修改数目
     */
//    @PreAuthorize("hasAuthority('FLOOR_SAVE')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(FloorAdvertisement floorAdvertisement);

    /**
     * 更具id删除指定的楼层广告信息
     *
     * @param id 主键
     */
//    @PreAuthorize("hasAuthority('FLOOR_DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);
}
