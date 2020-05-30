package com.samagra.Application;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
public class AppConfiguration {

  @Bean
  @Qualifier("rest")
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
 }
  
  @Bean
  @Qualifier("custom")
  public RestTemplate getCustomTemplate(RestTemplateBuilder builder) {
      Credentials credentials = new UsernamePasswordCredentials("samagra","impact@scale");
      CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
      credentialsProvider.setCredentials(AuthScope.ANY, credentials);

      HttpClient httpClient = HttpClients
              .custom()
              .setDefaultCredentialsProvider(credentialsProvider)
              .disableAuthCaching()
              .build();

      return builder
              .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClient))
              .build();
  }
}
