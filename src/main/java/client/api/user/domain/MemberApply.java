package client.api.user.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by SJOS on 2016-10-24.
 */
@Data
public class MemberApply implements Serializable {

    private Long id;

    /**
     * 用户id;
     */
    private Long userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String idCardNum;

    /**
     * 身份证正面图片URL
     */
    private String idCardFrontUrl;

    /**
     * 身份证反面图片URL
     */
    private String idCardBackUrl;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 审核状态
     */
    private Integer verifiStatus;

    /**
     * 审核结果
     */
    private Integer verifiResult;

    /**
     * 审核人
     */
    private String verifier;

    /**
     * 审核时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime verifiDate;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    private String createTimeStr; // 新建时间字符串

    /**
     * 更新时间
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedTime;

    private String updatedTimeStr; // 更新时间字符串

    private String remark; // 备注

    private String verifiDateStr; // 创建时间字符串

}

