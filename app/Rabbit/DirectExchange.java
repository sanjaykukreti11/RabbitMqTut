package Rabbit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectExchange {

    public static void declareExchange() throws IOException, TimeoutException {
        ConnectionFactory factory  = new ConnectionFactory();
        Connection connection = factory.newConnection(Config.AMQP_URL);
        Channel channel = connection.createChannel();

        channel.exchangeDeclare("my-direct-exchange", BuiltinExchangeType.DIRECT, true);
        channel.close();
    }
   public static void declareQueues() throws IOException, TimeoutException {
        ConnectionFactory factory  = new ConnectionFactory();
        Connection connection = factory.newConnection(Config.AMQP_URL);
        Channel channel = connection.createChannel();
        channel.queueDeclare("q-1", true, false, false, null);

        channel.close();
    }

    public static void declareBindings() throws IOException, TimeoutException {

        ConnectionFactory factory  = new ConnectionFactory();
        Connection connection = factory.newConnection(Config.AMQP_URL);
        Channel channel = connection.createChannel();

        channel.queueBind("q-1", "my-direct-exchange", "userData");
        channel.close();
    }

  public static void publishMessage(String message) throws IOException, TimeoutException {
        ConnectionFactory factory  = new ConnectionFactory();
        Connection connection = factory.newConnection(Config.AMQP_URL);
        Channel channel = connection.createChannel();


        channel.basicPublish("my-direct-exchange", "userData", null, message.getBytes());
        channel.close();
    }

}