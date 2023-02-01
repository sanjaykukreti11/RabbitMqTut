package Rabbit;


import java.io.IOException;

import java.util.concurrent.TimeoutException;

public class Producer {


    private static String userData;

    public static void setUserData(String userData) {
        Producer.userData = userData;
    }

    public static void save() throws IOException, TimeoutException {

        DirectExchange.declareExchange();
        DirectExchange.declareQueues();
        DirectExchange.declareBindings();
        DirectExchange.publishMessage(userData);

        Thread subscribe = new Thread(){
            @Override
            public void run() {
                try {
                    Consumer.Subscribe();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread publish = new Thread(){
            @Override
            public void run() {
                try {
                    DirectExchange.publishMessage(userData);
                } catch (IOException | TimeoutException e) {
                    e.printStackTrace();
                }
            }
        };

        subscribe.start();
        publish.start();


    }

}
