package com.control.page.service.impl;

import com.control.page.dao.BasicSettingsDao;
import com.control.page.service.BasicSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BasicSettingsServiceImpl implements BasicSettingsService {

    /**
     * dao注入
     */
    @Autowired
    private BasicSettingsDao basicSettingsDao;

    /**
     * 返回系统参数
     * @return
     */
    @Override
    public Map<String, Object> getBasicSetting() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 1);

        result.put("data", basicSettingsDao.getBasicSetting());
        result.put("code", 3);
        result.put("msg", "操作成功！");
        return result;
    }


}
