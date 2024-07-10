package com.mac.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.model.entity.product.Brand;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月10日, 16:31:15
 */
public interface BrandService {

    // 查询品牌列表
    PageInfo<Brand> brandPageList(Integer currentPage, Integer pageSize);

    // 添加品牌信息
    void addBrand(Brand brand);

    // 修改品牌信息
    void updateBrand(Brand brand);

    // 删除品牌信息
    void deleteBrand(Long id);
}