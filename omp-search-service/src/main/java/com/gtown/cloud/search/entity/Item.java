package com.gtown.cloud.search.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author penn
 * @since 2019/10/22
 */
@Data
@Document(indexName = "test_index",type = "item", shards = 1, replicas = 0)
public class Item {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String attrs;

    @Field(type = FieldType.Keyword, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String category;

    @Field(type = FieldType.Keyword, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String brand;

    @Field(type = FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Boolean)
    private Boolean isOnSale;

    @Field(index = false, type = FieldType.Keyword)
    private String images;

    @Field(type = FieldType.Date)
    private Date createTime;

    public Item(){}

    public Item(Long id, String title, String attrs, String category, String brand, Integer price, Boolean isOnSale, String images, Date createTime) {
        this.id = id;
        this.title = title;
        this.attrs = attrs;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.isOnSale = isOnSale;
        this.images = images;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", attrs='" + attrs + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", isOnSale=" + isOnSale +
                ", images='" + images + '\'' +
                ", createTime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(createTime) +
                '}';
    }
}
