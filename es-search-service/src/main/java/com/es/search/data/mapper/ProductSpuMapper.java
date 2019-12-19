package com.es.search.data.mapper;

import com.es.search.data.entity.ProductSpu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author penn.zhang
 */
@Repository
public interface ProductSpuMapper {
    /**
     * 条件查询
     * @param p 参数
     * @param start 起始
     * @param end 结束
     * @return List<ProductSpu>
     */
    List<ProductSpu> select(@Param("q") ProductSpu p, @Param("start")int start, @Param("end")int end);

    /**
     * id查询
     * @param id 参数
     * @return ProductSpu
     */
    ProductSpu selectById(Long id);
}