package anxian.gateway.admin.module.business.controller.order;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import anxian.gateway.admin.module.business.controller.order.utils.Encoder;
import anxian.gateway.admin.module.business.controller.order.utils.EncoderHelper;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.image.common.Setting;
import client.api.order.OrderApiClient;
import client.api.order.PrintApiClient;
import client.api.order.model.Order;
import client.api.order.model.OrderApiResponse;
import client.api.order.model.PrintOrderItem;
import client.api.order.model.PrintOrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by JiangZhe on 16/2/14.
 */
@Controller
@RequestMapping("/printOrder")
public class PrintController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintController.class);

    @Autowired
    private PrintApiClient printApiClient;

    @Autowired
    private OrderApiClient orderApiClient;

    @Value("${picture.upload.path}")
    private String pictureUploadPath;

    Encoder mEncoder = EncoderHelper.buildEncoder();

    /**
     * 打开订单打印窗口
     *
     * @return
     */
    @RequestMapping(value = "/print/{id}/{type}", method = RequestMethod.GET)
    public String print(@PathVariable("id") Long id, @PathVariable("type") Integer type, Model model) {
        OrderApiResponse<PrintOrderView> printOrderViewOrderApiResponse = printApiClient.printOrderView(id);

        if (printOrderViewOrderApiResponse.getReturn_code() == OrderConstant.successCode) {
            PrintOrderView data = printOrderViewOrderApiResponse.getData();

            LOGGER.info("订单打印：{}", data.toString());

            String fileName = Setting.PRODUCT_IMAGE_UPLOAD_PATH + "/barcode/" + System.currentTimeMillis() + ".png";
            String barCodePath = pictureUploadPath.substring(pictureUploadPath.indexOf("//") + 2) + fileName;

            File imageFile = new File(barCodePath);
            if (!imageFile.getParentFile().exists()) {
                imageFile.getParentFile().mkdirs();
            }
            LOGGER.info("fileName：{}", fileName + "");

            LOGGER.info("barCodePath：{}", barCodePath + "");


            BufferedImage imageBarcode = mEncoder.encodeBar(id.toString());
            try {
                ImageIO.write(imageBarcode, "png", imageFile);
                data.setBarcode(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.info("ImageIO.write：{}", e + "");
            }



            /*if (BarcodeUtil.generateBarCode(imageFile.getPath(), id.toString())) {//生成条形码
                data.setBarcode(fileName);
            }
            */


            Order order = orderApiClient.findOrder(data.getOrderId());

            data.setPayType(order.getPayTypeView());
            data.setConsignee(order.getConsignee());
            data.setTelphone(order.getTelphone());
            data.setSendAddress(order.getSendAddress());

            int totalNumber = 0;
            for (PrintOrderItem printOrderItem : data.getPrintOrderItems()) {
                totalNumber += printOrderItem.getNumber();
            }

            data.setTotalNumber(totalNumber);

            model.addAttribute("order", data);

        }

        if (type == 0) {//配送订单
            return "print/printOrder";
        } else { //自提订单
            return "print/selfPrintOrder";
        }


    }


    /**
     * 打印订单
     *
     * @param orderId 订单ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/print", method = RequestMethod.POST)
    public JsonMsg printOrder(Long orderId) {

        AclUser user = UserContextHelper.getUserContext().getUser();

        OrderApiResponse orderApiResponse = printApiClient.printOrder(orderId, user.getId(), user.getFullName(), user.getOrg().getOrgNum());
        if (orderApiResponse.getReturn_code() == OrderConstant.successCode) {
            return JsonMsg.success("打印成功");
        }

        return JsonMsg.failure("打印失败");

    }


//    @RequestMapping(value = "/print", method = RequestMethod.GET)
//    public JsonMsg aa() {
//
//
//
//        String orderId = "789632908596224";
//            LOGGER.info("订单打印：{}", orderId+"");
//
//            String fileName = Setting.PRODUCT_IMAGE_UPLOAD_PATH + "/barcode/" + System.currentTimeMillis() + ".png";
//            String barCodePath = pictureUploadPath.substring(pictureUploadPath.indexOf("//") + 2) + fileName;
//
//        LOGGER.info("fileName：{}", fileName+"");
//
//        LOGGER.info("barCodePath：{}", barCodePath+"");
//
//
//
//            File imageFile = new File(barCodePath);
//            if (!imageFile.getParentFile().exists()) {
//                imageFile.getParentFile().mkdirs();
//            }
//            if (BarcodeUtil.generateBarCode(imageFile.getPath(), orderId.toString())) {//生成条形码
////                data.setBarcode(fileName);
//
//                System.out.println(fileName);
//            }
//
//
//        return JsonMsg.success(fileName);
//
//
//
//
//    }
//
//    @RequestMapping(value = "/print2", method = RequestMethod.GET)
//    public JsonMsg aa2() {
//
//
//
//        String orderId = "789632908596224";
//        LOGGER.info("订单打印：{}", orderId+"");
//
//        String fileName = Setting.PRODUCT_IMAGE_UPLOAD_PATH + "/barcode/" + System.currentTimeMillis() + ".png";
//        String barCodePath = pictureUploadPath.substring(pictureUploadPath.indexOf("//") + 2) + fileName;
//
//        LOGGER.info("fileName：{}", fileName+"");
//
//        LOGGER.info("barCodePath：{}", barCodePath+"");
//
//
//
//        File imageFile = new File(barCodePath);
//        if (!imageFile.getParentFile().exists()) {
//            imageFile.getParentFile().mkdirs();
//        }
//
//
//        Encoder mEncoder = EncoderHelper.buildEncoder();
//        BufferedImage imageBarcode = mEncoder.encodeBar(orderId);
//        try {
//            ImageIO.write(imageBarcode, "png", imageFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//        return JsonMsg.success(fileName);
//
//
//
//
//    }

}
