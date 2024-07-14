package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月13日, 18:49:53
 */
@Mapper
public interface ProductSkuMapper {

    void saveProductSku(ProductSku productSku);

    List<ProductSku> selectByProductId(Long id);

    void updateById(ProductSku productSku);

    void deleteByProductId(Long id);
}