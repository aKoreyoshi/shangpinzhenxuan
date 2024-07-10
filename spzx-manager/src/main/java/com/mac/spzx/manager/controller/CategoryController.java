package com.mac.spzx.manager.controller;

import com.mac.spzx.manager.service.CategoryService;
import com.mac.spzx.model.entity.product.Category;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月09日, 11:18:17
 */
@Tag(name = "分类管理", description = "分类管理")
@RestController
@RequestMapping("/admin/product/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "查询分类")
    @GetMapping("/getCategoryList/{parentId}")
    public Result getCategoryList(@PathVariable("parentId") Long parentId) {
        List<Category> categories = categoryService.getCategoryList(parentId);
        return Result.build(categories, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "导出数据")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }

    @Operation(summary = "导入数据")
    @PostMapping("/importData")
    public Result importData(MultipartFile file) {
        categoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}