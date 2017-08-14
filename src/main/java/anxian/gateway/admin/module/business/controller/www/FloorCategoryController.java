package anxian.gateway.admin.module.business.controller.www;

import anxian.gateway.admin.module.business.model.www.FloorCategoryProductModel;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import anxian.gateway.admin.utils.ExcelUtil;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.ProductFeign;
import client.api.item.domain.Product;
import client.api.www.feign.FloorCategoryFeign;
import client.api.www.model.CategoryProduct;
import client.api.www.model.FloorCategoryModel;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by qinhailong on 16-1-4.
 */
@RestController
@RequestMapping("floorCategory")
public class FloorCategoryController {

    @Autowired
    private FloorCategoryFeign floorCategoryFeign;

    @Autowired
    private ProductFeign productFeign;


    /**
     * 总列数
     */
    private static final int columnNum = 1;


    /**
     * 新增楼层分类信息
     *
     * @param floorCategoryModel 楼层分类信息
     * @return 楼层分类信息
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(FloorCategoryModel floorCategoryModel) {
        floorCategoryFeign.save(floorCategoryModel);
        return JsonMsg.success("保存成功!");
    }

    /**
     * 根据id得到楼层分类信息
     *
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonMsg get(@PathVariable("id") Long id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setData(floorCategoryFeign.get(id));
        return jsonMsg;
    }

    /**
     * 根据id得到楼层分类商品列表
     *
     * @param id 主键id
     * @return 分类商品列表
     */
    @RequestMapping(value = "{id}/products", method = RequestMethod.GET)
    public JsonMsg getCategoryproductsById(@PathVariable("id") Long id) {
        List<CategoryProduct> categoryProducts = floorCategoryFeign.getCategoryproductsById(id);
        if (CollectionUtils.isNotEmpty(categoryProducts)) {
            List<Long> productIds = Lists.newArrayList();
            Map<Long, Integer> ordersMap = Maps.newHashMap();
            categoryProducts.forEach(categoryProduct -> {
                Long productId = categoryProduct.getProductId();
                productIds.add(productId);
                ordersMap.put(productId, categoryProduct.getOrders());
            });
            List<Product> products = productFeign.findByIdIn(productIds);
            List<FloorCategoryProductModel> floorCategoryProductModels = Lists.newArrayList();
            products.forEach(product -> {
                FloorCategoryProductModel floorCategoryProductModel = new FloorCategoryProductModel();
                Long productId = product.getId();
                floorCategoryProductModel.setId(productId);
                floorCategoryProductModel.setName(product.getName());
                floorCategoryProductModel.setOrders(MapUtils.getInteger(ordersMap, productId, 0));
                floorCategoryProductModel.setSn(product.getSn());
                floorCategoryProductModel.setStatus(product.getStatus());
                floorCategoryProductModels.add(floorCategoryProductModel);
            });
            return JsonMsg.success(floorCategoryProductModels);
        }
        return JsonMsg.success(Lists.newArrayList());
    }

    /**
     * 更新楼层分类信息
     *
     * @param floorCategoryModel 楼层分类信息
     * @return 修改数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(FloorCategoryModel floorCategoryModel) {
        floorCategoryFeign.update(floorCategoryModel);
        return JsonMsg.success("修改成功!");
    }

    /**
     * 根据主键删除
     *
     * @param id 主键
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long id) {
        floorCategoryFeign.delete(id);
        return JsonMsg.success("删除成功!");
    }

    /**
     * 导入楼层分类商品
     *
     * @param categoryId    楼层分类categoryId
     * @param multipartFile 文件
     * @return 是否成功
     */
    @RequestMapping(value = "importProduct", method = RequestMethod.POST)
    public JsonMsg importProduct(HttpServletResponse response, Long categoryId, @RequestParam("file") MultipartFile multipartFile) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");//添加了文件上传后跨域问题解决办法
        XSSFWorkbook wb;
        try {
            wb = new XSSFWorkbook(multipartFile.getInputStream());
        } catch (IOException e) {
            return JsonMsg.failure("导入失败!");
        }
        if (null != wb) {
            XSSFSheet sheet = wb.getSheetAt(0);
            if (null != sheet) {
                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                Set<String> sns = Sets.newHashSet();
                for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
                    XSSFRow row = sheet.getRow(i);
                    if (null == row) {
                        continue;
                    }
                    String[] datas = new String[columnNum];
                    for (int j = 0; j < columnNum; j++) {
                        XSSFCell cell = row.getCell(j);
                        datas[j] = null != cell ? ExcelUtil.getCellValue(cell).trim() : null;
                    }
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(datas[0])) {
                        sns.add(datas[0]);
                    }
                }
                ResponseMessage responseMessage = floorCategoryFeign.importProducts(categoryId, Lists.newArrayList(sns));
                if ("success".equals(responseMessage.getType().name())) {
                    return JsonMsg.success("导入楼层分类商品成功！");
                }
            }
        }
        return JsonMsg.failure("导入楼层分类商品失败！");
    }
}
