package net.dut.anonymizer;

import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;
import com.typesafe.config.Config;
import net.dut.anonymizer.api.PinRoute;
import net.dut.anonymizer.api.UserAuthenticator;
import org.slf4j.Logger;

import static net.dut.anonymizer.model.helper.ConfigHelper.appConfig;
import static net.dut.anonymizer.model.helper.ConfigHelper.profile;
import static net.dut.anonymizer.model.helper.ConfigHelper.port;
import static org.slf4j.LoggerFactory.getLogger;

public class TestApp extends HttpApp {

    private static final Logger log = getLogger(TestApp.class);

    private final Route route;

    public TestApp(final Config c) {

        log.info("up ...");

        final UserAuthenticator userAuthenticator = new UserAuthenticator("admin", "admin");

        route = route(
                new PinRoute(userAuthenticator).route
        );

        log.info("UP_DONE");
    }

    @Override
    public Route routes() {
        return route;
    }

    public static void main(String[] args) throws Exception {
        log.info("active profile: {}, http port: {}", profile, port);
        new App(appConfig).startServer("0.0.0.0", port);
    }
}