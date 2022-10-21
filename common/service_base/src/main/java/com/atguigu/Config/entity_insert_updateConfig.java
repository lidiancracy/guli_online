package com.atguigu.Config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName entity_insert_updateConfig
 * @Description TODO
 * @Date 2022/10/21 19:27
 */
@Component
public class entity_insert_updateConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //参数1：对应类中的属性名称
        this.setFieldValByName("gmtCreate", new Date(), metaObject);
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified", new Date(), metaObject);
    }

}
