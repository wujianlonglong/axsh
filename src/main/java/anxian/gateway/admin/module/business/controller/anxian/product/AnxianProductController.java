package anxian.gateway.admin.module.business.controller.anxian.product;

import anxian.gateway.admin.module.business.model.item.CommodityInfoUpdateJson;
import anxian.gateway.admin.module.business.model.item.ProductPriceModel;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import anxian.gateway.admin.utils.BeanUtil;
import anxian.gateway.admin.utils.ExcelUtil;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.ProductAttributeApiClient;
import client.api.item.ProductFeign;
import client.api.item.SnFeign;
import client.api.item.domain.Product;
import client.api.item.domain.ProductAttributeValue;
import client.api.item.domain.ProductImage;
import client.api.item.domain.ProductModel;
import client.api.item.model.PageModel;
import client.api.item.model.ProductImageModel;
import client.api.item.model.SearchCoditionModel;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by jiangzhe on 15-12-3.
 */
@RestController
@RequestMapping("/anxian/sjes_product")
public class AnxianProductController {

    Logger LOGGER = LoggerFactory.getLogger(AnxianProductController.class);

    @Autowired
    private ProductFeign productFeign;

    @Autowired
    private ProductAttributeApiClient productAttributeApiClient;

    //图片应用地址
    @Value("${picture.application.address}")
    private String pictureApplicationAddress;

    //图片存放根目录
    @Value("${picture.upload.path}")
    private String pictureUploadPath;

    @Autowired
    private SnFeign snFeign;

    /**
     * 总列数
     */
    private static final int columnNum = 3;


    /**
     * 根据productId得到单品属性值列表
     *
     * @param productId 单品Id
     * @return 单品属性值列表
     */
    @RequestMapping(value = "/listByProductId/{productId}", method = RequestMethod.GET)
    public List<ProductAttributeValue> listByProductId(@PathVariable("productId") Long productId) {
        return productAttributeApiClient.listByProductId(productId);
    }

    /**
     * 商品信息维护列表
     *
     * @return 分页列表
     */
    @RequestMapping("/informationList")
    public PageModel<Product> commodityInformationList(int page, int limit, Product searchProduct) {
        SearchCoditionModel<Product> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(searchProduct);
        return productFeign.searchProductPackage(searchCoditionModel);
    }

    /**
     * 根据主键得到商品信息
     *
     * @param id 主键
     * @return 商品信息
     */
    @RequestMapping(value = "/findInformationById/{id}", method = RequestMethod.GET)
    public Product findInformationById(@PathVariable("id") Long id) {
        return productFeign.findById(id);
    }

    /**
     * 根据商品编码得到商品信息
     *
     * @param sn 商品编码
     * @return 商品信息
     */
    @RequestMapping(value = "/findBySn/{sn}", method = RequestMethod.GET)
    public Product findBySn(@PathVariable("sn") String sn) {
        return productFeign.findBySn(sn);
    }

    /**
     * 根据商品Id得到商品详细信息
     *
     * @param sn 商品编码
     * @return 详细信息
     */
    @RequestMapping(value = "/getProductDetail", method = RequestMethod.POST)
    public JsonMsg getProductDetail(String sn, Long productId, Integer status) {
        JsonMsg jsonMsg = new JsonMsg();
        if (StringUtils.isBlank(sn)) {
            sn = snFeign.generateProductSn(productId);
        }
        ProductModel productModel = productFeign.getProductDetail(sn, status);

        CommodityInfoUpdateJson commodityInfoUpdateJson = new CommodityInfoUpdateJson();
        BeanUtil.setBean2Bean(productModel, commodityInfoUpdateJson);
        commodityInfoUpdateJson.setGoodsName(productModel.getGoods() != null ? productModel.getGoods().getGoodsName() : "");
        commodityInfoUpdateJson.setBrandName(productModel.getBrand() != null ? productModel.getBrand().getName() : "");
        commodityInfoUpdateJson.setParameterGroupModels(productModel.getParameterGroupModels());
        commodityInfoUpdateJson.setProductImages(productModel.getProductImages());

        jsonMsg.setSuccess(true);
        jsonMsg.setData(commodityInfoUpdateJson);

        return jsonMsg;
    }

    /**
     * 商品信息维护修改操作
     *
     * @return
     */
    @RequestMapping(value = "/updateProductModel", method = RequestMethod.POST)
    public JsonMsg updateProductModel(ProductModel productModel) {

        List<ProductImage> productImages = Lists.newArrayList();

        for (ProductImage productImage : productModel.getProductImages()) {
            if (productImage.getLarge800() != null) {//过滤掉没有地址的图片,由于前台点了删除按钮后图片集合的下标无法改变,故在此做过滤
                productImages.add(productImage);
            }
        }
        productModel.setProductImages(productImages);
        int result = productFeign.updateProductModel(productModel);//更新
        if (result > 0) {
            return JsonMsg.success("修改成功");
        } else {
            return JsonMsg.success("修改失败");
        }
    }

