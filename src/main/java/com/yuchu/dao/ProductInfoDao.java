package com.yuchu.dao;

import com.yuchu.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 16:39  2017/8/27
 * @Modified By:
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String>{

    List<ProductInfo> findByProductStatus(Integer productStatus);

}
