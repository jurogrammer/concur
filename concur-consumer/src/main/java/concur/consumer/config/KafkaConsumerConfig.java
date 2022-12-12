package concur.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import concur.core.config.KafkaCount;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, KafkaCount> consumerFactory() {
		JsonDeserializer<KafkaCount> deserializer = new JsonDeserializer<>(KafkaCount.class);
		deserializer.setRemoveTypeHeaders(false);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);

		Map<String, Object> props = new HashMap<>();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "1");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaCount> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, KafkaCount> factory =
			new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConcurrency(1);
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	//
	// @Bean
	// public ConsumerFactory<String, KafkaCount> oneConsumerFactory() {
	//     JsonDeserializer<KafkaCount> deserializer = new JsonDeserializer<>(KafkaCount.class);
	//     deserializer.setRemoveTypeHeaders(false);
	//     deserializer.addTrustedPackages("*");
	//     deserializer.setUseTypeMapperForKey(true);
	//
	//     Map<String, Object> props = new HashMap<>();
	//
	//     props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	//     props.put(ConsumerConfig.GROUP_ID_CONFIG, "2");
	//     props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	//     props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
	//
	//     return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	// }
	//
	// @Bean
	// public ConcurrentKafkaListenerContainerFactory<String, KafkaCount> oneConsumerContainerFactory() {
	//     ConcurrentKafkaListenerContainerFactory<String, KafkaCount> factory =
	//         new ConcurrentKafkaListenerContainerFactory<>();
	//     factory.setConcurrency(1);
	//     factory.setConsumerFactory(consumerFactory());
	//     return factory;
	// }
}
