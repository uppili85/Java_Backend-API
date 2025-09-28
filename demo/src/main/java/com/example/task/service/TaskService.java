package com.example.task.service;

import com.example.task.model.Task;
import com.example.task.model.TaskExecution;
import com.example.task.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() { return repository.findAll(); }
    public Optional<Task> getTaskById(String id) { return repository.findById(id); }
    public Task saveTask(Task task) { return repository.save(task); }
    public void deleteTask(String id) { repository.deleteById(id); }
    public List<Task> findByName(String name) { return repository.findByNameContaining(name); }

    public TaskExecution runTask(Task task) throws Exception {
        TaskExecution exec = new TaskExecution();
        exec.setStartTime(new Date());

        // Simple shell execution (runs locally, later replace with Kubernetes pod exec)
        Process process = Runtime.getRuntime().exec(task.getCommand());
        java.io.BufferedReader reader = new java.io.BufferedReader(
            new java.io.InputStreamReader(process.getInputStream())
        );
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) { output.append(line).append("\n"); }
        process.waitFor();

        exec.setEndTime(new Date());
        exec.setOutput(output.toString());
        task.getTaskExecutions().add(exec);
        repository.save(task);
        return exec;
    }
}
