package com.mac.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.manager.service.BrandCategoryService;
import com.mac.spzx.model.dto.product.CategoryBrandDto;
import com.mac.spzx.model.entity.product.CategoryBrand;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月12日, 11:53:39
 */
@Tag(name = "品牌分类管理")
@RestController
@RequestMapping("/admin/product/brandCategory")
public class BrandCategoryController {

    private BrandCategoryService brandCategoryService;

    public BrandCategoryController(BrandCategoryService brandCategoryService) {
        this.brandCategoryService = brandCategoryService;
    }

    @Operation(summary = "查询品牌分类列表")
    @GetMapping("/getPageList/{currentPage}/{pageSize}")
    public Result getPageList(@PathVariable("currentPage") Integer currentPage,
                              @PathVariable("pageSize") Integer pageSize,
                              CategoryBrandDto categoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = brandCategoryService
                .getPageList(currentPage, pageSize, categoryBrandDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "新增品牌分类")
    @PostMapping("/addBrandCategory")
    public Result addBrandCategory(@RequestBody CategoryBrand categoryBrand) {
        brandCategoryService.addBrandCategory(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改品牌分类")
    @PutMapping("/updateBrandCategory")
    public Result updateBrandCategory(@RequestBody CategoryBrand categoryBrand) {
        brandCategoryService.updateBrandCategory(categoryBrand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除品牌分类")
    @DeleteMapping("/deleteBrandCategory/{id}")
    public Result deleteBrandCategory(@PathVariable("id") Long id) {
        brandCategoryService.deleteBrandCategory(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}