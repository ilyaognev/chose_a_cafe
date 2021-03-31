package ru.ilfire.ccafe.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity {
    private User user;
    private Restaurant restaurant;
    private LocalDateTime time;

    public Vote() {
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDateTime time) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.time = time;
    }

    public Vote(User user, Restaurant restaurant, LocalDateTime time) {
        this.user = user;
        this.restaurant = restaurant;
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
