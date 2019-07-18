package com.oleksii.config;

import com.microsoft.bot.connector.customizations.MicrosoftAppCredentials;
import com.microsoft.bot.schema.models.ResourceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BotConfig {

  @Autowired
  private Environment environment;

  @Bean(name = "credentials")
  public MicrosoftAppCredentials getCredentials() {
    return new MicrosoftAppCredentials("7419eda9-428a-4688-b84c-f429d0447024",
        "qwertyasdf123");
  }

  @Bean
  public List<ResourceResponse> getResponses(){
    return new ArrayList<>();
  }
}
