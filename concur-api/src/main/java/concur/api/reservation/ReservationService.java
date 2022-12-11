package concur.api.reservation;

import concur.core.entity.Reservation;
import concur.core.entity.User;
import concur.core.repository.ReservationRepository;
import concur.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final RedisTemplate<String, Integer> redisTemplate;

    @Transactional
    public Reservation reserve(String userName) {
        User user = userRepository.findByName(userName).orElseThrow();
        Reservation savedReservation = reservationRepository.save(new Reservation("reservation:" + userName, user));

        user.upCnt();
        userRepository.save(user);

        ValueOperations<String, Integer> valueOp = redisTemplate.opsForValue();
        valueOp.increment(userName);
        return savedReservation;
    }

    @Transactional
    public Reservation cancel(String userName) {
        Reservation reservation = reservationRepository.findByName("reservation:" + userName).orElseThrow();
        reservationRepository.delete(reservation);

        User user = userRepository.findByName(userName).orElseThrow();
        user.downCnt();
        userRepository.save(user);

        ValueOperations<String, Integer> valueOp = redisTemplate.opsForValue();
        valueOp.decrement(userName);
        return reservation;
    }
}
