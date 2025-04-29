package activity4;

import io.vertx.core.AbstractVerticle;

import java.time.LocalTime;

public class SenderVerticle extends AbstractVerticle {
    @Override
    public void start() {
        vertx.setPeriodic(3000, id -> {
            vertx.eventBus().publish("news.updates", "New update at " + LocalTime.now());
        });
    }
}
