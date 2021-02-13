package com.example.fss.service;

import com.example.fss.domain.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class publishes a message to the RabbitMQ exchange
 * with random city name and random boolean fuellid value
 */
@Component
@Slf4j
public class MessagePublisher {

    /**
     * Interval for each event trigger
     */
    private static final int PUBLISH_INTERVAL_TIME_MILLIS = 120000;
    /**
     * Cities under consideration for this application
     */
    List<String> cities = Arrays.asList("Bangalore", "Delhi", "Lucknow", "Mumbai");
    /**
     * exchange name
     */
    @Value("${ayush.rabbitmq.exchange}")
    private String exchange;
    /**
     * routing key for the exchange and queue binding
     */
    @Value("${ayush.rabbitmq.routingkey}")
    private String routingkey;
    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * Scheduled method that will publish message to RabbitMQ
     * every 2 minutes
     */
    @Scheduled(fixedDelay = PUBLISH_INTERVAL_TIME_MILLIS, initialDelay = PUBLISH_INTERVAL_TIME_MILLIS)
    public void publish() {
        Car car = generate();
        log.info("Random city name :: {}", car.getCity());
        log.info("Random fuellid value :: {}", car.getFuellid());
        amqpTemplate.convertAndSend(exchange, routingkey, car);
        log.info("Sending message :: {}", car);
    }

    /**
     * Generate car object with random city name and fuellid value
     *
     * @return Car object
     */
    public Car generate() {
        int index = new Random().nextInt(cities.size());
        String cityName = cities.get(index);
        Boolean fuellid = new Random().nextBoolean();
        Car car = new Car(cityName, fuellid);
        return car;
    }

    /**
     * Trigger manual event
     *
     * @param car object
     */
    public void publishManualEvent(Car car) {
        amqpTemplate.convertAndSend(exchange, routingkey, car);
        log.info("Sending manual message :: {}", car);
    }
}
