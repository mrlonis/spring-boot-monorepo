/**
 * This package contains the JPA entities for the application.
 *
 * <p>There are 5 entities:
 *
 * <ol>
 *   <li>{@link com.mrlonis.time.entity.TestEntityCalendar} - A test entity where the date-time fields are represented
 *       by {@link java.util.Calendar} objects.
 *   <li>{@link com.mrlonis.time.entity.TestEntityDate} - A test entity where the date-time fields are represented by
 *       {@link java.util.Date} objects.
 *   <li>{@link com.mrlonis.time.entity.TestEntityOffsetDateTime} - A test entity where the date-time fields are
 *       represented by {@link java.time.OffsetDateTime} objects.
 *   <li>{@link com.mrlonis.time.entity.TestEntityTimestamp} - A test entity where the date-time fields are represented
 *       by {@link java.sql.Timestamp} objects.
 *   <li>{@link com.mrlonis.time.entity.TestEntityZonedDateTime} - A test entity where the date-time fields are
 *       represented by {@link java.time.ZonedDateTime} objects.
 * </ol>
 *
 * These all correspond to MySQL's Supported “instant” date-time classes: <a
 * href="https://dev.mysql.com/blog-archive/support-for-date-time-types-in-connector-j-8-0/">MySQL Documentation -
 * Support for Date-Time Types in Connector/J 8.0</a>
 */
package com.mrlonis.time.entity;
