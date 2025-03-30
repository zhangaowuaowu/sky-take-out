package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    /**
     * 新增菜品與口味
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    void saveWithFlavor(DishDTO dto);

    /**
     * 菜品分頁列表
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 10:12:33 Version:1.0
     * change Description:
     */
    PageResult pageQuery(DishPageQueryDTO dto);

    /**
     * 分類id查詢菜品
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 10:29:52 Version:1.0
     * change Description:
     */
    List<DishVO> list(Integer categoryId);

    /**
     * id查詢菜品
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 10:40:53 Version:1.0
     * change Description:
     */
    DishVO selectById(Long id);

    /**
     * 批量刪除菜品
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 11:27:20 Version:1.0
     * change Description:
     */
    void deleteBatch(List<Long> ids);

    /**
     * 更新菜品
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 13:21:20 Version:1.0
     * change Description:
     */
    void update(DishDTO dto);

    /**
     * 菜品起售停售
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 14:21:51 Version:1.0
     * change Description:
     */
    void startOrStopDish(Integer status, Long id);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
