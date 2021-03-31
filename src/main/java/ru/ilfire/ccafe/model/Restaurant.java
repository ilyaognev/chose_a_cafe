package ru.ilfire.ccafe.model;

import java.util.List;

public class Restaurant extends AbstractBaseEntity {
    private String name;
    private List<Menu> menu;

    public Restaurant() {
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Restaurant(String name, List<Menu> menu) {
        this.name = name;
        this.menu = menu;
    }

    public Restaurant(Integer id, String name, List<Menu> menu) {
        super(id);
        this.name = name;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }
}
