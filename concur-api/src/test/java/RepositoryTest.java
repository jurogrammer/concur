import concur.api.ConCurApiApplication;
import concur.core.entity.User;
import concur.core.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ConCurApiApplication.class)
public class RepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void test() {
        User user = userRepository.save(new User("hello", 10));
        System.out.println("user = " + user);
    }
}
