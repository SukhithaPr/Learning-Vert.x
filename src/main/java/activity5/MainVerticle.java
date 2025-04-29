package activity5;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import java.util.Map;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MainVerticle extends AbstractVerticle {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public void start(Promise<Void> startPromise) {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        // Create
        router.post("/users").handler(ctx -> {
            User user = ctx.body().asJsonObject().mapTo(User.class);
            user.setId(UUID.randomUUID().toString());
            users.put(user.getId(), user);
            ctx.response()
                    .setStatusCode(201)
                    .putHeader("Content-Type", "application/json")
                    .end(Json.encodePrettily(user));
        });

        // Read (Single)
        router.get("/users/:id").handler(ctx -> {
            String id = ctx.pathParam("id");
            User user = users.get(id);
            if (user == null) {
                ctx.response()
                        .setStatusCode(404)
                        .end(Json.encodePrettily(Map.of("error", "User not found")));
            } else {
                ctx.response()
                        .putHeader("Content-Type", "application/json")
                        .end(Json.encodePrettily(user));
            }
        });

        // Read (All)
        router.get("/users").handler(ctx -> {
            ctx.response()
                    .putHeader("Content-Type", "application/json")
                    .end(Json.encodePrettily(users.values()));
        });

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080)
                .onSuccess(server -> startPromise.complete())
                .onFailure(startPromise::fail);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }
}