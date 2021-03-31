package ru.ilfire.ccafe.repository;

import ru.ilfire.ccafe.model.Dish;

import java.util.List;

public interface DishRepository {
    Dish save(Dish dish, int restaurantId);

    boolean delete(int id, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAllFromRestaurant(int restaurantId);
}
