package com.example.mazerunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mazerunner.parts.MazeMaster;
import io.split.client.SplitClient;
import io.split.client.SplitClientConfig;
import io.split.client.SplitFactory;
import io.split.client.SplitFactoryBuilder;

@SpringBootApplication
public class Application {

    private static final String SPIT_API_KEY = "8qm4hdpqnufit0rhruu1ar8a24b6jcvr7jdb"; // staging
    //private static final String SPIT_API_KEY = "hqcsjesdq8iuk56kps9f28hlr2v2smvikdgv"; // personal


    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Map<Integer, MazeMaster> mazeMasterList() throws IOException {
        final Map<Integer, MazeMaster> mazeMasters = new HashMap<>();

        mazeMasters.put(1, new MazeMaster("theSnake.txt"));
        mazeMasters.put(2, new MazeMaster("theTrapper.txt"));
        mazeMasters.put(3, new MazeMaster("theDrain.txt"));
        mazeMasters.put(4, new MazeMaster("theCurl.txt"));
        mazeMasters.put(5, new MazeMaster("theDoubleBack.txt"));
        mazeMasters.put(6, new MazeMaster("theQuadrupleBypass.txt"));
        mazeMasters.put(7, new MazeMaster("theSpellingBee.txt"));
        mazeMasters.put(8, new MazeMaster("gregsMaze.txt"));

        mazeMasters.put(9, new MazeMaster("firstFloor.txt", "secondFloor.txt", "thirdFloor.txt"));

        return mazeMasters;
    }

    @Bean
    public SplitClient splitClient() throws IOException, URISyntaxException {
        //@formatter:off
        final SplitClientConfig config = SplitClientConfig.builder()
                .endpoint("https://sdk.split-stage.io","https://events.split-stage.io")
                .authServiceURL("https://auth.split-stage.io/api/auth")
                .telemetryURL("https://telemetry.split-stage.io/api/v1")
                .setBlockUntilReadyTimeout(10000)
                .build();
        //@formatter:on

        final SplitFactory splitFactory = SplitFactoryBuilder.build(SPIT_API_KEY, config);
        final SplitClient client = splitFactory.client();
        try {
            client.blockUntilReady();
        } catch (final TimeoutException | InterruptedException e) {
            // log & handle
        }

        return client;
    }

}
