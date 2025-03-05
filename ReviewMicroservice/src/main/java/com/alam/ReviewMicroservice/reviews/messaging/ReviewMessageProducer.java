package com.alam.ReviewMicroservice.reviews.messaging;

import com.alam.ReviewMicroservice.reviews.Review;
import com.alam.ReviewMicroservice.reviews.dto.ReviewMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review review) {
       ReviewMessage reviewMessage = new ReviewMessage();
       reviewMessage.setId(review.getId());
       reviewMessage.setRating(review.getRating());
       reviewMessage.setCompanyId(review.getCompanyId());
       reviewMessage.setTitle(review.getTitle());
       reviewMessage.setDescription(review.getDescription());

       rabbitTemplate.convertAndSend("companyRatingsQueue", reviewMessage);
       System.out.println("Message sent to CompanyRatingsQueue");
    }
}
