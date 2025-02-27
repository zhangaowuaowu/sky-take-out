package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result save(@RequestBody SetmealDTO dto){
        setmealService.save(dto);
        return Result.success();
    }
}
