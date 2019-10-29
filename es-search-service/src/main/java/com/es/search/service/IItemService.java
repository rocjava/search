package com.es.search.service;/**
 * @author penn
 * @since 2019/10/23
 */

import com.es.search.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description
 * @Author penn
 * @Date 2019-10-23
 */
public interface IItemService {


    /**
     * 多列查询,
     * 基于QueryBuilders.boolQuery()，它有四个主要函数
     * must() 必须满足
     * mustNot() 不能有
     * should() 或者满足or
     * filter() 过滤
     * brand
     * attrs
     * isOnSale
     * price
     * createTime
     */
    List<Item> multiFieldQuery(String brand, String attrs, Boolean isOnSale, Integer price, Date createTime);

    /**
     * 多列查询带排序
     * brand
     * attrs
     * isOnSale
     * price
     * createTime
     */
    Page<Item> multiFieldQueryWithSort(String brand, String attrs, Boolean isOnSale, Integer price, Date createTime);

    /**
     * 多列查询带排序带分页
     * brand
     * attrs
     * isOnSale
     * price
     * createTime
     */
    Page<Item> multiFieldQueryWithSortWithPage(int page, int pageSize, String brand, String attrs, Boolean isOnSale, Integer price, Date createTime);

    /**
     * 基于方法名称查询 ES会自动解析方法名称
     * 需要注意两点：
     * 1、尽量不要有两个前缀相同的属性 如code,codeValue,这样当使用findByCodeValue时会解析失败
     * 2、findBy后面的字段必须会实体的属性，所以不要随便使用findBy，相当于是"特殊字符"
     * 通过title查询
     *
     * @param title
     * @return
     */
    List<Item> findByTitle(String title);

    /**
     * 通过title查询 非
     *
     * @param title
     * @return
     */
    List<Item> findByTitleNot(String title);

    /**
     * 通过title 模糊查询
     *
     * @param title
     * @return
     */
    List<Item> findByTitleLike(String title);

    /**
     * 通过title boolean 可售状态
     *
     * @return
     */
    List<Item> findByIsOnSaleTrue();


    /**
     * 通过title boolean 可售状态
     *
     * @return
     */
    List<Item> findByIsOnSaleFalse();

    /**
     * 通过title null
     * 该方法不可用，可用findByIsOnSale(null, PageRequest.of(0,100));代替
     *
     * @return
     */
    @Deprecated
    List<Item> findByIsOnSaleIsNull();

    /**
     * 查询null的值，并分页
     *
     * @param isOnSale
     * @param request
     * @return
     */
    List<Item> findByIsOnSale(Boolean isOnSale, PageRequest request);

    /**
     * 通过title 开头
     *
     * @param title
     * @return
     */
    List<Item> findByTitleStartingWith(String title);

    /**
     * 通过title 开头
     *
     * @param title
     * @return
     */
    List<Item> findByTitleStartsWith(String title);

    /**
     * 通过title 结尾
     *
     * @param title
     * @return
     */
    List<Item> findByTitleEndingWith(String title);

    /**
     * 通过title 包含
     *
     * @param title
     * @return
     */
    List<Item> findByTitleContaining(String title);


    /**
     * 通过 brand in
     *
     * @param brands
     * @return
     */
    Stream<Item> findByBrandIn(List<String> brands);

    /**
     * 通过 brand not in
     *
     * @param brands
     * @return
     */
    Stream<Item> findByBrandNotIn(List<String> brands);


    /**
     * 通过 brand 和 price
     *
     * @param brand
     * @param price
     * @return
     */
    Stream<Item> findByBrandAndPrice(String brand, Integer price);

    /**
     * 通过 brand 或者 price
     *
     * @param brand
     * @param price
     * @return
     */
    Stream<Item> findByBrandOrPrice(String brand, Integer price);

    /**
     * price范围查询
     *
     * @param price1
     * @param price2
     * @return
     */
    Stream<Item> findByPriceBetween(Integer price1, Integer price2);

    /**
     * price范围查询 GreaterThan
     *
     * @param price1
     * @return
     */
    Stream<Item> findByPriceGreaterThan(Integer price1);

    /**
     * price范围查询 LessThan
     *
     * @param price1
     * @return
     */
    Stream<Item> findByPriceLessThan(Integer price1);

    /**
     * price范围查询 LessThan And Equal
     *
     * @param price1
     * @return
     */
    Stream<Item> findByPriceBefore(Integer price1);

    /**
     * price范围查询 GreaterThan And Equal
     *
     * @param price1
     * @return
     */
    Stream<Item> findByPriceAfter(Integer price1);
}
