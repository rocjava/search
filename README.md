#####安装说明  
1.安装es,版本6.2.4，路径:D:\dev\elasticsearch-6.2.4  
2.安装elasticsearch-head，路径:D:\dev\elasticsearch-head\elasticsearch-head  
3.安装elasticsearch-head, 它是一个web管理工具，可以查询并操作es数据。依赖nodejs环境，所以需要先安装nodejs  
4.下载分词器，并解压到D:\dev\elasticsearch-6.2.4\plugins目录下，注意分词器版本和es版本要匹配  
****
#####说明  
1.数据初始化类src/test/TestItem2  
2.数据测试类src/test/TestItem1  
3.data目录下只做数据迁移用，用来造数据
****
#####记录  
1.使用程序批量插入数据，会出现越往后，库中数据库越多，插入越慢的情况

****
#####问题  
1.must和filter的区别?  
2.index、shards、replicas的结构关系?


