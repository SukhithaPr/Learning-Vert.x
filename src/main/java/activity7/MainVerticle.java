package activity7;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start() {
        vertx.createHttpServer()
                .requestHandler(request -> request.response().end("Hello World!"))
                .listen(8888, result -> {
                    if (result.succeeded()) {
                        System.out.println("Server is now listening on port 8888");
                    } else {
                        System.out.println("Failed to bind server: " + result.cause());
                    }
                });
    }
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }
}
