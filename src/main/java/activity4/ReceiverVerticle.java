package activity4;

import io.vertx.core.AbstractVerticle;

public class ReceiverVerticle extends AbstractVerticle {
    @Override
    public void start() {
        vertx.eventBus().consumer("news.updates", message -> {
            System.out.println("Received message: " + message.body());
        });
    }
}
