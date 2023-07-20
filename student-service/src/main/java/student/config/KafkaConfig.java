package student.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {
	@Value(value = "${spring.kafka.bootstrap-servers:localhost:9092}")
	private String bootstrapAddress;
	@Value(value = "${spring.kafka.student.topic.name:studentTopic}")
	private String topicName;
	@Value(value = "${spring.kafka.add.student.topic.name:addStudentTopic}")
	private String addStudentTopic;
	@Value(value = "${spring.kafka.buy.element.student.topic.name:buyElementTopic}")
	private String buyElementTopic;
	@Value(value = "${spring.kafka.remove.element.student.topic.name:removeElementTopic}")
	private String removeElementTopic;
	@Value(value = "${spring.kafka.buy.reward.student.topic.name:buyRewardTopic}")
	private String buyRewardTopic;
	@Value(value = "${spring.kafka.group.id:SWA_Project}")
	private String groupId;

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new KafkaAdmin(configs);
	}
	@Bean
	public NewTopic buyRewardTopic() {
		return new NewTopic(buyRewardTopic, 1, (short) 1);
	}

	@Bean
	public NewTopic removeElementTopic() {
		return new NewTopic(removeElementTopic, 1, (short) 1);
	}

	@Bean
	public NewTopic buyElementTopic() {
		return new NewTopic(buyElementTopic, 1, (short) 1);
	}

	@Bean
	public NewTopic addStudentTopic() {
		return new NewTopic(addStudentTopic, 1, (short) 1);
	}

	@Bean
	public NewTopic studentTopic() {
		return new NewTopic(topicName, 1, (short) 1);
	}

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapAddress);
		configProps.put(
				ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class);
		configProps.put(
				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
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
