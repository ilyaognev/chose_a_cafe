package ru.ilfire.ccafe.service;

import ru.ilfire.ccafe.model.Menu;
import ru.ilfire.ccafe.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

public class MenuService {
    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public Menu get(int id, int restaurantId) {
        return repository.get(id, restaurantId);
    }

    public boolean delete(int id, int restaurantId) {
        return repository.delete(id, restaurantId);
    }

    public Menu update(Menu menu, int restaurantId) {
        return repository.save(menu, restaurantId);
    }

    public Menu create(Menu menu, int restaurantId) {
        return repository.save(menu, restaurantId);
    }

    public List<Menu> getAllForDay(LocalDate date) {
        return repository.getAllForDay(date);
    }
}
