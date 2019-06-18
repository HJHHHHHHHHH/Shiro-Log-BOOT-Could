package com.control.page.controller;

import com.alibaba.fastjson.JSONObject;
import com.control.page.config.log.LogAnno;
import com.control.page.config.shiro.LoginInfo;
import com.control.page.dao.MemberDao;
import com.control.page.entity.Member;
import com.control.page.service.BasicSettingsService;
import com.control.page.util.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: HJH
 * @Description: 系统信息相关的接口
 * @Date: Created in 17:13 2019/2/26
 * @Modified By:
 */
@RestController
@RequestMapping
public class LoginContorller {

    @Autowired
    private MemberDao memberDao;


    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(String username, String password) {

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 执行认证登陆
        try {
            subject.login(token);//会触发com.itclj.common.shiro.UserRealm的doGetAuthenticationInfo方法;
        }catch(AuthenticationException e){
            String str = "账号或密码错误！";
         /*   if("账户已停用！".equals(e.getMessage())){
                str = "账户已停用！";
            }*/
            return JsonResult.returnFailJsonResult(str);
        }

        return JsonResult.ok("登录成功！",null);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public JsonResult logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return JsonResult.ok("退出登录成功！",null);
    }



    @ApiOperation(value = "获取当前登录人,权限设置")
    @PostMapping("/test")
    @RequiresPermissions("testPermission") //  是否有权限
    @LogAnno(operateType = "自动日志 测试") //是否增加日志
    public String test(String test){
        String account = SecurityUtils.getSubject().getPrincipal().toString();
        Member member =  memberDao.getMemberByAccount(account);
        new LoginInfo().getLoginUserId();
        return  JSONObject.toJSON(member).toString();
    }



    public static void main(String[] args) {
        // 1a3d9d44bdee370767c112857a45a984
            String x = new Md5Hash("password").toString();
        System.out.println(x);
        Subject currentUser = SecurityUtils.getSubject();
       // SysMemberEntity principals = (SysMemberEntity)currentUser.getPrincipal();

    }


}
