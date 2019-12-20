package data;

import com.es.search.Application;
import com.es.search.data.entity.ProductSpu;
import com.es.search.data.service.IProductSpuService;
import com.es.search.entity.Product;
import com.es.search.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author penn
 * @since 2019/12/19
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestProduct1 {

    @Autowired
    private IProductSpuService productSpuService;

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testCreateIndex() {
        elasticsearchTemplate.createIndex(Product.class);
        elasticsearchTemplate.putMapping(Product.class);
    }

    @Test
    public void initData() {

        List<ProductSpu> list;
        List<Product> batch;
        Product product;
        for (int i = 1; i < 1500; i++) {
            list = productSpuService.queryAllProducts(new ProductSpu(), i, 100);
            batch = new ArrayList<>();
            for (ProductSpu spuDTO : list) {
                product = new Product();
                BeanUtils.copyProperties(spuDTO, product);
                batch.add(product);
            }
            if(!batch.isEmpty()){
                productRepository.saveAll(batch);
                log.info(i + "页");
            }
        }

    }

    /**
     * 查询所有
     */
    @Test
    public void testComplexQuery() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("spuName", "小天鹅"));
        Iterable<Product>  items = this.productRepository.search(boolQueryBuilder);
        for (Product product : items) {
            log.info(product.getId().toString());
        }
    }

    /**
     * 查询待分页信息
     */
    @Test
    public void testComplexQueryReturnPageInfo(){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("spuName", "小天鹅"));
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build();
        Page<Product> pageList = this.productRepository.search(query);
        for(Product product : pageList){
            log.info(product.toString());
        }

    }

    /**
     * 分页带排序查询
     */
    @Test
    public void testComplexQueryByPage(){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("spuName", "小天鹅"));
        FieldSortBuilder sortBuilder = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        Pageable pageable = PageRequest.of(3, 10);

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withSort(sortBuilder)
                .withPageable(pageable)
                .build();

        Page<Product> items = this.productRepository.search(query);
        for(Product product : items){
            log.info(product.getId().toString());
        }

    }

    /**
     * 使用elasticsearchTemplate分页查询
     */
    @Test
    public void testQueryByPage(){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("spuName", "小天鹅"));
        FieldSortBuilder sortBuilder = SortBuilders.fieldSort("id").order(SortOrder.DESC);
        //查询总数
        long total = elasticsearchTemplate.count(new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build(), Product.class);
        log.info(String.valueOf(total));
        long totalPage = total/10 + 1;
        //分页查询
        for(int i = 0;i<totalPage;i++){
            log.info(i + "页");
            Pageable pageable = PageRequest.of(i, 10);
            SearchQuery query = new NativeSearchQueryBuilder()
                    .withQuery(boolQueryBuilder)
                    .withSort(sortBuilder)
                    .withPageable(pageable)
                    .build();
            List<Product> list = elasticsearchTemplate.queryForList(query, Product.class);
            list.forEach(e -> log.info(e.getId().toString()));

        }
    }
}
