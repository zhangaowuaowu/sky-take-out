package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import com.sky.result.Result;
import com.sky.service.ShoppingCatrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "C端購物車相關接口")
public class ShoppingCartController {

    @Autowired
    private ShoppingCatrService shoppingCatrService;

    @PostMapping("/add")
    @ApiOperation("添加購物車")
    @Transactional(rollbackFor = Exception.class)
    public Result add(@RequestBody ShoppingCartDTO dto) {
        shoppingCatrService.add(dto);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("查詢購物車")
    public Result<List<ShoppingCart>> list() {
        List<ShoppingCart> list = shoppingCatrService.showShoppingCart();
        return Result.success(list);
    }

    @DeleteMapping("/clean")
    @ApiOperation("清空購物車")
    @Transactional(rollbackFor = Exception.class)
    public Result clean() {
        shoppingCatrService.cleanShoppingCart();
        return Result.success();
    }
}
