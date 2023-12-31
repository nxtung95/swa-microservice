package email.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {
	@Value(value = "${spring.kafka.bootstrap-servers:localhost:9092}")
	private String bootstrapAddress;
	@Value(value = "${spring.kafka.teacher.email.topic.name:teacherEmailTopic}")
	private String teacherEmailTopicName;
	@Value(value = "${spring.kafka.student.email.topic.name:studentEmailTopic}")
	private String studentEmailTopic;
	@Value(value = "${spring.kafka.group.id:SWA_Project}")
	private String groupId;

	@Bean
	public NewTopic teacherEmailTopic() {
		return new NewTopic(teacherEmailTopicName, 1, (short) 1);
	}

	@Bean
	public NewTopic studentEmailTopic() {
		return new NewTopic(studentEmailTopic, 1, (short) 1);
	}


	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(
				ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapAddress);
		props.put(
				ConsumerConfig.GROUP_ID_CONFIG,
				groupId);
		props.put(
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);
		props.put(
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
