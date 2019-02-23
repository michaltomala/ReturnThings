package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

    List<User> findAllByAdminFalse();
    List<User> findAllByAdmin(boolean isAdmin);
    List<User> findAllByAdminIs(boolean isAdmin);
    List<User> findAllByIsAdmin(boolean isAdmin);
// todo do wyciągnięcia potrzeba skorzystać z zapytania
}
