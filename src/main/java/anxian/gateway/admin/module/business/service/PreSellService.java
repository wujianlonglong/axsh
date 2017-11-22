package anxian.gateway.admin.module.business.service;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.sale.PreSellApiClient;
import client.api.sale.model.SaleConstant;
import client.api.sale.model.SaleManageCondition;
import client.api.sale.model.preSell.PreSell;
import client.api.sale.util.SaleResponseMessage;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by kimiyu on 2017/2/22.
 */
@Service
public class PreSellService {

    @Autowired
    private PreSellApiClient preSellApiClient;

    /**
     * 每天标记为0
     */
    private static final int everyDay = 0;

    /**
     * 保存预售信息
     * @param preSell
     * @return
     */
    public JsonMsg save(PreSell preSell) {
        if (StringUtils.isEmpty(preSell.getId())) {
            preSell.setCreatedDate(LocalDateTime.now());
        } else {
            SaleResponseMessage<PreSell> saleResponseMessage = preSellApiClient.getById(preSell.getId());
            if (saleResponseMessage.getCode() == SaleConstant.successCode) {
                preSell.setCreatedDate(saleResponseMessage.getData().getCreatedDate());
            }
            preSell.setUpdatedDate(LocalDateTime.now());
        }
        preSell.setStartDate(LocalDateTime.parse(preSell.getStartDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        preSell.setEndDate(LocalDateTime.parse(preSell.getEndDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        List<Integer> deliveryWeekDays = preSell.getDeliveryWeekDays();
        if (deliveryWeekDays.size() == 1
                && deliveryWeekDays.stream().anyMatch(deliveryWeekDay -> deliveryWeekDay.compareTo(everyDay) == 0)) {
            preSell.setDeliveryEveryDay(true);
        }
        if (LocalDateTime.now().compareTo(preSell.getStartDate()) >= 0 &&
                LocalDateTime.now().compareTo(preSell.getEndDate()) <= 0)  {
            preSell.setStatus(SaleConstant.doing);
        } else if (LocalDateTime.now().compareTo(preSell.getStartDate()) < 0) {
            preSell.setStatus(SaleConstant.notBegin);
        } else if (LocalDateTime.now().compareTo(preSell.getEndDate()) > 0) {
            preSell.setStatus(SaleConstant.stop);
        }
        // 预售促销类型
        preSell.setSaleType(SaleConstant.preSell);
        SaleResponseMessage<PreSell> saleResponseMessage = preSellApiClient.savePreSell(preSell);
        return saleResponseMessage.getCode() == SaleConstant.successCode ?
                JsonMsg.success("保存成功") : JsonMsg.failure(saleResponseMessage.getMessage());
    }

    /**
     * 根据id获取促销信息
     *
     * @param id
     * @return
     */
    public JsonMsg getOne(String id) {
        SaleResponseMessage<PreSell> saleResponseMessage = preSellApiClient.getById(id);
        return saleResponseMessage.getCode() == SaleConstant.successCode ?
                JsonMsg.success(saleResponseMessage.getData()) : JsonMsg.failure(saleResponseMessage.getMessage());
    }

    public SjesPage<PreSell> listBySearch(SaleManageCondition saleManageCondition) {
        if (saleManageCondition.getPromotionStatus().compareTo(0) == 0) {
            saleManageCondition.setPromotionStatus(null);
        }
        if (!StringUtils.isEmpty(saleManageCondition.getStartDateStr())){
            saleManageCondition.setPromotionStartDate(LocalDateTime.parse(saleManageCondition.getStartDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (!StringUtils.isEmpty(saleManageCondition.getEndDateStr())){
            saleManageCondition.setPromotionEndDate(LocalDateTime.parse(saleManageCondition.getEndDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return preSellApiClient.list(saleManageCondition);
    }

    public JsonMsg deleteById(String id) {
        SaleResponseMessage saleResponseMessage = preSellApiClient.deleteById(id);
        return saleResponseMessage.getCode() == SaleConstant.successCode ?
                JsonMsg.success("删除成功"):
                JsonMsg.failure(saleResponseMessage.getMessage());
    }

    /**
     * 强停
     * @param id
     * @param reason
     * @return
     */
    public JsonMsg manualStop(String id, String reason) {
        SaleResponseMessage<PreSell> responseMessage = preSellApiClient.getById(id);
        if (responseMessage.getCode() == SaleConstant.successCode){
            PreSell pre = responseMessage.getData();
            pre.setPromotionStopReason(reason);
            SaleResponseMessage<PreSell> saleResponseMessage = preSellApiClient.manualStop(pre);
            return saleResponseMessage.getCode() == SaleConstant.successCode ?
                    JsonMsg.success("强停成功") :
                    JsonMsg.failure(saleResponseMessage.getMessage());
        }
        return JsonMsg.failure("未找到预售活动");
    }
}
