package com.security.learn.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;

@Data

public class MongoEntity {

    @Id
    private Long id;

    private String title;

    private String description;

    private String by;

    private String url;

}
