package com.alam.ReviewMicroservice.reviews.messaging;


//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Queue;
        import org.springframework.amqp.rabbit.connection.ConnectionFactory;
        import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue companyRatingsQueue()
    {
        return new Queue("companyRatingsQueue");
    }

    @Bean
    public MessageConverter jsonMessageConvertor()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConvertor());
        return rabbitTemplate;
    }


}
