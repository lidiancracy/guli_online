package com.atguigu.Exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName lidianException
 * @Description TODO
 * @Date 2022/10/21 20:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class lidianException extends  RuntimeException{
    /**
     * code msg的值通过 new throw lidainexception(code,msg)传过来
     */
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("出错msg")
    private String msg;
}
