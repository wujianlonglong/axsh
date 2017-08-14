package client.api.www.feign;

import client.api.constants.Constants;
import client.api.www.model.CategoryAdvertisement;
import client.api.www.model.CategoryAdvertisementCountModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mac on 15/9/17.
 */
@FeignClient(Constants.SJES_API_WWW)
@RequestMapping("categoryAdvertisements")
public interface CategoryAdvertisementFeign {

    /**
     * 保存分类广告
     *
     * @param categoryAdvertisement 分类广告
     * @return 分类广告
     */
//    @PreAuthorize("hasAuthority('CLASSIFIEDADMAINTENANCE_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    CategoryAdvertisement save(CategoryAdvertisement categoryAdvertisement);

    /**
     * 分类广告计数
     *
     * @param categoryIds 分类Ids
     * @return 分类广告计数列表
     */
    @RequestMapping(value = "countByCategoryIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryAdvertisementCountModel> countByCategoryIds(List<Long> categoryIds);

    /**
     * 根据分类Ids查询分类广告列表
     *
     * @param categoryIds 分类Ids
     * @return 分类广告列表
     */
    @RequestMapping(value = "listByCategoryIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryAdvertisement> listByCategoryIds(List<Long> categoryIds);

    /**
     * 根据分类Id和类型查询列表
     *
     * @param categoryId 分类Id
     * @param type       类型
     * @return 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<CategoryAdvertisement> listByCategoryIdAndType(@RequestParam("categoryId") Long categoryId, @RequestParam("type") Integer type);

    /**
     * 根据指定id查询
     *
     * @param id 主键id
     * @return 分类广告
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    CategoryAdvertisement findOne(@PathVariable("id") Long id);

    /**
     * 根据指定id删除
     *
     * @param id 主键id
     */
//    @PreAuthorize("hasAuthority('CLASSIFIEDADMAINTENANCE_SAVE')")
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);

}
