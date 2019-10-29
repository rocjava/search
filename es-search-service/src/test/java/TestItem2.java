import com.es.search.Application;
import com.es.search.entity.Item;
import com.es.search.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author penn
 * @since 2019/10/23
 */

/**
 * @author penn
 * @since 2019/10/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestItem2 {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testCreateIndex() {
        elasticsearchTemplate.createIndex(Item.class);
        elasticsearchTemplate.putMapping(Item.class);
    }

    @Test
    public void testDeleteIndex() {
        elasticsearchTemplate.deleteIndex(Item.class);
    }


    @Test
    public void saveItems() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);

        Item item1 = new Item(1L, "小米1", "8G|红色",  "手机" , "小米", 88800, true, "www.baidu.com", new Date());
        Item item2 = new Item(2L, "小米2", "16G|黑色",  "手机" , "小米", 188800, true, "www.baidu.com", c.getTime());
        Item item3 = new Item(3L, "红米1", "8G|红色",  "手机" , "XIAOMI", 288800, true, "www.baidu.com", new Date());
        Item item4 = new Item(4L, "红米note", "32G|红色",  "手机" , "小米", 388800, false, "www.baidu.com", new Date());


        Item item5 = new Item(5L, "华为META10", "8G|黑色",  "手机" , "huawei", 388800, true, "www.baidu.com", new Date());
        Item item6 = new Item(6L, "meta20", "16g|白色",  "手机" , "华为", 488800, true, "www.baidu.com", new Date());
        Item item7 = new Item(7L, "Huawei META30", "8G|土豪金",  "手机" , "HUAWEI", 588800, false, "www.baidu.com", new Date());
        Item item8 = new Item(8L, "荣耀9", "8G|红色",  "手机" , "华为huawei", 688800, true, "www.baidu.com", new Date());
        Item item9 = new Item(9L, "荣耀10", "16G|土豪金",  "手机" , "华为", 788800, true, "www.baidu.com", new Date());
        Item item10 = new Item(10L, "麦芒5", "32G|土豪金",  "手机" , "华为", 688800, true, "www.baidu.com", new Date());
        Item item11 = new Item(11L, "麦芒6", "8G|红色",  "手机" , "华为", 688800, true, "www.baidu.com", c.getTime());
        Item item12 = new Item(12L, "麦芒6", "8G|白色",  "手机" , "华为", 798800, true, "www.baidu.com", new Date());

        List<Item> list = Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12);
        itemRepository.saveAll(list);

    }

    @Test
    public void updateItem(){
        Item item9 = new Item(9L, "荣耀10", "16G|黑色",  "手机" , "华为", 788800, true, "www.baidu.com", new Date());
        itemRepository.save(item9);
    }

}
