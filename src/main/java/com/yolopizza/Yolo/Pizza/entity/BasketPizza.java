package com.yolopizza.Yolo.Pizza.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class BasketPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pizza pizza;

    @ManyToOne
    private Basket basket;

    public BasketPizza() {

    }

    public BasketPizza(Pizza pizza, Basket basket) {
        this.pizza = pizza;
        this.basket = basket;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
