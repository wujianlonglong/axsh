package client.api.order.model;

import client.api.serializer.CustomDateDeSerializer;
import client.api.serializer.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by qinhailong on 16-10-31.
 */
@Data
public class OrderPicker implements Serializable {

    private Long id;

    private String shopId; // '门店ID'

    private String mobile; // '联系人手机'

    private String pikerName; // '拣货员名称'

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime createDate; // 创建时间

    @JsonDeserialize(using = CustomDateDeSerializer.class)
    @JsonSerialize(using = CustomDateSerializer.class)
    private LocalDateTime updateDate; // 更新时间

    private String createDateStr; // 创建时间字符串

    private String updateDateStr; // 更新时间字符串

}
