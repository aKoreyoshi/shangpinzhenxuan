package com.mac.spzx.manager.controller;

import com.mac.spzx.manager.service.ProductUnitService;
import com.mac.spzx.model.entity.base.ProductUnit;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月13日, 17:34:59
 */
@Tag(name = "商品计量单位管理")
@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {

    private ProductUnitService productUnitService;

    @Autowired
    public ProductUnitController(ProductUnitService productUnitService) {
        this.productUnitService = productUnitService;
    }

    @Operation(summary = "查询计量单位列表")
    @GetMapping("/getProductUnitList")
    public Result getProductUnitList() {
        List<ProductUnit> productUnitList = productUnitService.getProductUnitList();
        return Result.build(productUnitList, ResultCodeEnum.SUCCESS);
    }
}