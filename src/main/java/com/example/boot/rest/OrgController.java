package com.example.boot.rest;

import com.example.boot.entity.OrgVo;
import com.example.boot.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * org controller
 *
 * @author ltk
 * @date 2019/11/24
 */
@RestController
@RequestMapping("/org")
public class OrgController {

  @Autowired private OrgService orgService;

  /**
   * 查询组织机构-树状结构 @MethodName getOrgTree
   *
   * @return List<OrgVo>
   */
  @GetMapping("/getOrgTree")
  public List<OrgVo> getOrgTree() {
    return orgService.getOrgTree();
  }
}
