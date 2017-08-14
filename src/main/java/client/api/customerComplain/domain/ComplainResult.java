package client.api.customerComplain.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Data;

import javax.persistence.Transient;
import java.time.LocalDateTime;

/**
 * Created by byinbo on 2017/3/23.
 */
@Data
public class ComplainResult {

    private Long id;

    /**
     * 投诉编号
     */
    private Long complainId;

    /**
     * 受理部门
     */
    private String receiveDept;

    /**
     * 受理部门编号
     */
    private String receiveDeptNum;

    /**
     * 受理时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime acceptTime;

    @Transient
    private String acceptTimeStr;

    /**
     * 处理完成时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime finishTime;

    @Transient
    private String finishTimeStr;

    /**
     * 是否转单
     */
    private boolean turn;

    /**
     * 转单部门
     */
    private String turnDept;

    /**
     * 转单部门编号
     */
    private String turnDeptNum;

    /**
     * 处理结果
     */
    private String result;


}
