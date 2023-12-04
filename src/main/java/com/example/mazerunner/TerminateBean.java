package com.example.mazerunner;

import io.split.client.SplitClient;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;


public class TerminateBean {

  @Autowired
  private SplitClient splitClient;

  @PreDestroy
  public void onDestroy() throws Exception {
    System.out.println("****** Shutting down Split Client *******");
    splitClient.destroy();
  }
}
