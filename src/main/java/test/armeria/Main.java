package test.armeria;

import com.linecorp.armeria.common.*;
import com.linecorp.armeria.server.*;

public class Main {

	public static void main(String[] args) {
		Server server = Server.builder()
			.http(8080)
			.https(8443)
			.tlsSelfSigned()
			.service("/", (ctx, req) -> HttpResponse.of("Hello Armeria!"))
			.service("/hello/:name", Main::helloName)
			.build();
		
		server.start();
	}
	
	private static HttpResponse helloName(ServiceRequestContext ctx, HttpRequest req) {
		return HttpResponse.of("Hello, %s", ctx.pathParam("name"));
	}
}