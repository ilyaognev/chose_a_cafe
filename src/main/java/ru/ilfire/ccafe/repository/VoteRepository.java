package ru.ilfire.ccafe.repository;

import ru.ilfire.ccafe.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {
    Vote save(Vote vote, int userId);

    Vote get(int id, int userId);

    boolean delete(int id, int userId);

    List<Vote> getVotesForDay(LocalDate votesForDayDate);
}
