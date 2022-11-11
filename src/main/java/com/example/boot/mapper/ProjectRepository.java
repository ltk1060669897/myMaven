package com.example.boot.mapper;

import com.example.boot.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * project dao
 *
 * @author ltk
 * @date 2019/11/24
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

  /**
   * 根据ID查询项目信息
   *
   * @param projectId
   * @return
   */
  Project findByProjectId(int projectId);

}
