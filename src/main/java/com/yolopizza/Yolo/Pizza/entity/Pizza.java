package com.yolopizza.Yolo.Pizza.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private String image;

    @OneToMany(mappedBy = "pizza")
    private List<BasketPizza> basketPizzas;

    public Pizza() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<BasketPizza> getBasketPizzas() {
        return basketPizzas;
    }

    public void setBasketPizzas(List<BasketPizza> basketPizzas) {
        this.basketPizzas = basketPizzas;
    }
}