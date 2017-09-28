package anxian.gateway.admin.module.business.controller;

import anxian.gateway.admin.module.base.domain.NewRole;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.model.MenuModel;
import anxian.gateway.admin.module.base.service.NewMenuService;
import anxian.gateway.admin.utils.editor.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Jianghe on 16/2/2.
 */
public class BaseController {
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());
        binder.registerCustomEditor(LocalTime.class, new LocalTimeEditor());
        binder.registerCustomEditor(LocalDate.class, new LocalDateEditor());
        binder.registerCustomEditor(LocalDateTime.class, new LocalDateTimeEditor());
        binder.registerCustomEditor(Integer.class, new IntegerEditor());
        binder.registerCustomEditor(int.class, new IntegerEditor());
        binder.registerCustomEditor(long.class, new LongEditor());
        binder.registerCustomEditor(double.class, new DoubleEditor());
        binder.registerCustomEditor(float.class, new FloatEditor());
    }

    @Autowired
    private NewMenuService newMenuService;

    public void getMenus(User user, Model model) {

        NewRole newRole = user.getNewRole();
        List<MenuModel> menuModelList = newMenuService.showMenu(newRole.getNewMenus());
        if (CollectionUtils.isEmpty(menuModelList)) {
//            return "redirect:/login";
        }
        model.addAttribute("menus", menuModelList);
        model.addAttribute("user", user.getUsername());
    }

}
