package client.api.store.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by kimiyu on 15/9/16.
 */
@Data
public class GateShop implements Serializable {

    private Long id;

    private String serialNumber; // 门店编号      -----长度要varchar（6）

    private String parentSerialNumber;  // 上级门店编号

    private String name;         // 门店名称

    private String abbreviation; // 简称

    private String contactPerson;// 联系人

    private String contactMethod;// 联系方式

    private Integer status;     //  门店状态:0 正常;1 开店中;2 关闭    需修改数据

    private Boolean networkShoping; // 网购业务    需修改数据

    private Boolean selfPicking;    // 自提业务    需修改数据

    private String city;            // 城市名称--无

    private String fgs;             // 县市区

    private String area;            // 区域

    private String address;         // 门店地址

    private String latitudeAndLongitude; //经纬度     -------表机构需修改

    private String latitude;            //纬度
    private String longitude;            //经度

    private String convenientService;   // 便民服务--无

    private String businessTime;        // 营业时间

    private String transportLine;       // 交通线路--无

    private Long updater;             // 更新人


    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedDate;           // 更新时间

}
