package com.es.search.data.service.impl;

import com.es.search.data.entity.ProductSpu;
import com.es.search.data.mapper.ProductSpuMapper;
import com.es.search.data.service.IProductSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author penn
 * @since 2019/12/19
 */
@Service
public class ProductSpuServiceImpl implements IProductSpuService {

    @Autowired
    private ProductSpuMapper productSpuMapper;


    @Override
    public List<ProductSpu> queryAllProducts(ProductSpu spu, int page, int pageSize) {
        int start = (page -1) * pageSize;
        return   productSpuMapper.select(spu, start);
    }
}
