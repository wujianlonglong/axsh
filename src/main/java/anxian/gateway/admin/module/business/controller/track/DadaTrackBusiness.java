package anxian.gateway.admin.module.business.controller.track;

import client.api.track.DadaTrackApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by cs on 2017/6/15.
 */
@Component
public class DadaTrackBusiness {
    @Autowired
    private DadaTrackApiClient dadaTrackApiClient;

    public String sendToDada(Long orderId, String flag) {
        String msg = null;
        //todo 新接口时取消注释
        if (flag.equals("firstSend")) {
            msg = dadaTrackApiClient.submitOrderOfNewDada(orderId);
        }
        if (flag.equals("secondSend")) {
            msg = dadaTrackApiClient.reAddOrderOfNewDada(orderId);
        }
        //todo 新接口上线时，需注释此处老接口
//        if (flag.equals("firstSend")) {
//            ResponseMessage responseMessage = dadaTrackApiClient.submitOrderForCustomer(orderId);
//            msg = responseMessage.getMsg();
//            if (responseMessage.getCode() == 1) {
//                msg = "发送成功";
//            }
//        }
//        if (flag.equals("secondSend")) {
//            ResponseMessage responseMessage = dadaTrackApiClient.reAddOrder(orderId);
//            msg = responseMessage.getMsg();
//            if (responseMessage.getCode() == 1) {
//                msg = "发送成功";
//            }
//        }

        return msg;
    }
}
