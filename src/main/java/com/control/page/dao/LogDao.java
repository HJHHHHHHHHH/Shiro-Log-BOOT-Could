package com.control.page.dao;

import com.control.page.entity.Log;
import com.control.page.entity.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LogDao {

    @Insert("Insert into log (MEMBER,URI,REMARK,OPERATOR_IP,OPERATOR_TIME)" +
            " values ( #{member}, #{uri}, #{remark}, #{operatorIp} , #{operatorTime} ) ")
    int insertLog(Log log);



}
