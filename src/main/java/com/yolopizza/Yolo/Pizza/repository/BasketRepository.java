package com.yolopizza.Yolo.Pizza.repository;

import com.yolopizza.Yolo.Pizza.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
