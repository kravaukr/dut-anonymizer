package net.dut.anonymizer.api;

import akka.http.javadsl.model.headers.BasicHttpCredentials;
import akka.http.javadsl.testkit.JUnitRouteTest;
import akka.http.javadsl.testkit.TestRoute;
import net.dut.anonymizer.api.UserAuthenticator;
import org.junit.Before;
import org.junit.Test;

import static akka.http.javadsl.model.HttpRequest.POST;
import static akka.http.javadsl.model.StatusCodes.OK;
import static akka.http.javadsl.model.headers.HttpCredentials.createBasicHttpCredentials;

public class MainRouteJsonTest extends JUnitRouteTest {

    private TestRoute testRoute;

    private BasicHttpCredentials basicHttpCredentials;

    @Before
    public void init() {
        basicHttpCredentials = createBasicHttpCredentials("admin", "admin");
    }

    @Test
    public void test_anonymizer_response() {
        testRoute.run(POST("/dut/anonymizer/api/pin/2140")
                        .addCredentials(basicHttpCredentials))
                .assertStatusCode(OK);
    }
}
