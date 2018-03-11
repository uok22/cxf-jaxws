package the.camelsoapdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import the.camelsoapdemo.client.HelloWorldSoapClient;

import java.security.Principal;

@RestController
public class DemoRestApi {

	@Autowired
	HelloWorldSoapClient helloWorldSoapClient;


	@GetMapping(value = "/restit")
	public String testIt(Principal principal,
						 @RequestParam String name) {

		String response = helloWorldSoapClient.sayHello(name, "Jotain");
		return response;
	}

	@PostMapping(value = "postit")
	public ResponsePayload postPayload(@RequestBody RequestPayload requestPayload) {
		ResponsePayload responsePayload = new ResponsePayload();
		responsePayload.setName("Vastaus, ole hyvä.");
		return responsePayload;
	}
}
