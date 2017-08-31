package anxian.gateway.admin.module.business.controller.anxian.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping(value="tt")
    public String test(Model model){
        model.addAttribute("power","1000");
        return "anXian-goods/up-down-shelves";
    }
}
