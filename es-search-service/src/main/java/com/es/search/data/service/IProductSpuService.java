package com.es.search.data.service;

import com.es.search.data.entity.ProductSpu;

import java.util.List;

/**
 * @author penn
 * @since 2019/12/19
 */
public interface IProductSpuService {

    /**
     * 查询所有商品
     * @param spu 商品参数
     */
    List<ProductSpu> queryAllProducts(ProductSpu spu, int page, int pageSize);
}
