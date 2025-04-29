package activity4;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {
        vertx.createHttpServer()
                .requestHandler(req -> req.response().end("Hello, Vert.x!"))
                .listen(8080)
                .onSuccess(server -> {
                    System.out.println("Server running on http://localhost:8888");
                    startPromise.complete();
                })
                .onFailure(startPromise::fail);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
//        vertx.deployVerticle(new MainVerticle());
        vertx.deployVerticle(new SenderVerticle());
        vertx.deployVerticle(new ReceiverVerticle());
    }
}