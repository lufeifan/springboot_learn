package com.security.learn.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.security.learn.entity.Book;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RestController

@RequestMapping("/book")

public class BookController {

    @Resource
    private RestClient client;

 

 

    /**新增

     * 添加ES对象, Book的ID就是ES中存储的document的ID，所以最好不要为空，自定义生成的ID太浮夸

     *

     * @return ResponseEntity

     * @throws IOException

     */

    @RequestMapping("/add")
    public ResponseEntity<String> add(Book book) throws IOException {

        // 构造HTTP请求，第一个参数是请求方法，第二个参数是服务器的端点，host默认是http://localhost:9200，

        // endpoint直接指定为index/type的形式

        Request request = new Request("POST", new StringBuilder("/test_db/book/").

                append(book.getId()).toString());

        // 设置其他一些参数比如美化json

        request.addParameter("pretty", "true");

 

        String bookString=JSON.toJSONString(book);

        System.out.println(bookString);

 

        // 设置请求体并指定ContentType，如果不指定默认为APPLICATION_JSON

        request.setEntity(new NStringEntity(bookString, ContentType.APPLICATION_JSON));

 

        // 发送HTTP请求

        Response response = client.performRequest(request);

 

        // 获取响应体, id: AWXvzZYWXWr3RnGSLyhH

        String responseBody = EntityUtils.toString(response.getEntity());

        return new ResponseEntity<>(responseBody, HttpStatus.OK);

    }

 

    /**

     * 根据id获取ES对象

     *

     * @param id

     * @return

     * @throws IOException

     */

    @GetMapping("/getBookById/{id}")

    public ResponseEntity<String> getBookById(@PathVariable("id") String id) {

        Request request = new Request("GET", new StringBuilder("/test_db/book/").

                append(id).toString());

        // 添加json返回优化

        request.addParameter("pretty", "true");

        Response response = null;

        String responseBody = null;

        try {

            // 执行HHTP请求

            response = client.performRequest(request);

            responseBody = EntityUtils.toString(response.getEntity());

        } catch (IOException e) {

            return new ResponseEntity<>("can not found the book by your id", HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(responseBody, HttpStatus.OK);

    }

 

    /**

     * 根据id更新Book

     *

     * @param id

     * @param book

     * @return

     */

    @PostMapping("/updateBook/{id}")

    public ResponseEntity<String> updateBook(@PathVariable("id") String id, Book book) throws IOException {

        // 构造HTTP请求

        Request request = new Request("POST", new StringBuilder("/test_db/book/").

                append(id).append("/_update").toString());

        request.addParameter("pretty", "true");

 

        // 将数据丢进去，这里一定要外包一层“doc”，否则内部不能识别

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("doc", book);

        request.setEntity(new NStringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON));

 

        // 执行HTTP请求

        Response response = client.performRequest(request);

 

        // 获取返回的内容

        String responseBody = EntityUtils.toString(response.getEntity());

 

        return new ResponseEntity<>(responseBody, HttpStatus.OK);

    }

 

    /**

     * 使用脚本更新Book

     * @param id

     * @param

     * @return

     * @throws IOException

     */

    @PostMapping("/update2/{id}")

    public ResponseEntity<String> updateBook2(@PathVariable("id") String id, @RequestParam("name") String name) throws IOException {

        // 构造HTTP请求

        Request request = new Request("POST", new StringBuilder("/test_db/book/").

                append(id).append("/_update").toString());

        request.addParameter("pretty", "true");

 

        JSONObject jsonObject = new JSONObject();

        // 创建脚本语言，如果是字符变量，必须加单引号

        StringBuilder op1 = new StringBuilder("ctx._source.name=").append("'" + name + "'");

        jsonObject.put("script", op1);

 

        request.setEntity(new NStringEntity(jsonObject.toString(), ContentType.APPLICATION_JSON));

 

        // 执行HTTP请求

        Response response = client.performRequest(request);

 

        // 获取返回的内容

        String responseBody = EntityUtils.toString(response.getEntity());

 

        return new ResponseEntity<>(responseBody, HttpStatus.OK);

    }

 

    @PostMapping("/deleteById/{id}")

    public ResponseEntity<String> deleteById(@PathVariable("id") String id) throws IOException {

        Request request = new Request("DELETE", new StringBuilder("/test_db/book/").append(id).toString());

        request.addParameter("pretty", "true");

        // 执行HTTP请求

        Response response = client.performRequest(request);

        // 获取结果

        String responseBody = EntityUtils.toString(response.getEntity());

 

        return new ResponseEntity<>(responseBody, HttpStatus.OK);

    }

 

    /**

     * 获取ES对象列表

     *

     * @return

     * @throws IOException

     */

    @GetMapping("/getBookList")

    public ResponseEntity<Object> getBookList(@RequestParam("author") String author) {

        // 构造HTTP请求

        Request request = new Request("POST", new StringBuilder("/test_db/book/_search").toString());

        // 添加json返回优化

        request.addParameter("pretty", "true");


        StringBuilder requestBody = new StringBuilder();

        requestBody.append("{\"size\":10,\"query\":{\"match\":{\"author\":\"" + author + "\"}},\"from\": 0,\"_source\": [\"id\", \"name\", \"author\"]}");


        //JSONObject jsonObject = new JSONObject();

        // jsonObject.put("doc", requestBody.toString());


        request.setEntity(new NStringEntity(requestBody.toString(), ContentType.APPLICATION_JSON));


        Response response = null;

        String responseBody = null;

        Object result = null;

        try {

            // 执行HTTP请求

            response = client.performRequest(request);

            // 获取返回的内容

            responseBody = EntityUtils.toString(response.getEntity());

            Map jsonObject = JSON.parseObject(responseBody);

            result = jsonObject.get("hits");

            System.out.println(result);

        } catch (IOException e) {

            e.printStackTrace();

            return new ResponseEntity<>("can not found the book by your id", HttpStatus.NOT_FOUND);

        }


        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}