package com.neuymin.projectmanager.controller;

import com.neuymin.projectmanager.additional.TaskStatus;
import com.neuymin.projectmanager.entity.Project;
import com.neuymin.projectmanager.entity.Task;
import com.neuymin.projectmanager.service.ProjectService;
import com.neuymin.projectmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/projects/")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public String viewMainPage(Model model) {
        List<Project> listProjects = projectService.getProjectsByParentId(0);
        model.addAttribute("listProjects", listProjects);
        return "board";
    }

    @RequestMapping("/{id}")
    public String viewProjectPage(@PathVariable(name = "id") Integer id, Model model) {
        List<Project> listProjects = projectService.getProjectsByParentId(id);
        model.addAttribute("parentId", id);
        model.addAttribute("listProjects", listProjects);
        return "sub-board";
    }

    @RequestMapping("/{id}/tasks")
    public String viewTaskPage(@PathVariable(name = "id") Integer id, Model model) {
        List<Task> listTasks = taskService.getTasksByProjectId(id);
        model.addAttribute("parentId", id);
        model.addAttribute("listTasks", listTasks);
        return "board-tasks";
    }

    @RequestMapping("/new")
    public String showNewProjectForm(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);

        return "new_project";
    }

    @RequestMapping("/{id}/new")
    public String showNewSubProjectForm(@PathVariable(name = "id") Integer id, Model model) {
        Project project = new Project();
        project.setParentId(id);
        model.addAttribute("project", project);

        return "new_sub_project";
    }

    @RequestMapping("/{id}/tasks/new")
    public String showNewTaskForm(@PathVariable(name = "id") Integer id, Model model) {
        Task task = new Task();
        task.setProjectId(id);
        model.addAttribute("task", task);

        return "new_task";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProject(@ModelAttribute("project") Project project) {
        project.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        projectService.save(project);

        return "redirect:/projects/";
    }

    @RequestMapping(value = "/savetask", method = RequestMethod.POST)
    public String saveTask(@ModelAttribute("task") Task task) {
        task.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());

        Task taskObj = taskService.getTaskById(task.getId());

        if(taskObj == null) {
            task.setStatus(TaskStatus.NEW);
        } else {
            task.setDateCreated(taskObj.getDateCreated());
        }
        taskService.save(task);

        return "redirect:/projects/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProjectForm(@PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("edit_project");

        Project project = projectService.getProjectById(id);
        mav.addObject("project", project);

        return mav;
    }

    @RequestMapping("/{projectId}/tasks/edit/{id}")
    public ModelAndView showEditTaskForm(@PathVariable(name = "projectId") Integer projectId,
                                         @PathVariable(name = "id") Integer id) {
        ModelAndView mav = new ModelAndView("edit_task");

        Task task = taskService.getTaskById(id);
        mav.addObject("task", task);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProject(@PathVariable(name = "id") Integer id) {
        projectService.delete(id);

        return "redirect:/projects/";
    }

    @RequestMapping("/{projectId}/tasks/delete/{id}")
    public String deleteTask(@PathVariable(name = "projectId") Integer projectId,
                             @PathVariable(name = "id") Integer id) {

        taskService.delete(id);

        return "redirect:/projects/{projectId}/tasks";
    }

}
