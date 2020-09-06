package com.myblog.demo.Dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author lululua
 * @since 2020-09-02
 */
@Data
public class BannerEntityDto implements Serializable {

    /**
     * @NotNull：不能为null，但可以为empty
     *
     * @NotEmpty：不能为null，而且长度必须大于0
     *
     * @NotBlank：只能作用在String上，不能为null，而且调用trim()后，长度必须大于0
     */
    @URL
    @ApiModelProperty(value = "链接地址")
    private String url;

    @NotBlank(message = "名字不能为空")
    @ApiModelProperty(value = "名字")
    private String name;

    @NotNull(message = "是否显示不能为空")
    @ApiModelProperty(value = "是否显示")
    private Integer state;


}
