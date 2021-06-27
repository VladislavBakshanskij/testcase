package com.example.testcase.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfiguration {
    public static final AmqpHelper USER_INPUT = new AmqpHelper("user_input",
            "user_input_exchange", "user_input");
    public static final AmqpHelper USER_OUTPUT = new AmqpHelper("user_output",
            "user_output_exchange", "user_output");

    public static final AmqpHelper JOURNAL_INPUT = new AmqpHelper("journal_input",
            "journal_input_exchange", "journal_input");
    public static final AmqpHelper JOURNAL_OUTPUT = new AmqpHelper("journal_output",
            "journal_output_exchange", "journal_output");

    public static final AmqpHelper ERROR = new AmqpHelper("error",
            "error_exchange", "error");

    @Value
    public static class AmqpHelper {
        Queue queue;
        Exchange exchange;
        Binding binding;

        public AmqpHelper(String queue, String exchange, String binding) {
            this.queue = new Queue(queue);
            this.exchange = new DirectExchange(exchange);
            this.binding = BindingBuilder.bind(this.queue)
                    .to(this.exchange)
                    .with(binding)
                    .noargs();
        }

        public String getQueueName() {
            return queue.getName();
        }

        public String getExchangeName() {
            return exchange.getName();
        }
    }

    @Bean
    public Queue userInputQueue() {
        return USER_INPUT.getQueue();
    }

    @Bean
    public Queue userOutputQueue() {
        return USER_OUTPUT.getQueue();
    }


    @Bean
    public Queue journalInputQueue() {
        return JOURNAL_INPUT.getQueue();
    }

    @Bean
    public Queue journalOutputQueue() {
        return JOURNAL_OUTPUT.getQueue();
    }

    @Bean
    public Queue errorQueue() {
        return ERROR.getQueue();
    }

    @Bean
    public Exchange useInputExchange() {
        return USER_INPUT.getExchange();
    }

    @Bean
    public Exchange userOutputExchange() {
        return USER_OUTPUT.getExchange();
    }

    @Bean
    public Exchange journalInputExchange() {
        return JOURNAL_INPUT.getExchange();
    }

    @Bean
    public Exchange journalOutputExchange() {
        return JOURNAL_OUTPUT.getExchange();
    }

    @Bean
    public Exchange errorExchange() {
        return ERROR.getExchange();
    }

    @Bean
    public Binding userInputBinding() {
        return USER_INPUT.getBinding();
    }

    @Bean
    public Binding userOutputBinding() {
        return USER_OUTPUT.getBinding();
    }

    @Bean
    public Binding journalInputBinding() {
        return JOURNAL_INPUT.getBinding();
    }

    @Bean
    public Binding journalOutputBinding() {
        return JOURNAL_OUTPUT.getBinding();
    }

    @Bean
    public Binding errorBinding() {
        return ERROR.getBinding();
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}
