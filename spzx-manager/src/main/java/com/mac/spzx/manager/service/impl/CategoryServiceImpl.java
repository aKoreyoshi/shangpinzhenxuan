package com.mac.spzx.manager.service.impl;

import com.mac.spzx.manager.mapper.CategoryMapper;
import com.mac.spzx.manager.service.CategoryService;
import com.mac.spzx.model.entity.product.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月09日, 11:19:15
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getCategoryList(Long parentId) {
        // 根据id查询商品分类
        List<Category> categories = categoryMapper.selectByParentId(parentId);
        // 遍历商品分类，查询是否有子类
        categories.forEach(category -> {
            // 查询子类
            Integer count = categoryMapper.selectCount(category.getId());
            if (count > 0) {
                category.setHasChildren(true);
            } else {
                category.setHasChildren(false);
            }
        });
        return categories;
    }
}