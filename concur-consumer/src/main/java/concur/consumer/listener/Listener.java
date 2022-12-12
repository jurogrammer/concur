package concur.consumer.listener;

import concur.core.config.KafkaCount;
import concur.core.entity.User;
import concur.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class Listener {

    private final UserRepository userRepository;
    @Transactional
    @KafkaListener(topics = "count", groupId = "1")
    public void countTopicListener(KafkaCount kafkaCount) {
        User user = userRepository.findById(kafkaCount.getUserId()).orElseThrow();
        user.setCnt(kafkaCount.getCount());
    }



}
