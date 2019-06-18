package com.control.page.service.impl;


import com.control.page.entity.Log;
import com.control.page.dao.BasicSettingsDao;
import com.control.page.dao.LogDao;
import com.control.page.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    /**
     * dao注入
     */
    @Autowired
    private LogDao logDao;


    @Override
    public int insertLog(Log log) {
        return logDao.insertLog(log);
    }
}
