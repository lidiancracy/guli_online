package com.atguigu.Config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName mybatisConfig
 * @Description TODO
 * @Date 2022/10/21 17:05
 */
@Configuration
public class mybatisConfig {
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

}
