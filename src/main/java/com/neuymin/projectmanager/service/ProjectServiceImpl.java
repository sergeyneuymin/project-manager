package com.neuymin.projectmanager.service;

import com.neuymin.projectmanager.entity.Project;
import com.neuymin.projectmanager.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    @Transactional
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }


    @Override
    public void delete(Integer id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project getProjectById(Integer id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        return optionalProject.orElse(null);
    }

    @Override
    public List<Project> getProjectsByParentId(Integer id) {
        return projectRepository.findAllByParentId(id);
    }
}
