package client.api.www.feign;

import client.api.constants.Constants;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.www.model.Advertisement;
import client.api.www.model.AdvertisementModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mac on 15/9/17.
 */
@FeignClient(Constants.SJES_API_WWW)
@RequestMapping("advertisements")
public interface AdvertisementFeign {

    /**
     * 保存焦点图
     *
     * @param advertisementModel 焦点图
     */
//    @PreAuthorize("hasAuthority('FOCUSMAP_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveAdvertisementModel(AdvertisementModel advertisementModel);

    /**
     * 更新焦点图
     *
     * @param advertisementModel 焦点图
     */
//    @PreAuthorize("hasAuthority('FOCUSMAP_SAVE')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateAdvertisementModel(AdvertisementModel advertisementModel);

    /**
     * 焦点图列表
     *
     * @param searchCoditionModel 查询条件
     * @return 分页信息
     */
    @RequestMapping(value = "search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<AdvertisementModel> search(SearchCoditionModel<Advertisement> searchCoditionModel);

    /**
     * 根据Id得到焦点图
     *
     * @param id 主键
     * @return 焦点图
     */
    @RequestMapping(method = RequestMethod.GET)
    AdvertisementModel getAdvertisementModel(@RequestParam("id") Long id);

    /**
     * 删除指定id的焦点图
     *
     * @param id 主键id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);

    /**
     * 判断指定位置position和设备deviceId下是否已经存在焦点图
     *
     * @param position 焦点图位置
     * @param deviceId 设备Id
     * @return 是否存在
     */
    @RequestMapping(value = "isExist", method = RequestMethod.GET)
    Boolean isExist(@RequestParam("position") Integer position, @RequestParam("deviceId") Integer deviceId);

}
