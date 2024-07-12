package com.mac.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mac.spzx.manager.mapper.BrandCategoryMapper;
import com.mac.spzx.manager.service.BrandCategoryService;
import com.mac.spzx.model.dto.product.CategoryBrandDto;
import com.mac.spzx.model.entity.product.CategoryBrand;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月12日, 11:54:39
 */
@Service
public class BrandCategoryServiceImpl implements BrandCategoryService {

    private BrandCategoryMapper brandCategoryMapper;
    public BrandCategoryServiceImpl(BrandCategoryMapper brandCategoryMapper) {
        this.brandCategoryMapper = brandCategoryMapper;
    }

    /**
     * 条件分页查询品牌分类列表
     * @param currentPage
     * @param pageSize
     * @param categoryBrandDto
     * @return
     */
    @Override
    public PageInfo<CategoryBrand> getPageList(Integer currentPage, Integer pageSize, CategoryBrandDto categoryBrandDto) {
        // 开启分页
        PageHelper.startPage(currentPage, pageSize);
        // 查询品牌分类列表
        List<CategoryBrand> categoryBrands = brandCategoryMapper.selectByCondition(categoryBrandDto);
        // 获取分页结果
        PageInfo<CategoryBrand> page = new PageInfo<>(categoryBrands);
        return page;
    }

    /**
     * 新增品牌分类
     * @param categoryBrand
     */
    @Override
    public void addBrandCategory(CategoryBrand categoryBrand) {
        brandCategoryMapper.insert(categoryBrand);
    }

    /**
     * 更新品牌分类
     * @param categoryBrand
     */
    @Override
    public void updateBrandCategory(CategoryBrand categoryBrand) {
        brandCategoryMapper.update(categoryBrand);
    }

    /**
     * 删除品牌分类
     * @param id
     */
    @Override
    public void deleteBrandCategory(Long id) {
        brandCategoryMapper.deleteById(id);
    }
}