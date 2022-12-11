package concur.api.user;

import concur.core.config.RUser;
import concur.core.entity.User;
import concur.core.repository.ReservationRepository;
import concur.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class UserConfig {
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final RedisTemplate<String, String> redisTemplate;


    @PostConstruct
    @Transactional
    void setup() {
        reservationRepository.deleteAll();
        userRepository.deleteAll();

        ValueOperations<String, String> op = redisTemplate.opsForValue();
        for (RUser user : RUser.values()) {
            op.set(user.getName(), "0");
            userRepository.save(new User(user.getName(), 0));
        }
    }
}
