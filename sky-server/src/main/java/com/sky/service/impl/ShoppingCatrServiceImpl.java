package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCatrService;
import com.sky.vo.DishVO;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCatrServiceImpl implements ShoppingCatrService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 添加購物車
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Override
    public void add(ShoppingCartDTO dto) {
        // 判斷當前加入到購物車的商品是否存在
        ShoppingCart shopingCart = new ShoppingCart();
        BeanUtils.copyProperties(dto, shopingCart);
        Long userId = BaseContext.getCurrentId();
        shopingCart.setUserId(userId);

        List<ShoppingCart> list = shoppingCartMapper.list(shopingCart);
        // 如果存在，只需要物品數量加一
        if(list != null && !list.isEmpty()) {
            ShoppingCart cart = list.get(0);
            cart.setNumber(cart.getNumber() + 1);

            shoppingCartMapper.updateNumberById(cart);
        } else {
            // 如果不存儲，需要插入一條購物車數據
            Long dishId = dto.getDishId();
            if (dishId != null) {
                DishVO dishVO = dishMapper.selectById(dishId);
                shopingCart.setName(dishVO.getName());
                shopingCart.setImage(dishVO.getImage());
                shopingCart.setAmount(dishVO.getPrice());
            } else {
                Long setmealId = dto.getSetmealId();
                SetmealVO setmealVO = setmealMapper.selectById(setmealId);
                shopingCart.setName(setmealVO.getName());
                shopingCart.setImage(setmealVO.getImage());
                shopingCart.setAmount(setmealVO.getPrice());
            }
            shopingCart.setNumber(1);
            shopingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shopingCart);
        }
    }

    /**
     * 查詢購物車
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Override
    public List<ShoppingCart> showShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUserId(userId);
        List<ShoppingCart> cartList= shoppingCartMapper.list(shoppingCart);
        return cartList;
    }

    /**
     * 清空購物車
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Override
    public void cleanShoppingCart() {
        Long userId = BaseContext.getCurrentId();
        shoppingCartMapper.deleteByUserId(userId);
    }
}
