package com.control.page.config.log;


import java.lang.reflect.Method;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.control.page.dao.MemberDao;
import com.control.page.entity.Log;
import com.control.page.entity.Member;
import com.control.page.service.LogService;
import com.control.page.util.Util;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * AOP实现日志
 *
 * @author liqiang
 *
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private LogService logService;// 日志Service

    @Autowired
    private MemberDao memberDao;

    /**
     * 环绕通知记录日志通过注解匹配到需要增加日志功能的方法
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.control.page.config.log.LogAnno)")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        // 1.方法执行前的处理，相当于前置通知
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上面的注解
        LogAnno logAnno = method.getAnnotation(LogAnno.class);
        // 获取操作描述的属性值
        String operateType = logAnno.operateType();
        // 创建一个日志对象(准备记录日志)
        Log log = new Log();
        Object result = null;


        result = pjp.proceed();


        String account = SecurityUtils.getSubject().getPrincipal().toString();
        if( account != null ){
            Member member =  memberDao.getMemberByAccount(account);
            if(member != null){
                log.setMember(member.getId());
                log.setOperatorTime(new Date());
                // 获取输入参数
                RequestAttributes ra = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes sra = (ServletRequestAttributes)ra;
                HttpServletRequest request = sra.getRequest();

                log.setUri(request.getRequestURI());
                log.setRemark(operateType +" Param: "+ JSONObject.toJSON(request.getParameterMap()).toString());//操作说明
                if("/login".equals(request.getRequestURI())){
                    log.setRemark(operateType );//操作说明
                }

                log.setOperatorIp(Util.getIpAddr(request));
                //让代理方法执行
                int resultDb =  logService.insertLog(log);
            }
        }
        return result;
    }
}