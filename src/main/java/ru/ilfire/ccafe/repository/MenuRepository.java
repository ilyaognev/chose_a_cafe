package ru.ilfire.ccafe.repository;

import ru.ilfire.ccafe.model.Menu;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository {
    Menu save(Menu menu, int restaurantId);

    boolean delete(int id, int restaurantId);

    Menu get(int id, int restaurantId);

    List<Menu> getAllForDay(LocalDate date);
}
