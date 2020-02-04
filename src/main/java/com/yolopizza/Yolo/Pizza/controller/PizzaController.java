package com.yolopizza.Yolo.Pizza.controller;

import com.yolopizza.Yolo.Pizza.entity.Basket;
import com.yolopizza.Yolo.Pizza.entity.BasketPizza;
import com.yolopizza.Yolo.Pizza.entity.Pizza;
import com.yolopizza.Yolo.Pizza.repository.BasketPizzaRepository;
import com.yolopizza.Yolo.Pizza.repository.BasketRepository;
import com.yolopizza.Yolo.Pizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketPizzaRepository basketPizzaRepository;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(required = false) Long basketId) {

        Basket basket = new Basket();
        int quantity = 0;
        double amount = 0d;

        List<Basket> baskets = basketRepository.findAll();
        if (basketId == null && !baskets.isEmpty()) {
            basket = baskets.get(0);
        } else if (baskets.isEmpty() || basketId == -1L) {
            basket = basketRepository.save(basket);
        } else {
            Optional<Basket> optionalBasket = basketRepository.findById(basketId);
            if (optionalBasket.isPresent()) {
                basket = optionalBasket.get();
            } else {
                return "redirect:/";
            }
        }
        if (basket.getBasketPizzas() != null) {
            quantity = basket.getBasketPizzas().size();
            for (BasketPizza basketPizza : basket.getBasketPizzas()) {
                amount += basketPizza.getPizza().getPrice();
            }
        }

        model.addAttribute("quantity", quantity);
        model.addAttribute("amount", amount);
        model.addAttribute("basket", basket);
        List<Pizza> pizzas = pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("basketIds", basketRepository.findAll().stream().map(bskt -> bskt.getId()).collect(Collectors.toList()));
        return "index";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam Long pizzaId,
                      @RequestParam Long basketId) {
        Optional<Basket> optionalBasket = basketRepository.findById(basketId);
        Basket basket;
        if (optionalBasket.isPresent()) {
            basket = optionalBasket.get();
        } else {
            return "redirect:/";
        }
        Pizza pizza = pizzaRepository.findById(pizzaId).get();
        BasketPizza basketPizza = new BasketPizza(pizza, basket);
        basketPizzaRepository.save(basketPizza);
        return "redirect:/?basketId=" + basketId;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long basketId) {
        basketRepository.deleteById(basketId);
        return "redirect:/";
    }
}