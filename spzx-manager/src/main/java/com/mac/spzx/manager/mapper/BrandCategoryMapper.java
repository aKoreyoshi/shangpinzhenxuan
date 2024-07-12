package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.dto.product.CategoryBrandDto;
import com.mac.spzx.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月12日, 11:55:18
 */
@Mapper
public interface BrandCategoryMapper {
    List<CategoryBrand> selectByCondition(CategoryBrandDto categoryBrandDto);

    void insert(CategoryBrand categoryBrand);

    void update(CategoryBrand categoryBrand);

    void deleteById(Long id);
}