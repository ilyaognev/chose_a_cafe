package ru.ilfire.ccafe.service;

import org.springframework.stereotype.Service;
import ru.ilfire.ccafe.model.User;
import ru.ilfire.ccafe.repository.UserRepository;

import java.util.List;

import static ru.ilfire.ccafe.util.ValidationUtil.checkNotFound;
import static ru.ilfire.ccafe.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User update(User user) {
        return checkNotFoundWithId(repository.save(user), user.getId());
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User getByEmail(String email) {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }
}
