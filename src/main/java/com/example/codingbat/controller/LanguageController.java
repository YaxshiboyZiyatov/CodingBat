package com.example.codingbat.controller;

import com.example.codingbat.entity.Language;
import com.example.codingbat.payload.ApiResponse;
import com.example.codingbat.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("api/language")
public class LanguageController {
    @Autowired
    LanguageService programmingLanguageService;

    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok(programmingLanguageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> byId(@PathVariable Integer id){
        return ResponseEntity.ok(programmingLanguageService.getById(id));
    }
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Language programmingLanguage){
        ApiResponse add = programmingLanguageService.add(programmingLanguage);
        return ResponseEntity.status(add.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(add);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id, @RequestBody Language programmingLanguage){
        ApiResponse edit = programmingLanguageService.edit(id, programmingLanguage);
        return ResponseEntity.status(edit.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(edit);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> edited(@PathVariable Integer id){
        ApiResponse delete = programmingLanguageService.delete(id);
        return ResponseEntity.status(delete.isSuccess()?HttpStatus.ACCEPTED: HttpStatus.CONFLICT).body(delete);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
