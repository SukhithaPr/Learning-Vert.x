// Activity 1: Simple HTTP Server

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class MainVerticle_activity1 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {
        // Create an HTTP server
        vertx.createHttpServer()
                // Handle incoming HTTP requests by responding with a simple message
                .requestHandler(request -> request.response().end("Hello Sukhitha!"))
                // Start the server on port 8080
                .listen(8080)
                .onSuccess(server -> {
                    // Log a success message when the server starts
                    System.out.println("HTTP server started on port 8080");
                    startPromise.complete();
                })
                // Handle server start failure
                .onFailure(startPromise::fail);
    }

    public static void main(String[] args) {
        // Deploy the verticle to start the server
        Vertx.vertx().deployVerticle(new MainVerticle_activity1());
    }
}