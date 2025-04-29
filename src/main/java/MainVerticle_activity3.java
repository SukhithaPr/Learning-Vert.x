// Activity 2: Routing with Vert.x Web

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class MainVerticle_activity3 extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);

        router.get("/api/data").handler(ctx -> {
            fetchDataFromDB()
                    .onSuccess(data -> ctx.end(data))
                    .onFailure(err -> ctx.fail(500));
        });

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(8080);
    }

        private Future<String> fetchDataFromDB() {
            Promise<String> promise = Promise.promise();
            vertx.setTimer(2000, id -> promise.complete("Data from DB"));
            return promise.future();
        }

        public static void main(String[] args) {
            Vertx vertx = Vertx.vertx();
            vertx.deployVerticle(new MainVerticle_activity3());
        }
    }