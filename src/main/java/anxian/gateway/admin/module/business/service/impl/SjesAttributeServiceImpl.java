package anxian.gateway.admin.module.business.service.impl;

import anxian.gateway.admin.module.business.model.item.AttributeMaintainModel;
import anxian.gateway.admin.module.business.service.SjesAttributeService;
import client.api.category.AttributeApiClient;
import client.api.category.AttributeGroupApiClient;
import client.api.category.domain.Attribute;
import client.api.category.domain.AttributeOption;
import client.api.category.model.AttributeGroupModel;
import client.api.category.model.AttributeModel;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jianghe on 15/12/15.
 */
@Service
public class SjesAttributeServiceImpl implements SjesAttributeService {

    @Autowired
    private AttributeApiClient attributeApiClient;

    @Autowired
    private AttributeGroupApiClient attributeGroupApiClient;

    /**
     * 分页查询属性列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @Override
    public PageModel<Attribute> search(SearchCoditionModel<Attribute> searchCoditionModel) {
        PageModel<Attribute> search = attributeApiClient.search(searchCoditionModel);

        List<Attribute> hasClassificationList = Lists.newArrayList();
        for (Attribute old : search.getContent()) {
            hasClassificationList.add(old);
        }
        PageModel<Attribute> newPageModel = new PageModel<>(hasClassificationList, search.getTotal(), search.getPageable());
        return newPageModel;
    }


    /**
     * 保存商品属性
     *
     * @param attributeModel 商品属性
     * @return 商品属性
     */
    @Override
    public void saveAttributeModel(AttributeModel attributeModel, String[] htmlAttributeOptions) {

        if (htmlAttributeOptions != null) {
            List<AttributeOption> attributeOptions = Lists.newArrayList();
            for (int i = 0; i < htmlAttributeOptions.length; i++) {
                String htmlAttributeOption = htmlAttributeOptions[i];
                AttributeOption attributeOption = new AttributeOption();
                //  attributeOption.setAttributeId(attributeModel.getAttributeGroupId());
                attributeOption.setValue(htmlAttributeOption);
                attributeOption.setOrders(i);
                attributeOptions.add(attributeOption);
            }
            attributeModel.setAttributeOptions(attributeOptions);
        }
        attributeApiClient.saveAttributeModel(attributeModel);
    }

    /**
     * 根据分类Id得到AttributeGroupModel列表
     *
     * @param categoryId 分类Id
     * @return Map
     */
    @Override
    public Map getAttributeModels(Long categoryId) {
        Map map = new HashMap<>();

        List<AttributeMaintainModel> attributeMaintainModels = Lists.newArrayList();

        List<AttributeGroupModel> attributeGroupModels = attributeGroupApiClient.getAttributeModels(categoryId);
        attributeGroupModels.forEach(attributeGroupModel -> {
            List<AttributeModel> attributeModels = attributeGroupModel.getAttributeModels();
            if (CollectionUtils.isNotEmpty(attributeModels)) {
                for (AttributeModel attributeModel : attributeModels) {
                    AttributeMaintainModel attributeMaintainModel = new AttributeMaintainModel();
                    BeanUtils.copyProperties(attributeModel, attributeMaintainModel);
                    attributeMaintainModel.setAttributeGroup(attributeGroupModel.getAttributeGroupEnum().getName());
                    attributeMaintainModel.setAttributeGroupId(attributeGroupModel.getAttributeGroupEnum().getValue());
                    List<AttributeOption> attributeOptions = attributeModel.getAttributeOptions();
                    if (CollectionUtils.isNotEmpty(attributeOptions)) {
                        List<String> attributeOptionNames = Lists.newArrayList();
                        attributeOptions.forEach(attributeOption -> {
                            String attributeOptionName = attributeOption.getValue();
                            if (StringUtils.isNotBlank(attributeOptionName)) {
                                attributeOptionNames.add(attributeOptionName);
                            }
                        });
                        attributeMaintainModel.setAttributeOptions(StringUtils.join(attributeOptionNames, ","));
                    }
                    attributeMaintainModel.setCategoryId(categoryId);
                    attributeMaintainModels.add(attributeMaintainModel);
                }
            }
        });
        map.put("content", attributeMaintainModels);
        map.put("total", attributeMaintainModels.size());
        return map;
    }

    @Override
    public List<AttributeGroupModel> getAttributeModelsForProduct(Long categoryId) {
        return attributeGroupApiClient.getAttributeModels(categoryId);
    }

    @Override
    public AttributeModel getAttributeModel(Long attributeId, Long categoryId, Long attributeGroupId) {
        AttributeModel attributeModel = attributeApiClient.findAttributeModelById(attributeId);
        attributeModel.setCategoryId(categoryId);
        attributeModel.setAttributeGroupId(attributeGroupId);
        return attributeModel;
//        for (AttributeGroupModel attributeGroupModel : attributeGroupApiClient.getAttributeModels(categoryId)) {
//            if(attributeGroupModel.getAttributeModels() != null){
//               if(attributeId.toString().equals(attributeGroupModel.getAttributeModels().get(0).getId().toString())){
//                   AttributeModel attributeModel = attributeGroupModel.getAttributeModels().get(0);
//                   attributeModel.setCategoryId(categoryId);
//                   attributeModel.setAttributeGroupId(attributeGroupId);
//                   attributeModel.setType(attributeModel.getType());
//                   return attributeModel;
//               }
//            }
//        }
//        return null;
    }

    /**
     * 根据主键更新商品属性
     *
     * @param attributeModel 商品属性
     * @return 更新条数
     */
    @Override
    public void updateAttributeModel(AttributeModel attributeModel) {
        List<AttributeOption> attributeOptions = attributeModel.getAttributeOptions();
        List<AttributeOption> attributeOptionList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(attributeOptions)) {
            for (AttributeOption attributeOption : attributeOptions) {
                if (StringUtils.isNotBlank(attributeOption.getValue())) {
                    attributeOptionList.add(attributeOption);
                }
            }
        }
        attributeModel.setAttributeOptions(attributeOptionList);
        attributeApiClient.updateAttributeModel(attributeModel);
    }

    /**
     * 根据id删除指定的属性
     *
     * @param id 主键id
     */
    @Override
    public void delete(Long id) {
        attributeApiClient.delete(id);
    }
}
