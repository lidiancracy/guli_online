package com.atguigu.ossservice.service;

import java.io.InputStream;

/**
 * @ClassName ossservice
 * @Description TODO
 * @Date 2022/10/24 17:58
 */
public interface ossservice {

    String upload(InputStream inputStream, String filename);
}
