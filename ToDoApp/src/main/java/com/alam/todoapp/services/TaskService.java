package com.alam.todoapp.services;

import com.alam.todoapp.model.Task;
import com.alam.todoapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class TaskService
{
    @Autowired
    private  final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public  List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public void createTask(String title)
    {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(long id)
    {
        taskRepository.deleteById(id);
    }

    public void toggleTask(long id)
    {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Invalid task id: " + id));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
