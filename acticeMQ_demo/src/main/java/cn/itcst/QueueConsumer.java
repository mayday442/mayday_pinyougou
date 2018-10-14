package cn.itcst;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author mayday
 */
public class QueueConsumer {

    public static void main(String[] args) throws Exception{

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.55:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue testQueue = session.createQueue("test_queue");
        MessageConsumer consumer = session.createConsumer(testQueue);

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                MapMessage mapMessage=(MapMessage)message;
                try {
                    String key = mapMessage.getString("key");
                    String value = mapMessage.getString("value");
                    System.out.println("key + " + key);
                    System.out.println("value + " + value);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        consumer.close();
        session.close();
        connection.close();

    }

}
