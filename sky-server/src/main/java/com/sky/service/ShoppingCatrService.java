package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCatrService {

    /**
     * 添加購物車
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    void add(ShoppingCartDTO dto);

    /**
     * 查詢購物車
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    List<ShoppingCart> showShoppingCart();

    /**
     * 清空購物車
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    void cleanShoppingCart();
}
