package anxian.gateway.admin.module.business.controller.sale;


import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import client.api.sale.SecKillSyncApiClient;
import client.api.sale.model.secKill.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping(value="/admin/secKillSycn")
public class SecKillSyncController  extends BaseController{
    private static final Logger log= LoggerFactory.getLogger(SecKillSyncController.class);

    @Autowired
    private SecKillSyncApiClient secKillSyncApiClient;

    @Autowired
    UserService userService;


    /**
     * 保存促销秒杀信息
     *
     * @param secKillParamDTO 秒杀对象
     * @return 保存成功
     */
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ResponseBody
    public ResponseMessage save(@RequestBody SecKillParamDTO secKillParamDTO){
        return secKillSyncApiClient.save(secKillParamDTO);
    }


    /**
     * 促销管理
     *
     * @param saleManageCondition 促销管理查询条件
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<SjesPage<SecKillMongo>> list(@RequestBody SaleManageCondition saleManageCondition){
        saleManageCondition.setPage(saleManageCondition.getPage()-1);
        ResponseMessage<SjesPage<SecKillMongo>> result=secKillSyncApiClient.list(saleManageCondition);
        return result;
    }


    /**
     * 根据ID获取促销信息
     *
     * @param id 促销ID
     * @return 促销对象
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public SecKillMongo get(@PathVariable("id") String id){
        return secKillSyncApiClient.get(id);
    }


    @RequestMapping(value="/turnToEdit/{id}")
    public String turnToEdit(@PathVariable("id") String id,Model model,Principal principal){
        model.addAttribute("id",id);
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "anXian-promotion/edit-seckill";
    }

}
