package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.model.ResponseMessage;
import anxian.gateway.admin.module.base.service.AdminUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 用户列表
     *
     * @param userParamDTO
     * @return
     */
    @RequestMapping(value = "/admin/users/list", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage<Page<User>> list(@RequestBody UserParamDTO userParamDTO) {
        if (null == userParamDTO) {
            return ResponseMessage.defaultFailure("参数对象为空！", null);
        }

        int page = userParamDTO.getPage() - 1;
        int size = userParamDTO.getSize();
        String searchName = userParamDTO.getSearchContent();

        return adminUserService.list(searchName, page, size);
    }

    public ResponseMessage getById(@RequestParam("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseMessage.defaultFailure("参数为空！", null);
        }

        return adminUserService.getById(id);
    }
}
