package com.example.boot.service;

import com.example.boot.entity.Project;
import com.example.boot.mapper.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * project service
 *
 * @author ltk
 * @date 2019/11/24
 */
@Service
public class ProjectService {

  @Autowired private ProjectRepository projectRepository;

  /**
   * query project page list
   *
   * @return Page<Project>
   */
  public Page<Project> queryProjectPageList(PageRequest pageable) {
    return projectRepository.findAll(pageable);
  }

  /**
   * query project by id
   *
   * @return Project
   */
  public Project queryProjectById(int projectId) {
    return projectRepository.findByProjectId(projectId);
  }

}
