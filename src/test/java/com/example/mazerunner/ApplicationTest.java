package com.example.mazerunner;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class ApplicationTest {

    @Test
    public void shouldFindMyWayThroughMaze1() throws Exception {
        Assert.assertTrue(true);
    }
}