package com.pregnant_mannage.mapper;

import com.pregnant_mannage.entity.Doctor;
import com.pregnant_mannage.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface DoctorMapper {

    //film_play_status是正在热映还是即将上映，number要显示的数量
    Doctor getDoctorByDoctorid(@Param("Doctorid") String Doctorid);

    Doctor checkloginDoctor(@Param("Doctorid") String Doctorid,@Param("pwd") String pwd);

    @Select("select * from Doctor ${where_condition}")
    List<Doctor> queryDoctorListWhere(
            @Param("where_condition") String where_condition);

    @Select("select * from doctor ${where_condition} limit #{0}, #{1}")
    List<Doctor> queryDoctorListWhereaddpage(@Param("0") int current_page,//0, 1, 2,3 是做了一个转换。
                                         @Param("1") int page_size,
                                         @Param("where_condition") String where_condition);
}
