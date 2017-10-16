package client.api.sale.model.secKill;

import client.api.user.utils.page.CustomSortDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoqichao on 15-9-11. 给定对象的分页结果
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SjesPage<T> extends PageImpl<T> {
    /**
     * 是否第一页
     */
    private boolean first;
    /**
     * 是否最后一页
     */
    private boolean last;
    /**
     * 总页数
     */
    private int totalPages;
    /**
     * 结果总数
     */
    private long totalElements;
    /**
     * 排序对象
     */
    private Sort sort;
    /**
     * 当前页的元素数目
     */
    private int numberOfElements;
    /**
     * 每页显示的最大数目
     */
    private int size;
    /**
     * 当前页页码，从0开始计算
     */
    private int number;
    /**
     * 对象列表
     */
    private List<T> content = new ArrayList<T>();

    public SjesPage() {
        super(new ArrayList<>());
    }

    @JsonDeserialize(using = CustomSortDeserializer.class)
    public void setSort(Sort sort) {
        this.sort = sort;
    }

}
