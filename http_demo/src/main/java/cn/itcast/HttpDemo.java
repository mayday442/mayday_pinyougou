package cn.itcast;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mayday
 */
public class HttpDemo {

    @Test
    public void getDemo1() throws IOException {
        // 打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("http://www.baidu.com/");
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println(content.length());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response != null) {
                response.close();
            }
        }

        httpClient.close();

    }



    @Test
    public void getDemo2() throws IOException, URISyntaxException {
        // 打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();

        URI uri = new URIBuilder("http://www.baidu.com/s").setParameter("wd", "java").build();

        System.out.println(uri);

        HttpGet httpGet = new HttpGet(uri);

        CloseableHttpResponse response = null;

        response = httpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(content);
        }

    }

    @Test
    public void postDemo1() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://www.oschina.net/");

        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");

        CloseableHttpResponse response = null;
        response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity());
            System.out.println(content);
        }

        response.close();
        httpClient.close();

    }

    @Test
    public void postDemo2() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("https://www.oschina.net/search");

        List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);

        parameters.add(new BasicNameValuePair("scope", "project"));
        parameters.add(new BasicNameValuePair("q", "java"));

        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters,"UTF-8");

        httpPost.setEntity(formEntity);

        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) " +
                "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");

        CloseableHttpResponse response = null;
        response = httpClient.execute(httpPost);

        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity());
            System.out.println(content);
        }

        response.close();
        httpClient.close();

    }

}
