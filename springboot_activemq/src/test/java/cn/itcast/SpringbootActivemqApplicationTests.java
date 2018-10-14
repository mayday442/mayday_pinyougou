package cn.itcast;

import cn.itcast.demo.QueueProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootActivemqApplicationTests {

    @Autowired
    private QueueProducer queueProducer;


    @Test
    public void demo () {
        Destination destination = new ActiveMQQueue("myTest_queue");
        for (int i = 0; i < 10; i++) {
            queueProducer.sendMessage(destination,"点对点 text");
        }
    }

}
