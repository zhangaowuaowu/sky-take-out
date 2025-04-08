package com.sky.controller.user;

import com.sky.dto.ShoppingCartDTO;
import com.sky.result.Result;
import com.sky.service.ShoppingCatrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/shoppingCart")
@Slf4j
@Api(tags = "C端購物車相關接口")
public class ShoppingCartController {

    @Autowired
    private ShoppingCatrService shoppingCatrService;

    @PostMapping("/add")
    @ApiOperation("添加購物車")
    public Result add(@RequestBody ShoppingCartDTO dto) {
        shoppingCatrService.add(dto);
        return Result.success();
    }
}
