package com.example.demo.lll.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Xiao Pengwei
 * @since 2020-08-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Te implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String city;

    private Integer tel;


}
