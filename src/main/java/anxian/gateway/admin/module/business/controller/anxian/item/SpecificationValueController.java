package anxian.gateway.admin.module.business.controller.anxian.item;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.SpecificationValueFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 16-1-5.
 */
@RestController
@RequestMapping("anxian/specificationValues")
public class SpecificationValueController {

    @Autowired
    private SpecificationValueFeign specificationValueFeign;

    /**
     * 删除指定规格值 (同时删除子商品对应的规格值)
     *
     * @param id 规格id
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg deleteSpecificationValue(Long id) {
        specificationValueFeign.deleteSpecificationValue(id);
        return JsonMsg.success("删除成功");
    }


}
