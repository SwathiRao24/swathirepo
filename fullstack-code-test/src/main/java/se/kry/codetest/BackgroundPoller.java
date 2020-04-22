package se.kry.codetest;

import io.vertx.core.Future;
import io.vertx.ext.web.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class BackgroundPoller {

  public Future<List<String>> pollServices(Map<String, String> services) {
    //TODO
    services.forEach((url, status) -> {
      WebClient.create(vertx).getAbs(url).send(result -> {
        if (result == null || result.result() == null) {
          services.put(url, "FAIL");
          return;
        }

        if (result.result().statusCode() == 200) {
          services.put(url, "OK");
        } else {
          services.put(url, "FAIL");
        }

      });
    });
    return Future.succeededFuture();
    //return Future.failedFuture("TODO");
  }
}
