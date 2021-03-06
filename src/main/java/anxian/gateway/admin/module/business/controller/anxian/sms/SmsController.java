package anxian.gateway.admin.module.business.controller.anxian.sms;


import client.api.sms.SmsFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    SmsFeign smsFeign;


    /**
     * 分页查询短信列表
     * @param searchParam
     * @return
     */
    @RequestMapping(value="/searchSms")
    public ResponseMessage searchSms(SearchParam searchParam){
        ResponseMessage responseMessage=new ResponseMessage();
        if(searchParam==null||searchParam.getPage()==null||searchParam.getSize()==null){
            responseMessage.setFailure(-1,"请求参数的page或size不能为空！",null);
            return responseMessage;
        }
        if(searchParam.getPage()<=0||searchParam.getSize()<=0){
            responseMessage.setFailure(-1,"请求参数的page或size不能小于等于0！",null);
            return responseMessage;
        }
        responseMessage=smsFeign.searchSms(searchParam);
        return responseMessage;
    }


    /**
     * 重发短信
     * @param smsMsgId 重发的短信id
     * @return
     */
    @RequestMapping("/resendMessage")
    public ResponseMessage resendMessage(String smsMsgId){
        ResponseMessage responseMessage=new ResponseMessage();
        if(StringUtils.isEmpty(smsMsgId)){
            responseMessage.setFailure(-1, "请求参数的smsMsgId不能为空！", null);
            return responseMessage;
        }
        responseMessage = smsFeign.resendMessage(smsMsgId);
        return responseMessage;
    }






}
