package com.mrlonis.time;

import static com.mrlonis.time.util.TestData.getTestEntityCalendar;
import static com.mrlonis.time.util.TestData.getTestEntityDate;
import static com.mrlonis.time.util.TestData.getTestEntityOffsetDateTime;
import static com.mrlonis.time.util.TestData.getTestEntityTimestamp;
import static com.mrlonis.time.util.TestData.getTestEntityZonedDateTime;
import static com.mrlonis.time.util.TestUtils.assertEntityCreation;
import static com.mrlonis.time.util.TestUtils.assertInitialRepositoryConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.time.repository.TestEntityCalendarRepository;
import com.mrlonis.time.repository.TestEntityDateRepository;
import com.mrlonis.time.repository.TestEntityOffsetDateTimeRepository;
import com.mrlonis.time.repository.TestEntityTimestampRepository;
import com.mrlonis.time.repository.TestEntityZonedDateTimeRepository;
import com.mrlonis.time.util.TestcontainersConfigurations;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

/**
 * The goal of these tests are to combine all the repository tests into one place, and then perform the tests against a
 * database BEFORE it is migrated to datetime. This is to show that the code show in this repository works against the
 * database before it is migrated to datetime. This further shows how this migration is a low-risk operation, by proving
 * the application code can remain completely unchanged and still work against the database regardless of what the
 * underlying datetime data type is being using in the database table.
 */
class MySqlTimestampToDatetimeApplicationTimestampTests {
    @SpringBootTest
    @ActiveProfiles("test-timestamp")
    abstract static class BaseMySqlTimestampToDatetimeApplicationTimestampTest {
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

        @Test
        void contextLoads() {
            assertTrue(true);
        }

        @Test
        void testEntityCalendar() {
            assertInitialRepositoryConditions(testEntityCalendarRepository);
            assertEntityCreation(getTestEntityCalendar(), testEntityCalendarRepository);
        }

        @Test
        void testEntityDate() {
            assertInitialRepositoryConditions(testEntityDateRepository);
            assertEntityCreation(getTestEntityDate(), testEntityDateRepository);
        }

        @Test
        void testEntityOffsetDateTime() {
            assertInitialRepositoryConditions(testEntityOffsetDateTimeRepository);
            assertEntityCreation(getTestEntityOffsetDateTime(), testEntityOffsetDateTimeRepository);
        }

        @Test
        void testEntityTimestamp() {
            assertInitialRepositoryConditions(testEntityTimestampRepository);
            assertEntityCreation(getTestEntityTimestamp(), testEntityTimestampRepository);
        }

        @Test
        void testEntityZonedDateTime() {
            assertInitialRepositoryConditions(testEntityZonedDateTimeRepository);
            assertEntityCreation(getTestEntityZonedDateTime(), testEntityZonedDateTimeRepository);
        }
    }

    @Nested
    @Import(TestcontainersConfigurations.TestcontainersConfigurationMySQL5_7.class)
    class MySqlTimestampToDatetimeApplicationTimestampMySQL5_7Tests
            extends BaseMySqlTimestampToDatetimeApplicationTimestampTest {}

    @Nested
    @Import(TestcontainersConfigurations.TestcontainersConfigurationMySQL8.class)
    class MySqlTimestampToDatetimeApplicationTimestampMySQL8Tests
            extends BaseMySqlTimestampToDatetimeApplicationTimestampTest {}

    @Nested
    @Import(TestcontainersConfigurations.TestcontainersConfigurationMySQL9_0.class)
    class MySqlTimestampToDatetimeApplicationTimestampMySQL9Tests
            extends BaseMySqlTimestampToDatetimeApplicationTimestampTest {}
}
