package daggerok;

import org.picocontainer.*;
import org.picocontainer.behaviors.Caching;

import static spark.Spark.*;

public class Main {

  public interface JavaService {
    String getName();
  }

  public static class JavaServiceImpl implements JavaService {

    @Override
    public String getName() {
      return "Max";
    }
  }

  public static void main(String[] args) {

    final MutablePicoContainer container = new DefaultPicoContainer(new Caching());
    container.addComponent(JavaService.class, JavaServiceImpl.class);
    container.start();

    final JavaService service = container.getComponent(JavaService.class);

    port(8080);
    path("/", () ->
        get("*", (request, response) ->
            "hi, " + service.getName() + " from java"));
  }
}

