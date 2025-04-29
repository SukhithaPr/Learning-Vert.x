import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class MainVerticle_activity1 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {
        vertx.createHttpServer()
                .requestHandler(request -> request.response().end("Hello Sukhitha!"))
                .listen(8080)
                .onSuccess(server -> {
                    System.out.println("HTTP server started on port 8080");
                    startPromise.complete();
                })
                .onFailure(startPromise::fail);
    }
    public static void main(String[] args) {
        Vertx.vertx().deployVerticle(new MainVerticle_activity1());
    }
}