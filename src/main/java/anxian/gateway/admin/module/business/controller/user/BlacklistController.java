package anxian.gateway.admin.module.business.controller.user;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.user.BlacklistApiClient;
import client.api.user.domain.Blacklist;
import client.api.user.utils.page.PageForAdmin;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoqichao on 16-2-18.
 */
@Controller
@RequestMapping("/anxian/admin_blacklist")
public class BlacklistController extends BaseController{
    private static final Logger log = LoggerFactory.getLogger(BlacklistController.class);

    @Autowired
    private BlacklistApiClient blacklistApiClient;

    @Autowired
    UserService userService;

    /**
     * 分页取得黑名单列表
     *
     * @param page     页码
     * @param limit    每页显示的最大条目
     * @param username 用户名
     * @param mobile   手机号
     * @param email    邮箱
     * @param cardNum  会员卡卡号
     * @return 黑名单分页数据
     */
    @RequestMapping(method = RequestMethod.GET, value = "/page")
    @ResponseBody
    public PageForAdmin<Blacklist> pageList(int page, int limit, String username, String mobile, String email,
                                            String cardNum) {
        return blacklistApiClient.pageGetBlackLists(username, mobile, email, cardNum, page - 1, limit);
    }


    /**
     * 跳转至编辑页
     * @return
     */
    @RequestMapping(method=RequestMethod.GET,value="/turnToEditPage")
    public String turnToEditPage(Long userId,Model model,Principal principal){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);

        if(userId!=0){
            Blacklist blacklist=blacklistApiClient.findByUserId(userId);
            if(blacklist!=null){
                model.addAttribute("id",blacklist.getId());
                model.addAttribute("userId",blacklist.getUserId());
                model.addAttribute("username",blacklist.getUsername());
                model.addAttribute("phone",blacklist.getMobile());
                model.addAttribute("email",blacklist.getEmail());
                model.addAttribute("card",blacklist.getCustNum());
                model.addAttribute("reason",blacklist.getReason());
                model.addAttribute("limittype",blacklist.getLimitType());
            }
        }

        return "anXian-user/edit-blacklist";
    }

    /**
     * 新增黑名单数据
     *
     * @param blacklist 黑名单数据
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public JsonMsg add(Blacklist blacklist, Principal principal) {
        try {
            // 判断当前用户是否已经存在黑名单
            if (blacklistApiClient.findByUserId(blacklist.getUserId()) != null) {
                return JsonMsg.failure("用户已经加入黑名单，不能再次添加!");
            }

            blacklistApiClient.save(blacklist);
//            log.info("{}新增了黑名单：{}", principal.getName(), blacklist);
            log.info("新增了黑名单：{}", blacklist);
            return JsonMsg.success("添加成功");
        } catch (Exception e) {
            log.error("新增黑名单失败", e);
        }

        return JsonMsg.failure("添加黑名单失败");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    @ResponseBody
    public JsonMsg update(Blacklist blacklist, Principal principal) {
        int result = 1;
        try {
            Blacklist oldBlacklist = blacklistApiClient.findById(blacklist.getId());
            blacklistApiClient.save(blacklist);
            //log.info("{}将黑名单由:{}修改为：{}", principal.getName(), oldBlacklist, blacklist);
            log.info("将黑名单由:{}修改为：{}", oldBlacklist, blacklist);
            return JsonMsg.success("修改成功");
        } catch (Exception e) {
            log.error("新增黑名单失败", e);
        }

        return JsonMsg.failure("修改失败");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/batchDelete")
    @ResponseBody
    public JsonMsg delete(String idList, Principal principal) {
        if (StringUtils.isEmpty(idList)) {
            return JsonMsg.failure("id不能为空");
        }
        if (idList.startsWith(",")) {
            idList = idList.substring(1);
            if (StringUtils.isEmpty(idList)) {
                return JsonMsg.failure("id不能为空");
            }
        }

        try {
            List<Long> list = new ArrayList<>();
            String[] arr = idList.split(",");
            for (String id : arr) {
                list.add(Long.parseLong(id));
            }

            List<Blacklist> blacklists = blacklistApiClient.findByIdList(list);
            if (CollectionUtils.isNotEmpty(blacklists)) {
                blacklists.stream()
                        .forEach(blacklist -> {
                            blacklist.setFlag(-1);
//                            log.info("{}删除了黑名单：{}", principal.getName(), blacklist);
                            log.info("删除了黑名单：{}",  blacklist);
                        });

                blacklistApiClient.batchUpdate(blacklists);
            }

            return JsonMsg.success("删除黑名单数据成功");
        } catch (Exception e) {
            log.error("删除黑名单数据失败", e);
        }
        return JsonMsg.failure("删除成功");
    }




}
