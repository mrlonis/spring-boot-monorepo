package com.mrlonis.time.repository;

import static com.mrlonis.time.util.TestData.getTestEntityOffsetDateTime;
import static com.mrlonis.time.util.TestUtils.assertEntityCreation;
import static com.mrlonis.time.util.TestUtils.assertInitialRepositoryConditions;

import com.mrlonis.time.util.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TestEntityOffsetDateTimeRepositoryTests {
    @Autowired
    private TestEntityOffsetDateTimeRepository repository;

    @Test
    void testEntity() {
        assertInitialRepositoryConditions(repository);
        assertEntityCreation(getTestEntityOffsetDateTime(), repository);
    }
}
