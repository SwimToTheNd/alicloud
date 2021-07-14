package com.stte.alicloud.order.service;


import com.stte.alicloud.order.entity.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    Long create(Order order);
}