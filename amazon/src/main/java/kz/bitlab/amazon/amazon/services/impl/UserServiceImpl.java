package kz.bitlab.amazon.amazon.services.impl;

import kz.bitlab.amazon.amazon.models.User;
import kz.bitlab.amazon.amazon.models.enums.Role;
import kz.bitlab.amazon.amazon.repository.UserRepository;
import kz.bitlab.amazon.amazon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserServiceImpl implements UserService  {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(User user) {
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) {
            return false;
        }
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(email);
        }
        return user;
    }

    @Override
    public Boolean login(String email, String password) {
        User currentUser = userRepository.findByEmail(email);
        if(currentUser != null) {
            return currentUser.getPassword().equals(passwordEncoder.encode(password));
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if(user!=null)
            return user;
        throw new UsernameNotFoundException("User Not Found");
    }
}
