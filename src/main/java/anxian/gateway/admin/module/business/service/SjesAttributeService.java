package anxian.gateway.admin.module.business.service;

import client.api.category.domain.Attribute;
import client.api.category.model.AttributeGroupModel;
import client.api.category.model.AttributeModel;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;

import java.util.List;
import java.util.Map;

/**
 * Created by Jianghe on 15/12/15.
 */
public interface SjesAttributeService {

    PageModel<Attribute> search(SearchCoditionModel<Attribute> searchCoditionModel);


    //    @PreAuthorize("hasAuthority('COMMODITYATTRIBUTE_SAVE')")
    void saveAttributeModel(AttributeModel attributeModel, String[] htmlAttributeOptions);

    Map getAttributeModels(Long categoryId);

    List<AttributeGroupModel> getAttributeModelsForProduct(Long categoryId);

    AttributeModel getAttributeModel(Long attributeId, Long categoryId, Long attributeGroupId);

    //    @PreAuthorize("hasAuthority('COMMODITYATTRIBUTE_SAVE')")
    void updateAttributeModel(AttributeModel attributeModel);

    /**
     * 根据id删除指定的属性
     *
     * @param id 主键id
     */
    void delete(Long id);
}
