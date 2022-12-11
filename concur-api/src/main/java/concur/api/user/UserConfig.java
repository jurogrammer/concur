package concur.api.user;

import concur.core.entity.User;
import concur.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class UserConfig {
    private final UserRepository userRepository;
    private final RedisTemplate<String, Integer> redisTemplate;


    @PostConstruct
    @Transactional
    void setup() {
        ValueOperations<String, Integer> op = redisTemplate.opsForValue();
        op.set("injae", 1);
        Optional<User> injae = userRepository.findByName("injae");
        if (injae.isEmpty()) {
            userRepository.save(new User("injae", 0));
        }


    }
}
