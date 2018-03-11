package the.camelsoapdemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfSpringEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import the.camelsoapdemo.client.HelloWorldSoapClient;
import the.camelsoapdemo.types.helloworld.Greeting;
import the.camelsoapdemo.types.helloworld.Person;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = {SpringCxfApplication.class, SpringCxfApplicationTests.CamelSoapServerConfig.class})
public class SpringCxfApplicationTests {

  @Autowired
  private HelloWorldSoapClient helloWorldSoapClient;

  @Autowired
  TestRestTemplate testRestTemplate;

  @Test
  public void testSayHello() {

    assertThat(helloWorldSoapClient.sayHello("John", "Doe"))
        .isEqualTo("Hello John Doe!");
  }


  @Test
  public void testRest() {
      String string = testRestTemplate
              .withBasicAuth("ok", "22")

              .getForObject("/restit?name=Möttönen", String.class);

  }

  @Test
  public void testPost() {
      RequestPayload requestPayload = new RequestPayload();
      requestPayload.setName("Kuis hurisee?");

      ResponseEntity<ResponsePayload> responsePayload = testRestTemplate
              .withBasicAuth("ok", "22")
              .postForEntity("/postit", requestPayload, ResponsePayload.class);
  }


//  /Users/ok/dev/camel-soap-server-test/cxf-jaxws/cxf-jaxws-spring-boot/src/main/resources/wsdl/helloworld.wsdl

  @Configuration
  static class CamelSoapServerConfig {

    @Bean(name = "helloWorldPort")
    CxfSpringEndpoint helloWorldPort() throws Exception {
        CxfSpringEndpoint cxfSpringEndpoint = new CxfSpringEndpoint();
        cxfSpringEndpoint.setAddress("http://localhost:9090/camelsoapdemo/ws/helloworld");
        cxfSpringEndpoint.setServiceClass("the.camelsoapdemo.services.helloworld.HelloWorldPortType");
        cxfSpringEndpoint.setWsdlURL("src/main/resources/wsdl/helloworld.wsdl");

        return cxfSpringEndpoint;
    }


    @Bean
    RouteBuilder camelSoupServer() {
      return new RouteBuilder() {
        @Override
        public void configure() throws Exception {

          from("cxf:bean:helloWorldPort")
                  .process(exchange -> {
                      Person request = exchange.getIn().getBody(Person.class);
                      Greeting response = new Greeting();
                      response.setGreeting("Terkkuja Camel Soup Serveriltä, " + request.getFirstName());
                      exchange.getIn().setBody(response);
                  });
        }
      };
    }



  }
}
