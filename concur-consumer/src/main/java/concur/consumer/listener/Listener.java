package concur.consumer.listener;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import concur.core.config.KafkaCount;
import concur.core.entity.User;
import concur.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Listener {

	private final UserRepository userRepository;
	private final RedisTemplate<String, String> redisTemplate;

	@Transactional
	@KafkaListener(topics = "count", groupId = "1")
	public void redisTopicListener(KafkaCount kafkaCount) {
		User user = userRepository.findById(kafkaCount.getUserId()).orElseThrow();
		String userName = user.getName();
		ValueOperations<String, String> valueOp = redisTemplate.opsForValue();

		if (kafkaCount.getDelta() == 1) {
			valueOp.increment(userName);
		} else {
			valueOp.decrement(userName);
		}
	}

	@Transactional
	@KafkaListener(topics = "count", groupId = "2")
	public void mysqlTopicListener(KafkaCount kafkaCount) {
		User user = userRepository.findById(kafkaCount.getUserId()).orElseThrow();
		user.setCnt(user.getCnt() + kafkaCount.getDelta());
		userRepository.save(user);

	}

}
