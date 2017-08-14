package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.Authority;
import anxian.gateway.admin.module.base.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-18.
 */
@RestController
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Authority authority) {
        Map map = new HashMap<>();
        try {
            authorityService.save(authority);
            map.put("state", "success");
        } catch (Exception e) {
            e.getStackTrace();
            map.put("state", "error");
        }
        return map;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Object remove(String ids) {
        Map map = new HashMap<>();
        for (String id : ids.split(",")) {
            try {
                authorityService.remove(Long.valueOf(id));
                map.put("state", "success");
            } catch (Exception e) {
                map.put("state", "error");
            }
        }
        return map;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Authority authority) {
        Map map = new HashMap<>();
        try {
            authorityService.save(authority);
            map.put("state", "success");
        } catch (Exception e) {
            map.put("state", "error");
        }
        return map;
    }

    /**
     * 分页列表
     */
    @RequestMapping("/list")
    public Object findAll(int page, int limit, String authorityname) {
        Map<Object, Object> filter = new HashMap<>();
        filter.put("authorityname", authorityname);
        Page<Authority> authorityPage = authorityService.selectPageList(page, limit, filter);

        Map map = new HashMap<>();
        map.put("success", true);
        map.put("total", authorityPage.getTotalElements());
        map.put("items", authorityPage.getContent());
        return map;
    }


}
