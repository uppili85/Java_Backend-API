package com.example.task.controller;

import com.example.task.model.Task;
import com.example.task.model.TaskExecution;
import com.example.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) { this.service = service; }

    @GetMapping
    public List<Task> getAllTasks(@RequestParam(required = false) String id) {
        if (id != null) return service.getTaskById(id).map(List::of).orElse(List.of());
        return service.getAllTasks();
    }

    @PutMapping
    public Task createTask(@RequestBody Task task) { return service.saveTask(task); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        service.deleteTask(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public List<Task> findByName(@RequestParam String name) { return service.findByName(name); }

    @PutMapping("/{id}/execute")
    public TaskExecution runTask(@PathVariable String id) throws Exception {
        Task task = service.getTaskById(id).orElseThrow();
        return service.runTask(task);
    }
}

