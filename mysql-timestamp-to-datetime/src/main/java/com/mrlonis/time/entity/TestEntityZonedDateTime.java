package com.mrlonis.time.entity;

import com.mrlonis.time.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * This is a test entity that is used to test the application. It uses {@link ZonedDateTime java.time.ZonedDateTime} as
 * the data type for the datetime fields.
 *
 * <p>This is done to show that the {@link ZonedDateTime java.time.ZonedDateTime} data type is compatible with MySQL's
 * TIMESTAMP AND DATETIME data types. This is important because the application code does not need to change when the
 * database data type is changed from TIMESTAMP to DATETIME. Further evidence that this is a low-risk operation.
 */
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "TEST_ENTITY_ZONED_DATE_TIME")
@Entity
@NoArgsConstructor
@SuperBuilder
public class TestEntityZonedDateTime extends BaseEntity<ZonedDateTime> {}
