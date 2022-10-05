package com.example.api8client;

import com.example.grpc.TaskServiceOuterClass;
import org.json.JSONArray;

public class TaskService {

    public static JSONArray taskToJson(TaskServiceOuterClass.TaskResponse taskResponse){
        JSONArray object = new JSONArray();
        object.put(0, taskResponse.getId());
        object.put(1, taskResponse.getName());
        object.put(2, taskResponse.getDescription());
        object.put(3, taskResponse.getDone());
        return object;
    }

    public JSONArray tasksToJson(TaskServiceOuterClass.AllTasksResponse casesResponse){
        JSONArray products = new JSONArray();
        for (int i = 0; i < casesResponse.getCasesCount(); i++){
            products.put(i, taskToJson(casesResponse.getCases(i)));
        }
        return products;
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
