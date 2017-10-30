package anxian.gateway.admin.module.business.controller.webpage;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(value = "/")
//@RequestMapping(value = "/txd")
public class TxdPage extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/txdDailyShopReport", method = RequestMethod.GET)
//    @RequestMapping(value = "/report/txdDailyShopReport", method = RequestMethod.GET)
    public String txdDailyShopReport(Model model, Principal principal) {
//        User user = userService.getByUserName(principal.getName());
//        if (user == null) {
//            return "redirect:/login";
//        }
//
//        getMenus(user, model);
        return "/sales-report/sales-report";
    }
}
