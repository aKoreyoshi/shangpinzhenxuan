package com.mac.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mac.spzx.manager.mapper.ProductDetailMapper;
import com.mac.spzx.manager.mapper.ProductMapper;
import com.mac.spzx.manager.mapper.ProductSkuMapper;
import com.mac.spzx.manager.service.ProductService;
import com.mac.spzx.model.dto.product.ProductDto;
import com.mac.spzx.model.entity.product.Product;
import com.mac.spzx.model.entity.product.ProductDetails;
import com.mac.spzx.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月13日, 13:25:53
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductMapper productMapper;
    private ProductSkuMapper productSkuMapper;
    private ProductDetailMapper productDetailMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper,
                              ProductSkuMapper productSkuMapper,
                              ProductDetailMapper productDetailMapper) {
        this.productMapper = productMapper;
        this.productSkuMapper = productSkuMapper;
        this.productDetailMapper = productDetailMapper;
    }

    /**
     * 分页条件查询商品列表
     *
     * @param currentPage
     * @param pageSize
     * @param productDto
     * @return PageInfo
     */
    @Override
    public PageInfo<Product> getProductList(Integer currentPage, Integer pageSize, ProductDto productDto) {
        PageHelper.startPage(currentPage, pageSize);
        List<Product> products = productMapper.selectByCondition(productDto);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        return pageInfo;
    }

    /**
     * 添加商品
     *
     * @param product
     */
    @Transactional
    @Override
    public void saveProduct(Product product) {
        // 在product表中添加基础信息
        // 设置基础信息
        product.setStatus(0);
        product.setAuditStatus(0);
        productMapper.saveProduct(product);

        // 在product_sku表中添加商品详情信息
        List<ProductSku> skuList = product.getProductSkuList();
        skuList.forEach(productSku -> {
            // 生成商品skuId
            productSku.setProductId(product.getId());
            productSku.setStatus(0);
            productSku.setSkuCode(String.valueOf(System.currentTimeMillis()));
            productSku.setSkuName(product.getName() + productSku.getSkuSpec());
            productSku.setSaleNum(0);
            productSkuMapper.saveProductSku(productSku);
        });
        // 在product_detail表中添加商品图片信息
        ProductDetails productDetails = new ProductDetails();
        String detailsImageUrls = product.getDetailsImageUrls();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(detailsImageUrls);
        productDetailMapper.saveProductDetails(productDetails);
    }


    /**
     * 根据id获取商品详情
     *
     * @param id
     * @return Product
     */
    @Transactional
    @Override
    public Product getProductById(Long id) {
        // 根据id查询商品数据
        Product product = productMapper.selectById(id);
        // 根据商品的id查询sku数据
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(id);
        product.setProductSkuList(productSkuList);

        // 根据商品的id查询商品详情数据
        ProductDetails productDetails = productDetailMapper.selectByProductId(product.getId());
        product.setDetailsImageUrls(productDetails.getImageUrls());
        return product;
    }

    /**
     * 更新商品
     *
     * @param product
     */
    @Transactional
    @Override
    public void updateProduct(Product product) {
        // 修改商品基本数据
        productMapper.updateById(product);

        // 修改商品的sku数据
        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);
        });

        // 修改商品的详情数据
        ProductDetails productDetails = productDetailMapper.selectByProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailMapper.updateById(productDetails);
    }

    /**
     * 删除商品
     *
     * @param id
     */
    @Transactional
    @Override
    public void deleteProduct(Long id) {
        productMapper.deleteById(id);                   // 根据id删除商品基本数据
        productSkuMapper.deleteByProductId(id);         // 根据商品id删除商品的sku数据
        productDetailMapper.deleteByProductId(id);     // 根据商品的id删除商品的详情数据
    }

    /**
     * 更新商品审核状态
     *
     * @param id
     * @param auditStatus
     */
    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if (auditStatus == 1) {
            product.setAuditStatus(1);
            product.setAuditMessage("审核通过");
        } else {
            product.setAuditStatus(-1);
            product.setAuditMessage("审核不通过");
        }
        productMapper.updateById(product);
    }

    /**
     * 更新商品状态  ->上下架
     *
     * @param id
     * @param status
     */
    @Override
    public void updateProductStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if (status == 1) {
            product.setStatus(1);
        } else {
            product.setStatus(-1);
        }
        productMapper.updateById(product);
    }
}