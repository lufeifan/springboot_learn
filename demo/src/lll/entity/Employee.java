package com.example.demo.lll.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("FIRST_NAME")
    private String firstName;

    @TableField("LAST_NAME")
    private String lastName;

    @TableField("AGE")
    private Integer age;

    @TableField("SEX")
    private String sex;

    @TableField("INCOME")
    private Float income;


}
