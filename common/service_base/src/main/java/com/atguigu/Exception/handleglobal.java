package com.atguigu.Exception;


import com.atguigu.R.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理全局异常
 * 处理官方特定异常
 * 处理自定义异常
 */
@ControllerAdvice
public class handleglobal {
    /**
     * 全局异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    //因为他不在Controller中。没有@RestController，所以数据不会返回，需要加@ResponeseBody返回数据
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理。。。");
    }
    /**
     * 官方特定异常
     */
    @ExceptionHandler(ArithmeticException.class)
    //因为他不在Controller中。没有@RestController，所以数据不会返回，需要加@ResponeseBody返回数据
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("出现了算术异常");
    }
    /**
     * 自定义异常
     */
    @ExceptionHandler(lidianException.class)
    //因为他不在Controller中。没有@RestController，所以数据不会返回，需要加@ResponeseBody返回数据
    @ResponseBody
    public R error(lidianException e){
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}
