package com.mac.spzx.manager.service.impl;

import com.mac.spzx.manager.mapper.ProductUnitMapper;
import com.mac.spzx.manager.service.ProductUnitService;
import com.mac.spzx.model.entity.base.ProductUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月13日, 17:36:01
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    private ProductUnitMapper productUnitMapper;

    @Autowired
    public ProductUnitServiceImpl(ProductUnitMapper productUnitMapper) {
        this.productUnitMapper = productUnitMapper;
    }

    /**
     * 查询所有计量单位
     * @return List
     */
    @Override
    public List<ProductUnit> getProductUnitList() {
        List<ProductUnit> productUnits = productUnitMapper.getProductUnitList();
        return productUnits;
    }
}