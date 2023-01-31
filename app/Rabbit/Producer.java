package Rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

import java.util.concurrent.TimeoutException;

public class Producer {


    private static String userData;

    public static void setUserData(String userData) {
        Producer.userData = userData;
    }

    public static void save() throws IOException, TimeoutException {
        //// send data to rabbitmq server

        ConnectionFactory factory  = new ConnectionFactory();
        Connection connection = factory.newConnection(Config.AMQP_URL);

        Channel channel = connection.createChannel();
        channel.queueDeclare( Config.DEFAULT_QUEUE , true , false , false, null );
        channel.basicPublish("", Config.DEFAULT_QUEUE , null,userData.getBytes() );


        channel.close();
        connection.close();


    }

}
