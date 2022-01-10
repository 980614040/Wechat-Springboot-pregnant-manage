package com.pregnant_mannage.mapper;

import com.pregnant_mannage.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface AdminMapper {

    //film_play_status是正在热映还是即将上映，number要显示的数量
    Admin getAdminByadminid(@Param("adminid") String adminid);

    Admin checkloginadmin(@Param("adminid") String adminid,@Param("pwd") String pwd);

    @Select("select * from admin ${where_condition}")
    List<Admin> queryadminListWhere(
            @Param("where_condition") String where_condition);
}
