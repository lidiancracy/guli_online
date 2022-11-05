package com.atguigu.educenter.util;

import com.atguigu.utils.DateUtil;

import java.util.Date;

/**
 * @ClassName redisconstant
 * @Description TODO
 * @Date 2022/11/5 15:05
 */
public class redisconstant {
    public static String SPILT = ":";

    public static String createloginkey() {
        return "Logincount" + SPILT + DateUtil.formatDate(new Date());
    }

    public static String getloginkey(String day) {
        return "Logincount" + SPILT + day;
    }

}
