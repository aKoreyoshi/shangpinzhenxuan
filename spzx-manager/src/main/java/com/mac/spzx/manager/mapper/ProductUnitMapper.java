package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月13日, 17:36:18
 */
@Mapper
public interface ProductUnitMapper {
    List<ProductUnit> getProductUnitList();
}