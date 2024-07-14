package com.mac.spzx.manager.service;

import com.mac.spzx.model.entity.base.ProductUnit;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月13日, 17:35:52
 */
public interface ProductUnitService {

    // 查询所有计量单位
    List<ProductUnit> getProductUnitList();
}