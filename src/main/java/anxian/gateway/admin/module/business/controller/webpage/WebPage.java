package anxian.gateway.admin.module.business.controller.webpage;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/anXian")
public class WebPage {


    @RequestMapping(value="/good/goodsUpdown",method= RequestMethod.GET)
    public String goodsUpdown(){
        return "/anXian-goods/up-down-shelves";
    }


    @RequestMapping(value="/good/goodsTimeUpdown",method= RequestMethod.GET)
    public String goodsTimeUpdown(){
        return "/anXian-goods/timer-up-down-shelves";
    }


    @RequestMapping(value="/user/userList",method= RequestMethod.GET)
    public String userList(){
        return "/anXian-user/user";
    }

    @RequestMapping(value="/user/blackList",method= RequestMethod.GET)
    public String blackList(){
        return "/anXian-user/blacklist";
    }

    @RequestMapping(value="/pay/payList",method= RequestMethod.GET)
    public String payList(){
        return "/pay/pay";
    }

    @RequestMapping(value="/promotion/sync",method=RequestMethod.GET)
    public String syncList(){return "/anXian-promotion/sync.html";}

    @RequestMapping(value="/promotion/seckill",method=RequestMethod.GET)
    public String seckillList(){return "/anXian-promotion/seckill.html";}


}
