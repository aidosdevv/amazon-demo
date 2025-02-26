package kz.bitlab.amazon.amazon.repository;

import kz.bitlab.amazon.amazon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
