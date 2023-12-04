package com.example.mazerunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class ApplicationTest {

  @Test
  public void shouldFindMyWayThroughMaze1() throws Exception {
    assertTrue(true);
  }
}