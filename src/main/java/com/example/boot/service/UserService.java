package com.example.boot.service;

import com.example.boot.entity.Result;
import com.example.boot.entity.TbUser;
import com.example.boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * user service
 *
 * @author ltk
 * @date 2019/11/24
 */
@Service
public class UserService {

  @Autowired private UserMapper userMapper;

  /**
   * query all user list
   *
   * @return List<TbUser>
   */
  public List<TbUser> queryAllUser() {
    TbUser user = new TbUser();
    adjustParam(user);
    return userMapper.queryUser(user);
  }

  /**
   * query user list by name
   *
   * @return List<TbUser>
   */
  public List<TbUser> queryUserByName(String userName) {
    TbUser user = new TbUser();
    user.setUserName(userName);
    return userMapper.queryUser(user);
  }

  /**
   * update user
   *
   * @return Result
   */
  public Result update(TbUser user) {
    int count = userMapper.update(user);
    if (count > 0) {
      return new Result();
    }
    return new Result(Result.EMPTY);
  }

  /**
   * 处理param
   *
   * @param user
   * @return TbUser
   */
  private TbUser adjustParam(TbUser user) {
    if (StringUtils.isEmpty(user.getUserName())) {
      user.setUserName(Result.EMPTY);
    }
    return user;
  }
}
