package com.yolopizza.Yolo.Pizza.controller;

import com.yolopizza.Yolo.Pizza.entity.Basket;
import com.yolopizza.Yolo.Pizza.entity.BasketPizza;
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
                        @RequestParam(defaultValue = "0") Long basketId) {

        Basket basket = new Basket();
        int quantity = 0;
        double amount = 0d;
        Optional<Basket> optionalBasket = basketRepository.findById(basketId);
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
            quantity = basket.getBasketPizzas().size();
            for (BasketPizza basketPizza : basket.getBasketPizzas()) {
                amount+= basketPizza.getPizza().getPrice();
            }
        }
        model.addAttribute("quantity", quantity);
        model.addAttribute("amount", amount);
        model.addAttribute("basketId", basketId);
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "index";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam Long pizzaId,
                      @RequestParam Long basketId) {
        return "redirect:/?basketId=" + basketId;
    }
}