package com.yuchu.controller;

import com.yuchu.VO.ProductInfoVO;
import com.yuchu.VO.ProductVO;
import com.yuchu.VO.ResultVO;
import com.yuchu.domain.ProductCategory;
import com.yuchu.domain.ProductInfo;
import com.yuchu.enums.ProductStatus;
import com.yuchu.service.CategoryService;
import com.yuchu.service.ProductService;
import com.yuchu.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 19:05  2017/8/27
 * @Modified By:
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有的上架商品
        List<ProductInfo> productInfoList=productService.findUpAll();
        //2.查询类目（一次性查询）
        //List<Integer> categoryTypeList= new ArrayList<>();
            //2.1传统for循环
            /*for (ProductInfo productInfo : productInfoList){
                categoryTypeList.add(productInfo.getCategoryType());
            }*/
            //2.2精简方法
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e ->e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList= categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory: productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}

