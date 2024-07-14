package com.mac.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.manager.service.ProductService;
import com.mac.spzx.model.dto.product.ProductDto;
import com.mac.spzx.model.entity.product.Product;
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
 * @date: 2024年07月13日, 13:24:43
 */
@Tag(name = "商品管理")
@RestController
@RequestMapping("/admin/product/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "条件分页查询商品列表")
    @GetMapping("/getProductList/{currentPage}/{pageSize}")
    public Result getProductList(@PathVariable("currentPage") Integer currentPage,
                                 @PathVariable("pageSize") Integer pageSize,
                                 ProductDto productDto) {
        PageInfo<Product> pageInfo = productService
                .getProductList(currentPage, pageSize, productDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "添加商品")
    @PostMapping("/saveProduct")
    public Result saveProduct(@RequestBody Product product) {
        // TODO 前端图片上传失败，代码问题
        productService.saveProduct(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据id获取商品详情")
    @GetMapping("/getProductById/{id}")
    public Result getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        return Result.build(product , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "修改商品")
    @PutMapping("/updateProduct")
    public Result updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除商品")
    @DeleteMapping("/deleteProduct/{id}")
    public Result deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "审核商品")
    @PutMapping("/auditProduct/{id}/{auditStatus}")
    public Result auditProduct(@PathVariable("id") Long id,
                               @PathVariable("auditStatus") Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @Operation(summary = "商品上下架")
    @PutMapping("/updateProductStatus/{id}/{status}")
    public Result updateProductStatus(@PathVariable("id") Long id,
                                      @PathVariable("status") Integer status) {
        productService.updateProductStatus(id, status);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }


}