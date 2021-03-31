package ru.ilfire.ccafe.service;

import ru.ilfire.ccafe.model.Vote;
import ru.ilfire.ccafe.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;

public class VoteService {
    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote get(int id, int userId) {
        return repository.get(id, userId);
    }

    public boolean delete(int id, int userId) {
        return repository.delete(id, userId);
    }

    public Vote update(Vote vote, int userId) {
        return repository.save(vote, userId);
    }

    public Vote create(Vote vote, int userId) {
        return repository.save(vote, userId);
    }

    public List<Vote> getVotesForDay(LocalDate votesForDayDate) {
        return repository.getVotesForDay(votesForDayDate);
    }
}
