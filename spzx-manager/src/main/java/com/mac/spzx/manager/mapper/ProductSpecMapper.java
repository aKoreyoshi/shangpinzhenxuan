package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月12日, 20:47:41
 */
@Mapper
public interface ProductSpecMapper {

    List<ProductSpec> getSpecList();

    void addSpec(ProductSpec productSpec);

    void updateSpec(ProductSpec productSpec);

    void deleteSpec(Long id);
}