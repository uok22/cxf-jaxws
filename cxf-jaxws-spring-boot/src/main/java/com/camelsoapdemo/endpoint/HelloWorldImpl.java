package com.camelsoapdemo.endpoint;

import com.camelsoapdemo.services.helloworld.HelloWorldPortType;
import com.camelsoapdemo.types.helloworld.Greeting;
import com.camelsoapdemo.types.helloworld.ObjectFactory;
import com.camelsoapdemo.types.helloworld.Person;

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
