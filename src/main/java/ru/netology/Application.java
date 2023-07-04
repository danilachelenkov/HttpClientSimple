package ru.netology;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
;import java.io.IOException;
import java.util.List;

public class Application {
    public final static String CATS_FACT_RESOURCES = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    private static ObjectMapper mapper = new ObjectMapper();

    public void run() throws IOException {

        CloseableHttpClient httpClient = getHttpClient();

        HttpGet request = new HttpGet(CATS_FACT_RESOURCES);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        CloseableHttpResponse response = httpClient.execute(request);

        List<CatsFact> facts = mapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<>() {
                });

        facts.stream()
                .filter(x -> x.getUpvotes() != null && x.getUpvotes()>0)
                .forEach(System.out::println);


    }

    private CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(10000)
                        .setRedirectsEnabled(false)
                        .build()
                )
                .build();

        return httpClient;
    }
}
