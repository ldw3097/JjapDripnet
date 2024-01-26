package ldw3097.ldwboard.repository;

import ldw3097.ldwboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
