package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
