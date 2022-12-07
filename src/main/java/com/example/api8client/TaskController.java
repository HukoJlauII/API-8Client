package com.example.api8client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public String getIndex(Model model) {

        return "index";
    }

    @GetMapping("/allTasks")
    @ResponseBody
    public List<Task> showAllTasks()
    {
        return taskService.showAllTAsks(ClientServer.allTasks());
    }


    @PostMapping("/addTask")
    @ResponseBody
    public Task saveTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @DeleteMapping("/deleteTask/{id}")
    @ResponseBody
    public Long deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return id;
    }

    @PutMapping("/editTask/{id}")
    @ResponseBody
    public Task changeTask(@RequestBody Task task) {
        return taskService.changeTask(task);
    }

}
