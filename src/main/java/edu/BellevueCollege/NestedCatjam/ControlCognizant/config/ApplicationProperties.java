package edu.BellevueCollege.NestedCatjam.ControlCognizant.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
// https://developer.auth0.com/resources/code-samples/full-stack/hello-world/basic-role-based-access-control/spa/react-javascript/spring-java
@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

  private String clientOriginUrl;

  private String audience;
}
