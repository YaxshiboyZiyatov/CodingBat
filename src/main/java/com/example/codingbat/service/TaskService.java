package com.example.codingbat.service;

import com.example.codingbat.entity.Category;
import com.example.codingbat.entity.Task;
import com.example.codingbat.payload.ApiResponse;
import com.example.codingbat.payload.TaskDto;
import com.example.codingbat.repository.CategoryRepository;
import com.example.codingbat.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<Task> getAll(){
        return taskRepository.findAll();
    }

    public Task getById(Integer id){
        Optional<Task> byId = taskRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(TaskDto taskDto){
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        boolean exists = taskRepository.existsByNameAndDescription(taskDto.getName(), taskDto.getDescription());
        if (exists){
            return new ApiResponse("this is information already exists", false);
        }
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Not found category id", false);
        }
        Task task=new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setCategory(optionalCategory.get());

        taskRepository.save(task);

        return new ApiResponse("saved", true);

    }
    public ApiResponse edit(Integer id, TaskDto taskDto){
        Optional<Task> optionalTask = taskRepository.findById(id);
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryId());
        boolean exists = taskRepository.existsByNameAndDescription(taskDto.getName(), taskDto.getDescription());
        if (exists){
            return new ApiResponse("this is information already exists", false);
        }
        if (!optionalCategory.isPresent()){
            return new ApiResponse("Not found category id", false);
        }
        if (!optionalTask.isPresent()){
            return new ApiResponse("Not found task id", false);

        }
        Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setCategory(optionalCategory.get());

        taskRepository.save(task);

        return new ApiResponse("saved", true);
    }
    public ApiResponse delete(Integer id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()){
            taskRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found id", false);
    }
}
