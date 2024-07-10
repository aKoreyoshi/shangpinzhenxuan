package com.mac.spzx.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.mac.spzx.common.exception.KoreyoshiException;
import com.mac.spzx.manager.listener.ExcelListener;
import com.mac.spzx.manager.mapper.CategoryMapper;
import com.mac.spzx.manager.service.CategoryService;
import com.mac.spzx.model.entity.product.Category;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import com.mac.spzx.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月09日, 11:19:15
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> getCategoryList(Long parentId) {
        // 根据id查询商品分类
        List<Category> categories = categoryMapper.selectByParentId(parentId);
        // 遍历商品分类，查询是否有子类
        categories.forEach(category -> {
            // 查询子类
            Integer count = categoryMapper.selectCount(category.getId());
            if (count > 0) {
                category.setHasChildren(true);
            } else {
                category.setHasChildren(false);
            }
        });
        return categories;
    }


    /**
     * 导出商品分类
     *
     * @param response
     */
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            // 设置响应信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 设置头信息
            String fileName = URLEncoder.encode("商品分类", "UTF-8");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + fileName + ".xlsx");
            // 从数据库获取分类数据
            List<Category> categories = categoryMapper.selectAll();
            // 数据处理
            List<CategoryExcelVo> cevos = categories.stream().map(category -> {
                CategoryExcelVo cevo = new CategoryExcelVo();
                BeanUtils.copyProperties(category, cevo, CategoryExcelVo.class);
                return cevo;
            }).collect(Collectors.toList());
            // 导出
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                    .sheet("category").doWrite(cevos);
        } catch (Exception e) {
            throw new KoreyoshiException(ResultCodeEnum.DATA_ERROR);
        }

    }

    /**
     * 导入商品分类
     *
     * @param file
     */
    @Override
    public void importData(MultipartFile file) {
        try {
            ExcelListener<CategoryExcelVo> listener = new ExcelListener<CategoryExcelVo>(categoryMapper);
            EasyExcel.read(file.getInputStream(), CategoryExcelVo.class, listener).sheet().doRead();
        } catch (IOException e) {
            throw new KoreyoshiException(ResultCodeEnum.DATA_ERROR);
        }
    }
}