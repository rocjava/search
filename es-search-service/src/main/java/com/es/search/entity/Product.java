package com.es.search.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * @author penn
 * @since 2019/12/19
 */
@Data
@ToString
@Document(indexName = "product", shards = 1, replicas = 0)
public class Product {

    /**
     * ID
     */
    @Id
    private Long id;

    /**
     * 租户ID
     */
    @Field(type = FieldType.Integer)
    private Long tenantId;

    /**
     * 平台
     */
    @Field(type = FieldType.Keyword)
    private String platform;

    /**
     * 平台SPU编码
     */
    @Field(type = FieldType.Keyword)
    private String platformSpuCode;

    /**
     * 编码
     */
    @Field(type = FieldType.Keyword)
    private String spuCode;

    /**
     * 分类ID
     */
    @Field(type = FieldType.Long)
    private Long categoryId;

    /**
     * 分类名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String categoryName;

    /**
     * 品牌ID
     */
    @Field(type = FieldType.Long)
    private Long brandId;

    /**
     * 品牌名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String brandName;

    /**
     * 类型 0-虚拟/1-实物
     */
    @Field(type = FieldType.Integer)
    private Integer spuType;

    /**
     * 品名
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String spuName;

    /**
     * 副品名
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String spuSubName;

    /**
     * 售价，总售价与分期无关
     */
    @Field(type = FieldType.Integer)
    private Integer salePrice;

    /**
     * 会员售价
     */
    @Field(type = FieldType.Integer)
    private Integer vipPrice;

    /**
     * 市场价
     */
    @Field(type = FieldType.Integer)
    private Integer marketPrice;

    /**
     * 主图
     */
    private String mainPic;

    /**
     * 媒体信息（视频或多图）
     */
    private String mediaInfo;

    /**
     * 详情 富文本
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String details;

    /**
     * 是否3C
     */
    @Field(type = FieldType.Boolean)
    private Boolean ccc;

    /**
     * 条码
     */
    @Field(type = FieldType.Keyword)
    private String barCode;

    /**
     * 二维码
     */
    @Field(type = FieldType.Keyword)
    private String qrCode;

    /**
     * 是否组合商品 0-否/1-是
     */
    @Field(type = FieldType.Boolean)
    private Boolean group;

    /**
     * 是否代发 供应商发货
     */
    @Field(type = FieldType.Boolean)
    private Boolean supplierSend;

    /**
     * 已上架渠道编码集 eg:['C20159']
     */
    @Field(type = FieldType.Keyword)
    private String channelList;

    /**
     * 产品经理ID
     */
    @Field(type = FieldType.Long)
    private Long productUserId;

    /**
     * 产品经理名称
     */
    @Field(type = FieldType.Keyword)
    private String productUserName;

    /**
     * 供应商ID
     */
    @Field(type = FieldType.Long)
    private Long supplierId;

    /**
     * 供应商名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String supplierName;

    /**
     * 审核状态
     */
    @Field(type = FieldType.Text)
    private String auditStatus;

    /**
     * 同步状态
     */
    @Field(type = FieldType.Text)
    private String syncStatus;

    /**
     * 创建人
     */
    @Field(type = FieldType.Text)
    private String createBy;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private Date createTime;

    /**
     * 更新人
     */
    @Field(type = FieldType.Text)
    private String updateBy;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Date)
    private Date updateTime;

    /**
     * 逻辑删除状态
     */
    @Field(type = FieldType.Boolean)
    private Boolean del;

}
