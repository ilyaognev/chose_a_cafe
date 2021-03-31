package ru.ilfire.ccafe;

import ru.ilfire.ccafe.model.Role;
import ru.ilfire.ccafe.model.User;

import java.util.Collections;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator( "roles");

    public static final int USER_ID = 100000;
    public static final int ADMIN_ID = 100001;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setEmail("update@gmail.com");
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
