package ru.ilfire.ccafe.model;

import java.time.LocalDate;
import java.util.List;

public class Menu extends AbstractBaseEntity{
    private Restaurant restaurant;
    private List<Dish> dishes;
    private LocalDate date;

    public Menu() {
    }

    public Menu(Restaurant restaurant, List<Dish> dishes, LocalDate date) {
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.date = date;
    }

    public Menu(Integer id) {
        super(id);
    }

    public Menu(Integer id, Restaurant restaurant, List<Dish> dishes, LocalDate date) {
        super(id);
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
