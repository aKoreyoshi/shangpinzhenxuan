package com.mac.spzx.manager.service;

import com.mac.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月09日, 11:19:05
 */
public interface CategoryService {

    // 查询商品分类  通过id“懒加载”
    List<Category> getCategoryList(Long parentId);

    // 导出数据
    void exportData(HttpServletResponse response);

    // 导入数据
    void importData(MultipartFile file);
}