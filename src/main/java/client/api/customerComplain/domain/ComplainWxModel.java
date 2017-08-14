package client.api.customerComplain.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by byinbo on 2017/5/24.
 */
@Data
public class ComplainWxModel implements Serializable {

    private CustomerComplainWxModel customerComplainWxModel;

    private List<ComplainResult> complainResults;

    private List<String> shopIds;
}
