package com.mac.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mac.spzx.manager.mapper.ProductSpecMapper;
import com.mac.spzx.manager.service.ProductSpecService;
import com.mac.spzx.model.entity.product.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月12日, 20:47:28
 */
@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    private ProductSpecMapper productSpecMapper;
    @Autowired
    public ProductSpecServiceImpl(ProductSpecMapper productSpecMapper) {
        this.productSpecMapper = productSpecMapper;
    }

    /**
     * 查询规格列表
     * @param currentPage
     * @param pageSize
     * @return PageInfo
     */
    @Override
    public PageInfo<ProductSpec> getSpecList(Integer currentPage, Integer pageSize) {
        // 开启分页
        PageHelper.startPage(currentPage, pageSize);
        // 查询规格列表
        List<ProductSpec> list = productSpecMapper.getSpecList();
        // 获取分页信息
        PageInfo<ProductSpec> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public void addSpec(ProductSpec productSpec) {
        // 保存商品规格
        productSpecMapper.addSpec(productSpec);
    }

    @Override
    public void updateSpec(ProductSpec productSpec) {
        // 修改商品规格
        productSpecMapper.updateSpec(productSpec);
    }

    @Override
    public void deleteSpec(Long id) {
        // 删除商品规格
        productSpecMapper.deleteSpec(id);
    }

    /**
     * 获取商品规格列表
     * @return List
     */
    @Override
    public List<ProductSpec> specList() {
        // 获取商品规格列表
        List<ProductSpec> list = productSpecMapper.getSpecList();
        return list;
    }
}