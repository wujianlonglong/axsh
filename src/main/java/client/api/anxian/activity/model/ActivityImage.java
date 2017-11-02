package client.api.anxian.activity.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by byinbo on 2017/10/18.
 */
@Data
public class ActivityImage implements Serializable{

    private Integer sort;

    private String imagePath;

    private String sn;
}
