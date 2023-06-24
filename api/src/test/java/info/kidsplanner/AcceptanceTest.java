package info.kidsplanner;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public abstract class AcceptanceTest {
    @LocalServerPort
    private int port;

    @SuppressWarnings("rawtypes")
    @Container
    protected static final MySQLContainer MY_SQL_CONTAINER = new MySQLContainer("mysql:8");

    @BeforeEach
    protected void setUp() {
        RestAssured.port = port;
    }
}
