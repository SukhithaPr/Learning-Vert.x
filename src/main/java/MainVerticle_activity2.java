// Activity 2: Routing with Vert.x Web

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

import java.util.Map;

public class MainVerticle_activity2 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {
        // Create a router to handle HTTP routes
        Router router = Router.router(vertx);

        // Define a route for GET /api/hello that responds with a JSON message
        router.get("/api/hello").handler(ctx -> ctx.json(Map.of("message", "Hello!")));

        // Define a route for GET /api/users/:id that responds with the user ID in JSON
        router.get("/api/users/:id").handler(ctx -> {
            String id = ctx.pathParam("id"); // Extract the user ID from the path parameter
            ctx.json(Map.of("user_id", id)); // Respond with the user ID in JSON format
        });

        // Create an HTTP server and bind it to the router
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080)
                .onSuccess(server -> startPromise.complete()) // Complete the promise on success
                .onFailure(startPromise::fail); // Fail the promise on error
    }

    public static void main(String[] args) {
        // Deploy the verticle to start the server
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle_activity2());
    }
}