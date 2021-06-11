package com.example.codingbat.service;

import com.example.codingbat.entity.Language;
import com.example.codingbat.payload.ApiResponse;
import com.example.codingbat.repository.LanguageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository programmingLanguageRepository;

    public List<Language> getAll(){
        return programmingLanguageRepository.findAll();
    }

    public Language getById(Integer id){
        Optional<Language> byId = programmingLanguageRepository.findById(id);
        return byId.orElse(null);
    }
    public ApiResponse add(Language programmingLanguage){
        boolean exists = programmingLanguageRepository.existsByName(programmingLanguage.getName());
        if (exists){
            return new ApiResponse("this is programmingLanguage already exists", false);
        }
        Language programmingLanguage1=new Language();
        programmingLanguage1.setName(programmingLanguage.getName());
        programmingLanguageRepository.save(programmingLanguage1);
        return new ApiResponse("saved", true);
    }
    public ApiResponse edit(Integer id, Language programmingLanguage){
        Optional<Language> optionalProgrammingLanguage = programmingLanguageRepository.findById(id);
        if (!optionalProgrammingLanguage.isPresent()){
            return new ApiResponse("not found id", false);
        }
        boolean exists = programmingLanguageRepository.existsByName(programmingLanguage.getName());
        if (exists){
            return new ApiResponse("this is programmingLanguage already exists", false);
        }
        Language programmingLanguage1 = optionalProgrammingLanguage.get();
        programmingLanguage1.setName(programmingLanguage.getName());
        programmingLanguageRepository.save(programmingLanguage1);
        return new ApiResponse("saved", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Language> optionalProgrammingLanguage = programmingLanguageRepository.findById(id);
        if (optionalProgrammingLanguage.isPresent()){
            programmingLanguageRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found id", false);
    }
}
