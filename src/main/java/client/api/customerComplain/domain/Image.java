package client.api.customerComplain.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by byinbo on 2017/6/1.
 */
@Data
public class Image implements Serializable {

    private String img;

    public Image(String img){
        this.img = img;
    }
}
