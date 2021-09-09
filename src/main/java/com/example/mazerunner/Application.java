package com.example.mazerunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        mazeMasters.put(9, new MazeMaster("firstFloor.txt", "secondFloor.txt"));

        return mazeMasters;
    }

}
