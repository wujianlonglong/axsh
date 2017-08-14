package client.api.customerComplain.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by byinbo on 2017/7/13.
 */
@Data
public class ComplainStatu implements Serializable, Comparable<ComplainStatu> {
    private Integer order;

    private Integer complainStat;

    private String statName;

    private String imageUrl;

    private Long statCounts;

    @Override
    public int compareTo(ComplainStatu o) {
        int i = this.getOrder() - o.getOrder();
        return i;
    }
}
