package anxian.gateway.admin.utils;

import lombok.Data;

import java.util.List;

/**
 * Created by gaoqichao on 16-1-15.
 */
@Data
public class AdminPage<T> {
    /**
     * 总数
     */
    private long total = 0;

    /**
     * 数据列表
     */
    private List<T> items;
}
