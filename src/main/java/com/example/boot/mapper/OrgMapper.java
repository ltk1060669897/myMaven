package com.example.boot.mapper;

import com.example.boot.entity.OrgVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * org mapper
 *
 * @author ltk
 * @date 2019/11/24
 */
@Mapper
public interface OrgMapper {

  /**
   * query all org list
   *
   * @return List<OrgVo>
   */
  List<OrgVo> queryAllOrg();
}
