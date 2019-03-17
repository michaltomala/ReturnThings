package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

    List<User> findAllByIsAdmin(boolean isAdmin);

//   todo - przy wyświetlaniu użytkowników skorzystać z
//    tego zapytania- nie wyswietlac calej listy a pierwsze 20 rekordów
    List<User> findFirst20ByIdAfter(int id);
}
