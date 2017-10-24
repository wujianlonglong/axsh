package client.api.anxian.activity.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by byinbo on 2017/10/18.
 */
@Data
public class ActivityGoods implements Serializable{

    private String sn;

    private String name;

    private String status;
}
