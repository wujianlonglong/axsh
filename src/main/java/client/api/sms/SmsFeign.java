package client.api.sms;

import anxian.gateway.admin.module.business.controller.anxian.sms.ResponseMessage;
import anxian.gateway.admin.module.business.controller.anxian.sms.SearchParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="sjes-hub-api")
@RequestMapping("/yidong")
public interface SmsFeign {


    /**
     * 分页查询短信列表
     * @param searchParam
     * @return
     */
    @RequestMapping(value="/searchSms")
    ResponseMessage searchSms(@RequestBody SearchParam searchParam);


    @RequestMapping("/resendMessage")
    ResponseMessage resendMessage(@RequestParam("smsMsgId")String smsMsgId);

}
