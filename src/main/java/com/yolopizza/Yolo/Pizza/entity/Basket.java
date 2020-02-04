package com.yolopizza.Yolo.Pizza.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<BasketPizza> basketPizzas;

    public Basket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BasketPizza> getBasketPizzas() {
        return basketPizzas;
    }

    public void setBasketPizzas(List<BasketPizza> basketPizzas) {
        this.basketPizzas = basketPizzas;
    }
}