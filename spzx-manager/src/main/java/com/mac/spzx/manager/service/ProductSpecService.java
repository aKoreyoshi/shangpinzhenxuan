package com.mac.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.model.entity.product.ProductSpec;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月12日, 20:47:17
 */
public interface ProductSpecService {

    // 查询规格列表
    PageInfo<ProductSpec> getSpecList(Integer currentPage, Integer pageSize);

    // 新增商品规格
    void addSpec(ProductSpec productSpec);

    // 修改商品规格
    void updateSpec(ProductSpec productSpec);

    // 删除商品规格
    void deleteSpec(Long id);

    // 获取商品规格列表
    List<ProductSpec> specList();
}