package com.freestrokes;

import com.freestrokes.constants.PathConstants;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor
class WebClientTests {
  
  @Autowired
  private WebClient webClient;
  
  public WebClient request() {
    return webClient.mutate().baseUrl(
      "http://localhost:8080"
    ).build();
  }
  
  @Test
  public void contextLoads() {
    request()
      .get()
      .uri(PathConstants.HEALTH_CHECK)
      .retrieve()
      .bodyToMono(Void.class)
      .block();
  }
  
}
 
