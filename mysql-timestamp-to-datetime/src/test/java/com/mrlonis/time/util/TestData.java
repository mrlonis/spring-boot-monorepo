package com.mrlonis.time.util;

import static com.mrlonis.time.util.TestConstants.TEST_CODE;
import static com.mrlonis.time.util.TestConstants.TEST_NAME;
import static com.mrlonis.time.util.TestConstants.TEST_TYPE;
import static com.mrlonis.time.util.TestConstants.TEST_USER;

import com.mrlonis.time.entity.TestEntityCalendar;
import com.mrlonis.time.entity.TestEntityDate;
import com.mrlonis.time.entity.TestEntityOffsetDateTime;
import com.mrlonis.time.entity.TestEntityTimestamp;
import com.mrlonis.time.entity.TestEntityZonedDateTime;
import com.mrlonis.time.entity.base.BaseEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestData {
    public static TestEntityCalendar getTestEntityCalendar() {
        return addBaseEntityFields(TestEntityCalendar.builder()).build();
    }

    private static <D, T extends BaseEntity<D>, B extends BaseEntity.BaseEntityBuilder<D, T, B>> B addBaseEntityFields(
            BaseEntity.BaseEntityBuilder<D, T, B> builder) {
        return builder.name(TEST_NAME)
                .type(TEST_TYPE)
                .code(TEST_CODE)
                .createdUser(TEST_USER)
                .updatedUser(TEST_USER);
    }

    public static TestEntityDate getTestEntityDate() {
        return addBaseEntityFields(TestEntityDate.builder()).build();
    }

    public static TestEntityOffsetDateTime getTestEntityOffsetDateTime() {
        return addBaseEntityFields(TestEntityOffsetDateTime.builder()).build();
    }

    public static TestEntityTimestamp getTestEntityTimestamp() {
        return addBaseEntityFields(TestEntityTimestamp.builder()).build();
    }

    public static TestEntityZonedDateTime getTestEntityZonedDateTime() {
        return addBaseEntityFields(TestEntityZonedDateTime.builder()).build();
    }
}
