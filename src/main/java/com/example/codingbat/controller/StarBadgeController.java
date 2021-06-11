package com.example.codingbat.controller;

import com.example.codingbat.payload.ApiResponse;
import com.example.codingbat.payload.StarBadgeDto;
import com.example.codingbat.service.StarBadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/starBadges")
public class StarBadgeController {
    @Autowired
    StarBadgeService starBadgeService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(starBadgeService.getAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> ById(@PathVariable Integer id){
        return ResponseEntity.ok(starBadgeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody StarBadgeDto starBadgeDto){
        ApiResponse apiResponse = starBadgeService.add(starBadgeDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);

    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody StarBadgeDto starBadgeDto){
        ApiResponse apiResponse = starBadgeService.edit(id, starBadgeDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleted(@PathVariable Integer id){
        ApiResponse apiResponse = starBadgeService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);

    }

}
