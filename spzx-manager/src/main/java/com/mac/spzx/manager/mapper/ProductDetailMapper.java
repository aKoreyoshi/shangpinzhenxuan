package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月13日, 18:51:49
 */
@Mapper
public interface ProductDetailMapper {
    void saveProductDetails(ProductDetails productDetails);

    ProductDetails selectByProductId(Long id);

    void updateById(ProductDetails productDetails);

    void deleteByProductId(Long id);
}