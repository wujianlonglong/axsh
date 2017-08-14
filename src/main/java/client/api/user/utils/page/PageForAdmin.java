package client.api.user.utils.page;

import client.api.customerComplain.domain.ComplainStatu;
import lombok.Data;

import java.util.List;

/**
 * Created by gaoqichao on 15-12-1.
 */
@Data
public class PageForAdmin<T> {
    /**
     * 总数
     */
    long totalCount;
    /**
     * 数据列表
     */
    List<T> list;

    /**
     * 是否区总
     */
    boolean area;

    /**
     * 用户下属门店
     */
    List<String> shops;

    /**
     * 客诉状态
     */
    List<ComplainStatu> complainStatus;

}
