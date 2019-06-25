package com.control.page.dao;

import com.control.page.entity.Member;
import com.control.page.entity.Permission;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PermissionDao {

    @Select("SELECT p.ID as  id , p.permission_name as permissionName ,p.permission_code as permissionCode, p.url as url  , " +
            "   p.parentid as parentid  , p.type as type  ,sort as sort from member m  " +
            "  left join role_permission rp on rp.ROLE = m.ROLE " +
            " left join  permission  p  on p.ID = rp.PERMISSION " +
            "           WHERE m.name =  #{account}  order by sort asc  ")
    List<Permission> getPermission(String account);


    @Select("SELECT p.ID as  id , p.permission_name as permissionName ,p.permission_code as permissionCode, p.url as url  , " +
            "   p.parentid as parentid  , p.type as type ,sort as sort  from permission p order by sort asc ")
    List<Permission> getAllPermission();



}
