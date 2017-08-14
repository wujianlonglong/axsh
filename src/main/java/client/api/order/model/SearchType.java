package client.api.order.model;

/**
 * 后台查询条件
 * Created by kimiyu on 16/2/6.
 */
public interface SearchType {
    /**
     * 待拣货
     */
    int treatGoods = 1;
    /**
     * 已拣货
     */
    int completeGoods = 2;
    /**
     * 订单查询[拣货]
     */
    int orderSearchForPicking = 3;
}
