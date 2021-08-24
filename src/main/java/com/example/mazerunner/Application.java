package com.example.mazerunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mazerunner.parts.MazeMaster;

@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public List<MazeMaster> mazeMasterList() throws IOException, URISyntaxException {
        final List<MazeMaster> mazeMasters = new ArrayList<>();

        mazeMasters.add(new MazeMaster("theSnake.txt"));
        mazeMasters.add(new MazeMaster("theTrapper.txt"));
        mazeMasters.add(new MazeMaster("theDrain.txt"));
        mazeMasters.add(new MazeMaster("theCurl.txt"));
        mazeMasters.add(new MazeMaster("theDoubleBack.txt"));
        mazeMasters.add(new MazeMaster("theQuadrupleBypass.txt"));
        mazeMasters.add(new MazeMaster("theSpellingBee.txt"));

        return mazeMasters;
    }

}
