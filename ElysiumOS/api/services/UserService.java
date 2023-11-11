```java
package api.services;

import java.util.Optional;
import db.entities.User;
import db.repositories.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(long id, User user) {
        return userRepository.findById(id)
            .map(existingUser -> {
                existingUser.setUsername(user.getUsername());
                existingUser.setEmail(user.getEmail());
                existingUser.setPreferences(user.getPreferences());
                return userRepository.save(existingUser);
            })
            .orElseGet(() -> {
                user.setId(id);
                return userRepository.save(user);
            });
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
```