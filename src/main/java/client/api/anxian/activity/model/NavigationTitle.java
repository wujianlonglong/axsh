package client.api.anxian.activity.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by byinbo on 2017/10/18.
 */
@Data
public class NavigationTitle implements Serializable{

    private String fontColor;

    private Boolean isBold;

    private String navigationImage;
}
