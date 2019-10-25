import com.gtown.cloud.search.Application;
import com.gtown.cloud.search.entity.Item;
import com.gtown.cloud.search.repository.ItemRepository;
import org.elasticsearch.index.query.QueryBuilder;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * @author penn
 * @since 2019/10/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestES {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testCreateIndex() {
        elasticsearchTemplate.createIndex(Item.class);
    }

    @Test
    public void testAdd() {
        Item item = new Item(8L, "华为麦芒20", "16G|白色",  "手机" , "1华为", 388800, true, "www.baidu.com");
        item = itemRepository.save(item);
        System.out.println(item.toString());
    }


    @Test
    public void testBatchAdd(){
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米2", "8G|红色", " 手机", "小米", 88800, true, "http://image.baidu.com/1.jpg"));
        list.add(new Item(2L, "小米手机3", "32G|白色"," 手机", "小米", 188800, true, "http://image.baidu.com/2.jpg"));
        list.add(new Item(3L, "坚果手机R1","8G|红色", " 手机", "锤子", 288800, false, "http://image.baidu.com/3.jpg"));
        list.add(new Item(4L, "坚果手机R2", "16G|红色", " 手机", "锤子", 388800, false, "http://image.baidu.com/4.jpg"));
        list.add(new Item(5L, "华为META10", "8G|红色", " 手机", "华为", 488800, true, "http://image.baidu.com/5.jpg"));
        list.add(new Item(6L, "华为META20", "16G|银色", " 手机", "华为", 588800, true, "http://image.baidu.com/6.jpg"));
        list.add(new Item(7L, "华为 META30", "32G|黑色", " 手机", "华为", 688800, false, "http://image.baidu.com/7.jpg"));

        System.out.println(itemRepository.saveAll(list));
    }

    @Test
    public void testQueryAll(){
        // 查找所有
        //Iterable<Item> list = this.itemRepository.findAll();
        // 对某字段排序查找所有 Sort.by("price").descending() 降序
        // Sort.by("price").ascending():升序
        Iterable<Item> list = this.itemRepository.findAll(Sort.by("id").ascending());

        for (Item item:list){
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
        for (Item item:items){
            System.out.println(item);
        }
    }

    @Test
    public void testFindByTitleNot() {
        List<Item> items = this.itemRepository.findByTitleNot("华为META10");
        for (Item item:items){
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethod() {
        List<Item> items = this.itemRepository.findByTitleLike("华为MATA");
        for (Item item:items){
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodAvailable() {
        List<Item> items = this.itemRepository.findByIsOnSaleTrue();
        for (Item item:items){
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodNotAvailable() {
        List<Item> items = this.itemRepository.findByIsOnSaleFalse();
        for (Item item:items){
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodNull() {
        List<Item> items = this.itemRepository.findByIsOnSaleIsNull();
        for (Item item:items){
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodNull_() {
        List<Item> items = this.itemRepository.findByIsOnSale(null, PageRequest.of(0, 100));
        for (Item item:items){
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodStartingWith() {
        List<Item> items = this.itemRepository.findByTitleStartingWith("小米");
        for (Item item:items){
            System.out.println(item);
        }
    }

    @Test
    public void testFieldMethodStartsWith() {
        List<Item> items = this.itemRepository.findByTitleStartsWith("小米");
        for (Item item:items){
            System.out.println(item);
        }
    }

    /**
     * 注意英文大小写
     */
    @Test
    public void testFieldMethodEndingWith() {
        List<Item> items = this.itemRepository.findByTitleEndingWith("R1".toLowerCase());
        for (Item item:items){
            System.out.println(item);
        }
    }

    /**
     * 注意英文大小写
     */
    @Test
    public void testFieldMethodContaining() {
        List<Item> items = this.itemRepository.findByTitleContaining("META".toLowerCase());
        for (Item item:items){
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
        for (Item item:items){
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
        for (Item item:items){
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
        for (Item item:items){
            System.out.println(item);
        }
    }
}
