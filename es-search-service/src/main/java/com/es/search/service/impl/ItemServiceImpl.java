package com.es.search.service.impl;

import com.es.search.entity.Item;
import com.es.search.repository.ItemRepository;
import com.es.search.service.IItemService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author penn
 * @since 2019/10/23
 */
@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    /**
     * 查询createTime之后创建的、售价大于price、在售的华为8G的手机
     *
     * @param brand      品牌
     * @param attrs      属性
     * @param isOnSale   是否在售
     * @param price      价格
     * @param createTime 创建时间 排序
     * @return list
     */
    @Override
    public List<Item> multiFieldQuery(String brand, String attrs, Boolean isOnSale, Integer price, Date createTime) {
        Iterable<Item> items = this.itemRepository.search(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("brand", brand))
                .must(QueryBuilders.matchQuery("attrs", attrs))
                .mustNot(QueryBuilders.matchQuery("isOnSale", isOnSale.toString()))
                .filter(QueryBuilders.rangeQuery("price").from(price))
                .filter(QueryBuilders.rangeQuery("createTime").from(createTime.getTime())));
        List<Item> list = new ArrayList<>();
        items.forEach(e -> list.add(e));
        return list;
    }

    @Override
    public Page<Item> multiFieldQueryWithSort(String brand, String attrs, Boolean isOnSale, Integer price, Date createTime) {
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("brand", brand))
                        .must(QueryBuilders.matchQuery("attrs", attrs))
                        .mustNot(QueryBuilders.matchQuery("isOnSale", isOnSale.toString()))
                        .filter(QueryBuilders.rangeQuery("price").from(price))
                        .filter(QueryBuilders.rangeQuery("createTime").from(createTime.getTime())))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC))
                .build();
        Page<Item> items = this.itemRepository.search(query);
        return items;
    }

    @Override
    public Page<Item> multiFieldQueryWithSortWithPage(int page, int pageSize, String brand, String attrs, Boolean isOnSale, Integer price, Date createTime) {
        //查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("brand", brand))
                .must(QueryBuilders.matchQuery("attrs", attrs))
                .mustNot(QueryBuilders.matchQuery("isOnSale", isOnSale.toString()))
                .filter(QueryBuilders.rangeQuery("price").from(price))
                .filter(QueryBuilders.rangeQuery("createTime").from(createTime.getTime()));

        //排序
        FieldSortBuilder sortBuilder1 = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
        FieldSortBuilder sortBuilder2 = SortBuilders.fieldSort("price").order(SortOrder.DESC);
        //分页
        Pageable pageable = PageRequest.of(page, pageSize);

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withSort(sortBuilder1)
                .withSort(sortBuilder2)
                .withPageable(pageable)
                .build();

        Page<Item> items = this.itemRepository.search(query);
        return items;
    }

    @Override
    public List<Item> findByTitle(String title) {
        return itemRepository.findByTitle(title);
    }

    @Override
    public List<Item> findByTitleNot(String title) {
        return itemRepository.findByTitleNot(title);
    }

    @Override
    public List<Item> findByTitleLike(String title) {
        return itemRepository.findByTitleLike(title);
    }

    @Override
    public List<Item> findByIsOnSaleTrue() {
        return itemRepository.findByIsOnSaleTrue();
    }

    @Override
    public List<Item> findByIsOnSaleFalse() {
        return itemRepository.findByIsOnSaleFalse();
    }

    @Override
    public List<Item> findByIsOnSaleIsNull() {
        return null;
    }

    @Override
    public List<Item> findByIsOnSale(Boolean isOnSale, PageRequest request) {
        return itemRepository.findByIsOnSale(isOnSale, request);
    }

    @Override
    public List<Item> findByTitleStartingWith(String title) {
        return itemRepository.findByTitleStartingWith(title);
    }

    @Override
    public List<Item> findByTitleStartsWith(String title) {
        return itemRepository.findByTitleStartsWith(title);
    }

    @Override
    public List<Item> findByTitleEndingWith(String title) {
        return itemRepository.findByTitleEndingWith(title);
    }

    @Override
    public List<Item> findByTitleContaining(String title) {
        return itemRepository.findByTitleContaining(title);
    }

    @Override
    public Stream<Item> findByBrandIn(List<String> brands) {
        return itemRepository.findByBrandIn(brands);
    }

    @Override
    public Stream<Item> findByBrandNotIn(List<String> brands) {
        return itemRepository.findByBrandNotIn(brands);
    }

    @Override
    public Stream<Item> findByBrandAndPrice(String brand, Integer price) {
        return itemRepository.findByBrandAndPrice(brand, price);
    }

    @Override
    public Stream<Item> findByBrandOrPrice(String brand, Integer price) {
        return itemRepository.findByBrandOrPrice(brand, price);
    }

    @Override
    public Stream<Item> findByPriceBetween(Integer price1, Integer price2) {
        return itemRepository.findByPriceBetween(price1, price2);
    }

    @Override
    public Stream<Item> findByPriceGreaterThan(Integer price1) {
        return itemRepository.findByPriceGreaterThan(price1);
    }

    @Override
    public Stream<Item> findByPriceLessThan(Integer price1) {
        return itemRepository.findByPriceLessThan(price1);
    }

    @Override
    public Stream<Item> findByPriceBefore(Integer price1) {
        return itemRepository.findByPriceBefore(price1);
    }

    @Override
    public Stream<Item> findByPriceAfter(Integer price1) {
        return itemRepository.findByPriceAfter(price1);
    }
}
