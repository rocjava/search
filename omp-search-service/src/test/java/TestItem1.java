import com.gtown.cloud.search.Application;
import com.gtown.cloud.search.entity.Item;
import com.gtown.cloud.search.repository.ItemRepository;
import com.gtown.cloud.search.service.IItemService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @author penn
 * @since 2019/10/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestItem1 {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private IItemService iItemService;

    @Test
    public void testQueryAll() {
        // 查找所有
        //Iterable<Item> list = this.itemRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<Item> list = this.itemRepository.findAll(Sort.by("createTime").ascending());

        for (Item item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void testUpdate() {
        Optional<Item> optional = itemRepository.findById(1L);
        Item item = optional.get();
        item.setImages("www.huya.com");
        this.itemRepository.save(item);
    }

    @Test
    public void testFindByTitle() {
        List<Item> items = this.itemRepository.findByTitle("华为META10");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void testFindByTitleNot() {
        List<Item> items = this.itemRepository.findByTitleNot("华为META10");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethod() {
        List<Item> items = this.itemRepository.findByTitleLike("华为MATA");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodAvailable() {
        List<Item> items = this.itemRepository.findByIsOnSaleTrue();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodNotAvailable() {
        List<Item> items = this.itemRepository.findByIsOnSaleFalse();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodNull() {
        List<Item> items = this.itemRepository.findByIsOnSaleIsNull();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodNull_() {
        List<Item> items = this.itemRepository.findByIsOnSale(null, PageRequest.of(0, 100));
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodStartingWith() {
        List<Item> items = this.itemRepository.findByTitleStartingWith("小米");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodStartsWith() {
        List<Item> items = this.itemRepository.findByTitleStartsWith("小米");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    /**
     * 注意英文大小写
     */
    @Test
    public void testFieldMethodEndingWith() {
        List<Item> items = this.itemRepository.findByTitleEndingWith("R1".toLowerCase());
        for (Item item : items) {
            System.out.println(item);
        }
    }

    /**
     * 注意英文大小写
     */
    @Test
    public void testFieldMethodContaining() {
        List<Item> items = this.itemRepository.findByTitleContaining("META".toLowerCase());
        for (Item item : items) {
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodIn() {
        Stream<Item> items = this.itemRepository.findByBrandIn(Arrays.asList("华为", "小米"));
        items.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void testFieldMethodNotIn() {
        Stream<Item> items = this.itemRepository.findByBrandNotIn(Arrays.asList("华为", "小米"));
        items.forEach(e -> System.out.println(e.toString()));
    }


    @Test
    public void testMultiFieldOrMethod() {
        Stream<Item> items = this.itemRepository.findByBrandOrPrice("华为", 88800);
        items.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void testMultiFieldOrMethod1() {
        Stream<Item> items = this.itemRepository.findByBrandOrPriceAfter("华为", 388800);
        items.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void testMethodStream() {
        Stream<Item> items = this.itemRepository.findByBrandAndPrice("华为", 488800);
        items.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void testMethodStreamBetween() {
        Stream<Item> items = this.itemRepository.findByPriceBetween(288800, 488800);
        items.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void testMethodStreamLessThan() {
        Stream<Item> items = this.itemRepository.findByPriceLessThan(288800);
        items.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void testMethodStreamGreaterThan() {
        Stream<Item> items = this.itemRepository.findByPriceGreaterThan(288800);
        items.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void testMethodStreamBefore() {
        Stream<Item> items = this.itemRepository.findByPriceBefore(288800);
        items.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void testMethodStreamAfter() {
        Stream<Item> items = this.itemRepository.findByPriceAfter(288800);
        items.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void findByBrandAndCreateTimeAfterAndPriceAfter() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-29 00:00:00");
        Stream<Item> items = this.itemRepository.findByBrandAndCreateTimeAfterAndPriceAfter("华为", date.getTime(), 388800);
        items.forEach(e -> System.out.println(e.toString()));
    }

    /**
     * multiMatchQuery
     */
    @Test
    public void testSelfDefinedMethod0() {
        Iterable<Item> items = this.itemRepository.search(QueryBuilders.multiMatchQuery("华为", "title", "brand"));
        for (Item item : items) {
            System.out.println(item);
        }
    }

    /**
     * boolQuery
     */
    @Test
    public void testSelfDefinedMethod01() {
        Iterable<Item> items = this.itemRepository.search(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("brand", "华为"))
                .filter(QueryBuilders.termQuery("title", "麦芒")));
        for (Item item : items) {
            System.out.println(item);
        }

    }

    /**
     * filter
     */
    @Test
    public void testSelfDefinedMethod02() {
        Iterable<Item> items = this.itemRepository.search(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("brand", "华为"))
                .filter(QueryBuilders.matchQuery("attrs", "8G")));
        for (Item item : items) {
            System.out.println(item);
        }

    }

    /**
     * boolQuery
     */
    @Test
    public void testSelfDefinedMethod03() {
        Iterable<Item> items = this.itemRepository.search(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("brand", "华为"))
                .must(QueryBuilders.matchQuery("attrs", "8G|红色")));
        for (Item item : items) {
            System.out.println(item);
        }

    }

    /**
     * rangeQuery
     */
    @Test
    public void testSelfDefinedMethod04() {
        Iterable<Item> items = this.itemRepository.search(QueryBuilders.rangeQuery("price").from(488800).to(688800));
        for (Item item : items) {
            System.out.println(item);
        }

    }

    /**
     * should
     */
    @Test
    public void testSelfDefinedMethod05() {
        Iterable<Item> items = this.itemRepository.search(QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("brand", "HUAWEI 华为 huawei"))
                .should(QueryBuilders.matchQuery("brand", "小米 xiaomi XIAOMI")));
        for (Item item : items) {
            System.out.println(item);
        }

    }

    /**
     * must and mustNot and filter
     */
    @Test
    public void testSelfDefinedMethod06() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-29 00:00:00");
        System.out.println("==============分割线原始===============");
        Iterable<Item> items = this.itemRepository.search(QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("brand", "HUAWEI 华为 huawei"))
                .must(QueryBuilders.matchQuery("attrs", "8G"))
                .mustNot(QueryBuilders.matchQuery("isOnSale", "false"))
                .filter(QueryBuilders.rangeQuery("price").from(688800))
                .filter(QueryBuilders.rangeQuery("createTime").from(date.getTime())));
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("==============分割线接口方式===============");
        List<Item> list = iItemService.multiFieldQuery("HUAWEI 华为 huawei", "8G", false, 688800, date);

        for (Item item : list) {
            System.out.println(item);
        }

        System.out.println("==============分割线排序===============");
        List<Item> list1 = iItemService.multiFieldQueryWithSort("HUAWEI 华为 huawei", "8G", false, 688800, date);
        for (Item item : list1) {
            System.out.println(item);
        }

        System.out.println("==============分割线分页===============");
        List<Item> list2 = iItemService.multiFieldQueryWithSortWithPage(0, 10, "HUAWEI 华为 huawei", "8G", false, 688800, date);
        for (Item item : list1) {
            System.out.println(item);
        }
    }

    /**
     * 分页
     */
    @Test
    public void testSelfDefinedMethod1() {
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder
                .withQuery(matchQuery("title", "华为MATE10"))
                .withPageable(PageRequest.of(0, 10));
        Page<Item> items = this.itemRepository.search(queryBuilder.build());
        System.out.println("总条数 = " + items.getTotalElements());
        System.out.println("总页数 = " + items.getTotalPages());
        System.out.println("当前页：" + items.getNumber());
        System.out.println("每页大小：" + items.getSize());
        System.out.println("内容大小：" + items.getNumberOfElements());
        for (Item item : items) {
            System.out.println(item);
        }
    }


    /**
     * termQuery 精确匹配，查询前不会对参数进行分词
     */
    @Test
    public void testSelfDefinedMethod2() {
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("title", "meta"))
                .build();
        Page<Item> items = this.itemRepository.search(query);
        for (Item item : items) {
            System.out.println(item);
        }
    }

    /**
     * termQuery 精确匹配，查询前不会对参数进行分词
     */
    @Test
    public void testSelfDefinedMethod3() {
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.termQuery("title", "华为"))
                .withFilter(QueryBuilders.termQuery("attrs", "16"))
                .withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("brand").order(SortOrder.DESC))
                .build();
        Page<Item> items = this.itemRepository.search(query);
        for (Item item : items) {
            System.out.println(item);
        }
    }
}
