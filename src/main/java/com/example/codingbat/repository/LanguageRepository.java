package com.example.codingbat.repository;

import com.example.codingbat.entity.Language;
import com.example.codingbat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    boolean existsByName(@NotNull(message = "programmingLanguage ni kiriting!") String name);
}
