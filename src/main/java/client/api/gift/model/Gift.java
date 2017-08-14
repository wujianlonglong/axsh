package client.api.gift.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by cs on 2015/9/29. 正品维护
 */
@Data
public class Gift implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * '赠品编码'
     */
    private String code;
    /**
     * ''赠品名称''
     */
    private String name;
    /**
     * '赠品来源，1表示大库，2表示采购（供应商），3表示外采，4表示内采'
     */
    private String sorce;
    /**
     * 赠品单位
     */
    private String unitName;
    /**
     * 商品价值
     */
    private Double price;

    /**
     * 保质期
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime shelflife;
    /**
     * 入库数量
     */
    private Integer storagenu;

    /**
     * 消耗数量
     */
    private Integer usenu;

    /**
     * 锁定数量
     */
    private Integer locknu;

    /**
     * 实际数量
     */
    private int actualNum;

    public int getActualNum() {
        return (storagenu == null ? 0 : storagenu) - (usenu == null ? 0 : usenu) - (locknu == null ? 0 : locknu);
    }

    /**
     * 状态，1表示正常，2表示过期，3表示是无货
     */
    private Integer status = GiftConstant.Normal;

    /**
     * 备注
     */
    private String remarks;
    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime crdate_time;
    /**
     * 创建人
     */
    private Long crdate_people;
    /**
     * 修改时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime update_time;
    /**
     * 修改人
     */
    private Long update_people;
}
