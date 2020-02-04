package com.yolopizza.Yolo.Pizza.repository;

import com.yolopizza.Yolo.Pizza.entity.BasketPizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketPizzaRepository extends JpaRepository<BasketPizza, Long> {
}
