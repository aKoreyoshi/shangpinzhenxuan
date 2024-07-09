package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月09日, 11:19:27
 */
@Mapper
public interface CategoryMapper {
    List<Category> selectByParentId(Long parentId);

    Integer selectCount(Long id);
}