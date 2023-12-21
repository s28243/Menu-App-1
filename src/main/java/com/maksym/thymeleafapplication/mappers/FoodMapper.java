package com.maksym.thymeleafapplication.mappers;

import com.maksym.thymeleafapplication.model.Food;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.ResponseEntity;

@Mapper
public interface FoodMapper {
    @Select("select * from food")
    Food[] selectedFood();

    @Insert("insert into food values (#{id}, #{name}, #{price})")
    int saveFood(Food food);

    @Select("select max(id)+1 from food")
    int getNextId();
    @Update("update food set price = #{price} where name = #{name}")
    void updatePrice(Food food);
    @Select("select * from food where food.name = #{name}")
    Food[] ifExists(Food food);
}
