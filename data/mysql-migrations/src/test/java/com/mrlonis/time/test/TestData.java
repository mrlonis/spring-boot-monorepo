package com.mrlonis.time.test;

import static com.mrlonis.time.test.TestConstants.TEST_CODE;
import static com.mrlonis.time.test.TestConstants.TEST_NAME;
import static com.mrlonis.time.test.TestConstants.TEST_TYPE;
import static com.mrlonis.time.test.TestConstants.TEST_USER;

import com.mrlonis.time.entity.CalendarEntity;
import com.mrlonis.time.entity.DateEntity;
import com.mrlonis.time.entity.OffsetDateTimeEntity;
import com.mrlonis.time.entity.TimestampEntity;
import com.mrlonis.time.entity.ZonedDateTimeEntity;
import com.mrlonis.time.entity.base.BaseEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestData {
    public static CalendarEntity getTestEntityCalendar() {
        return addBaseEntityFields(CalendarEntity.builder()).build();
    }

    private static <D, T extends BaseEntity<D>, B extends BaseEntity.BaseEntityBuilder<D, T, B>> B addBaseEntityFields(
            BaseEntity.BaseEntityBuilder<D, T, B> builder) {
        return builder.name(TEST_NAME)
                .type(TEST_TYPE)
                .code(TEST_CODE)
                .createdUser(TEST_USER)
                .updatedUser(TEST_USER);
    }

    public static DateEntity getTestEntityDate() {
        return addBaseEntityFields(DateEntity.builder()).build();
    }

    public static OffsetDateTimeEntity getTestEntityOffsetDateTime() {
        return addBaseEntityFields(OffsetDateTimeEntity.builder()).build();
    }

    public static TimestampEntity getTestEntityTimestamp() {
        return addBaseEntityFields(TimestampEntity.builder()).build();
    }

    public static ZonedDateTimeEntity getTestEntityZonedDateTime() {
        return addBaseEntityFields(ZonedDateTimeEntity.builder()).build();
    }
}
