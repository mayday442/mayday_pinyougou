package cn.itcast;

import org.junit.Test;

import java.io.IOException;

/**
 * @author mayday
 */
public class HttpClientDemo {

    @Test
    public void testDemo() throws IOException {
        HttpClient httpClient = new HttpClient("http://www.baidu.com");
        httpClient.get();
        System.err.println(httpClient.getStatusCode());
    }

}
