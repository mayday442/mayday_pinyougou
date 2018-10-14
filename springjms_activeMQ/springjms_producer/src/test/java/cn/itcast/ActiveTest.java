package cn.itcast;

import cn.itcast.demo.ProducerQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-mq-producer.xml")
public class ActiveTest {

    @Autowired
    private ProducerQueue producerQueue;

    @Test
    public void demo () {

        producerQueue.send("你个骗子");

    }



}
