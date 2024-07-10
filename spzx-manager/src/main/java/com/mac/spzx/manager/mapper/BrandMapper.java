package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月10日, 16:31:41
 */
@Mapper
public interface BrandMapper {
    List<Brand> selectBrandList();

    void addBand(Brand brand);

    void updateBrand(Brand brand);

    void deleteBrand(Long id);
}