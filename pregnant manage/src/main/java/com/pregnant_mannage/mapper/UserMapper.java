package com.pregnant_mannage.mapper;

import com.pregnant_mannage.entity.User;
import com.pregnant_mannage.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    //film_play_status是正在热映还是即将上映，number要显示的数量
    User getUserByUserid(@Param("Userid") String Userid);
    User checkloginUser(@Param("Userid") String Userid, @Param("pwd") String pwd);

    @Select("select DISTINCT user.*  from User,exam_paper ${where_condition}")
    List<User> getuserlistbydoctorid(
            @Param("where_condition") String where_condition);


    @Select("select * from User ${where_condition}")
    List<User> queryUserListWhere(
            @Param("where_condition") String where_condition);


    @Select("select * from user ${where_condition} limit #{0}, #{1}")
    List<User> queryUserListWhereaddpage(@Param("0") int current_page,//0, 1, 2,3 是做了一个转换。
                                  @Param("1") int page_size,
                                  @Param("where_condition") String where_condition);

}