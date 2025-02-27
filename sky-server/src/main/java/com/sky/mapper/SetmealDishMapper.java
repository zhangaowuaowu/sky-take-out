package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    List<Long> selectByDishId(List<Long> ids);

    @AutoFill(OperationType.INSERT)
    void saveBatch(List<SetmealDish> list);
}