    /**
     * 更新单品状态
     *
     * @param id
     * @param status
     * @return 更新的数量
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public JsonMsg updateStatus(Authentication authentication, Long id, Integer status, String message) {
        LOGGER.info("  ############################################################     ");
        LOGGER.info("  #######   进行商品上下架，status: {} , id: {}", status, id);
        LOGGER.info("  ############################################################     ");
        int result = productFeign.updateStatus(id, status, message);
        if (result > 0) {
            return JsonMsg.success("更新状态成功");
        } else {
            return JsonMsg.success("更新状态失败");
        }
    }


    /**
     * 分页查询商品价格修改列表
     *
     * @param product 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "searchProductPrices")
    public PageModel<ProductPriceModel> searchProductPrices(Product product, int page, int limit) {
        SearchCoditionModel<Product> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(product);
        return productFeign.searchProductPrices(searchCoditionModel);
    }

    /**
     * 根据商品管理码列表分页查询商品列表
     *
     * @param erpGoodsIds 商品管理码列表
     * @param page
     * @param limit
     * @return 商品列表
     */
    @RequestMapping(value = "listByErpGoodsIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<ProductImageModel> listByErpGoodsIds(String[] erpGoodsIds, int page, int limit) {
        SearchCoditionModel<String[]> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(erpGoodsIds);
        return productFeign.listByErpGoodsIds(searchCoditionModel);
    }

    /**
     * 修改商品价格
     *
     * @param productId   商品id
     * @param salePrice   销售价
     * @param memberPrice 会员价
     */
    @RequestMapping(value = "updatePrice", method = RequestMethod.POST)
    public JsonMsg updatePrice(Authentication authentication, Long productId, BigDecimal salePrice, BigDecimal memberPrice) {
        ResponseMessage responseMessage = productFeign.updatePrice(authentication.getName(), productId, salePrice, memberPrice);
        if ("success".equals(responseMessage.getType().name())) {
            return JsonMsg.success("修改价格成功！");
        } else {
            return JsonMsg.failure("修改价格失败！");
        }
    }

    /**
     * 产生商品编码
     *
     * @param productId 商品Id
     * @return 商品编号
     */
    @RequestMapping(value = "generateProductSn/{productId}", method = RequestMethod.GET)
    public JsonMsg generateProductSn(@PathVariable("productId") Long productId) {
        String sn = snFeign.generateProductSn(productId);
        if (StringUtils.isNotBlank(sn)) {
            return JsonMsg.success("产生商品ID成功！");
        }
        return JsonMsg.failure("产生商品ID失败！");
    }


    /**
     * 批量商品价格修改
     *
     * @param multipartFile 文件
     * @return 是否成功
     */
    @RequestMapping(value = "batchProductPrice", method = RequestMethod.POST)
    public JsonMsg batchProductPrice(Authentication authentication, HttpServletResponse response, @RequestParam("file") MultipartFile multipartFile) {
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
                String modifyUserName = authentication.getName();
                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                List<ProductPriceModel> productPrices = Lists.newArrayList();
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
                    if (StringUtils.isNotBlank(datas[0])) {
                        ProductPriceModel productPriceModel = new ProductPriceModel();
                        productPriceModel.setErpGoodsId(Long.parseLong(datas[0]));
                        productPriceModel.setSalePrice(new BigDecimal(datas[1]));
                        productPriceModel.setMemberPrice(new BigDecimal(datas[2]));
                        productPriceModel.setSalePriceModifyUserName(modifyUserName);
                        productPriceModel.setMemberPriceModifyUserName(modifyUserName);
                        productPrices.add(productPriceModel);
                    }
                }
                if (CollectionUtils.isNotEmpty(productPrices)) {
                    ResponseMessage responseMessage = productFeign.batUpdatePrice(productPrices);
                    if ("success".equals(responseMessage.getType().name())) {
                        return JsonMsg.success("批量商品价格修改成功！");
                    }
                }
            }
        }
        return JsonMsg.failure("批量商品价格修改失败！");
    }

    /**
     * 批量更新单品状态
     *
     * @param status
     * @return 更新的数量
     */
    @RequestMapping(value = "batUpdateStatus/{status}", method = RequestMethod.POST)
    public JsonMsg batUpdateStatus(Authentication authentication, HttpServletResponse response, @RequestParam("file") MultipartFile multipartFile, @PathVariable("status") Integer status) {
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
                List<Long> erpGoodIds = Lists.newArrayList();
                for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
                    XSSFRow row = sheet.getRow(i);
                    if (null == row) {
                        continue;
                    }
                    String[] datas = new String[1];
                    for (int j = 0; j < 1; j++) {
                        XSSFCell cell = row.getCell(j);
                        datas[j] = null != cell ? ExcelUtil.getCellValue(cell).trim() : null;
                    }
                    if (StringUtils.isNotBlank(datas[0])) {
                        erpGoodIds.add(Long.parseLong(datas[0]));
                    }
                }
                ResponseMessage responseMessage = productFeign.batUpdateStatus(erpGoodIds, status);
                if ("success".equals(responseMessage.getType().name())) {
                    return JsonMsg.success("批量更新单品状态成功！");
                }
            }
        }
        return JsonMsg.failure("批量更新单品状态失败！");
    }


}
