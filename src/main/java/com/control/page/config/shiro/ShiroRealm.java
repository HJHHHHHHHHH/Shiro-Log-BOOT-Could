package com.control.page.config.shiro;

import com.control.page.dao.MemberDao;
import com.control.page.dao.PermissionDao;
import com.control.page.entity.Member;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


//继承AuthorizingRealm，重写认证和授权方法
public class ShiroRealm extends AuthorizingRealm {

   @Autowired
   private MemberDao memberDao;

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 授权方法，如果不设置缓存管理的话，需要访问需要一定的权限或角色的请求时会进入这个方法
     */
    //角色权限和对应权限添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name= (String) principalCollection.getPrimaryPrincipal();
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        Member member = memberDao.getMemberByAccount(name);
        //查询用户
        SecurityUtils.getSubject().getSession().setAttribute(LoginInfo.USER_ID,member.getId());


       List<com.control.page.entity.Permission> permissionList = permissionDao.getPermission(name);
       if(name.equals("admin")){//admin账号默认有全部权限
           permissionList = permissionDao.getAllPermission();
       }
       if( permissionList != null && permissionList.size() > 0 ) {
           for (com.control.page.entity.Permission permission : permissionList) {
                /*//添加角色
                simpleAuthorizationInfo.addRole(role.getRoleName());*/
               //添加权限
               if(permission != null) {
                   simpleAuthorizationInfo.addStringPermission(permission.getPermissionCode());
               }
           }
       }
        return simpleAuthorizationInfo;
    }



    /**
     * 认证方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken userToken= (UsernamePasswordToken) token;
        //根据登录名查询用户，这里不用校验密码，因为密码的校验是交给shiro来完成的
        // 从数据库获取对应用户名密码的用户
        String password = memberDao.getPassword(userToken.getUsername());
        if(password == null){
            throw new AuthenticationException("账号或密码错误！");
        }

        // 设置登录过期时间为永不过期，毫秒值
        SecurityUtils.getSubject().getSession().setTimeout(-1000L);

        Member member = memberDao.getMemberByAccount(userToken.getUsername());
        if (member != null && member.getStatus()==2){
            throw new AuthenticationException("账户已停用！");
        }

        return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());

    }


}