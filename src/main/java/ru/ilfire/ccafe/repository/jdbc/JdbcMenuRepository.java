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
import ru.ilfire.ccafe.model.Menu;
import ru.ilfire.ccafe.repository.MenuRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcMenuRepository implements MenuRepository {
    private static final RowMapper<Menu> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Menu.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMenu;

    @Autowired
    public JdbcMenuRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMenu = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("menus")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Menu save(Menu menu, int restaurantId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", menu.getId())
                .addValue("date", menu.getDate())
                .addValue("restaurant_id", restaurantId);

        if (menu.isNew()) {
            Number newId = insertMenu.executeAndReturnKey(map);
            menu.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                    "UPDATE menus " +
                    "   SET date=:date WHERE id=:id AND restaurant_id=:restaurant_id", map) == 0) {
                return null;
            }
        }
        return menu;
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return jdbcTemplate.update("DELETE FROM menus WHERE id=? AND restaurant_id=?", id, restaurantId) != 0;
    }

    @Override
    public Menu get(int id, int restaurantId) {
        List<Menu> menus = jdbcTemplate.query(
                "SELECT * FROM menus WHERE id = ? AND restaurant_id = ?", ROW_MAPPER, id, restaurantId);
        return DataAccessUtils.singleResult(menus);
    }

    @Override
    public List<Menu> getAllForDay(LocalDate date) {
        return jdbcTemplate.query(
                "SELECT * FROM menus WHERE date=?", ROW_MAPPER, date);
    }
}
