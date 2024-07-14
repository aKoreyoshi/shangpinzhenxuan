package com.mac.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.model.dto.product.ProductDto;
import com.mac.spzx.model.entity.product.Product;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月13日, 13:25:43
 */
public interface ProductService {

    // 查询商品列表
    PageInfo<Product> getProductList(Integer currentPage, Integer pageSize, ProductDto productDto);

    // 添加商品
    void saveProduct(Product product);

    // 根据id获取商品详情
    Product getProductById(Long id);

    // 修改商品
    void updateProduct(Product product);

    // 删除商品
    void deleteProduct(Long id);

    // 审核商品
    void updateAuditStatus(Long id, Integer auditStatus);

    // 上下架商品
    void updateProductStatus(Long id, Integer status);
}