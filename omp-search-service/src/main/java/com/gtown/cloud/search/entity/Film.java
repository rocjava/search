package com.gtown.cloud.search.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 示例2
 * @author penn.zhang
 * @since 2019/10/23
 */
@Data
@Document(indexName = "film",type = "action")
public class Film {
    /**
     * 主键
     */
    @Id
    private Long id;

    @Field(index = true,analyzer = "ik_max_word",store = true,searchAnalyzer = "ik_max_word",type = FieldType.Text)
    private String title;

    @Field(index = true,analyzer = "ik_max_word",store = true,searchAnalyzer = "ik_max_word",type = FieldType.Text)
    private String content;

    @Field(index = true,analyzer = "ik_max_word",store = true,searchAnalyzer = "ik_max_word",type = FieldType.Text)
    private String date;

    @Field(index = true,analyzer = "ik_max_word",store = true,searchAnalyzer = "ik_max_word",type = FieldType.Text)
    private String price;

    @Field(index = true,analyzer = "ik_max_word",store = true,searchAnalyzer = "ik_max_word",type = FieldType.Text)
    private String director;

}