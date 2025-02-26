package kz.bitlab.amazon.amazon.services;

import kz.bitlab.amazon.amazon.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public boolean createUser(User user);

    public User getUserByEmail(String email);

    Boolean login(String email, String password);
}
