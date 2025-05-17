package com.mrlonis.time.repository;

import static com.mrlonis.time.util.TestData.getTestEntityTimestamp;
import static com.mrlonis.time.util.TestUtils.assertEntityCreation;
import static com.mrlonis.time.util.TestUtils.assertInitialRepositoryConditions;

import com.mrlonis.time.util.TestcontainersConfigurations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfigurations.TestcontainersConfigurationMySQL8_0.class)
@SpringBootTest
class TestEntityTimestampRepositoryTests {
    @Autowired
    private TestEntityTimestampRepository repository;

    @Test
    void testEntity() {
        assertInitialRepositoryConditions(repository);
        assertEntityCreation(getTestEntityTimestamp(), repository);
    }
}
