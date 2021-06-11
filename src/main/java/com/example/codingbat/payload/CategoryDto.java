package com.example.codingbat.payload;

import com.example.codingbat.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String name;
    private String description;
    private int starNumber;

    private List<Integer> languageListIds;


}
