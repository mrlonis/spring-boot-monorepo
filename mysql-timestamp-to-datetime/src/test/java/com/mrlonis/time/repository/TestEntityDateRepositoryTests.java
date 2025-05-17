package com.mrlonis.time.repository;

import static com.mrlonis.time.util.TestData.getTestEntityDate;
import static com.mrlonis.time.util.TestUtils.assertEntityCreation;
import static com.mrlonis.time.util.TestUtils.assertInitialRepositoryConditions;

import com.mrlonis.time.util.TestcontainersConfigurations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfigurations.TestcontainersConfigurationMySQL8_0.class)
@SpringBootTest
class TestEntityDateRepositoryTests {
    @Autowired
    private TestEntityDateRepository repository;

    @Test
    void testEntity() {
        assertInitialRepositoryConditions(repository);
        assertEntityCreation(getTestEntityDate(), repository);
    }
}
