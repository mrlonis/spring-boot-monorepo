package com.mrlonis.time.repository;

import static com.mrlonis.time.test.TestData.getTestEntityDate;
import static com.mrlonis.time.test.TestUtils.assertEntityCreation;
import static com.mrlonis.time.test.TestUtils.assertInitialRepositoryConditions;

import com.mrlonis.time.test.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DateEntityRepositoryIT {
    @Autowired
    private DateEntityRepository repository;

    @Test
    void testEntity() {
        assertInitialRepositoryConditions(repository);
        assertEntityCreation(getTestEntityDate(), repository);
    }
}
