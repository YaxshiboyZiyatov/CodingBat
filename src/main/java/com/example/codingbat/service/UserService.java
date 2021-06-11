package com.example.codingbat.service;

import com.example.codingbat.entity.StarBadge;
import com.example.codingbat.entity.Task;
import com.example.codingbat.entity.User;
import com.example.codingbat.payload.ApiResponse;
import com.example.codingbat.payload.UserDto;
import com.example.codingbat.repository.StarBadgeRepository;
import com.example.codingbat.repository.TaskRepository;
import com.example.codingbat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    StarBadgeRepository starBadgeRepository;

    public List<User> getAll(){
      return userRepository.findAll();
    }

    public User getById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public ApiResponse add(UserDto userDto){
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (exists){
            return new ApiResponse("this is email already exists", false);
        }
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(userDto.getStarBadgeId());
        if (!optionalStarBadge.isPresent()){
            return new ApiResponse("Not found StarBadge Id", false);
        }

        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        List<Integer> integerList = userDto.getTaskList();

        List<Task> taskList=new ArrayList<>();

        for (Integer integer : integerList) {
            Optional<Task> byId = taskRepository.findById(integer);
            if (byId.isPresent()){
                Task task = byId.get();
                taskList.add(task);

            }
        }
        user.setStarBadge(optionalStarBadge.get());
        user.setTaskList(taskList);
        userRepository.save(user);

        return new ApiResponse("saved", true);
    }
    public ApiResponse edit(Integer id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new ApiResponse("not found user id", false);
        }
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (exists){
            return new ApiResponse("this is email already exists", false);
        }
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(userDto.getStarBadgeId());
        if (!optionalStarBadge.isPresent()){
            return new ApiResponse("Not found StarBadge Id", false);
        }
        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        List<Integer> integerList = userDto.getTaskList();

        List<Task> taskList=new ArrayList<>();

        for (Integer integer : integerList) {
            Optional<Task> byId = taskRepository.findById(integer);
            if (byId.isPresent()){
                Task task = byId.get();
                taskList.add(task);

            }
        }

//        List<Task> userTaskList = user.getTaskList();
//
//        List<Integer> integerList = userDto.getTaskList();
//        for (Integer integer : integerList) {
//            boolean b = userTaskList.removeIf(task -> integer == task.getId());
//            if (!b){
//                userTaskList.add(taskRepository.getOne(integer));
//
//            }else {
//                return new ApiResponse("not found task id", false);
//            }
//        }
        user.setStarBadge(optionalStarBadge.get());
        user.setTaskList(taskList);
        userRepository.save(user);

        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            userRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found id",false);
    }
}
