package data;

import com.es.search.Application;
import com.es.search.data.entity.ProductSpu;
import com.es.search.data.service.IProductSpuService;
import com.es.search.entity.Item;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        for (int i = 1; i < 1000; i++) {
            list = productSpuService.queryAllProducts(new ProductSpu(), i, 100);
            batch = new ArrayList<>();
            for (ProductSpu spuDTO : list) {
                product = new Product();
                BeanUtils.copyProperties(spuDTO, product);
                batch.add(product);
                log.info(product.getId().toString());
            }
            if(!batch.isEmpty()){
                productRepository.saveAll(batch);
                log.info(i + "页");
            }
        }

    }


    @Test
    public void testComplexQuery() throws ParseException {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-01-01 00:00:00");
        boolQueryBuilder
                .must(QueryBuilders.matchQuery("spuName", "小天鹅"))
                .must(QueryBuilders.matchQuery("attrs", "KaiPuLe"))
                .mustNot(QueryBuilders.matchQuery("ccc", Boolean.TRUE.toString()))
                .filter(QueryBuilders.rangeQuery("salePrice").from(699911))
                .filter(QueryBuilders.rangeQuery("createTime").from(date.getTime()));

        //排序
        FieldSortBuilder sortBuilder1 = SortBuilders.fieldSort("createTime").order(SortOrder.DESC);
        FieldSortBuilder sortBuilder2 = SortBuilders.fieldSort("salePrice").order(SortOrder.DESC);
        //分页
        Pageable pageable = PageRequest.of(20, 100);

        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withSort(sortBuilder1)
                .withSort(sortBuilder2)
                .withPageable(pageable)
                .build();

        Page<Product> items = this.productRepository.search(query);
        for (Product product : items) {
            System.out.println(product);
        }
    }
}
