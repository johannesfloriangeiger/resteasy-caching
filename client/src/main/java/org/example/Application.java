package org.example;

import jakarta.ws.rs.client.ClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.cache.BrowserCacheFeature;

import java.text.MessageFormat;
import java.util.stream.IntStream;

public class Application {

    public static void main(final String[] args) {
        try (final var client = ClientBuilder.newClient()) {
            final var resteasyWebTarget = (ResteasyWebTarget) client.target("http://localhost:8080/api/message");
            final var browserCacheFeature = new BrowserCacheFeature();
            resteasyWebTarget.register(browserCacheFeature);

            IntStream.range(0, 10)
                    .forEach(value -> {
                        try {
                            Thread.sleep(5000 + 100);
                        } catch (final InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        final var message = resteasyWebTarget.request()
                                .get(String.class);
                        System.out.println(MessageFormat.format("{0}: {1}", value, message));
                    });
        }
    }
}
