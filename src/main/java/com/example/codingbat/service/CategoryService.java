package com.example.codingbat.service;

import com.example.codingbat.entity.Category;
import com.example.codingbat.entity.Language;
import com.example.codingbat.payload.ApiResponse;
import com.example.codingbat.payload.CategoryDto;
import com.example.codingbat.repository.CategoryRepository;
import com.example.codingbat.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.orElseGet(Category::new);
    }

    public ApiResponse add(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setStarNumber(categoryDto.getStarNumber());

        List<Integer> languageList = categoryDto.getLanguageListIds();//languages id lar list lari bor
        List<Language> languages = new ArrayList<>();

        for (Integer integer : languageList) {
            Optional<Language> byId = languageRepository.findById(integer);
            if (byId.isPresent()) {
                Language language = byId.get();
                languages.add(language);
            } else {
                return new ApiResponse("Not found language", false);
            }
        }
        category.setLanguageList(languages);

        categoryRepository.save(category);
        return new ApiResponse("saved", true, category);

    }
    public ApiResponse edit(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();

            category.setName(categoryDto.getName());
            category.setDescription(categoryDto.getDescription());
            category.setStarNumber(categoryDto.getStarNumber());

            List<Language> languageList = category.getLanguageList();

            List<Integer> languageListIds = categoryDto.getLanguageListIds();

            for (Integer languageListId : languageListIds) {
                boolean b = languageList.removeIf(language -> languageListId == language.getId());
                // for (Language language : languageList) {
//                    if (languageListId == language.getId()){
//                        languageList.remove(language)
//                    }
//                }
                if (!b) {
                    languageList.add(languageRepository.getOne(languageListId));
                }else {
                    return new ApiResponse("not found language id", false);
                }
            }
            category.setLanguageList(languageList);
            categoryRepository.save(category);

        }
        return new ApiResponse("edited ", true);

    }
    public ApiResponse delete(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            categoryRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found category", false);

    }
}
