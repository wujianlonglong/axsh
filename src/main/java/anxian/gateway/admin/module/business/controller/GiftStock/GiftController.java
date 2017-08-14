package anxian.gateway.admin.module.business.controller.GiftStock;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.business.controller.GiftStock.viewmodel.GiftViewModel;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.gift.GiftApiClient;
import client.api.gift.model.Gift;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by kimiyu on 16/1/11.
 */
@RestController
@RequestMapping(value = "/gifts")
public class GiftController {

    @Autowired
    private GiftApiClient giftApiClient;

    /**
     * 赠品搜索
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public PageModel<Gift> search(String name, String code, int limit, int page) {
        SjesPage<Gift> gifts = giftApiClient.findByNameOrCode(page - 1, limit, name, code);
        return new PageModel<>(gifts.getContent(), gifts.getTotalElements(), new Pageable(page, limit));
    }

    /**
     * 赠品搜索[满赠].
     */
    @RequestMapping(method = RequestMethod.GET, value = "/listForFullGift")
    public PageModel<Gift> searchForFullGift(String name, String code, int limit, int page) {
        SjesPage<Gift> gifts = giftApiClient.findByNameOrCodeForFullGift(page - 1, limit, name, code);
        return new PageModel<>(gifts.getContent(), gifts.getTotalElements(), new Pageable(page, limit));
    }

    /**
     * 保存赠品
     *
     * @param giftViewModel 赠品对象
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMsg save(GiftViewModel giftViewModel) {
        if (giftViewModel != null) {
            String name = giftViewModel.getName();
            String sorce = giftViewModel.getSorce();
            Double price = giftViewModel.getPrice();
            String shelflife = giftViewModel.getShelflife();
            Integer storagenu = giftViewModel.getStoragenu();
            String remarks = giftViewModel.getRemarks();
            if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(sorce)) {
                AclUser aclUser = UserContextHelper.getUser();
                if (aclUser != null) {
                    Gift gift = new Gift();
                    gift.setName(name);
                    gift.setSorce(sorce);
                    gift.setPrice(price);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    gift.setShelflife(LocalDateTime.parse(shelflife, dtf));
                    gift.setStoragenu(storagenu);
                    gift.setRemarks(remarks);
                    gift.setCrdate_people(aclUser.getId());
                    gift.setCrdate_time(LocalDateTime.now());
                    Gift result = giftApiClient.createGift(gift);
                    return JsonMsg.success("保存成功");
                } else {
                    return JsonMsg.failure("保存失败!");
                }
            } else {
                return JsonMsg.failure("参数校验失败!");
            }
        } else {
            return JsonMsg.failure("参数对象传递失败!");
        }
    }

    /**
     * 更新赠品
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonMsg update(GiftViewModel giftViewModel) {
        if (giftViewModel != null) {
            String code = giftViewModel.getCode();
            String name = giftViewModel.getName();
            String sorce = giftViewModel.getSorce();
            Double price = giftViewModel.getPrice();
            Integer locknu = giftViewModel.getLocknu();
            String shelflife = giftViewModel.getShelflife();
            if (!StringUtils.isEmpty(code) && !StringUtils.isEmpty(name)
                    && sorce != null && price != null
                    && locknu != null && !StringUtils.isEmpty(shelflife)) {
                AclUser aclUser = UserContextHelper.getUser();
                if (aclUser != null) {
                    Gift gift = new Gift();
                    gift.setCode(code);
                    gift.setName(name);
                    gift.setPrice(price);
                    gift.setSorce(sorce);
                    gift.setLocknu(locknu);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    gift.setShelflife(LocalDateTime.parse(shelflife, dtf));
                    gift.setRemarks(giftViewModel.getRemarks());
                    gift.setUpdate_people(aclUser.getId());
                    gift.setUpdate_time(LocalDateTime.now());
                    giftApiClient.updateGift(gift);
                    return JsonMsg.success("数据更新成功!");
                } else {
                    return JsonMsg.failure("保存失败!");
                }
            } else {
                return JsonMsg.failure("参数校验失败!");
            }
        } else {
            return JsonMsg.failure("对象传递失败!");
        }

    }


}
