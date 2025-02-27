package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相關接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 套餐分頁查詢
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @GetMapping("/page")
    @ApiOperation("分頁查詢")
    public Result<PageResult> page(SetmealPageQueryDTO dto) {
        PageResult pageResult = setmealService.pageQuery(dto);
        return Result.success(pageResult);
    }

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
    public Result save(@RequestBody SetmealDTO dto){
        setmealService.save(dto);
        return Result.success();
    }

    /**
     * 根據ID查詢套餐
     * @param 
     * @return 
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @GetMapping("/{id}")
    @ApiOperation("根據ID查詢套餐")
    public Result<SetmealVO> selectById(@PathVariable Long id){
        SetmealVO setmealVO = setmealService.selectById(id);
        return Result.success(setmealVO);
    }
    
    /**
     * 修改套餐
     * @param
     * @return 
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @PutMapping
    @ApiOperation("修改套餐")
    @Transactional(rollbackFor = Exception.class)
    public Result update(@RequestBody SetmealDTO dto){
        setmealService.update(dto);
        return Result.success();
    }

    /**
     * 套餐起售/停售
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售/停售")
    public Result startOrStop(@PathVariable Integer status, Long id){
        setmealService.startOrStop(status, id);
        return Result.success();
    }
    
    /**
     * 批量删除套餐
     * @param 
     * @return 
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteBatch(@RequestParam List<Long> ids){
        setmealService.deleteBatch(ids);
        return Result.success();
    }
}
