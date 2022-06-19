package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //All

    User findByEmail(String email);

    Boolean existsByEmail(String email);
}
