package com.control.page.dao;

import com.control.page.entity.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface MemberDao {

    @Select("SELECT ID as  id ,account as account ,NAME as name  , STATUS as status from member "  +
            "WHERE name = #{account} and  password = #{password}  ")
    Member getMember(String account ,String password);

    @Select("SELECT ID as  id ,account as account ,NAME as name  , STATUS as status from member "  +
            "WHERE name = #{account}")
    Member getMemberByAccount(String account );

    @Select("SELECT password as  password from member "  +
            "WHERE name = #{account}")
    String getPassword(String account );

}
