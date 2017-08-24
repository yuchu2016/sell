package com.yuchu.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by dell on 2017/8/24.
 * product_category
 * 如果是s_product_category,需要加注解@Table(name="s_product_category")
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    /*类目ID*/
    @Id
    @GeneratedValue
    private Integer categoryId;

    /*类目名称*/
    private String categoryName;

    /*类目编号*/
    private Integer categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
