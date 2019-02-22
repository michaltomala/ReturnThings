package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

// todo do wyciągnięcia potrzeba skorzystać z zapytania
}
