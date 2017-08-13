package it.blackwhitestudio.xenia.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClientConfigurer;
import org.springframework.test.web.reactive.server.WebTestClient.Builder;

import org.springframework.web.server.adapter.WebHttpHandlerBuilder;


import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlacesControllerTests {

	@Autowired
	ApplicationContext context;
	
	@LocalServerPort
	private int port;
	
	WebTestClient client;
	
	@Before
	public void setup() {
		client = WebTestClient
				.bindToApplicationContext(context)
				.configureClient()
				.baseUrl(String.format("http://localhost:%d/", port))
				.build();
	}
	
	@Test
	public void getPlacesWhenNotAuthenticatedThenIsUnauthorized() {
		client
			.get().uri("/place").exchange()
			.expectStatus().isUnauthorized();
	}
	
	@Test
	public void getPlacesWhenAuthenticatedThenIsOk() {
		client
			.mutateWith(identity("jlong", "password"))
			.get().uri("/place").exchange()
			.expectStatus().isOk();
	}
	
	private static WebTestClientConfigurer identity(String userName, String password) {
		return new WebTestClientConfigurer() {
			@Override
			public void afterConfigurerAdded(Builder builder, WebHttpHandlerBuilder httpHandlerBuilder,
					ClientHttpConnector connector) {
				builder.filter(basicAuthentication("jlong", "password"));
			}
		};
	}
}
