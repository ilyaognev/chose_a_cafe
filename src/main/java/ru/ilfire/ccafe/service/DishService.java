package ru.ilfire.ccafe.service;

import ru.ilfire.ccafe.model.Dish;
import ru.ilfire.ccafe.repository.DishRepository;

import java.util.List;

public class DishService implements DishRepository {
    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish save(Dish menu, int restaurantId) {
        return repository.save(menu, restaurantId);
    }

    public boolean delete(int id, int restaurantId) {
        return repository.delete(id, restaurantId);
    }

    public Dish get(int id, int restaurantId) {
        return repository.get(id, restaurantId);
    }

    public List<Dish> getAllFromRestaurant(int restaurantId) {
        return repository.getAllFromRestaurant(restaurantId);
    }
}
