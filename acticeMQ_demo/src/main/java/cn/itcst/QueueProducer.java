package cn.itcst;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


/**
 * @author mayday
 */
public class QueueProducer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.55:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue testQueue = session.createQueue("test_queue");
        MessageProducer producer = session.createProducer(testQueue);

        MapMessage mapMessage = session.createMapMessage();
        mapMessage.setString("key", "你好");
        mapMessage.setString("value", "不好");
        producer.send(mapMessage);

        producer.close();
        session.close();
        connection.close();


    }

}
