package the.camelsoapdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringCxfApplication {

  @Autowired
  ApplicationContext applicationContext;

  public static void main(String[] args) {
    SpringApplication.run(SpringCxfApplication.class, args);
  }



/*
  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {

      System.out.println("Let's inspect the beans provided by Spring Boot:");

      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String beanName : beanNames) {
        if(beanName.equals("orderEndpoint")) {
          Object o = applicationContext.getBean(beanName);
          System.out.println();
        }
        System.out.println(beanName);
      }
    };
  }
*/


}
