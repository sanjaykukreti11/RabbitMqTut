package Rabbit;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

import models.Users;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {



        com.rabbitmq.client.ConnectionFactory factory = new ConnectionFactory();
        com.rabbitmq.client.Connection connection = factory.newConnection(Config.AMQP_URL);

        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (s, delivery)->{

            String userData = new String( delivery.getBody() );
            Users user = new ObjectMapper().readValue(userData, Users.class);

            // Store userData to database
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();



        };

        CancelCallback cancelCallback = s ->{
            System.out.println(s);
        };

        channel.basicConsume(Config.DEFAULT_QUEUE  , true , deliverCallback , cancelCallback);


    }

}