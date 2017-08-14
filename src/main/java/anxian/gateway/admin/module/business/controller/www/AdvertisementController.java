package anxian.gateway.admin.module.business.controller.www;

import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.module.business.model.www.AdvertisementPosition;
import anxian.gateway.admin.module.business.model.www.FocusMapModel;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.www.constants.AdvertisementPositionEnum;
import client.api.www.feign.AdvertisementFeign;
import client.api.www.model.Advertisement;
import client.api.www.model.AdvertisementDevice;
import client.api.www.model.AdvertisementModel;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 焦点图维护
 * Created by qinhailong on 15-12-30.
 */
@RestController
@RequestMapping("advertisement")
public class AdvertisementController extends BaseController {

    @Autowired
    private AdvertisementFeign advertisementFeign;

    /**
     * 保存焦点图
     *
     * @param advertisementModel 焦点图
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg saveAdvertisementModel(AdvertisementModel advertisementModel) {
        List<AdvertisementDevice> advertisementDevices = advertisementModel.getAdvertisementDevice();
        if (CollectionUtils.isNotEmpty(advertisementDevices)) {
            List<AdvertisementDevice> advertisementDevice = Lists.newArrayList();
            for (AdvertisementDevice device : advertisementModel.getAdvertisementDevice()) {
                if (device.getImageUrl() != null) {
                    advertisementDevice.add(device);
                }
            }
            advertisementModel.setAdvertisementDevice(advertisementDevice);
            advertisementFeign.saveAdvertisementModel(advertisementModel);
            return JsonMsg.success("保存成功");
        }
        return JsonMsg.failure("请选择焦点图范围");
    }

    /**
     * 焦点图列表
     *
     * @param advertisement 查询条件
     * @return 分页信息
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public PageModel<FocusMapModel> search(Advertisement advertisement, int page, int limit) {
        SearchCoditionModel<Advertisement> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setSearchCodition(advertisement);
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);

        List<FocusMapModel> focusMapModels = Lists.newArrayList();
        PageModel<AdvertisementModel> search = advertisementFeign.search(searchCoditionModel);
        List<AdvertisementModel> content = search.getContent();
        LocalDateTime now = LocalDateTime.now();
        for (AdvertisementModel advertisementModel : content) {
            FocusMapModel focusMapModel = new FocusMapModel();
            BeanUtils.copyProperties(advertisementModel, focusMapModel);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startDate = advertisementModel.getStartDate();
            LocalDateTime endDate = advertisementModel.getEndDate();
            focusMapModel.setStartDate(startDate != null ? formatter.format(startDate) : "");
            focusMapModel.setEndDate(endDate != null ? formatter.format(endDate) : "");
            focusMapModel.setStatus(endDate.isAfter(now) ? focusMapModel.getStatus() : 2);
            for (AdvertisementDevice advertisementDevice : advertisementModel.getAdvertisementDevice()) {
                if (advertisementDevice.getDeviceId() == 0L) {//网站
                    focusMapModel.setPcImg(advertisementDevice.getImageUrl());
                    focusMapModel.setPcHref(advertisementDevice.getHrefUrl());
                    focusMapModel.setPcOrders(advertisementModel.getOrders());
                }

                if (advertisementDevice.getDeviceId() == 1L && advertisementModel.getPosition().intValue() != AdvertisementPositionEnum.app.getValue()) {//APP
                    focusMapModel.setAppImg(advertisementDevice.getImageUrl());
                    focusMapModel.setAppHref(advertisementDevice.getHrefUrl());
                    focusMapModel.setAppOrders(advertisementModel.getOrders());
                }

                if (advertisementDevice.getDeviceId() == 2L) {//微商城
                    focusMapModel.setWImg(advertisementDevice.getImageUrl());
                    focusMapModel.setWHref(advertisementDevice.getHrefUrl());
                    focusMapModel.setWOrders(advertisementModel.getOrders());
                }

                if (advertisementDevice.getDeviceId() == 1L && advertisementModel.getPosition().intValue() == AdvertisementPositionEnum.app.getValue()) {//APP促销
                    focusMapModel.setAppPromotionImg(advertisementDevice.getImageUrl());
                    focusMapModel.setAppPromotionHref(advertisementDevice.getHrefUrl());
                    focusMapModel.setAppPromotionOrders(advertisementModel.getOrders());
                }

            }

            focusMapModels.add(focusMapModel);
        }
        return new PageModel<>(focusMapModels, search.getTotal(), search.getPageable());
    }

    /**
     * 根据Id得到焦点图
     *
     * @param id 主键
     * @return 焦点图
     */
    @RequestMapping(method = RequestMethod.GET)
    public JsonMsg getAdvertisementModel(Long id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setData(advertisementFeign.getAdvertisementModel(id));
        return jsonMsg;
    }

    /**
     * 更新焦点图
     *
     * @param advertisementModel 焦点图
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg updateAdvertisementModel(AdvertisementModel advertisementModel) {
        List<AdvertisementDevice> advertisementDevice = Lists.newArrayList();
        for (AdvertisementDevice device : advertisementModel.getAdvertisementDevice()) {
            if (device.getImageUrl() != null) {
                device.setAdvertisementId(advertisementModel.getId());
                advertisementDevice.add(device);
            }
        }
        advertisementModel.setAdvertisementDevice(advertisementDevice);
        advertisementFeign.updateAdvertisementModel(advertisementModel);
        return JsonMsg.success("修改成功");
    }

    /**
     * 删除指定id的焦点图
     *
     * @param id 主键id
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public JsonMsg delete(Long id) {
        advertisementFeign.delete(id);
        return JsonMsg.success("删除成功");
    }

    /**
     * 获取焦点图区域枚举数据
     *
     * @return
     */
    @RequestMapping("advertisementPositionEnumList")
    public List<AdvertisementPosition> getAdvertisementPositionEnumList() {

        AdvertisementPosition top = new AdvertisementPosition(AdvertisementPositionEnum.top.getValue(), AdvertisementPositionEnum.top.getName());

        AdvertisementPosition half = new AdvertisementPosition(AdvertisementPositionEnum.half.getValue(), AdvertisementPositionEnum.half.getName());

        AdvertisementPosition header = new AdvertisementPosition(AdvertisementPositionEnum.header.getValue(), AdvertisementPositionEnum.header.getName());

        AdvertisementPosition middle = new AdvertisementPosition(AdvertisementPositionEnum.middle.getValue(), AdvertisementPositionEnum.middle.getName());

        AdvertisementPosition right = new AdvertisementPosition(AdvertisementPositionEnum.right.getValue(), AdvertisementPositionEnum.right.getName());

        AdvertisementPosition recommended = new AdvertisementPosition(AdvertisementPositionEnum.recommended.getValue(), AdvertisementPositionEnum.recommended.getName());

        AdvertisementPosition app = new AdvertisementPosition(AdvertisementPositionEnum.app.getValue(), AdvertisementPositionEnum.app.getName());

        return Lists.newArrayList(top, half, header, middle, right, recommended, app);
    }


}
