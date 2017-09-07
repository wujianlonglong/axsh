package anxian.gateway.admin.module.business.controller.anxian.shop;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.anxian.AXGateShopClient;
import client.api.anxian.shop.AXGateShop;
import client.api.anxian.shop.AXGateShopSearch;
import client.api.sale.model.ResponseMessage;
import client.api.user.utils.page.SjesPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/anxian/shop")
public class AXGateShopController {

    @Autowired
    private AXGateShopClient axGateShopClient;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    public String  pageList(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int size,
            @RequestParam(name = "shopName", required = false) String shopName,
            @RequestParam(name = "shopId", required = false) String shopId,
            @RequestParam(name = "state", required = false) Integer state,
            @RequestParam(value = "flag", required = false) String flag, Model model) {

        AXGateShopSearch gateShopSearch = new AXGateShopSearch();

        gateShopSearch.setPage(page);

        if (StringUtils.isNotEmpty(shopId)) {
            gateShopSearch.setShopId(shopId);
        }

        if (StringUtils.isNotEmpty(shopName)) {
            gateShopSearch.setShopName(shopName);
        }
        if (state != null) {
            gateShopSearch.setState(state);
        }

        ResponseMessage<SjesPage<AXGateShop>> responseMessage = axGateShopClient.getShopList(gateShopSearch);
        SjesPage<AXGateShop> data = responseMessage.getData();

        model.addAttribute("pageNum",page);
        model.addAttribute("isFirstPage",data.isFirst());
        model.addAttribute("pageSize",data.getNumberOfElements());
        model.addAttribute("totalCount",data.getTotalElements());
        model.addAttribute("totalPage",data.getTotalPages());
        model.addAttribute("shops",data.getContent());
        model.addAttribute("isLastPage", data.isLast());
        model.addAttribute("gateShop",data);
        if (flag == null) {
            return "anXian-platform/store";
        } else {
            return "anXian-platform/store-ajax";
        }

    }

    @RequestMapping(value = "/shopDetail", method = RequestMethod.GET)
    public String getShopDetail(String shopId, Model model) {
        AXGateShop axGateShop = axGateShopClient.getShopDetail(shopId);
        model.addAttribute("detail",axGateShop);
        return "anXian-platform/edit-store";
    }

    @RequestMapping(value = "/updateShop", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg updateShopInfo(@RequestBody AXGateShop axGateShop) {
        try {
            ResponseMessage responseMessage = axGateShopClient.updateShop(axGateShop);
            if (responseMessage.getCode() == 1) {
                return JsonMsg.success("修改成功");
            } else {
                return JsonMsg.failure(responseMessage.getCodeMessage());
            }
        } catch (Exception e) {
            return JsonMsg.failure("网络错误");
        }
    }
}
