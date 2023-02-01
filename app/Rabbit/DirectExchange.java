package Rabbit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Selective message broadcast with routingkey filter.
 */
public class DirectExchange {

    //Step-1: Declare the exchange
    public static void declareExchange() throws IOException, TimeoutException {
        ConnectionFactory factory  = new ConnectionFactory();
        Connection connection = factory.newConnection(Config.AMQP_URL);
        Channel channel = connection.createChannel();

        //Declare my-direct-exchange DIRECT exchange
        channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.DIRECT, true);
        channel.close();
    }

    //Step-2: Declare the Queues
    public static void declareQueues() throws IOException, TimeoutException {
        //Create a channel - do not share the Channel instance
        ConnectionFactory factory  = new ConnectionFactory();
        Connection connection = factory.newConnection(Config.AMQP_URL);
        Channel channel = connection.createChannel();

        //Create the Queues
        channel.queueDeclare("q-1", true, false, false, null);

        channel.close();
    }

    //Step-3: Create the Bindings
    public static void declareBindings() throws IOException, TimeoutException {

        ConnectionFactory factory  = new ConnectionFactory();
        Connection connection = factory.newConnection(Config.AMQP_URL);
        Channel channel = connection.createChannel();

        //Create bindings - (queue, exchange, routingKey)
        channel.queueBind("q-1", "my-direct-exchange", "userData");
        channel.close();
    }


    //Step-5: Publish the messages
    public static void publishMessage(String message) throws IOException, TimeoutException {
        ConnectionFactory factory  = new ConnectionFactory();
        Connection connection = factory.newConnection(Config.AMQP_URL);
        Channel channel = connection.createChannel();


        channel.basicPublish("my-direct-exchange", "userData", null, message.getBytes());
        channel.close();
    }

}