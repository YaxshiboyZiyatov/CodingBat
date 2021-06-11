package com.example.codingbat.service;

import com.example.codingbat.entity.Language;
import com.example.codingbat.entity.StarBadge;
import com.example.codingbat.entity.enums.StarBadgeValue;
import com.example.codingbat.payload.ApiResponse;
import com.example.codingbat.payload.StarBadgeDto;
import com.example.codingbat.repository.LanguageRepository;
import com.example.codingbat.repository.StarBadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StarBadgeService {
    @Autowired
    StarBadgeRepository starBadgeRepository;
    @Autowired
    LanguageRepository languageRepository;

    public List<StarBadge> getAll() {
        return starBadgeRepository.findAll();
    }

    public StarBadge getById(Integer id) {
        Optional<StarBadge> byId = starBadgeRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse add(StarBadgeDto starBadgeDto) {

        Optional<Language> optionalLanguage = languageRepository.findById(starBadgeDto.getLanguageId());
        if (!optionalLanguage.isPresent()) {
            return new ApiResponse("Not found id", false);
        }

        StarBadge starBadge1 = new StarBadge();

        if (starBadgeDto.getValue() <= 5) {
            starBadge1.setValue(StarBadgeValue.EASY);
            starBadge1.setLanguage(optionalLanguage.get());
            starBadgeRepository.save(starBadge1);
            return new ApiResponse("saved", true);
        } else if (starBadgeDto.getValue() <= 15) {
            starBadge1.setValue(StarBadgeValue.MEDIUM);
            starBadge1.setLanguage(optionalLanguage.get());
            starBadgeRepository.save(starBadge1);
            return new ApiResponse("saved", true);
        } else if (starBadgeDto.getValue() <= 25) {
            starBadge1.setValue(StarBadgeValue.HARD);
            starBadge1.setLanguage(optionalLanguage.get());
            starBadgeRepository.save(starBadge1);
            return new ApiResponse("saved", true);


        }
        return new ApiResponse("Error", false);

    }
        public ApiResponse edit(Integer id, StarBadgeDto starBadgeDto) {
            Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
            Optional<Language> optionalLanguage = languageRepository.findById(starBadgeDto.getLanguageId());
            if (!optionalLanguage.isPresent()) {
                return new ApiResponse("Not found Language id", false);
            }
            if (!optionalStarBadge.isPresent()) {
                return new ApiResponse("Not found StarBadge id", false);
            }
            StarBadge starBadge1 = optionalStarBadge.get();
            if (starBadgeDto.getValue() <= 5) {
                starBadge1.setValue(StarBadgeValue.EASY);
                starBadge1.setLanguage(optionalLanguage.get());
                starBadgeRepository.save(starBadge1);
                return new ApiResponse("saved", true);
            } else if (starBadgeDto.getValue() <= 15) {
                starBadge1.setValue(StarBadgeValue.MEDIUM);
                starBadge1.setLanguage(optionalLanguage.get());
                starBadgeRepository.save(starBadge1);
                return new ApiResponse("saved", true);
            } else if (starBadgeDto.getValue() <= 25) {
                starBadge1.setValue(StarBadgeValue.HARD);
                starBadge1.setLanguage(optionalLanguage.get());
                starBadgeRepository.save(starBadge1);
                return new ApiResponse("saved", true);


            }
            return new ApiResponse("Error", false);

        }
    public ApiResponse delete(Integer id) {
        Optional<StarBadge> optionalStarBadge = starBadgeRepository.findById(id);
        if (optionalStarBadge.isPresent()) {
            starBadgeRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("Not found id", false);
    }
}
