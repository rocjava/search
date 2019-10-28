import com.gtown.cloud.search.Application;
import com.gtown.cloud.search.entity.Film;
import com.gtown.cloud.search.service.IFilmService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class TestFile {

    @Autowired
    private IFilmService filmService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void createIndex() {
        elasticsearchTemplate.createIndex(Film.class);
        elasticsearchTemplate.putMapping(Film.class);
    }

    @Test
    public void saveFilm1() {
        Film film = new Film();
        film.setTitle("蜘蛛侠：英雄远征 Spider-Man: Far from Home");
        film.setContent("在复仇者联盟众英雄的努力下，于灭霸无限手套事件中化作为灰烬的人们，重新回到了人世间，曾经消失的蜘蛛侠 彼得帕克 也回归到了普通的生活之中，数月后，蜘蛛侠彼得帕克所在的学校举行了欧洲旅游，帕克也在其中， 在欧州威尼斯旅行时，一个巨大无比的水怪袭击了威尼斯，不敌敌人的蜘蛛侠幸得一位自称神秘客的男子搭救才击退敌人，之后 神盾局局长找上正在旅游的彼得帕克并要求其加入神盾局，并安排神秘客协助帕克，神秘客自称来自其他宇宙，并告知一群名为元素众的邪恶势力已经入侵这个宇宙了，为了守护来之不易的和平，蜘蛛侠决定与神秘客联手，然而在神秘客那头罩之中，似乎隐藏着什么不为人知的真相……");
        film.setDate("2019-06-28");
        film.setDirector("乔·沃茨");
        film.setPrice("78");
        film.setId(1L);
        filmService.save(film);
    }

    @Test
    public void saveFilm2() {
        Film film = new Film();
        film.setId(2L);
        film.setTitle("追龙Ⅱ 追龍2：贼王");
        film.setContent("悍匪龙志强，在香港回归前，趁香港英政府不作为，而屡犯巨案，先后绑架富豪利家及雷家之长子，勒索超过二十亿元，事主怕被报复, 交赎款后都不敢报警。中国公安部极为关注，与香港警方合力，派香港警员何天卧底潜入龙志强犯罪团伙，发现他正策划绑架澳门富豪贺不凡，最终陆港警察合力勇擒龙志强，救出贺不凡。");
        film.setDate("2019-06-06");
        film.setDirector("王晶/关智耀");
        film.setPrice("45");
        filmService.save(film);
    }

    @Test
    public void saveFilm3() {
        Film film = new Film();
        film.setId(3L);
        film.setTitle("红海行动");
        film.setContent("中东国家伊维亚共和国发生政变，武装冲突不断升级。刚刚在索马里执行完解救人质任务的海军护卫舰临沂号，受命前往伊维亚执行撤侨任务。舰长高云（张涵予 饰）派出杨锐（张译 饰）率领的蛟龙突击队登陆战区，护送华侨安全撤离。谁知恐怖组织扎卡却将撤侨部队逼入交火区，一场激烈的战斗在所难免。与此同时，法籍华人记者夏楠（海清 饰）正在伊维亚追查威廉·柏森博士贩卖核原料的事实，而扎卡则突袭柏森博士所在的公司，意图抢走核原料。混战中，一名隶属柏森博士公司的中国员工成为人质。为了解救该人质，八名蛟龙队员必须潜入有150名恐怖分子的聚集点，他们用自己的信念和鲜血铸成中国军人顽强不屈的丰碑！ ");
        film.setDate("2018-02-16");
        film.setDirector("林超贤");
        film.setPrice("32");
        filmService.save(film);
    }

    @Test
    public void saveFilm4() {
        Film film = new Film();
        film.setId(4L);
        film.setTitle("让子弹飞");
        film.setContent("民国年间，花钱捐得县长的马邦德（葛优 饰）携妻（刘嘉玲 饰）及随从走马上任。途经南国某地，遭劫匪张麻子（姜文 饰）一伙伏击，随从尽死，只夫妻二人侥幸活命。马为保命，谎称自己是县长的汤 师爷。为汤师爷许下的财富所动，张麻子摇身一变化身县长，带着手下赶赴鹅城上任。有道是天高皇帝远，鹅城地处偏僻，一方霸主黄四郎（周润发 饰）只手遮天，全然不将这个新来的县长放在眼里。张麻子痛打了黄的武教头（姜武 饰），黄则设计害死张的义子小六（张默 饰）。原本只想赚钱的马邦德，怎么也想不到竟会被卷入这场土匪和恶霸的角力之中。鹅城上空愁云密布，血雨腥风在所难免……");
        film.setDate("2010-12-16");
        film.setDirector("姜文");
        film.setPrice("33");
        filmService.save(film);
    }

    @Test
    public void saveFilm5() {
        Film film = new Film();
        film.setId(5L);
        film.setTitle("无双 無雙");
        film.setContent("身陷囹圄的李问（郭富城 饰）被引渡回港，他原本隶属于一个的跨国假钞制贩组织。该组织曾犯下过多宗恶性案件，而首脑“画家”不仅始终逍遥法外，连真面目都没人知道。为了逼迫李问吐露“画家”真实身份，警方不惜用足以判死刑的假罪证使其就范。就在此时，富有的遗孀阮文（张静初 饰）申请保释李问，而警方提出的条件依然是“画家”的真面目。 \n" +
                "　　原来早在十数年前，李问和阮文是一对画家情侣，无奈女友的作品受人青睐，李问的画作却被贬得一文不值。就在此困顿期间，他制作假画的才能被“画家”（周润发 饰）发掘，进而成为对方美元假币团伙中的一员……");
        film.setDate("2018-09-30");
        film.setDirector("庄文强");
        film.setPrice("46");
        filmService.save(film);
    }

    @Test
    public void saveFilm6() {
        Film film = new Film();
        film.setId(6L);
        film.setTitle("战狼2");
        film.setContent("由于一怒杀害了强拆牺牲战友房子的恶霸，屡立功勋的冷锋（吴京 饰）受到军事法庭的判决。在押期间，亲密爱人龙小云壮烈牺牲。出狱后，冷锋辗转来到非洲，他辗转各地，只为寻找杀害小云的凶手。在此期间，冷锋逗留的国家发生叛乱，叛徒红巾军大开杀戒，血流成河。中国派出海军执行撤侨任务，期间冷锋得知有一位陈博士被困在五十五公里外的医院，而叛军则试图抓住这位博士。而从另一位华侨（于谦 饰）口中得知，杀害小云的凶手正待在这个国家。 \n" +
                "　　在无法得到海军支援的情况下，冷锋只身闯入硝烟四起的战场。不屈不挠的战狼，与冷酷无情的敌人展开悬殊之战……");
        film.setDate("2017-07-27");
        film.setDirector("吴京");
        film.setPrice("23");
        filmService.save(film);
    }

    @Test
    public void saveFilm7() {
        Film film = new Film();
        film.setId(7L);
        film.setTitle("邪不压正");
        film.setContent("七七事变前夕，华裔青年小亨德勒（彭于晏 饰）从美国远赴重洋，回到阔别十数年之久的北平从医。然而他真正的名字叫李天然，十三岁那年曾亲眼目睹师父一家遭师兄朱潜龙（廖凡 饰）和日本人根本一郎（泽田谦也 饰）灭门。侥幸逃生的天然被美国人亨德勒医生送往大洋彼岸，接受了极其严苛的训练，而今他怀着绝密的任务踏上故土。亨德勒父子租住神秘男子蓝青峰（姜文 饰）的宅子，蓝是当年辛亥革命的参与者，他与现为警察局长的朱潜龙过从甚密，却又以杀死李天然为筹码，暗中怂恿朱除掉根本一郎。复仇心切的李天然寻找到了仇人，而亨德勒医生则全力阻止养子冒险。在这一过程中，交际花唐凤仪（许晴 饰）与裁缝关巧红（周韵 饰）也卷入了男人的勾心斗角的漩涡里。直到七七事变爆发，所有的矛盾迎来了决断的时刻…… ");
        film.setDate("2018-07-13");
        film.setDirector("姜文");
        film.setPrice("46");
        filmService.save(film);
    }

    @Test
    public void saveFilm8() {
        Film film = new Film();
        film.setId(8L);
        film.setTitle("湄公河行动");
        film.setContent("金三角湄公河上，一处被称为“鬼门关”的河段，两艘来自中国的商船遭到不明身份之人的枪击袭击。未过多久，泰国军方召开新闻发布会，指责中国商船贩卖毒品。虽然发布会宣称船员全部逃亡，但是十三具遭受残忍杀害的中国船员尸体很快被人发现。这起胆大妄为的案件令中国警方大为震惊，云南省缉毒总队队长高刚（张涵予 饰）受命带特别行动小组前往泰国，并与情报员方新武（彭于晏 饰）合作接洽。根据现有资料显示，这件案子由盘踞在金三角的大毒枭糯卡所为。糯卡贪婪残忍，胆大包天，是湄公河流域上一颗惊扰运输安全的毒瘤。为了将这个恶棍绳之于法，中国、老挝、缅甸开展了三国联合巡逻，集中对糯卡的制毒窝点进行扫荡。而高刚等人也深入最危险境地，与丧失人性的贩毒分子进行惨烈对决…… ");
        film.setDate("2016-09-30");
        film.setDirector("林超贤");
        film.setPrice("79");
        filmService.save(film);
    }

    @Test
    public void saveFilm9() {
        Film film = new Film();
        film.setId(9L);
        film.setTitle("大黄蜂 Bumblebee");
        film.setContent("意图征服一切的霸天虎全员出击，塞伯坦即将陨落，B-127（迪伦·奥布莱恩 Dylan O'Brien 配音）临危受命前往地球。在1987年的加州，B-127不仅遭到特工伯恩斯（约翰·塞纳 John Cena 饰演）的追捕，更被闪电（大卫·索博洛夫 David Sobolov 配音）殴至重伤，语言模块和记忆模块严重受损。B-127变形为大众甲壳虫，被失去了父亲（蒂姆·马丁·格利森 Tim Martin Gleason 饰演）的查莉·沃森（海莉·斯坦菲尔德 Hailee Steinfeld 饰演）发现并开回家。查莉为B-127取名大黄蜂，双方成为亲密无间的好朋友。然而好景不长，反射弹（贾斯汀·塞洛克斯 Justin Theroux 配音）与粉碎（安吉拉·贝塞特 Angela Bassett 配音）追至地球，利用美国军方的卫星网络追踪到大黄蜂，并企图将霸天虎大部队召唤到这里。为了阻止反弹射和粉碎，大黄蜂和他的新朋友一起踏上了保卫这个蓝色星球的征途……");
        film.setDate("2019-01-04");
        film.setDirector("特拉维斯·奈特");
        film.setPrice("16");
        filmService.save(film);
    }

    @Test
    public void saveFilm10() {
        Film film = new Film();
        film.setId(10L);
        film.setTitle("反贪风暴4");
        film.setContent("廉政公署收到报案人廖雨萍（周秀娜饰）的实名举报，举报正在坐牢的富二代曹元元（林峯饰）涉嫌行贿监狱里的监督沈济全（谭耀文饰）以及惩教员，首席调查主任陆志廉（古天乐饰）决定深入虎穴，卧底狱中。在监狱里，被陆志廉送入监狱的前警司黄文彬（林家栋饰）以及曹元元两大帮派势成水火，陆志廉趁机接近曹元元取得信任。同时监狱外的廉政公署总调查主任程德明（郑嘉颖饰）、国内反贪局行动处处长洪亮（丁海峰饰）也陆港联手，通力合作，最终成功破获贪腐行贿大案。");
        film.setDate("2019-04-04");
        film.setDirector("林德禄");
        film.setPrice("56");
        filmService.save(film);
    }

    @Test
    public void saveFilm11() {
        Film film = new Film();
        film.setId(11L);
        film.setTitle("寻龙诀");
        film.setContent("上世纪80年代末，胡八一（陈坤 饰）、王凯旋（黄渤 饰）与Shirley杨（舒淇 饰）在美国打算金盆洗手，本来叱咤风云的摸金校尉沦为街头小贩被移民局追得满街跑。就在此时，意外发现20年前死在草原上的初恋对象丁思甜（杨颖 饰）可能还活着，胡八一、王凯旋、Shirley杨决定再入草原千年古墓……");
        film.setDate("2015-12-18");
        film.setDirector("乌尔善");
        film.setPrice("54");
        filmService.save(film);
    }

    @Test
    public void saveFilm12() {
        Film film = new Film();
        film.setId(12L);
        film.setTitle("速度与激情7");
        film.setContent("经历了紧张刺激的伦敦大战，多米尼克·托雷托（范·迪塞尔 Vin Diesel 饰）和他的伙伴们重新回归平静的生活，但是江湖的恩恩怨怨却决不允许他们轻易抽身而去。棘手的死对头欧文·肖瘫在医院，不得动弹，他的哥哥戴克·肖（杰森·斯坦森 Jason Stantham 饰）则发誓要为弟弟复仇。戴克曾是美国特种部队的王牌杀手，不仅身怀绝技，而且心狠手辣。他干掉了远在东京的韩，还几乎把探长卢克·霍布斯（道恩·强森 Dwayne Johnson 饰）送到另一个世界，甚至多米尼克那世外桃源般的家也被对方炸毁。");
        film.setDate("2015-04-12");
        film.setDirector("温子仁");
        film.setPrice("62");
        filmService.save(film);
    }

    @Test
    public void saveFilm13() {
        Film film = new Film();
        film.setId(13L);
        film.setTitle("追龙");
        film.setContent("20世纪60年代，中国香港鱼龙混杂，混乱不堪。英国人治下的皇家警察贪婪无度，甚至与黑道相互勾结，搜刮民脂。年轻探长雷洛（刘德华 饰）踌躇满志，小心周旋在英国人和华人上司之间，并瞅准一切机会攀登权力山峰。在某次火并事件中，雷洛看中了敢打敢杀的伍世豪（甄子丹 饰）。来自南洋的伍世豪义字当头，情愿为兄弟两肋插刀。他凭借一股闯劲儿在黑道打出自己的位置，同时借着与雷洛相互帮衬的势头而声名鹊起。最终，刀头舔血的雷洛和伍世豪共同成为分别执掌白道和黑道的重量级人物。 \n" +
                "　　而在这一过程中，这对兄弟也渐渐发生分歧。当风暴来临之际，一切即将变化……");
        film.setDate("2017-09-30");
        film.setDirector("王晶 / 关智耀");
        film.setPrice("53");
        filmService.save(film);
    }

    @Test
    public void saveFilm14() {
        Film film = new Film();
        film.setId(14L);
        film.setTitle("神奇女侠");
        film.setContent("戴安娜（盖尔·加朵 Gal Gadot 饰）是女王希波吕忒（康妮·尼尔森 Connie Nielsen 饰）的女儿，自幼生活在天堂岛上。巨大的屏障将这座岛屿同外界的纷纷扰扰隔开犹如一个世外桃源，而岛上生活着的亦都是女性。在女武官安提奥普（罗宾·莱特 Robin Wright 饰）的教导之下，戴安娜习得了高强的武艺，而她的体内，似乎隐藏着某种强大的未知力量。 \n" +
                "　　一场意外中，一位名为史蒂夫（克里斯·派恩 Chris Pine 饰）的男子来到了岛上，从他口中，戴安娜得知外面的世界正在经历战争的磨难，而造成这一切的罪魁祸首，是战神阿瑞斯（大卫·休里斯 David Thewlis 饰）。为了拯救人类于水火之中，戴安娜依然拿起了长剑与盾牌，发誓要彻底摧毁阿瑞斯的阴谋。");
        film.setDate("2017-06-02");
        film.setDirector("派蒂·杰金斯");
        film.setPrice("53");
        filmService.save(film);
    }

    @Test
    public void saveFilm15() {
        Film film = new Film();
        film.setId(15L);
        film.setTitle("一代宗师");
        film.setContent("广东佛山人叶问（梁朝伟 饰），年少时家境优渥，师从咏春拳第三代传人陈华顺学习拳法，师傅“一条腰带一口气”的告诫，支持他走过兵荒马乱、朝代更迭的混乱年代。妻子张永成（宋慧乔 饰）泼辣干练，二人夫唱妇随，琴瑟合璧。 \n" +
                "　　1936年，佛山武术界乱云激荡。八卦拳宗师宫羽田（王庆祥 饰）年事已高，承诺隐退。其所担任的中华武士会会长职位，自然引起武林高手的关注与觊觎。包括宫羽田的独生女宫二（章子怡 饰）在内，白猿马三（张晋 饰）、关东之鬼丁连山（赵本山 饰）、咏春叶问等高手无不将目光聚焦在正气凛然的宫羽田身上。拳有南北，国有南北乎？最有德行之人才堪会长重任，然这浮世虚名却引得无数迷乱之人狂醉奔忙，浪掷残生。生逢乱世，儿女情长埋藏心底，被冷若寒冰的车轮碾作碎泥……");
        film.setDate("2013-01-08");
        film.setDirector("王家卫");
        film.setPrice("40");
        filmService.save(film);
    }


    /**
     * 高亮查询
     */
    @Test
    public void testNativeSearchQuery(){
        String fild="content";
        NativeSearchQuery nativeSearchQuery=new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery("黄").defaultField(fild))
                .withHighlightFields(new HighlightBuilder.Field(fild))
                .build();
        AggregatedPage<Film> list =  elasticsearchTemplate.queryForPage(nativeSearchQuery, Film.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                List<Film> films = new ArrayList<>();
                SearchHits hits = response.getHits();
                for (SearchHit hit:hits){
                    if(hits.getHits().length<=0){
                        return null;
                    }
                    Map<String,Object> sourceAsMap = hit.getSourceAsMap();
                    Film film=new Film();
                    String hightLightMessage = hit.getHighlightFields().get(fild).fragments()[0].toString();
                    film.setId(Long.parseLong(hit.getId()));
                    film.setTitle(sourceAsMap.get("title").toString());
                    film.setContent(sourceAsMap.get("content").toString());
                    film.setDirector(sourceAsMap.get("director").toString());
                    film.setPrice(sourceAsMap.get("price").toString());
                    film.setDate(sourceAsMap.get("date").toString());

                    try {
                        String setMethodName = parSetName(fild);
                        Class<? extends Film> filmClass = film.getClass();
                        Method setMethod = filmClass.getMethod(setMethodName,String.class);
                        setMethod.invoke(film,hightLightMessage);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    films.add(film);

                }
                if (films.size()>0){
                    return new AggregatedPageImpl<>((List<T>) films);
                }
                return null;
            }
        });
        list.getContent().forEach(film -> System.out.println(film));
    }



    public String parSetName(String fieldName){
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "set" + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

}
