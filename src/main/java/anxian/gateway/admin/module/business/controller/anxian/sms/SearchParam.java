package anxian.gateway.admin.module.business.controller.anxian.sms;

import lombok.Data;

@Data
public class SearchParam {

    private Integer page;

    private Integer size;

    private String platName;

    private String sendMobile;

    private Integer templateType;

    private String sendStatus;

    private String startDate;

    private String endDate;

}
