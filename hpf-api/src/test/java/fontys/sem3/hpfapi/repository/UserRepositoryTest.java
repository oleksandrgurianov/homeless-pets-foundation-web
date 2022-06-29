package fontys.sem3.hpfapi.repository;

import fontys.sem3.hpfapi.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_shouldReturnUser_whenItExists() {
        entityManager.persist(User.builder()
                .avatar("https://drive.google.com/uc?export=view&id=1_dkDl3VGEjdKqyO900DmdmBGg3DbaVVo")
                .fullName("Manna Barnhoorn")
                .email("admin@hpf.com")
                .phoneNumber("0641261843")
                .password("password")
                .role("ADMIN")
                .build());

        User actualUser = userRepository.findByEmail("admin@hpf.com");

        assertNotNull(actualUser.getId());
        User expectedUser = User.builder()
                .avatar("https://drive.google.com/uc?export=view&id=1_dkDl3VGEjdKqyO900DmdmBGg3DbaVVo")
                .fullName("Manna Barnhoorn")
                .email("admin@hpf.com")
                .phoneNumber("0641261843")
                .password("password")
                .role("ADMIN")
                .build();
        assertThat(actualUser)
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expectedUser);
    }

    @Test
    void findByEmail_shouldReturnNull_whenUserNotFound() {
        User actualUser = userRepository.findByEmail("admin@hpf.com");
        assertNull(actualUser);
    }
}
