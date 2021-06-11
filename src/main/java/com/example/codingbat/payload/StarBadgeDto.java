package com.example.codingbat.payload;

import com.example.codingbat.entity.enums.StarBadgeValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class StarBadgeDto {


    @NotNull(message = "value empty!")
    private Integer value;//5 10 25

    private Integer languageId; //til ko'payishi mumkin
}
