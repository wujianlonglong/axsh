package anxian.gateway.admin.module.business.controller.sale;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.JsonMsg;
import anxian.gateway.admin.utils.editor.DateEditor;
import anxian.gateway.admin.utils.editor.LocalDateEditor;
import anxian.gateway.admin.utils.editor.LocalDateTimeEditor;
import anxian.gateway.admin.utils.editor.LocalTimeEditor;
import client.api.sale.VolumeApiClient;
import client.api.sale.model.*;
import client.api.user.utils.page.SjesPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jianghe on 16/1/16.
 */
@RestController
@RequestMapping(value = "/volume")
public class VolumeController {

    @Autowired
    private VolumeApiClient volumeApiClient;


    /**
     * 显示优惠劵列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public SjesPage<BenefitListView> getList(VolumeCondition volumeCondition, int page, int limit) {
        volumeCondition.setPage(page - 1);
        volumeCondition.setSize(limit);
        SjesPage<BenefitListView> benefitVolumes = volumeApiClient.getList(volumeCondition);
        return benefitVolumes;
    }

    /**
     * 显示积分优惠劵列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/integalList")
    public SjesPage<JoinedVolumeViewModel> getJoinVolumeList(VolumeCondition volumeCondition, int page, int limit) {
        volumeCondition.setPage(page - 1);
        volumeCondition.setSize(limit);
        SjesPage<JoinedVolumeViewModel> joinedVolumeViewModels = volumeApiClient.getJoinVolumeList(volumeCondition);
        return joinedVolumeViewModels;
    }


    /**
     * 保存现金/满减优惠劵信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveCash")
    public JsonMsg saveCashVolume(VolumeViewModel volumeViewModel) {
        AclUser user = UserContextHelper.getUser();

        volumeViewModel.setUserId(user.getId());
        volumeViewModel.setStartDate(volumeViewModel.getGetVolumeStartDate());
        volumeViewModel.setEndDate(volumeViewModel.getGetVolumeEndDate());

        //处理阶梯中的现金券信息
        List<JoinedVolume> joinedVolumes = volumeViewModel.getJoinedVolumes();
        for (JoinedVolume joinedVolume : joinedVolumes) {
            joinedVolume.setUserId(user.getId());
            joinedVolume.setSaleType(volumeViewModel.getSaleType());
            if (null == joinedVolume.getOrderMoney()) {
                joinedVolume.setOrderMoney(0.0);
            }
        }

        if (volumeViewModel.getParticipationMode() == 2) {//是选择了部分商品有列表
            //参与商品处理
            List<JoinedItem> joinedItems = volumeViewModel.getJoinedItems();
            for (JoinedItem joinedItem : joinedItems) {
                joinedItem.setSaleType(volumeViewModel.getSaleType());
                joinedItem.setUserId(user.getId());
                joinedItem.setParticipationMode(volumeViewModel.getParticipationMode());
            }
        }


        if (volumeViewModel.getJoinGateShopType() == 2) {//是选择了部分门店有列表
            List<JoinedGateShop> gateShops = volumeViewModel.getGateShops();
            for (JoinedGateShop gateShop : gateShops) {
                gateShop.setUserId(user.getId());
                gateShop.setSaleType(volumeViewModel.getSaleType());
            }
        }


        ResponseMessage<BenefitVolume> responseMessage = volumeApiClient.saveCashVolume(volumeViewModel);
        if (responseMessage.getCode() == SaleConstant.successCode) {
            return JsonMsg.success("保存成功");
        } else {
            return JsonMsg.failure(responseMessage.getCodeMessage());
        }
    }

    /**
     * 导出前先做判断
     */
    @RequestMapping(method = RequestMethod.GET, value = "/dedrivedJudgment")
    public JsonMsg dedrivedJudgment(String id) {
        AclUser user = UserContextHelper.getUser();
        ResponseMessage<List<VolumeModel>> listResponseMessage = volumeApiClient.exportTxt(id, user.getId());
        if (listResponseMessage.getCode() == SaleConstant.successCode) {
            return JsonMsg.success("ok");
        } else {
            return JsonMsg.success(listResponseMessage.getCodeMessage());
        }
    }


