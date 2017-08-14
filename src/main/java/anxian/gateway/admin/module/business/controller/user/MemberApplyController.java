package anxian.gateway.admin.module.business.controller.user;

import anxian.gateway.admin.module.business.service.MemberApplyService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.user.domain.MemberApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 16-11-7.
 */
@RestController
@RequestMapping("memberApplys")
public class MemberApplyController {

    @Autowired
    private MemberApplyService memberApplyService;

    /**
     * 分页查询会员申请列表
     *
     * @param memberApply 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search")
    public PageModel<MemberApply> search(MemberApply memberApply, int page, int limit) {
        return memberApplyService.search(memberApply, page, limit);
    }

    /**
     * 根据指定id查询会员申请
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public MemberApply findById(@PathVariable("id") Long id) {
        return memberApplyService.findById(id);
    }

    /**
     * 审核会员申请信息
     *
     * @param memberApply 更新会员申请信息
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg audit(Authentication authentication, MemberApply memberApply) {
        memberApply.setVerifier(authentication.getName());
        int retVal = memberApplyService.update(memberApply);
        return retVal > 0 ? JsonMsg.success("审核会员申请成功！") : JsonMsg.failure("审核会员申请失败！");
    }

}
