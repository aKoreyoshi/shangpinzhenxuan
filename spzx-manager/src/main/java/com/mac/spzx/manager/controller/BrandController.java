package com.mac.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.manager.service.BrandService;
import com.mac.spzx.model.entity.product.Brand;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月10日, 16:30:24
 */
@Tag(name = "品牌管理", description = "品牌管理")
@RestController
@RequestMapping("/admin/product/brand")
public class BrandController {

    private BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Operation(summary = "分页查询品牌列表")
    @GetMapping("/brandPageList/{currentPage}/{pageSize}")
    public Result brandPageList(@PathVariable("currentPage") Integer currentPage,
                                @PathVariable("pageSize") Integer pageSize) {
        PageInfo<Brand> pageInfo = brandService.brandPageList(currentPage, pageSize);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "新增品牌")
    @PostMapping("/addBrand")
    public Result addBrand(@RequestBody Brand brand) {
        brandService.addBrand(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改品牌")
    @PutMapping("/updateBrand")
    public Result updateBrand(@RequestBody Brand brand) {
        brandService.updateBrand(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除品牌")
    @DeleteMapping("/deleteBrand/{id}")
    public Result deleteBrand(@PathVariable("id") Long id) {
        brandService.deleteBrand(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}