package com.cache.demo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lululua
 * @since 2020-08-17
 */
@Data
@ApiModel(value="User对象", description="")
public class ShiroUser implements Serializable {

    private String name;

    private String password;

}
