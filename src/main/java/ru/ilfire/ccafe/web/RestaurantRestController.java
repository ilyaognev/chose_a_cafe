package ru.ilfire.ccafe.web;

import ru.ilfire.ccafe.service.RestaurantService;

public class RestaurantRestController {
    private final RestaurantService service;

    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }
}
