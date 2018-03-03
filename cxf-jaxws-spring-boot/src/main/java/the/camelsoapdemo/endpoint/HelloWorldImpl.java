package the.camelsoapdemo.endpoint;

import the.camelsoapdemo.services.helloworld.HelloWorldPortType;
import the.camelsoapdemo.types.helloworld.Greeting;
import the.camelsoapdemo.types.helloworld.ObjectFactory;
import the.camelsoapdemo.types.helloworld.Person;

public class HelloWorldImpl implements HelloWorldPortType {

  @Override
  public Greeting sayHello(Person request) {
    String greeting = "Hello " + request.getFirstName() + " "
        + request.getLastName() + "!";

    ObjectFactory factory = new ObjectFactory();
    Greeting response = factory.createGreeting();
    response.setGreeting(greeting);

    return response;
  }
}