    /**
     * 导出txt文件[微信活动才能导出]
     *
     * @param id 促销ID
     */
    @RequestMapping(method = RequestMethod.GET, value = "/export")
    public ResponseEntity<byte[]> exportTxt(HttpServletRequest httpServletRequest, String id) {
        AclUser user = UserContextHelper.getUser();

        ResponseMessage<List<VolumeModel>> listResponseMessage = volumeApiClient.exportTxt(id, user.getId());

        ResponseEntity<byte[]> responseEntity = null;

        if (listResponseMessage.getCode() == SaleConstant.successCode) {
            try {
                String path = SaleConstant.VOLUMETMP_PATH;
                //文件名为volume+登陆用户名.txt
                String fileName = "volume" + user.getId() + ".txt";
                String destPath = path + fileName;
                ServletContext servletContext = httpServletRequest.getSession(Boolean.TRUE).getServletContext();
                File exportFile = new File(servletContext.getRealPath(destPath));
                // 创建文件夹
                if (!exportFile.getParentFile().exists()) {
                    exportFile.getParentFile().mkdirs();
                }
                // 判断文件是否存在
                if (exportFile.exists()) {
                    exportFile.delete();
                }
                exportFile.createNewFile();
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentDispositionFormData("attachment", "volume.txt");
                List<VolumeModel> volumeModels = listResponseMessage.getData();
                if (CollectionUtils.isNotEmpty(volumeModels)) {
                    FileOutputStream fileOutputStream = new FileOutputStream(exportFile);
                    StringBuilder content = new StringBuilder();
                    for (VolumeModel volumeModel : volumeModels) {
                        String volumeId = volumeModel.getVolumeNumber();
                        content.append("促销卷码:").append(volumeId).append("\r\n");
                        Integer saleType = volumeModel.getSaleType();
                        if (saleType == SaleConstant.cash) {
                            content.append("现金券:").append(volumeModel.getCash()).append("\r\n");
                        } else if (saleType == SaleConstant.fullReduce) {
                            content.append("满减券:订单满").append(volumeModel.getOrderMoney()).append("减").append(volumeModel.getCash()).append("\r\n");
                        }
                        content.append("促销名称:").append(volumeModel.getSaleName()).append("\r\n");

                    }
                    fileOutputStream.write(content.toString().getBytes("UTF-8"));
                    fileOutputStream.close();
                }
                responseEntity = new ResponseEntity<>(FileUtils.readFileToByteArray(exportFile), httpHeaders, HttpStatus.OK);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;

    }


    /**
     * 删除优惠券
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public JsonMsg delBySaleModel(String saleId) {
        ResponseMessage responseMessage = volumeApiClient.delBySaleModel(saleId);
        if (responseMessage.getCode() == SaleConstant.successCode) {
            return JsonMsg.success("删除成功");
        } else {
            return JsonMsg.failure("删除失败");
        }

    }

    /**
     * 根据id获取优惠劵活动
     *
     * @param id 主键
     * @return 返回优惠劵活动那个
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public JsonMsg getById(@PathVariable("id") String id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        BenefitVolume byId = volumeApiClient.getById(id);
        jsonMsg.setData(byId);
        return jsonMsg;
    }

    /**
     * 根据id 梯级获取优惠券活动
     *
     * @param id
     * @param level 梯级
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/{level}")
    public JsonMsg getByIdAndLevel(@PathVariable("id") String id, @PathVariable("level") Integer level) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        BenefitVolume byId = volumeApiClient.getById(id);
        byId.setSaleId(byId.getId());
        byId.setId(null);
        List<JoinedVolume> volumes = byId.getJoinedVolumes();
        if (CollectionUtils.isNotEmpty(volumes)) {
            Optional<JoinedVolume> volumeOptional = volumes.stream().filter(joinedVolume -> joinedVolume.getVolumeLevel().equals(level)).findAny();
            if (volumeOptional.isPresent()) {
                JoinedVolume joinedVolume = volumeOptional.get();
                if (joinedVolume.getSaleType() == SaleConstant.cash) {
                    byId.setJoinVolumeContent("现金券" + joinedVolume.getReduceMoney() + "元");
                } else if (joinedVolume.getSaleType() == SaleConstant.fullReduce) {
                    byId.setJoinVolumeContent("订单满" + joinedVolume.getOrderMoney() + "减" + joinedVolume.getReduceMoney() + "元");
                }
            }
        }

        jsonMsg.setData(byId);
        return jsonMsg;
    }


    /**
     * 更新优惠券活动
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonMsg updateBenefitVolume(String id, String name, String usedExplanation) {
        ResponseMessage<BenefitVolume> benefitVolumeResponseMessage = volumeApiClient.updateBenefitVolume(id, name, usedExplanation);
        if (benefitVolumeResponseMessage.getCode() == SaleConstant.successCode) {
            return JsonMsg.success("修改成功");
        } else {
            return JsonMsg.failure(benefitVolumeResponseMessage.getCodeMessage());
        }

    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
        binder.registerCustomEditor(LocalTime.class, new LocalTimeEditor());
        binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimeEditor());
    }


}
