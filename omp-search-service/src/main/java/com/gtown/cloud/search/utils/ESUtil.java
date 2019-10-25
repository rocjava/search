package com.gtown.cloud.search.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

/**
 * @author penn
 * @since 2019/10/23
 */
@Component
public class ESUtil {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

}
