package com.mrlonis.time;

import static com.mrlonis.time.util.TestUtils.assertInitialRepositoryConditions;

import com.mrlonis.time.repository.TestEntityCalendarRepository;
import com.mrlonis.time.repository.TestEntityDateRepository;
import com.mrlonis.time.repository.TestEntityOffsetDateTimeRepository;
import com.mrlonis.time.repository.TestEntityTimestampRepository;
import com.mrlonis.time.repository.TestEntityZonedDateTimeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.images.PullPolicy;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * This test class is used to test the application with a MySQL database using Testcontainers. It is very similar in
 * setup to the {@link ApplicationTests ApplicationTests} class. The difference is that this test is a more "manual"
 * approach to the Testcontainers setup, while the other one uses a {@link ServiceConnection @ServiceConnection}
 * annotation to automatically configure the database connection removing the need for
 * {@link DynamicPropertySource @DynamicPropertySource} used in this class.
 *
 * <p>This class will likely never be expanded and will eventually diverge from the other test class. It is here to show
 * how to set up a test with Testcontainers manually. The other test class is the preferred way to set up a test with
 * Testcontainers with Spring Boot 3.1+.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class ManualTestcontainersConfigurationsExampleTests {
    static final MySQLContainer<?> MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0").withImagePullPolicy(PullPolicy.alwaysPull());
        MY_SQL_CONTAINER.start();
    }

    @Autowired
    private TestEntityCalendarRepository testEntityCalendarRepository;

    @Autowired
    private TestEntityDateRepository testEntityDateRepository;

    @Autowired
    private TestEntityOffsetDateTimeRepository testEntityOffsetDateTimeRepository;

    @Autowired
    private TestEntityTimestampRepository testEntityTimestampRepository;

    @Autowired
    private TestEntityZonedDateTimeRepository testEntityZonedDateTimeRepository;

    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
    }

    @Test
    void contextLoads() {
        assertInitialRepositoryConditions(testEntityCalendarRepository);
        assertInitialRepositoryConditions(testEntityDateRepository);
        assertInitialRepositoryConditions(testEntityOffsetDateTimeRepository);
        assertInitialRepositoryConditions(testEntityTimestampRepository);
        assertInitialRepositoryConditions(testEntityZonedDateTimeRepository);
    }
}
