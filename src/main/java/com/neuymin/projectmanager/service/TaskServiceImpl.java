package com.neuymin.projectmanager.service;

import com.neuymin.projectmanager.entity.Project;
import com.neuymin.projectmanager.entity.Task;
import com.neuymin.projectmanager.repository.ProjectRepository;
import com.neuymin.projectmanager.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    @Transactional
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }


    @Override
    public void delete(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getTaskById(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    @Override
    public List<Task> getTasksByProjectId(Integer id) {
        return taskRepository.findAllByProjectId(id);
    }
}
