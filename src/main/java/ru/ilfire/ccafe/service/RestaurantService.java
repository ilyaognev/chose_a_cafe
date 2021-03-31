package ru.ilfire.ccafe.service;

import ru.ilfire.ccafe.model.Restaurant;
import ru.ilfire.ccafe.repository.RestaurantRepository;

import java.util.List;

public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant get(int id) {
        return repository.get(id);
    }

    public boolean delete(int id) {
        return repository.delete(id);
    }

    public Restaurant update(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }
}
