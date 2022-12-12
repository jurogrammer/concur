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
	public void countTopicListener(KafkaCount kafkaCount) {
		// 차라리 redis에서 값을 가져와서 업데이트 치는게 나을 듯.
		ValueOperations<String, String> valueOp = redisTemplate.opsForValue();
		User user = userRepository.findById(kafkaCount.getUserId()).orElseThrow();
		String s = valueOp.get(user.getName());
		user.setCnt(Integer.parseInt(s));
		userRepository.save(user);
	}

}
