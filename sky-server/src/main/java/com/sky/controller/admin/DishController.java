package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.vo.DishVO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品相關接口")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @PostMapping
    @ApiOperation("新增菜品")
    @Transactional(rollbackFor = Exception.class)
    public Result save (@RequestBody DishDTO dto) {
        dishService.saveWithFlavor(dto);

        // 新增時清理緩存數據
        String key = "dish_" + dto.getCategoryId();
        this.clearCache(key);
        return Result.success();
    }

    /**
     * 菜品分頁列表
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 10:12:33 Version:1.0
     * change Description:
     */
    @GetMapping("/page")
    @ApiOperation("菜品分頁列表")
    public Result<PageResult> page(DishPageQueryDTO dto) {
        PageResult pageResult = dishService.pageQuery(dto);
        return Result.success(pageResult);
    }
    
    /**
     * 分類id查詢菜品
     * @param
     * @return 
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 10:29:52 Version:1.0
     * change Description:
     */
    @GetMapping("/list")
    @ApiOperation("分類id查詢菜品")
    public Result<List<Dish>> list (Integer categoryId) {
        // 使用redis做緩存
        // 構造redis中的key，規則：dish_分類id
        String key = "dish_" + categoryId;

        // 查詢redis中是否存在菜品數據
        List<Dish> dishList = (List<Dish>) redisTemplate.opsForValue().get(key);
        if (dishList != null && dishList.size() > 0) {
            // 如果存在，直接回傳redis中資料。
            return Result.success(dishList);
        }

        // 如果不存在則查詢數據庫，并且放入redis中。
        List<DishVO> dishVOList = dishService.list(categoryId);
        // 暫時用spring原生的方法來做list轉換
        dishList = dishVOList.stream().map(dishVO -> {
            Dish dish = new Dish();
            BeanUtils.copyProperties(dishVO, dish);
            return dish;
        }).collect(Collectors.toList());
        redisTemplate.opsForValue().set(key, dishList);
        return Result.success(dishList);
    }

    /**
     * id查詢菜品
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 10:40:53 Version:1.0
     * change Description:
     */
    @GetMapping("/{id}")
    @ApiOperation("id查詢菜品")
    public Result<DishVO> selectById (@PathVariable Long id) {
        DishVO dishVO = dishService.selectById(id);
        return Result.success(dishVO);
    }

    /**
     * 批量刪除菜品
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 11:27:20 Version:1.0
     * change Description:
     */
    @DeleteMapping
    @ApiOperation("批量刪除菜品")
    @Transactional(rollbackFor = Exception.class)
    public Result delete(@RequestParam List<Long> ids){
        dishService.deleteBatch(ids);

        // 刪除時清空緩存
        this.clearCache("dish_*");
        return Result.success();
    }

    /**
     * 更新菜品
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 13:21:20 Version:1.0
     * change Description:
     */
    @PutMapping
    @ApiOperation("更新菜品")
    @Transactional(rollbackFor = Exception.class)
    public Result update(@RequestBody DishDTO dto) {
        dishService.update(dto);

        // 刪除時清空緩存
        this.clearCache("dish_*");
        return Result.success();
    }

    /**
     * 菜品起售停售
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-26 14:21:51 Version:1.0
     * change Description:
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品起售停售")
    @Transactional(rollbackFor = Exception.class)
    public Result startOrStopDish(@PathVariable Integer status, Long id) {
        dishService.startOrStopDish(status, id);

        // 刪除時清空緩存
        this.clearCache("dish_*");
        return Result.success();
    }

    private void clearCache(String pattern) {
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
