package com.example.controllertask.Controller;

import com.example.controllertask.Api.ApiResponse;
import com.example.controllertask.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {


    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTask () {
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addTask (@RequestBody Task task) {
        tasks.add(task);
        return new ApiResponse("Task added successfully");
    }

    @PutMapping("/change/{index}/{status}")
    public Task changeTask (@PathVariable String status,@PathVariable int index) {
        for (int i = 0; i < tasks.size(); i++) {
            if (i == index ) {
                tasks.get(index).setStatus(status);
                return tasks.get(index);
            }
        }
        return null;
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask (@PathVariable int index) {
        tasks.remove(index);
        return new ApiResponse("Task deleted successfully");
    }

    @GetMapping("/search/{title}")
    public Task gtTaskByTitle (@PathVariable String title) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTitle().equalsIgnoreCase(title)){
                return tasks.get(i);
            }
        }
        return null;
    }


}
