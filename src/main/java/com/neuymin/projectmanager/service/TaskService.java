package com.neuymin.projectmanager.service;

import com.neuymin.projectmanager.entity.Project;
import com.neuymin.projectmanager.entity.Task;

import java.util.List;


public interface TaskService {

    List<Task> getAllTasks();

    void save(Task task);

    void delete(Integer id);

    Task getTaskById(Integer id);

    List<Task> getTasksByProjectId(Integer id);
}
