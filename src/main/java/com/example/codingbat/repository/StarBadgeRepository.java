package com.example.codingbat.repository;

import com.example.codingbat.entity.StarBadge;
import com.example.codingbat.entity.User;
import com.example.codingbat.entity.enums.StarBadgeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarBadgeRepository extends JpaRepository<StarBadge, Integer> {
}
