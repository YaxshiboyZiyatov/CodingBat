package com.example.codingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Email(message = "Email valid emas!")
    @Column(unique = true)

    @NotEmpty(message = "Empty bo'lmasin")
    private String email;


    @NotNull(message = "bo'sh bo'lmasin")
    private String password;

    private List<Integer> taskList;

    private Integer starBadgeId;


}
