package com.es.search.data.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author penn.zhang
 */
@Data
public class ProductSpu {

    /**
     * ID
     */
    private Long id;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 平台
     */
    private String platform;

    /**
     * 平台SPU编码
     */
    private String platformSpuCode;

    /**
     * 编码
     */
    private String spuCode;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 类型 0-虚拟/1-实物
     */
    private Integer spuType;

    /**
     * 品名
     */
    private String spuName;

    /**
     * 副品名
     */
    private String spuSubName;

    /**
     * 售价，总售价与分期无关
     */
    private Integer salePrice;

    /**
     * 会员售价
     */
    private Integer vipPrice;

    /**
     * 市场价
     */
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
    private String details;

    /**
     * 是否3C
     */
    private Boolean ccc;

    /**
     * 条码
     */
    private String barCode;

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * 是否组合商品 0-否/1-是
     */
    private Boolean group;

    /**
     * 是否代发 供应商发货
     */
    private Boolean supplierSend;

    /**
     * 已上架渠道编码集 eg:['C20159']
     */
    private String channelList;

    /**
     * 产品经理ID
     */
    private Long productUserId;

    /**
     * 产品经理名称
     */
    private String productUserName;

    /**
     * 供应商ID
     */
    private Long supplierId;

    /**
     * 供应商名称
     */
    private String supplierName;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 同步状态
     */
    private String syncStatus;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除状态
     */
    private Boolean del;

}