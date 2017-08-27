package com.yuchu.service.Impl;

import com.yuchu.dao.ProductCategoryDao;
import com.yuchu.domain.ProductCategory;
import com.yuchu.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 19:06  2017/8/24
 * @Modified By:
 */
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private ProductCategoryDao dao;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return dao.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return dao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypeList) {
        return dao.findByCategoryTypeIn(CategoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return dao.save(productCategory);
    }
}
