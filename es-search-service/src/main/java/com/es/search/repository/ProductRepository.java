package com.es.search.repository;


import com.es.search.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author penn
 * @since 2019/12/19
 */
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {


}
