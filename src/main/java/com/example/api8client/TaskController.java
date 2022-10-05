package com.example.api8client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public String getIndex(Model model){

        model.addAttribute("tasks", taskService.tasksToJson(ClientServer.allTasks()).toList());
        return "index";
    }

    @PostMapping("/addTask")
    @ResponseBody
    public Task saveCase(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @DeleteMapping("/deleteTask/{id}")
    @ResponseBody
    public Long deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return id;
    }

    @PutMapping("/editTask/{id}")
    @ResponseBody
    public Task changeTask(@RequestBody Task task){
        return taskService.changeTask(task);
    }

}
