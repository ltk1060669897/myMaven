package com.example.boot.rest;

import com.example.boot.entity.Project;
import com.example.boot.service.ProjectService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * project controller
 *
 * @author ltk
 * @date 2019/11/24
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

  @Autowired private ProjectService projectService;

  /**
   * 分页查询项目
   *
   * @return Page<Project> @MethodName queryProjectPageList
   */
  @GetMapping("/queryProjectPageList")
  public Page<Project> queryProjectPageList() {
    PageRequest pageable = PageRequest.of(1, 10);
    return projectService.queryProjectPageList(pageable);
  }

  /**s
   * 分页查询项目 倒序
   *
   * @return Page<Project> @MethodName queryProjectPageListDesc
   */
  @GetMapping("/queryProjectPageListDesc")
  public Page<Project> queryProjectPageListDesc() {
    List<Sort.Order> orders = new ArrayList<>();
    orders.add(new Sort.Order(Direction.DESC,"projectId"));
    PageRequest pageable = PageRequest.of(1, 5,Sort.by(orders));
    return projectService.queryProjectPageList(pageable);
  }

  /**
   * 根据ID查询项目
   *
   * @return Project @MethodName queryProjectById
   */
  @GetMapping("/queryProjectById/{projectId}")
  public Project queryProjectById(@PathVariable int projectId) {
    return projectService.queryProjectById(projectId);
  }
}
