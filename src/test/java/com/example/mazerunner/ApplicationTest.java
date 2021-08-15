package com.example.mazerunner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class, args = "theSnake.txt")
public class ApplicationTest {

    @Test
    public void shouldFindMyWayThroughMaze1() throws Exception {

    }
}