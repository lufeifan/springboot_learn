package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.User;
import com.rabbitmq.client.*;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private RestHighLevelClient client;

    ElasticsearchRestTemplate
    /**
     * 创建index
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("posts");
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除index
     * @throws IOException
     */
    @Test
    public void tets2() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("posts");
        AcknowledgedResponse exists = client.indices().delete(request, RequestOptions.DEFAULT);
    }
    /**
     * 是否存在index
     */
    @Test
    public void test3() throws IOException {
        GetIndexRequest request = new GetIndexRequest("twitter");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }
    /**
     * 创建内容
     */
    @Test
    public void test4() throws IOException {
        User user = new User("lufeifan", 20);
        IndexRequest request = new IndexRequest("twitter")
                .id("1").source(JSON.toJSONString(user),XContentType.JSON).timeout(TimeValue.timeValueSeconds(1));
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse);
    }

    @Test
    public void test5() throws IOException {
        IndexRequest request = new IndexRequest("posts")
                .id("3")
                .source("user", "3",
                        "postDate", new Date(),
                        "message", "trying out Elasticsearch");
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse);
    }

    /**
     * 查看文档内容
     * @throws IOException
     */
    @Test
    public void test6() throws IOException {
        GetRequest getRequest = new GetRequest(
                "posts",
                "2");
//        不返回 _source
//        getRequest.fetchSourceContext(new FetchSourceContext(false));
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
//        返回文档内容
//        System.out.println(getResponse.getSource());
        System.out.println(getResponse.getSourceAsString());
    }

    /**
     * 判断文档是否存在
     * @throws IOException
     */
    @Test
    public void test7() throws IOException {
        GetRequest getRequest = new GetRequest(
                "posts",
                "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    /**
     * 删除文档内容
     * @throws IOException
     */
    @Test
    public void test8() throws IOException {
        DeleteRequest request = new DeleteRequest(
                "posts",
                "4");
        DeleteResponse deleteResponse = client.delete(
                request, RequestOptions.DEFAULT);
        System.out.println(deleteResponse);
    }

    /**
     * 更新文档内容
     * @throws IOException
     */
    @Test
    public void test9() throws IOException {

        UpdateRequest request = new UpdateRequest(
                "posts",
                "3").doc("field","foot");
//        User user = new User("lufeifan", 21);
//        UpdateRequest request = new UpdateRequest(
//                "twitter",
//                "1");
//        request.doc(JSON.toJSONString(user),XContentType.JSON);
        UpdateResponse updateResponse = client.update(
                request, RequestOptions.DEFAULT);
        System.out.println(updateResponse);
    }
    /**
     * 批量插入数据
     */
    @Test
    public void test10() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("posts").id("1")
                .source(XContentType.JSON,"field", "foo","other","1"));
        request.add(new IndexRequest("posts").id("2")
                .source(XContentType.JSON,"field", "bar","other","2"));
        request.add(new IndexRequest("posts").id("3")
                .source(XContentType.JSON,"field", "baz","other","3"));
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);
    }
    @Test
    public void test11() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest("posts", "3"));
        request.add(new UpdateRequest("posts", "2")
                .doc(XContentType.JSON,"other", "test"));
        request.add(new IndexRequest("posts").id("4")
                .source(XContentType.JSON,"field", "baz"));
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulkResponse);
    }
    /**
     * 查询
     */
    @Test
    public void test12() throws IOException {
        SearchRequest searchRequest = new SearchRequest("posts");
        //查询构建器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //条件
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("2","other"));

        //将构建器放入
        SearchRequest source = searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits());
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }
    /**
     * 高亮查询
     */
    @Test
    public void test13() throws IOException {
        SearchRequest searchRequest = new SearchRequest("posts");
        //构造搜索对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //条件
        /**
         * 查询field 字段 值为 foo
         */
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("foo","field"));
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("field");
//        多个高亮显示
        highlightBuilder.requireFieldMatch(true);
////       字段 field
//        HighlightBuilder.Field highlightTitle =
//                new HighlightBuilder.Field("other");
////        高亮类型
//        highlightTitle.highlighterType("unified");
//        highlightTitle.preTags("<span style='color:red>");
//        highlightTitle.postTags("</span>");
//////
//        highlightBuilder.field(highlightTitle);
//        HighlightBuilder.Field highlightUser = new HighlightBuilder.Field("other");
//        highlightBuilder.field(highlightUser);


        searchSourceBuilder.highlighter(highlightBuilder);

        SearchRequest source = searchRequest.source(searchSourceBuilder);
//        返回结果
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        for (SearchHit hit : searchResponse.getHits().getHits()) {
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            System.out.println(highlightFields.get("other"));
            System.out.println(highlightFields.get("field"));

//            System.out.println(hit.getSourceAsMap());
        }
    }
    @Autowired
    private Connection connection;

    @Test
    public void mq() throws IOException, TimeoutException {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost("127.0.0.1");
//        connectionFactory.setVirtualHost("mqtest");
//        connectionFactory.setUsername("mq");
//        connectionFactory.setPassword("123");
//        Connection connection = connectionFactory.newConnection();
//        创建消息队列
        Channel channel = connection.createChannel();
//        绑定消息队列
        channel.queueDeclare("lululua",false,false,false,null);
//        发布消息，交换机
        String message = "Hello World!";
//        for (int i = 0; i < 10; i++) {
//            channel.basicPublish("","lululua",null, (i+":"+message).getBytes());
//        }
        channel.basicPublish("","lululua",null, message.getBytes());
        /**
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         * 持久化MessageProperties.PERSISTENT_TEXT_PLAIN,
         */


        connection.close();
    }

    @Test
    public void get_msg() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setVirtualHost("mqtest");
        connectionFactory.setUsername("mq");
        connectionFactory.setPassword("123");
        Connection connection = connectionFactory.newConnection();
//        创建消息队列
        Channel channel = connection.createChannel();
//        绑定消息队列
        /**
         * durable 持久化
         * exclusive 独占队列
         * autoDelete 自动删除
         * queueDeclare（String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments）
         */
        channel.queueDeclare("lululua",false,false,false,null);
//        发布消息，交换机
        /**
         * basicConsume(String queue, boolean autoAck, DeliverCallback deliverCallback, CancelCallback cancelCallback)
         */
        channel.basicConsume("lululua", true, new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(message);
            }
        }, consumerTag -> { });

        connection.close();
    }
}
