package com.example.boot.mapper;

import com.example.boot.entity.TbUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * user mapper
 *
 * @author ltk
 * @date 2019/11/24
 */

@Mapper
public interface UserMapper {

    /**
     * query all user list
     *
     * @param user
     * @return List<TbUser>
     */
    List<TbUser> queryUser(@Param("user") TbUser user);

    /**
     * update user
     *
     * @param user
     * @return int
     */
    int update(@Param("user") TbUser user);

}
