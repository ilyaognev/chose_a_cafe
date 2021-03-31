package ru.ilfire.ccafe.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.ilfire.ccafe.model.Dish;
import ru.ilfire.ccafe.repository.DishRepository;

import java.util.List;

@Repository
public class JdbcDishRepository implements DishRepository {

    private static final RowMapper<Dish> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Dish.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertDish;

    @Autowired
    public JdbcDishRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertDish = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("dishes")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Dish save(Dish dish, int restaurantId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", dish.getId())
                .addValue("name", dish.getName())
                .addValue("price", dish.getPrice())
                .addValue("restaurant_id", restaurantId);

        if (dish.isNew()) {
            Number newId = insertDish.executeAndReturnKey(map);
            dish.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                    "UPDATE dishes " +
                    "SET name=:name, price=:price " +
                    "WHERE id=:id AND restaurant_id=:restaurant_id", map) == 0) {
                return null;
            }
        }
        return dish;
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return jdbcTemplate.update("DELETE FROM dishes WHERE id=? AND restaurant_id=?", id, restaurantId) != 0;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        List<Dish> dishes = jdbcTemplate.query(
                "SELECT * FROM dishes WHERE id = ? AND restaurant_id = ?", ROW_MAPPER, id, restaurantId);
        return DataAccessUtils.singleResult(dishes);
    }

    @Override
    public List<Dish> getAllFromRestaurant(int restaurantId) {
        return jdbcTemplate.query(
                "SELECT * FROM dishes WHERE restaurant_id=? ORDER BY name DESC", ROW_MAPPER, restaurantId);
    }
}
