package com.mac.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.manager.service.ProductSpecService;
import com.mac.spzx.model.entity.product.ProductSpec;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月12日, 20:46:28
 */
@Tag(name = "商品规格管理", description = "商品规格管理")
@RestController
@RequestMapping("/admin/product/productSpec")
public class ProductSpecController {

    private ProductSpecService productSpecService;
    @Autowired
    public ProductSpecController(ProductSpecService productSpecService) {
        this.productSpecService = productSpecService;
    }

    @Operation(summary = "查询商品规格列表")
    @GetMapping("/getSpecList/{currentPage}/{pageSize}")
    public Result getSpecList(@PathVariable("currentPage") Integer currentPage,
                              @PathVariable("pageSize") Integer pageSize) {
        PageInfo<ProductSpec> page = productSpecService.getSpecList(currentPage,pageSize);
        return Result.build(page, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "新增商品规格")
    @PostMapping("/addSpec")
    public Result addSpec(@RequestBody ProductSpec productSpec) {
        productSpecService.addSpec(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改商品规格")
    @PutMapping("/updateSpec")
    public Result updateSpec(@RequestBody ProductSpec productSpec) {
        productSpecService.updateSpec(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除商品规格")
    @DeleteMapping("/deleteSpec/{id}")
    public Result deleteSpec(@PathVariable("id") Long id) {
        productSpecService.deleteSpec(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取商品规格列表")
    @GetMapping("/specList")
    public Result specList() {
        List<ProductSpec> list = productSpecService.specList();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }
}