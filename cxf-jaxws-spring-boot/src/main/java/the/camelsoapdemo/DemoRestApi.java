package the.camelsoapdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import the.camelsoapdemo.client.HelloWorldClient;

@RestController
public class DemoRestApi {

	@Autowired
	HelloWorldClient helloWorldSoapClient;


	@RequestMapping
	public String testIt() {

		String response = helloWorldSoapClient.sayHello("Jopas", "Jotain");
		return response;
	}
}
