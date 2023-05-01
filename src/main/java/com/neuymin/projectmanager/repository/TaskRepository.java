package com.neuymin.projectmanager.repository;

import com.neuymin.projectmanager.entity.Project;
import com.neuymin.projectmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByProjectId(Integer id);

    

}
