package com.gtown.cloud.search.service.impl;

import com.gtown.cloud.search.entity.Item;
import com.gtown.cloud.search.repository.ItemRepository;
import com.gtown.cloud.search.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

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
