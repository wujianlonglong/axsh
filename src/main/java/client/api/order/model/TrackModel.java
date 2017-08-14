package client.api.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by gaoqichao on 16-1-26.
 */
@Data
public class TrackModel {
    /**
     * 时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime time;

    /**
     * 物流信息
     */
    String message;
}
