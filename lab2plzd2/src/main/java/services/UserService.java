package services;

import models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(User user);

    boolean czyLoginJestUnikalny(String czyLoginJestUnikalny);
}
