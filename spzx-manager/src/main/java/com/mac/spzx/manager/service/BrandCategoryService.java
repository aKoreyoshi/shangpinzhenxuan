package com.mac.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.model.dto.product.CategoryBrandDto;
import com.mac.spzx.model.entity.product.Brand;
import com.mac.spzx.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月12日, 11:54:26
 */
public interface BrandCategoryService {

    // 条件分页查询品牌分类列表
    PageInfo<CategoryBrand> getPageList(Integer currentPage, Integer pageSize, CategoryBrandDto categoryBrandDto);

    // 新增
    void addBrandCategory(CategoryBrand categoryBrand);

    // 修改
    void updateBrandCategory(CategoryBrand categoryBrand);

    // 删除
    void deleteBrandCategory(Long id);

    // 根据分类id获取品牌数据
    List<Brand> getBrandByCategoryId(Long categoryId);
}