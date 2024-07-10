package com.mac.spzx.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;
import com.mac.spzx.manager.mapper.CategoryMapper;
import com.mac.spzx.manager.service.CategoryService;
import com.mac.spzx.model.vo.product.CategoryExcelVo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月10日, 12:13:33
 */
public class ExcelListener<T> extends AnalysisEventListener<T> {


    private CategoryMapper categoryMapper;

    public ExcelListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<CategoryExcelVo> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(T data, AnalysisContext context) {
        cachedDataList.add(((CategoryExcelVo) data));
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 清除缓存
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    private void saveData() {
        categoryMapper.insertBatch(cachedDataList);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // excel解析完毕以后需要执行的代码
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }
}