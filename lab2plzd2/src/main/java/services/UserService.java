package services;

import models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save(User user);

    boolean czyLoginJestUnikalny(String czyLoginJestUnikalny);

    void delete(Long id);

    Page<User> getAllUsers(Pageable pageable);

    List<User> getAllUsersList();
}
