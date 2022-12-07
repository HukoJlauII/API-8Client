package com.example.api8client;

import com.example.grpc.TaskServiceOuterClass;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TaskService {

    public Task showTask(TaskServiceOuterClass.TaskResponse taskResponse)
    {
        return new Task(taskResponse.getId(),taskResponse.getName(),taskResponse.getDescription(),taskResponse.getDone());
    }

    public List<Task> showAllTAsks(TaskServiceOuterClass.AllTasksResponse tasksResponse)
    {
        List<Task> tasks=new ArrayList<>();
        for (int i = 0; i < tasksResponse.getCasesCount(); i++) {
            tasks.add(showTask(tasksResponse.getCases(i)));
        }
        return tasks;
    }

    public Task addTask(Task task)
    {
        TaskServiceOuterClass.TaskResponse response = ClientServer.addTask(task);
        return new Task(response.getId(),  response.getName(), response.getDescription(),response.getDone());
    }

    public void deleteTask(Long id){
        TaskServiceOuterClass.Success response = ClientServer.deleteTask(id);
    }

    public Task changeTask(Task task){
        TaskServiceOuterClass.TaskResponse response = ClientServer.editTask(task);
        return new Task(response.getId(), response.getName(), response.getDescription(), response.getDone());
    }

    
}
