package com.example.boot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * project entity
 *
 * @author ltk
 * @date 2021/03/11
 */
@Entity
@Table(name = "bus_project")
@Data
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int projectId;

  @Column(name = "project_name")
  private String projectName;

  @Override
  public String toString() {
    return "Project{"
        + "projectId='"
        + projectId
        + '\''
        + ", projectName='"
        + projectName
        + '\''
        + '}';
  }
}
