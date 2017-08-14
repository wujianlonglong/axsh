package anxian.gateway.admin.module.business.controller.customerComplain;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.base.repository.AclUserRepository;
import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.customerComplain.CustomerComplainApiClient;
import client.api.customerComplain.domain.*;
import client.api.sale.model.ResponseMessage;
import client.api.sale.model.SaleConstant;
import client.api.user.utils.page.PageForAdmin;
import client.api.user.utils.page.SjesPage;
import com.google.common.collect.Lists;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by byinbo on 2017/3/20.
 */
@RestController
@RequestMapping(value = "/admin/customerComplain")
public class CustomerComplainController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(CustomerComplainController.class);

    @Override
    protected void initBinder(WebDataBinder binder) {
        super.initBinder(binder);
    }

    @Autowired
    private CustomerComplainApiClient customerComplainApiClient;

    @Autowired
    private AclUserRepository aclUserRepository;

    /**
     * 投诉待处理
     */
    int COMPLAIN_STAT_READY = 1;

    /**
     * 投诉已完成
     */
    int COMPLAIN_STAT_FINISH = 2;

    /**
     * 投诉已关闭
     */
    int COMPLAIN_STAT_CLOSE = 3;

    /**
     * 投诉处理中
     */
    int COMPLAIN_STAT_HANDLE = 4;

    /**
     * @param receiveDept
     * @param complainType
     * @param complainStat
     * @param overTime
     * @param searchStr
     * @param startDate
     * @param endDate
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/pageList")
    public PageForAdmin<CustomerComplain> pageList(@RequestParam(name = "receiveDept", required = false) String receiveDept,
                                                   @RequestParam(name = "complainType", required = false) String complainType,
                                                   @RequestParam(name = "complainStat", required = false) Integer complainStat,
                                                   @RequestParam(name = "responStat", required = false) Integer responStat,
                                                   @RequestParam(name = "overTime", required = false) Integer overTime,
                                                   @RequestParam(name = "searchStr", required = false) String searchStr,
                                                   @RequestParam(name = "startDate", required = false) String startDate,
                                                   @RequestParam(name = "endDate", required = false) String endDate,
                                                   @RequestParam(name = "memberStat", required = false) Integer memberStat,
                                                   @RequestParam(name = "jobNum", required = false) String jobNum,
                                                   @RequestParam(name = "clientType", required = false) Integer clientType,
                                                   @RequestParam(name = "page", required = false, defaultValue = "1")
                                                           int page,
                                                   @RequestParam(name = "limit", required = false, defaultValue = "10")
                                                           int size,
                                                   Principal principal) {
        String orgNum = "";
        if (StringUtils.isNotEmpty(jobNum)) {
            StringBuilder sb = new StringBuilder();
            List<AclUser> aclUsers = aclUserRepository.getAclUserByJobNum(jobNum);
            for (AclUser aclUser : aclUsers) {
                if (aclUser.getOrg() != null) {
                    sb.append(aclUser.getOrg().getOrgNum()).append(",");
                }
            }
            orgNum = sb.toString();
        } else {
            try {
                AclUser aclUser = aclUserRepository.findByName(principal.getName());
                orgNum = aclUser.getOrg().getOrgNum();
            } catch (Exception e) {
                log.info("客诉登录用户获取失败");
            }
        }

        return customerComplainApiClient.pageGetCustomerComplainList(receiveDept, complainType,
                complainStat, responStat, overTime, searchStr, startDate, endDate, memberStat, orgNum, clientType, page - 1, size);
    }

    /**
     * @param receiveDept
     * @param complainType
     * @param complainStat
     * @param overTime
     * @param searchStr
     * @param startDate
     * @param endDate
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/wx/pageList")
    public PageForAdmin<CustomerComplain> pageWxList(@RequestParam(name = "receiveDept", required = false) String receiveDept,
                                                     @RequestParam(name = "complainType", required = false) String complainType,
                                                     @RequestParam(name = "complainStat", required = false) Integer complainStat,
                                                     @RequestParam(name = "responStat", required = false) Integer responStat,
                                                     @RequestParam(name = "overTime", required = false) Integer overTime,
                                                     @RequestParam(name = "searchStr", required = false) String searchStr,
                                                     @RequestParam(name = "startDate", required = false) String startDate,
                                                     @RequestParam(name = "endDate", required = false) String endDate,
                                                     @RequestParam(name = "memberStat", required = false) Integer memberStat,
                                                     @RequestParam(name = "jobNum", required = false) String jobNum,
                                                     @RequestParam(name = "clientType", required = false) Integer clientType,
                                                     @RequestParam(name = "page", required = false, defaultValue = "1")
                                                             int page,
                                                     @RequestParam(name = "limit", required = false, defaultValue = "10")
                                                             int size,
                                                     Principal principal) {
        String orgNum = "";
        Set<String> shops = new HashSet<>();
        boolean area = false;
        if (StringUtils.isNotEmpty(jobNum)) {
            StringBuilder sb = new StringBuilder();
            List<AclUser> aclUsers = aclUserRepository.getAclUserByJobNum(jobNum);
            for (AclUser aclUser : aclUsers) {
                if (aclUser.getOrg() != null) {
                    String orgN = aclUser.getOrg().getOrgNum();
                    if (orgN.length() == 4) {
                        area = true;
                        shops.addAll(customerComplainApiClient.getShopNames(orgN));
                    } else {
                        shops.add(aclUser.getOrg().getOrgName());
                    }
                    sb.append(orgN).append(",");
                }
            }
            orgNum = sb.toString();
        } else {
            AclUser aclUser = aclUserRepository.findByName(principal.getName());
            orgNum = aclUser.getOrg().getOrgNum();
        }

        PageForAdmin<CustomerComplain> pageForAdmin = customerComplainApiClient.pageGetCustomerComplainList(receiveDept, complainType,
                complainStat, responStat, overTime, searchStr, startDate, endDate, memberStat, orgNum, clientType, page - 1, size);

        List<CustomerComplain> customerComplainList = customerComplainApiClient.countCustomerComplainList(orgNum) == null ? Lists.newArrayList() : customerComplainApiClient.countCustomerComplainList(orgNum);

        Map<Integer, Long> statusGroup = customerComplainList.stream().collect(Collectors.groupingBy(customerComplain -> customerComplain.getComplainStat(), Collectors.counting()));
        pageForAdmin.setArea(area);
        pageForAdmin.setShops(new ArrayList<>(shops));
        List<ComplainStatu> complainStatus = Lists.newArrayList();
        for (int i = 1; i <= 4; i++) {
            ComplainStatu complainStatu = new ComplainStatu();
            if (i == COMPLAIN_STAT_READY) {
                complainStatu.setOrder(1);
                complainStatu.setComplainStat(COMPLAIN_STAT_READY);
                complainStatu.setImageUrl("http://img.sanjiang.com/image/complainStat/%E5%BE%85%E5%A4%84%E7%90%86-1.png");
                complainStatu.setStatCounts(statusGroup.get(COMPLAIN_STAT_READY));
                complainStatu.setStatName("待处理");
            }
            if (i == COMPLAIN_STAT_HANDLE) {
                complainStatu.setOrder(2);
                complainStatu.setComplainStat(COMPLAIN_STAT_HANDLE);
                complainStatu.setImageUrl("http://img.sanjiang.com/image/complainStat/%E5%A4%84%E7%90%86%E4%B8%AD-1.png");
                complainStatu.setStatCounts(statusGroup.get(COMPLAIN_STAT_HANDLE));
                complainStatu.setStatName("处理中");
            }
            if (i == COMPLAIN_STAT_FINISH) {
                complainStatu.setOrder(3);
                complainStatu.setComplainStat(COMPLAIN_STAT_FINISH);
                complainStatu.setImageUrl("http://img.sanjiang.com/image/complainStat/%E5%B7%B2%E5%AE%8C%E6%88%90-1.png");
                complainStatu.setStatCounts(statusGroup.get(COMPLAIN_STAT_FINISH));
                complainStatu.setStatName("已完成");
            }
            if (i == COMPLAIN_STAT_CLOSE) {
                complainStatu.setOrder(4);
                complainStatu.setComplainStat(COMPLAIN_STAT_CLOSE);
                complainStatu.setImageUrl("http://img.sanjiang.com/image/complainStat/%E6%97%A0%E6%95%88-1.png");
                complainStatu.setStatCounts(statusGroup.get(COMPLAIN_STAT_CLOSE));
                complainStatu.setStatName("无效投诉");
            }
            complainStatus.add(complainStatu);
        }
        Collections.sort(complainStatus);
        pageForAdmin.setComplainStatus(complainStatus);
        return pageForAdmin;
    }

    /**
     * 门店列表
     *
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping(value = "/listForComplain", method = RequestMethod.GET)
    public SjesPage<ShopInfo> shopListForComplain(String shopId, String shopName, int limit, int page) {
        ShopSearch gateShopSearch = new ShopSearch();
        gateShopSearch.setShopId(shopId);
        gateShopSearch.setShopName(shopName);
        if (gateShopSearch != null && limit != 0 && page != 0) {
            Pageable pageable = new PageRequest(page, limit);
            gateShopSearch.setPage(page - 1);
            gateShopSearch.setSize(limit);

            return customerComplainApiClient.getShopList(gateShopSearch);
        }
        return null;
    }

    /**
     * 根据投诉编号获取处理结果
     *
     * @param complainId
     * @return
     */
    @RequestMapping(value = "/result/{complainId}", method = RequestMethod.GET)
    public List<ComplainResult> findComplainResults(@PathVariable("complainId") Long complainId, Principal principal) {
        AclUser aclUser = aclUserRepository.findByName(principal.getName());
        String orgNum = aclUser.getOrg().getOrgNum();
        CustomerComplainWxModel customerComplainWxModel = customerComplainApiClient.getComplainById(complainId);
        if (customerComplainWxModel.getComplainStat() == COMPLAIN_STAT_READY && (orgNum.equals(customerComplainWxModel.getReceiveDeptNum()))) {
            customerComplainApiClient.handle(complainId);
        }
        return customerComplainApiClient.findComplainResult(complainId);
    }

    /**
     * 根据投诉编号获取处理结果 公众号
     *
     * @param complainId
     * @return
     */
    @RequestMapping(value = "/result/wx/{complainId}/{jobNum}", method = RequestMethod.GET)
    public ComplainWxModel findComplainById(@PathVariable("complainId") Long complainId, @PathVariable("jobNum") String jobNum) {
        List<AclUser> aclUsers = aclUserRepository.getAclUserByJobNum(jobNum);
        List<String> shopIds = Lists.newArrayList();
        for (AclUser aclUser : aclUsers) {
            if (aclUser.getOrg() != null) {
                if (aclUser.getOrg().getOrgNum().length() != 4) {
                    shopIds.add(aclUser.getOrg().getOrgNum());
                }
            }
        }
        ComplainWxModel complainWxModel = new ComplainWxModel();
        complainWxModel.setShopIds(shopIds);
        CustomerComplainWxModel customerComplainWxModel = customerComplainApiClient.getComplainById(complainId);
        if (customerComplainWxModel.getComplainStat() == COMPLAIN_STAT_READY && (shopIds.contains(customerComplainWxModel.getReceiveDeptNum()) == true)) {
            customerComplainApiClient.handle(complainId);
        }
        List<Image> imagePathLists = new ArrayList<>();
        String[] imagArr = customerComplainWxModel.getImagePaths().split(",");
        for (String imag : imagArr) {
            if (StringUtils.isNotEmpty(imag)) {
                Image image = new Image(imag);
                imagePathLists.add(image);
            }
        }
        customerComplainWxModel.setImagePathLists(imagePathLists);
        List<ComplainResult> complainResults = customerComplainApiClient.findComplainResult(complainId);
        complainWxModel.setCustomerComplainWxModel(customerComplainWxModel);
        complainWxModel.setComplainResults(complainResults);
        return complainWxModel;

    }


    /**
     * 提交处理结果
     *
     * @param type     1：提交处理结果 2：关闭无效投诉
     * @param id
     * @param result
     * @param turn
     * @param shopId
     * @param shopName
     * @return
     */
    @RequestMapping(value = "/result/submit/{type}", method = RequestMethod.POST)
    public JsonMsg resultSubmit(@PathVariable("type") int type, @RequestParam("complainId") Long id, @RequestParam("result") String result, @RequestParam(value = "turn", required = false) boolean turn, @RequestParam(value = "shopId", required = false) String shopId, @RequestParam(value = "shopName", required = false) String shopName) {

//        System.out.println(type+"   "+id+"   "+result+"   "+ turn +"   "+ shopId+"   "+shopName);
        ResponseMessage responseMessage = customerComplainApiClient.submit(type, id, result, turn, shopId, shopName);
        if (responseMessage.getCode() == SaleConstant.successCode) {
            return JsonMsg.success("提交成功");
        } else {
            return JsonMsg.failure(responseMessage.getCodeMessage());
        }

    }

    /**
     * 客诉列表导出
     *
     * @param httpServletRequest
     * @param receiveDept
     * @param complainType
     * @param complainStat
     * @param overTime
     * @param searchStr
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/export")
    public ResponseEntity<byte[]> pageListExport(HttpServletRequest httpServletRequest,
                                                 @RequestParam(name = "receiveDept", required = false) String receiveDept,
                                                 @RequestParam(name = "complainType", required = false) String complainType,
                                                 @RequestParam(name = "complainStat", required = false) Integer complainStat,
                                                 @RequestParam(name = "responStat", required = false) Integer responStat,
                                                 @RequestParam(name = "overTime", required = false) Integer overTime,
                                                 @RequestParam(name = "searchStr", required = false) String searchStr,
                                                 @RequestParam(name = "startDate", required = false) String startDate,
                                                 @RequestParam(name = "endDate", required = false) String endDate,
                                                 @RequestParam(name = "memberStat", required = false) Integer memberStat,
                                                 @RequestParam(name = "clientType", required = false) Integer clientType,
                                                 @RequestParam(name = "page", required = false, defaultValue = "1")
                                                         int page,
                                                 @RequestParam(name = "limit", required = false, defaultValue = "10000")
                                                         int size, Principal principal) {
        AclUser aclUser = aclUserRepository.findByName(principal.getName());
        PageForAdmin<CustomerComplain> CustomerComplainPage = customerComplainApiClient.pageGetCustomerComplainList(receiveDept, complainType,
                complainStat, responStat, overTime, searchStr, startDate, endDate, memberStat, aclUser.getOrg().getOrgNum(), clientType, page - 1, size);

        List<CustomerComplain> content = CustomerComplainPage.getList();
        ResponseEntity<byte[]> responseEntity = null;
        if (!org.apache.commons.collections.CollectionUtils.isEmpty(content)) {
            Map<String, List<CustomerComplainModel>> beanParams = new HashMap<>();
            beanParams.put("list", this.covertCustomerComplainToModel(content));

            try {

                String fileName = "客户投诉.xls";

                String destFilePath = OrderConstant.ORDER_PATH + fileName;

                ServletContext servletContext = httpServletRequest.getSession(Boolean.TRUE).getServletContext();
                File exportFile = new File(servletContext.getRealPath(destFilePath));
                if (!exportFile.getParentFile().exists()) {
                    exportFile.getParentFile().mkdirs();
                }

                // 判断文件是否存在
                if (exportFile.exists()) {
                    exportFile.delete();
                }
                exportFile.createNewFile();

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentDispositionFormData("attachment",
                        URLEncoder.encode(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd")) + "_" +
                                fileName, "UTF-8"));

                XLSTransformer former = new XLSTransformer();
                InputStream inputStream = this.getClass().getResourceAsStream
                        ("/xls_template/customerComplainList_export.xlsx");
                Workbook sheets = former.transformXLS(inputStream, beanParams);
                OutputStream outputStream = new FileOutputStream(exportFile);
                sheets.write(outputStream);

                responseEntity = new ResponseEntity<>(FileUtils.readFileToByteArray(exportFile), httpHeaders,
                        HttpStatus.OK);

                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;
    }

    /**
     * @param customerComplainList
     * @return
     */
    private List<CustomerComplainModel> covertCustomerComplainToModel(List<CustomerComplain> customerComplainList) {
        List<CustomerComplainModel> customerComplainModelList = new ArrayList<>();

        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(customerComplainList)) {
            customerComplainList.stream()
                    .forEach(customerComplain -> {
                        CustomerComplainModel customerComplainModel = new CustomerComplainModel();
                        customerComplainModel.setReceiveDept(customerComplain.getReceiveDept());
                        customerComplainModel.setGateShop(customerComplain.getGateShop());
                        customerComplainModel.setComplainContent(customerComplain.getComplainContent());
                        int comStat = customerComplain.getComplainStat();
                        String comStatStr = "";
                        if (comStat == 1) {
                            comStatStr = "待处理";
                        } else if (comStat == 2) {
                            comStatStr = "已完成";
                        } else if (comStat == 3) {
                            comStatStr = "无效投诉";
                        } else if (comStat == 4) {
                            comStatStr = "处理中";
                        }
                        customerComplainModel.setComplainStat(comStatStr);
                        customerComplainModel.setComplainTime(customerComplain.getComplainTime() == null
                                ? ""
                                : customerComplain.getComplainTime()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        customerComplainModel.setFinishTime(customerComplain.getFinishTime() == null
                                ? ""
                                : customerComplain.getFinishTime()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        customerComplainModel.setId(customerComplain.getId());
                        customerComplainModel.setMobile(customerComplain.getMobile());
                        customerComplainModel.setOverTime(customerComplain.getOverTime() == 1 ? "是" : "否");
                        String comType = customerComplain.getComplainType();
                        String comTypeStr = "";
                        if (comType.equals(ComplainTypeEnum.QUALITY_GOOD.getComplainTypeEnum())) {
                            comTypeStr = "商品质量";
                        } else if (comType.equals(ComplainTypeEnum.PRICE_GOOD.getComplainTypeEnum())) {
                            comTypeStr = "商品价格";
                        } else if (comType.equals(ComplainTypeEnum.LESS_GOOD.getComplainTypeEnum())) {
                            comTypeStr = "商品缺货";
                        } else if (comType.equals(ComplainTypeEnum.ATTITUDE_SERVICE.getComplainTypeEnum())) {
                            comTypeStr = "服务态度";
                        } else if (comType.equals(ComplainTypeEnum.LOGISTICS.getComplainTypeEnum())) {
                            comTypeStr = "物流配送";
                        } else if (comType.equals(ComplainTypeEnum.SHOP_ENVIRONMENT.getComplainTypeEnum())) {
                            comTypeStr = "购物环境";
                        } else {
                            comTypeStr = "其他类型";
                        }
                        customerComplainModel.setComplainType(comTypeStr);
                        String platform = customerComplain.getPlatform();
                        String platformStr = "";
                        if (platform.equals(PlatformEnum.PLATFORM_BD.getPlatformEnum())) {
                            platformStr = "百度外卖(三江门店)";
                        } else if (platform.equals(PlatformEnum.PLATFORM_JD.getPlatformEnum())) {
                            platformStr = "京东到家(三江门店)";
                        } else if (platform.equals(PlatformEnum.PLATFORM_TB.getPlatformEnum())) {
                            platformStr = "淘宝便利店(宁波)";
                        } else if (platform.equals(PlatformEnum.PLATFORM_SJMD.getPlatformEnum())) {
                            platformStr = "三江购物各门店";
                        } else if (platform.equals(PlatformEnum.PLATFORM_SJW.getPlatformEnum())) {
                            platformStr = "三江购物网";
                        } else if (platform.equals(PlatformEnum.PLATFORM_TXD.getPlatformEnum())) {
                            platformStr = "淘鲜达(宁波)";
                        } else {
                            platformStr = "其他投诉";
                        }
                        customerComplainModel.setResponStat(customerComplain.getResponStat() == 1 ? "已定责" : "未定责");
                        customerComplainModel.setPlatform(platformStr);
                        String responseStr = customerComplain.getResponStrs();
                        StringBuilder sb = new StringBuilder();
                        String resStr = "";
                        if (responseStr != null) {
                            String[] resArray = responseStr.split(",");
                            for (String res : resArray) {
                                if (res.equals(ComplainDutyEnum.SHOP.getComplainDutyEnum())) {
                                    sb.append("门店，");
                                } else if (res.equals(ComplainDutyEnum.BUYER.getComplainDutyEnum())) {
                                    sb.append("采购，");
                                } else if (res.equals(ComplainDutyEnum.SANJIANG_LOGISTICS.getComplainDutyEnum())) {
                                    sb.append("三江配送，");
                                } else if (res.equals(ComplainDutyEnum.DEPT_QUQUDAO.getComplainDutyEnum())) {
                                    sb.append("全渠道，");
                                } else if (res.equals(ComplainDutyEnum.TAOBAO.getComplainDutyEnum())) {
                                    sb.append("淘宝便利店/淘鲜达，");
                                } else if (res.equals(ComplainDutyEnum.JD.getComplainDutyEnum())) {
                                    sb.append("京东到家，");
                                } else if (res.equals(ComplainDutyEnum.BAIDU.getComplainDutyEnum())) {
                                    sb.append("百度外卖，");
                                } else if (res.equals(ComplainDutyEnum.THIRD_LOGISTICS.getComplainDutyEnum())) {
                                    sb.append("其他第三方配送，");
                                } else if (res.equals(ComplainDutyEnum.DEPT_INFO.getComplainDutyEnum())) {
                                    sb.append("信息部，");
                                } else if (res.equals(ComplainDutyEnum.DEPT_HANDING.getComplainDutyEnum())) {
                                    sb.append("加工中心，");
                                } else if (res.equals(ComplainDutyEnum.SUPPLIER.getComplainDutyEnum())) {
                                    sb.append("供应商，");
                                } else if (res.equals(ComplainDutyEnum.OTHER.getComplainDutyEnum())) {
                                    sb.append("其他，");
                                } else if (res.equals(ComplainDutyEnum.NO_RESPONSE.getComplainDutyEnum())) {
                                    sb.append("无责任，");
                                }
                            }
                            resStr = sb.toString().substring(0, sb.toString().length() - 1);
                        }
                        customerComplainModel.setResponStr(resStr);
                        customerComplainModel.setMemberStr(customerComplain.getMember() == 1 ? "是" : "否");
                        customerComplainModelList.add(customerComplainModel);
                    });
        }

        return customerComplainModelList;
    }

    /**
     * 投诉定责列表
     *
     * @param responStat
     * @param searchStr
     * @param page
     * @param size
     * @param principal
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/duty/pageList")
    public PageForAdmin dutyPageList(@RequestParam(name = "responStat", required = false) Integer responStat,
                                     @RequestParam(name = "memberStat", required = false) Integer memberStat,
                                     @RequestParam(name = "searchStr", required = false) String searchStr,
                                     @RequestParam(name = "page", required = false, defaultValue = "1")
                                             int page,
                                     @RequestParam(name = "limit", required = false, defaultValue = "10")
                                             int size,
                                     Principal principal) {
        AclUser aclUser = aclUserRepository.findByName(principal.getName());
        return customerComplainApiClient.pageGetComplainDutyList(responStat, searchStr, memberStat, aclUser.getOrg().getOrgNum(), page - 1, size);
    }

    @RequestMapping(value = "/duty/submit", method = RequestMethod.POST)
    public JsonMsg dutySubmit(@RequestParam("responStrs") int[] responStrs, @RequestParam("complainId") Long id, Principal principal) {

        ResponseMessage responseMessage = customerComplainApiClient.dutySubmit(responStrs, id);
        if (responseMessage.getCode() == SaleConstant.successCode) {
            log.info("客诉定责：complainId=" + id + "   操作人：" + principal.getName());
            return JsonMsg.success("提交成功");
        } else {
            return JsonMsg.failure(responseMessage.getCodeMessage());
        }

    }

    /**
     * 投诉定责导出
     *
     * @param httpServletRequest
     * @param responStat
     * @param searchStr
     * @param page
     * @param size
     * @param principal
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/duty/export")
    public ResponseEntity<byte[]> pageListExportDuty(HttpServletRequest httpServletRequest,
                                                     @RequestParam(name = "responStat", required = false) Integer responStat,
                                                     @RequestParam(name = "memberStat", required = false) Integer memberStat,
                                                     @RequestParam(name = "searchStr", required = false) String searchStr,
                                                     @RequestParam(name = "page", required = false, defaultValue = "1")
                                                             int page,
                                                     @RequestParam(name = "limit", required = false, defaultValue = "10000")
                                                             int size, Principal principal) {
        AclUser aclUser = aclUserRepository.findByName(principal.getName());
        PageForAdmin<CustomerComplain> CustomerComplainPage = customerComplainApiClient.pageGetComplainDutyList(responStat, searchStr, memberStat, aclUser.getOrg().getOrgNum(), page - 1, size);
        List<CustomerComplain> content = CustomerComplainPage.getList();
        ResponseEntity<byte[]> responseEntity = null;
        if (!org.apache.commons.collections.CollectionUtils.isEmpty(content)) {
            Map<String, List<CustomerComplainModel>> beanParams = new HashMap<>();
            beanParams.put("list", this.covertCustomerComplainToModel(content));

            try {

                String fileName = "投诉定责.xls";

                String destFilePath = OrderConstant.ORDER_PATH + fileName;

                ServletContext servletContext = httpServletRequest.getSession(Boolean.TRUE).getServletContext();
                File exportFile = new File(servletContext.getRealPath(destFilePath));
                if (!exportFile.getParentFile().exists()) {
                    exportFile.getParentFile().mkdirs();
                }

                // 判断文件是否存在
                if (exportFile.exists()) {
                    exportFile.delete();
                }
                exportFile.createNewFile();

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentDispositionFormData("attachment",
                        URLEncoder.encode(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd")) + "_" +
                                fileName, "UTF-8"));

                XLSTransformer former = new XLSTransformer();
                InputStream inputStream = this.getClass().getResourceAsStream
                        ("/xls_template/complainDutyList_export.xlsx");
                Workbook sheets = former.transformXLS(inputStream, beanParams);
                OutputStream outputStream = new FileOutputStream(exportFile);
                sheets.write(outputStream);

                responseEntity = new ResponseEntity<>(FileUtils.readFileToByteArray(exportFile), httpHeaders,
                        HttpStatus.OK);

                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;
    }

}
