package ru.alex.vic.hhtool.json;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;
import ru.alex.vic.hhtool.jpa.HHLocatinsUtil;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.TimeUnit;

@Component
public class JsonClient {
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private Gson gson;

    @PostConstruct
    private void init() {
        gson = new Gson();
    }


    public HHLocationJson[] getLocations() throws IOException {
        HttpGet httpGet = new HttpGet("https://api.hh.ru/areas");
        httpGet.setHeader(CONTENT_TYPE_HEADER, "application/json");
        try (CloseableHttpClient httpclient = HttpClientBuilder.create().setConnectionTimeToLive(2, TimeUnit.MINUTES).build();
             CloseableHttpResponse response = httpclient.execute(httpGet);
             Reader reader = new InputStreamReader(response.getEntity().getContent(), "UTF-8");
        ) {
            return gson.fromJson(reader, HHLocationJson[].class);
        }


    }

    public static void main(String[] args) throws IOException {
        JsonClient jsonClient = new JsonClient();
        jsonClient.init();
        final HHLocationJson[] locations = jsonClient.getLocations();
        HHLocatinsUtil.iterate(locations, hhLocation -> {
            System.out.println("hhLocation = " + hhLocation);
        });
        System.out.println("locations = " + locations);
    }


}
