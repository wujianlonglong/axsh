package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.AclOrg;
import anxian.gateway.admin.module.base.model.LeftTreeChild;
import anxian.gateway.admin.module.base.model.LeftTreeParent;
import anxian.gateway.admin.module.base.model.OrgJson;
import anxian.gateway.admin.module.base.service.AclOrgService;
import anxian.gateway.admin.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-16.
 */
@RestController
@RequestMapping("/org")
public class AclOrgController {
    @Autowired
    private AclOrgService aclOrgService;

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(AclOrg aclOrg) {
        Map map = new HashMap<>();
        try {
            aclOrgService.save(aclOrg);
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
                aclOrgService.del(Long.valueOf(id));
                map.put("state", "success");
            } catch (Exception e) {
                map.put("state", "error");
            }
        }
        return map;
    }

    /**
     * 查询
     */
    @RequestMapping(value = "/viewById", method = RequestMethod.POST)
    public Object viewById(Long id) {
        AclOrg aclOrg = aclOrgService.findById(id);
        OrgJson orgJson = new OrgJson();
        BeanUtil.setBean2Bean(aclOrg, orgJson);
        return orgJson;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(AclOrg org) {
        Map map = new HashMap<>();
        try {
            aclOrgService.save(org);
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
    public Object findAll(int page, int limit, String orgName) {
        Map<Object, Object> filter = new HashMap<>();
        filter.put("orgName", orgName);
        Page<AclOrg> orgPage = aclOrgService.selectPageList(page, limit, filter);
        List<OrgJson> orgJsonList = new ArrayList<>();
        for (AclOrg aclOrg : orgPage) {
            OrgJson orgJson = new OrgJson();
            orgJson.setId(aclOrg.getId());
            orgJson.setOrgName(aclOrg.getOrgName());
            orgJson.setOrgNum(aclOrg.getOrgNum());
            orgJson.setManager(aclOrg.getManager() != null ? aclOrg.getManager().getFullName() : "");
            orgJson.setParentOrgId(aclOrg.getParentOrg() != null ? aclOrg.getParentOrg().getId() : 0L);
            orgJson.setParentOrg(aclOrg.getParentOrg() != null ? aclOrg.getParentOrg().getOrgName() : "");
            orgJson.setDescription(aclOrg.getDescription());
            orgJsonList.add(orgJson);
        }
        Map map = new HashMap<>();
        map.put("success", true);
        map.put("total", orgPage.getTotalElements());
        map.put("items", orgJsonList);
        return map;
    }

    /**
     * 部门树
     */
    @RequestMapping("/orgTree")
    public Object orgTree() {
        List<AclOrg> allParent = aclOrgService.findAllParent();

        List<LeftTreeParent> parentOrgs = new ArrayList<>();
        for (AclOrg parent : allParent) {
            LeftTreeParent parentOrg = new LeftTreeParent();
            parentOrg.setId(parent.getId());
            parentOrg.setExpanded(false);
            parentOrg.setParent(true);
            parentOrg.setText(parent.getOrgName());

            List<LeftTreeChild> childrenOrgs = new ArrayList<>();
            for (AclOrg child : parent.getChildren()) {
                LeftTreeChild childOrg = new LeftTreeChild();
                childOrg.setId(child.getId());
                childOrg.setText(child.getOrgName());
                childOrg.setLeaf(true);
                childrenOrgs.add(childOrg);
            }

            parentOrg.setChildren(childrenOrgs);
            parentOrgs.add(parentOrg);
        }


        return parentOrgs;
    }
}
