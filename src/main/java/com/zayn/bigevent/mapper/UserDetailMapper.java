package com.zayn.bigevent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zayn.bigevent.pojo.UserDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author zayn
 * * @date 2024/3/27/23:09
 */
@Mapper
public interface UserDetailMapper extends BaseMapper<UserDetail> {
    @Update("update user_detail set avatar = #{avatar} , update_time = now() where user_id = #{id}")
    void updateAvatar(Integer id, String avatar);
}
