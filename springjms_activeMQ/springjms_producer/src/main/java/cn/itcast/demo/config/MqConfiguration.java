package cn.itcast.demo.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;

/**
 * @author mayday
 */
@Configuration
public class MqConfiguration {


    @Bean("connectionFactory")
    public SingleConnectionFactory getSingleConnectionFactory(ActiveMQConnectionFactory factory) {
        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory();
        singleConnectionFactory.setTargetConnectionFactory(factory);

        return singleConnectionFactory;
    }


}
