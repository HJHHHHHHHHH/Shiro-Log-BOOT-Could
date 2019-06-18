package com.control.page.service;

import com.control.page.entity.Log;

/**
 * @description 日志接口
 */
public interface LogService {

    /**
     * 插入日志
     * @param entity
     * @return
     */
    int insertLog(Log entity);
}

