package com.control.page.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface BasicSettingsDao {

    @Select({ })
    Map<String ,Object> getBasicSetting();



}
