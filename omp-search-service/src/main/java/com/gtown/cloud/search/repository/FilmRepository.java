package com.gtown.cloud.search.repository;/**
 * @author penn
 * @since 2019/10/23
 */

import com.gtown.cloud.search.entity.Film;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description
 * @Author penn
 * @Date 2019-10-23
 */
public interface FilmRepository  extends ElasticsearchRepository<Film,Long> {

}
