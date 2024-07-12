package com.mac.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mac.spzx.manager.mapper.BrandMapper;
import com.mac.spzx.manager.service.BrandService;
import com.mac.spzx.model.entity.product.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月10日, 16:31:26
 */
@Service
public class BrandServiceImpl implements BrandService {

    private BrandMapper brandMapper;

    @Autowired
    public BrandServiceImpl(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    /**
     * 分页查询品牌列表
     * @param currentPage
     * @param pageSize
     * @return PageInfo<Brand>
     */
    @Override
    public PageInfo<Brand> brandPageList(Integer currentPage, Integer pageSize) {
        // 开启分页
        PageHelper.startPage(currentPage, pageSize);
        // 查询品牌列表
        List<Brand> brandList = brandMapper.selectBrandList();
        // 获取分页信息
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        return pageInfo;
    }

    /**
     * 添加品牌
     * @param brand
     */
    @Override
    public void addBrand(Brand brand) {
        brandMapper.addBand(brand);
    }

    /**
     * 修改品牌信息
     * @param brand
     */
    @Override
    public void updateBrand(Brand brand) {
        brandMapper.updateBrand(brand);
    }

    /**
     * 删除品牌信息
     * @param id
     */
    @Override
    public void deleteBrand(Long id) {
        brandMapper.deleteBrand(id);
    }

    /**
     * 查询品牌列表
     * @return
     */
    @Override
    public List<Brand> brandList() {
        List<Brand> brands = brandMapper.selectBrandList();
        return brands;
    }
}