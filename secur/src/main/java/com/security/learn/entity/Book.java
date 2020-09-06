package com.security.learn.entity;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Book {
    @Id
    String id;
    String name;
    String message;
    String type;

}