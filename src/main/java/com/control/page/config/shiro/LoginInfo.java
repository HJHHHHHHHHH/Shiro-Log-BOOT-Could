package com.control.page.config.shiro;

import org.apache.shiro.SecurityUtils;

/**
 * 描述:
 * 用户登录常量信息

 */
public class LoginInfo {

    public final static String USER_ID="loginUserId";


    public static Long getLoginUserId() {
        Long id =  (Long) SecurityUtils.getSubject().getSession().getAttribute(USER_ID);
        return id;
    }
}
