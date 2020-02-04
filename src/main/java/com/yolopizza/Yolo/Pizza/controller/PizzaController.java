package com.yolopizza.Yolo.Pizza.controller;

import com.yolopizza.Yolo.Pizza.entity.Basket;
import com.yolopizza.Yolo.Pizza.entity.Pizza;
import com.yolopizza.Yolo.Pizza.repository.BasketRepository;
import com.yolopizza.Yolo.Pizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private BasketRepository basketRepository;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(required = false) Long basketId) {

        Basket basket = new Basket();
        if (basketId != null) {
            Optional<Basket> optionalBasket = basketRepository.findById(basketId);
            if (optionalBasket.isPresent()) {
                basket = optionalBasket.get();
            }
        }
        model.addAttribute("basket", basket);
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "index";
    }
}
