package com.example.codingbat.entity;

import com.example.codingbat.entity.enums.StarBadgeValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StarBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private StarBadgeValue value;//5 10 25

    @ManyToOne
    private Language language; //til ko'payishi mumkin
}
