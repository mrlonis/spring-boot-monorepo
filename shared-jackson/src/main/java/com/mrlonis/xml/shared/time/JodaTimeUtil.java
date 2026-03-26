package com.mrlonis.xml.shared.time;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

@UtilityClass
public class JodaTimeUtil {
    public static final DateTimeFormatter PARSE_FORMAT = ISODateTimeFormat.dateTime();

    public static DateTime unmarshalDateTime(String v) {
        try {
            return DateTime.parse(v);
        } catch (IllegalArgumentException e) {
            return DateTime.parse(v, PARSE_FORMAT);
        }
    }

    public static String marshalDateTime(DateTime v) {
        return PARSE_FORMAT.print(v);
    }

    public static LocalDateTime unmarshalLocalDateTime(String v) {
        try {
            return LocalDateTime.parse(v);
        } catch (IllegalArgumentException e) {
            return LocalDateTime.parse(v, PARSE_FORMAT);
        }
    }

    public static String marshalLocalDateTime(LocalDateTime v) {
        return PARSE_FORMAT.print(v);
    }

    public static DateTime deserializeDateTime(JsonParser jsonparser, DeserializationContext context)
            throws IOException {
        String v = jsonparser.getText();

        try {
            return DateTime.parse(v);
        } catch (IllegalArgumentException e) {
            return DateTime.parse(v, PARSE_FORMAT);
        }
    }

    public static void serializeDateTime(DateTime v, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        if (v == null) {
            gen.writeNull();
        } else {
            gen.writeString(v.toString());
        }
    }

    public static LocalDateTime deserializeLocalDateTime(JsonParser jsonparser, DeserializationContext context)
            throws IOException {
        String v = jsonparser.getText();

        try {
            return LocalDateTime.parse(v);
        } catch (IllegalArgumentException e) {
            return LocalDateTime.parse(v, PARSE_FORMAT);
        }
    }

    public static void serializeLocalDateTime(LocalDateTime v, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {
        if (v == null) {
            gen.writeNull();
        } else {
            gen.writeString(v.toString());
        }
    }
}
