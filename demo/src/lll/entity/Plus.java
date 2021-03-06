package com.example.demo.lll.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Plus implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


}
