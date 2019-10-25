import com.gtown.cloud.search.Application;
import com.gtown.cloud.search.entity.Film;
import com.gtown.cloud.search.repository.FilmRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
public class TestFile2 {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void saveFilm1() {

    }

}
