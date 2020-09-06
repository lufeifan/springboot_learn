//package com.security.learn.controller;
//
//import com.security.learn.dao.BookESMapper;
//import com.security.learn.entity.Book;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//import java.util.Date;
//
//@RestController
//public class HelloController {
//    @RequestMapping("/")
//    public String hello() {
//        return "hello";
//    }
//    @RequestMapping("/auth")
//    public String auth() {
//        return "auth";
//    }
//    @RequestMapping("/err")
//    public String err() {
//        return "erro";
//    }
//    @RequestMapping("/out")
//    public String out() {
//        return "out";
//    }
//    @RequestMapping("/time")
//    public Date time() {
//        return new Date();
//    }
//
////    ES搜索
//    @Resource
//    BookESMapper bookRepository;
//
//    @RequestMapping("/auth/add")
//    public String add(){
//        Book book = new Book();
//        book.setId("3");
//        book.setMessage("TTTTGGGGDDD");
//        book.setType("es");
//        book.setName("spring");
//        bookRepository.save(book);
//        return "success";
//    }
//
//
//}