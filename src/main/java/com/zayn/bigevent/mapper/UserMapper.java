package com.zayn.bigevent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zayn.bigevent.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author zayn
 * * @date 2024/3/26/15:13
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("update user set password = #{md5String} where id = #{id}")
    void updatePassword(Integer id, String md5String);
}
