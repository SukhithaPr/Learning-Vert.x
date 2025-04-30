package activity6;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class MainVerticle extends AbstractVerticle {
    @Override
    public void start() {
        System.out.println("MainVerticle started");
    }
    @Override
    public void stop() {
        System.out.println("MainVerticle stopped");
    }
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle());
    }
}
