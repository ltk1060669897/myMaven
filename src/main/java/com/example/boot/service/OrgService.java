package com.example.boot.service;

import com.example.boot.entity.OrgVo;
import com.example.boot.mapper.OrgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * org service
 *
 * @author ltk
 * @date 2019/11/24
 */
@Service
public class OrgService {

  private static final int ROOT_MENU_ID = 0;

  @Autowired private OrgMapper orgMapper;

  /**
   * query all org list
   *
   * @return List<OrgVo>
   */
  public List<OrgVo> getOrgTree() {
    List<OrgVo> orgList = orgMapper.queryAllOrg();
    return convertTree(orgList, new ArrayList<>(), ROOT_MENU_ID);
  }

  /**
   * 树状结构转换
   *
   * @param list
   * @param resultList
   * @param parentId
   * @return List<OrgVo>
   */
  private List<OrgVo> convertTree(List<OrgVo> list, List<OrgVo> resultList, int parentId) {
    Map<Integer, OrgVo> map = new HashMap(6);
    // 将数据放入map中，便于根据key获取
    for (OrgVo orgVo : list) {
      // 以orgId为key，缓存数据
      map.put(orgVo.getOrgId(), orgVo);
    }
    // 根据map的key获取父类，将vo直接放入父类中
    for (OrgVo orgVo : list) {
      // 判断父ID是否根节点
      if (orgVo.getParentId() == ROOT_MENU_ID) {
        // 如果是根节点，放入list中
        resultList.add(orgVo);
      } else {
        // 非根节点，放入父类的childList中
        map.get(orgVo.getParentId()).getChildList().add(orgVo);
      }
    }

    return resultList;
  }
}
