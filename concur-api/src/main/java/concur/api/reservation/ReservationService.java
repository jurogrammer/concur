package concur.api.reservation;

import concur.core.config.KafkaCount;
import concur.core.config.Topic;
import concur.core.entity.Reservation;
import concur.core.entity.User;
import concur.core.repository.ReservationRepository;
import concur.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final KafkaTemplate<String, KafkaCount> countKafkaTemplate;

    @Transactional
    public Reservation reserve(String userName) {
        User user = userRepository.findByName(userName).orElseThrow();
        Reservation savedReservation = reservationRepository.save(new Reservation("reservation:" + userName, user));

        countKafkaTemplate.send(Topic.COUNT.getTopicName(), new KafkaCount(user.getId(), user.getCnt() + 1));
        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.increment(userName);
        return savedReservation;
    }

    @Transactional
    public Reservation cancel(String userName) {
        Reservation reservation = reservationRepository.findByName("reservation:" + userName).orElseThrow();
        reservationRepository.delete(reservation);

        User user = userRepository.findByName(userName).orElseThrow();
        countKafkaTemplate.send(Topic.COUNT.getTopicName(), new KafkaCount(user.getId(), user.getCnt() - 1));

        ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
        valueOp.decrement(userName);
        return reservation;
    }
}
