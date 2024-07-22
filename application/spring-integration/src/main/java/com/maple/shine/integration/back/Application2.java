package com.maple.shine.integration.back;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.ws.SimpleWebServiceOutboundGateway;
import org.springframework.integration.ws.WebServiceHeaders;

/**
 * Started Classes
 *
 * @author leihz
 * @version 1.0.0
 * @since 2024/07/22 11:15
 */
// @SpringBootApplication
public class Application2 {

  public static void main(String[] args) {
    ConfigurableApplicationContext ctx = SpringApplication.run(Application2.class, args);
    TempConverter converter = ctx.getBean(TempConverter.class);
    System.out.println(converter.fahrenheitToCelcius(68.0f));
    ctx.close();
  }

  @MessagingGateway
  public interface TempConverter {

    @Gateway(requestChannel = "convert.input")
    float fahrenheitToCelcius(float fahren);

  }

  @Bean
  public IntegrationFlow convert() {
    return f -> f
        .transform(payload ->
            "<FahrenheitToCelsius xmlns=\"https://www.w3schools.com/xml/\">"
                + "<Fahrenheit>" + payload + "</Fahrenheit>"
                + "</FahrenheitToCelsius>")
        .enrichHeaders(h -> h
            .header(WebServiceHeaders.SOAP_ACTION,
                "https://www.w3schools.com/xml/FahrenheitToCelsius"))
        .handle(new SimpleWebServiceOutboundGateway(
            "https://www.w3schools.com/xml/tempconvert.asmx"))
//        .transform(Transformer.transform("/*[local-name()=\"FahrenheitToCelsiusResponse\"]"
//            + "/*[local-name()=\"FahrenheitToCelsiusResult\"]"));
        .transform("/*[local-name()=\"FahrenheitToCelsiusResponse\"]"
            + "/*[local-name()=\"FahrenheitToCelsiusResult\"]");
  }
}
