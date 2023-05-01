package com.neuymin.projectmanager.service;

import com.neuymin.projectmanager.entity.Project;

import java.util.List;


public interface ProjectService {

    List<Project> getAllProjects();

    void save(Project project);

    void delete(Integer id);

    Project getProjectById(Integer id);

    List<Project> getProjectsByParentId(Integer id);
}
