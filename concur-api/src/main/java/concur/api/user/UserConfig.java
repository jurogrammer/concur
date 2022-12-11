package concur.api.user;

import concur.core.entity.User;
import concur.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class UserConfig {
    private final UserRepository userRepository;

    @PostConstruct
    void setup() {
        Optional<User> injae = userRepository.findByName("injae");
        if (injae.isEmpty()) {
            userRepository.save(new User("injae", 0));
        }
    }
}
