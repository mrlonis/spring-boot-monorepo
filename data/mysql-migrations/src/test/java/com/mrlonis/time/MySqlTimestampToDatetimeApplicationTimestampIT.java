package com.mrlonis.time;

import static com.mrlonis.time.test.TestData.getTestEntityCalendar;
import static com.mrlonis.time.test.TestData.getTestEntityDate;
import static com.mrlonis.time.test.TestData.getTestEntityOffsetDateTime;
import static com.mrlonis.time.test.TestData.getTestEntityTimestamp;
import static com.mrlonis.time.test.TestData.getTestEntityZonedDateTime;
import static com.mrlonis.time.test.TestUtils.assertEntityCreation;
import static com.mrlonis.time.test.TestUtils.assertInitialRepositoryConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mrlonis.time.repository.CalendarEntityRepository;
import com.mrlonis.time.repository.DateEntityRepository;
import com.mrlonis.time.repository.OffsetDateTimeEntityRepository;
import com.mrlonis.time.repository.TimestampEntityRepository;
import com.mrlonis.time.repository.ZonedDateTimeEntityRepository;
import com.mrlonis.time.test.TestcontainersConfiguration;
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
@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ActiveProfiles("test-timestamp")
class MySqlTimestampToDatetimeApplicationTimestampIT {
    @Autowired
    private CalendarEntityRepository calendarEntityRepository;

    @Autowired
    private DateEntityRepository dateEntityRepository;

    @Autowired
    private OffsetDateTimeEntityRepository offsetDateTimeEntityRepository;

    @Autowired
    private TimestampEntityRepository timestampEntityRepository;

    @Autowired
    private ZonedDateTimeEntityRepository zonedDateTimeEntityRepository;

    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void testEntityCalendar() {
        assertInitialRepositoryConditions(calendarEntityRepository);
        assertEntityCreation(getTestEntityCalendar(), calendarEntityRepository);
    }

    @Test
    void testEntityDate() {
        assertInitialRepositoryConditions(dateEntityRepository);
        assertEntityCreation(getTestEntityDate(), dateEntityRepository);
    }

    @Test
    void testEntityOffsetDateTime() {
        assertInitialRepositoryConditions(offsetDateTimeEntityRepository);
        assertEntityCreation(getTestEntityOffsetDateTime(), offsetDateTimeEntityRepository);
    }

    @Test
    void testEntityTimestamp() {
        assertInitialRepositoryConditions(timestampEntityRepository);
        assertEntityCreation(getTestEntityTimestamp(), timestampEntityRepository);
    }

    @Test
    void testEntityZonedDateTime() {
        assertInitialRepositoryConditions(zonedDateTimeEntityRepository);
        assertEntityCreation(getTestEntityZonedDateTime(), zonedDateTimeEntityRepository);
    }
}
