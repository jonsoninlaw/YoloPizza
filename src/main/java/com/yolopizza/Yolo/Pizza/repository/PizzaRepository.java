package com.yolopizza.Yolo.Pizza.repository;

import com.yolopizza.Yolo.Pizza.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
