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

    @Select("   SELECT m.ID as  id ,m.account as account ,m.role as role, (Select ROLE_NAME from role where role.ID = m.role) as roleName, NAME as name  , STATUS as status ,l.OPERATOR_TIME as lastOperatorTime,l.OPERATOR_IP as lastOperatorIp,\n" +
            " (select OPERATOR_TIME from log log  where  m.id = log.member and log.uri = '/login' order by log.id DESC limit 1  ) as operatorTime, " +
            "  (select OPERATOR_IP from log log  where  m.id = log.member and log.uri = '/login' order by log.id DESC limit 1  ) as operatorIp, " +
            " (select count(1) from log log  where  m.id = log.member and log.uri = '/login'   ) as loginTimes " +
            " from member m " +
            " left join log l on l.member = m.id WHERE name =  #{account}  and l.uri = '/login'   " +
            " order by l.id DESC limit 1,1 " )
    Map getMemberLoginInfoByAccount(String account );

    @Select("SELECT password as  password from member "  +
            "WHERE name = #{account}")
    String getPassword(String account );

}
