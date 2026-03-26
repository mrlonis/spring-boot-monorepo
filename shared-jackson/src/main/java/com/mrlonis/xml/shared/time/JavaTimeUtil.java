package com.mrlonis.xml.shared.time;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JavaTimeUtil {
    public static final DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ISO_DATE_TIME;
    public static final DateTimeFormatter ZONED_OUTPUT_FORMAT = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    public static final DateTimeFormatter NO_ZONE_OUTPUT_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static LocalDateTime unmarshalLocalDateTime(String v) {
        try {
            return LocalDateTime.parse(v);
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(v, PARSE_FORMAT);
        }
    }

    public static String marshalLocalDateTime(LocalDateTime v) {
        return v.format(NO_ZONE_OUTPUT_FORMAT);
    }

    public static ZonedDateTime unmarshalZonedDateTime(String v) {
        try {
            return ZonedDateTime.parse(v);
        } catch (DateTimeParseException e) {
            return ZonedDateTime.parse(v, PARSE_FORMAT);
        }
    }

    public static String marshalZonedDateTime(ZonedDateTime v) {
        return v.format(ZONED_OUTPUT_FORMAT);
    }

    public static LocalDateTime deserializeLocalDateTime(JsonParser jsonparser, DeserializationContext context)
            throws IOException {
        String v = jsonparser.getText();
        try {
            return LocalDateTime.parse(v);
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(v, PARSE_FORMAT);
        }
    }

    public static void serializeLocalDateTime(LocalDateTime v, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {
        if (v == null) {
            gen.writeNull();
        } else {
            String dateString = v.format(NO_ZONE_OUTPUT_FORMAT);
            gen.writeString(dateString);
        }
    }

    public static ZonedDateTime deserializeZonedDateTime(JsonParser jsonparser, DeserializationContext context)
            throws IOException {
        String v = jsonparser.getText();
        try {
            return ZonedDateTime.parse(v);
        } catch (DateTimeParseException e) {
            return ZonedDateTime.parse(v, PARSE_FORMAT);
        }
    }

    public static void serializeZonedDateTime(ZonedDateTime v, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {
        if (v == null) {
            gen.writeNull();
        } else {
            String dateString = v.format(ZONED_OUTPUT_FORMAT);
            gen.writeString(dateString);
        }
    }
}
