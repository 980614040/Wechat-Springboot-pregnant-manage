package com.pregnant_mannage.mapper;

import com.pregnant_mannage.entity.Doctortime;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DoctortimeMapper {

    //film_play_status是正在热映还是即将上映，number要显示的数量
    //Doctortime getDoctortimeByDoctorid(@Param("Doctorid") String Doctorid);
    @Select("select * from Doctortime ${where_condition}")
    List<Doctortime> queryDoctortimeListWhere(
            @Param("where_condition") String where_condition);



}
