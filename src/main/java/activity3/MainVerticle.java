package activity3;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {
        // Create a router to handle HTTP routes
        Router router = Router.router(vertx);

        // Define a route for GET /api/data
        router.get("/api/data").handler(ctx -> {
            // Fetch data from the database (simulated with a timer)
            fetchDataFromDB()
                    // On success, send the data as the response
                    .onSuccess(data -> ctx.end(data))
                    // On failure, respond with a 500 error
                    .onFailure(err -> ctx.fail(500));
        });

        // Create an HTTP server and bind it to the router
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080); // Start the server on port 8080
    }

    // Simulate fetching data from a database
    private Future<String> fetchDataFromDB() {
        Promise<String> promise = Promise.promise();
        // Simulate a delay of 2 seconds before returning the data
        vertx.setTimer(2000, id -> promise.complete("Data from DB"));
        return promise.future();
    }

    public static void main(String[] args) {
        // Deploy the verticle to start the server
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }
}