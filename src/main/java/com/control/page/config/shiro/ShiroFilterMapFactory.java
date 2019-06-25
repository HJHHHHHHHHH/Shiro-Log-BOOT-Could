package com.control.page.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShiroFilterMapFactory {

    public static Map<String, String> shiroFilterMap() {
//		设置路径映射，注意这里要用LinkedHashMap 保证有序
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "anon");


        //不拦截vue
       // filterChainDefinitionMap.put("/vue-admin/index.html", "anon");
        filterChainDefinitionMap.put("/vue-admin/**", "anon");//index.html#/login
        filterChainDefinitionMap.put("/favicon.ico", "anon");

        //不拦截swagger
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/api-docs/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");

        //其它路径均需要登录
        filterChainDefinitionMap.put("/**", "authc");

        return filterChainDefinitionMap;
    }
}