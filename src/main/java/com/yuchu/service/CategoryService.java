package com.yuchu.service;

import com.yuchu.domain.ProductCategory;

import java.util.List;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 17:35  2017/8/24
 * @Modified By:
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> CategoryTypeList);

    ProductCategory save(ProductCategory productCategory); 
}
