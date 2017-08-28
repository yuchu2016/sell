package com.yuchu.service;

import com.yuchu.domain.ProductInfo;
import com.yuchu.dto.CartDTO;
import com.yuchu.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yuchu
 * @Description:商品
 * @Date: Create in 17:11  2017/8/27
 * @Modified By:
 */
public interface ProductService {

    ProductInfo findOne(String productId);
    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
