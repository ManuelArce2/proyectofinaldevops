package com.example;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testHelloWorldEndpoint() {
        Javalin app = App.createApp();
        JavalinTest.test(app, (server, client) -> {
            assertThat(client.get("/hello").body().string()).isEqualTo("Hello Worldd");
   });
}
}
