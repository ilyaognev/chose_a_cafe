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
import ru.ilfire.ccafe.model.Vote;
import ru.ilfire.ccafe.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.ilfire.ccafe.util.VotesUtil.checkVoteTime;

@Repository
public class JdbcVoteRepository implements VoteRepository {
    private static final RowMapper<Vote> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Vote.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertVote;

    @Autowired
    public JdbcVoteRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertVote = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("votes")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Vote save(Vote vote, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", vote.getId())
                .addValue("date_time", vote.getTime())
                .addValue("user_id", userId)
                .addValue("restaurant_id", vote.getRestaurant().getId());

        if (vote.isNew()) {
            Number newId = insertVote.executeAndReturnKey(map);
            vote.setId(newId.intValue());
        } else {
            if (checkVoteTime(vote.getTime())) {
                throw new RuntimeException("You are late...");
            }
            if (namedParameterJdbcTemplate.update("" +
                    "UPDATE vote " +
                    "SET date_time=:date_time, user_id=:user_id, restaurant_id=:restaurant_id " +
                    "WHERE id=:id", map) == 0) {
                return null;
            }
        }
        return vote;
    }

    @Override
    public Vote get(int id, int userId) {
        List<Vote> votes = jdbcTemplate.query(
                "SELECT * FROM votes WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(votes);
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM votes WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public List<Vote> getVotesForDay(LocalDate votesForDayDate) {
        return jdbcTemplate.query(
                "SELECT * FROM votes WHERE date_time = ?", ROW_MAPPER, votesForDayDate);
    }
}
