package com.control.page.controller;

import com.control.page.service.BasicSettingsService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: HJH
 * @Description: 系统信息相关的接口
 * @Date: Created in 17:13 2019/2/26
 * @Modified By:
 */
@RestController
@RequestMapping("/basic")
public class BasicSettingsContorller {
    /** 注入service */
    @Autowired
    private BasicSettingsService basicSettingsService;


    /** 返回系统参数 */
    @ApiOperation(value = "返回系统参数")
    @PostMapping("/getBasicSetting")
    public Map<String ,Object> getBasicSetting(){
        return basicSettingsService.getBasicSetting();
    }








}
