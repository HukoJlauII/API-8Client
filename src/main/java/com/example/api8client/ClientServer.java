package com.example.api8client;

import com.example.grpc.TaskServiceGrpc;
import com.example.grpc.TaskServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ClientServer {
    public static TaskServiceGrpc.TaskServiceBlockingStub taskServiceBlockingStub;

    public static void startClientServer() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8081").usePlaintext().build();
        taskServiceBlockingStub = TaskServiceGrpc.newBlockingStub(channel);
    }

    public static TaskServiceOuterClass.AllTasksResponse allTasks() {
        TaskServiceOuterClass.Success success = TaskServiceOuterClass.Success.newBuilder().setSuccess(true).build();
        return taskServiceBlockingStub.allTasks(success);
    }


    public static TaskServiceOuterClass.TaskResponse addTask(Task task)
    {
        TaskServiceOuterClass.TaskRequest taskRequest= TaskServiceOuterClass.TaskRequest.newBuilder().setId(0).setName(task.getName()).setDescription(task.getDescription()).setDone(task.isDone()).build();
        return taskServiceBlockingStub.addTask(taskRequest);
    }

    public static TaskServiceOuterClass.Success deleteTask(Long id)
    {
        TaskServiceOuterClass.TaskId request= TaskServiceOuterClass.TaskId.newBuilder().setId(id).build();
        return taskServiceBlockingStub.deleteTask(request);
    }

    public static TaskServiceOuterClass.TaskResponse editTask(Task task)
    {
        TaskServiceOuterClass.TaskRequest taskRequest= TaskServiceOuterClass.TaskRequest.newBuilder().setId(task.getId()).setName(task.getName()).setDescription(task.getDescription()).setDone(task.isDone()).build();
        return taskServiceBlockingStub.editTask(taskRequest);
    }



}
