package the.camelsoapdemo.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import the.camelsoapdemo.services.helloworld.HelloWorldPortType;
import the.camelsoapdemo.types.helloworld.Greeting;
import the.camelsoapdemo.types.helloworld.ObjectFactory;
import the.camelsoapdemo.types.helloworld.Person;

@Component
public class HelloWorldClient {

  @Autowired
  private HelloWorldPortType helloWorldProxy;

  // public String sayHello(String firstName, String lastName) {
  public String sayHello(String firstName, String lastName) {

    ObjectFactory factory = new ObjectFactory();
    Person person = factory.createPerson();
    person.setFirstName(firstName);
    person.setLastName(lastName);

    Greeting response = helloWorldProxy.sayHello(person);

    return response.getGreeting();
  }
}
