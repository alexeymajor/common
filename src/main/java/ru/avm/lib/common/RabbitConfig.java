package ru.avm.lib.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor

@Slf4j
@Configuration
public class RabbitConfig {

    private final ObjectMapper objectMapper;

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        val rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange("amq.topic");
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitListenerErrorHandler rabbitErrorHandler() {
        return (amqpMessage, channel, message, exception) -> {

            val props = amqpMessage.getMessageProperties();

            log.error("RabbitMQ listener error - Queue: '{}', RoutingKey: '{}', Exchange: '{}', " +
                            "MessageId: '{}', CorrelationId: '{}', Timestamp: {}, DeliveryTag: {}, " +
                            "Redelivered: {}, Priority: {}, ContentType: '{}', Error: '{}'",
                    props.getConsumerQueue(),
                    props.getReceivedRoutingKey(),
                    props.getReceivedExchange(),
                    props.getMessageId(),
                    props.getCorrelationId(),
                    props.getTimestamp(),
                    props.getDeliveryTag(),
                    props.isRedelivered(),
                    props.getPriority(),
                    props.getContentType(),
                    exception.getMessage(),
                    exception);

            try {
                String messageBody = new String(amqpMessage.getBody(), StandardCharsets.UTF_8);
                log.error("Failed message body: {}", messageBody);
            } catch (Exception e) {
                log.warn("Could not decode message body as UTF-8: {}", e.getMessage());
            }

            if (props.getHeaders() != null && !props.getHeaders().isEmpty()) {
                log.error("Message headers: {}", props.getHeaders());
            }

            throw exception;
        };
    }
}
