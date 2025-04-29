
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

import java.util.Map;

public class MainVerticle_activity2 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) {
        Router router = Router.router(vertx);
        router.get("/api/hello").handler(ctx -> ctx.json(Map.of("message", "Hello!")));
        router.get("/api/users/:id").handler(ctx -> {
            String id = ctx.pathParam("id");
            ctx.json(Map.of("user_id", id));
        });

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080)
                .onSuccess(server -> startPromise.complete())
                .onFailure(startPromise::fail);
    }
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle_activity2());
    }

}