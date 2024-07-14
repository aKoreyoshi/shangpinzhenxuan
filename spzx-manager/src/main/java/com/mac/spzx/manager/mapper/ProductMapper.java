package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.dto.product.ProductDto;
import com.mac.spzx.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月13日, 13:26:33
 */
@Mapper
public interface ProductMapper {
    List<Product> selectByCondition(ProductDto productDto);

    void saveProduct(Product product);

    Product selectById(Long id);

    void updateById(Product product);

    void deleteById(Long id);
}