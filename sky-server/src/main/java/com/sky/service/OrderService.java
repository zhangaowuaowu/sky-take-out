package com.sky.service;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.vo.OrderSubmitVO;

public interface OrderService {

    /**
     * 用戶下單
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO dto);
}
