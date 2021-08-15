package com.example.mazerunner;

import java.io.IOException;
import java.net.URISyntaxException;

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
    public MazeMaster levelOneMazeMaster() throws IOException, URISyntaxException {
        return new MazeMaster("theSnake.txt");
    }

    @Bean
    public MazeMaster levelTwoMazeMaster() throws IOException, URISyntaxException {
        return new MazeMaster("theTrapper.txt");
    }

    @Bean
    public MazeMaster levelThreeMazeMaster() throws IOException, URISyntaxException {
        return new MazeMaster("theDrain.txt");
    }

    @Bean
    public MazeMaster levelFourMazeMaster() throws IOException, URISyntaxException {
        return new MazeMaster("theCurl.txt");
    }

}
