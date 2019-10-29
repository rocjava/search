package com.es.search.repository;/**
 * @author penn
 * @since 2019/10/23
 */

import com.es.search.entity.Item;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.stream.Stream;

/**
 * 示例
 * spring data 根据方法名自动实现
 * 方法名称要符合一定的约定
 * @author penn
 * @since 2019-10-23
 */
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {

    /**
     * 通过title查询
     * @param title
     * @return
     */
    List<Item> findByTitle(String title);

    /**
     * 通过title查询 非
     * @param title
     * @return
     */
    List<Item> findByTitleNot(String title);

    /**
     * 通过title 模糊查询
     * @param title
     * @return
     */
    List<Item> findByTitleLike(String title);

    /**
     * 通过title boolean 可售状态
     * @return
     */
    List<Item> findByIsOnSaleTrue();


    /**
     * 通过title boolean 可售状态
     * @return
     */
    List<Item> findByIsOnSaleFalse();

    /**
     * 通过title null
     * 该方法不可用，可用findByIsOnSale(null, PageRequest.of(0,100));代替
     * @return
     */
    @Deprecated
    List<Item> findByIsOnSaleIsNull();

    /**
     * 查询null的值，并分页
     * @param isOnSale
     * @param request
     * @return
     */
    List<Item> findByIsOnSale(Boolean isOnSale, PageRequest request);

    /**
     * 通过title 开头
     * @param title
     * @return
     */
    List<Item> findByTitleStartingWith(String title);

    /**
     * 通过title 开头
     * @param title
     * @return
     */
    List<Item> findByTitleStartsWith(String title);

    /**
     * 通过title 结尾
     * @param title
     * @return
     */
    List<Item> findByTitleEndingWith(String title);

    /**
     * 通过title 包含
     * @param title
     * @return
     */
    List<Item> findByTitleContaining(String title);


    /**
     * 通过 brand in
     * @param brands
     * @return
     */
    Stream<Item> findByBrandIn(List<String> brands);

    /**
     * 通过 brand not in
     * @param brands
     * @return
     */
    Stream<Item> findByBrandNotIn(List<String> brands);


    /**
     * 通过 brand 和 price
     * @param brand
     * @param price
     * @return
     */
    Stream<Item> findByBrandAndPrice(String brand, Integer price);

    /**
     * 通过 brand 或者 price
     * @param brand
     * @param price
     * @return
     */
    Stream<Item> findByBrandOrPrice(String brand, Integer price);

    /**
     * 通过 brand 或者 >=price
     * @param brand
     * @param price
     * @return
     */
    Stream<Item> findByBrandOrPriceAfter(String brand, Integer price);

    /**
     * price范围查询
     * @param price1
     * @param price2
     * @return
     */
    Stream<Item> findByPriceBetween(Integer price1, Integer price2);

    /**
     * price范围查询 GreaterThan
     * @param price1
     * @return
     */
    Stream<Item> findByPriceGreaterThan(Integer price1);

    /**
     * price范围查询 LessThan
     * @param price1
     * @return
     */
    Stream<Item> findByPriceLessThan(Integer price1);

    /**
     * price范围查询 LessThan And Equal
     * @param price1
     * @return
     */
    Stream<Item> findByPriceBefore(Integer price1);

    /**
     * price范围查询 GreaterThan And Equal
     * @param price1
     * @return
     */
    Stream<Item> findByPriceAfter(Integer price1);

    /**
     * 通过 brand 或者 >=price
     * @param brand
     * @param price
     * @return
     */
    Stream<Item> findByBrandAndCreateTimeAfterAndPriceAfter(String brand, Long createTime, Integer price);
}
