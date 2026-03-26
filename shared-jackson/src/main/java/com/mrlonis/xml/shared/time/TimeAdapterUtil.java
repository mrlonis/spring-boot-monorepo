package com.mrlonis.xml.shared.time;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;

@UtilityClass
@Slf4j
public class TimeAdapterUtil {
    private static final String UNSUPPORTED_TYPE = "Unsupported type: ";

    public static <T> T unmarshal(String v, @Nullable Class<T> type) {
        if (type != null) {
            if (type == LocalDateTime.class) {
                return type.cast(JavaTimeUtil.unmarshalLocalDateTime(v));
            } else if (type == ZonedDateTime.class) {
                return type.cast(JavaTimeUtil.unmarshalZonedDateTime(v));
            } else if (type == org.joda.time.LocalDateTime.class) {
                return type.cast(JodaTimeUtil.unmarshalLocalDateTime(v));
            } else if (type == org.joda.time.DateTime.class) {
                return type.cast(JodaTimeUtil.unmarshalDateTime(v));
            }
        }
        throw new UnsupportedOperationException(generateErrorMessageAndLogIt(type));
    }

    public static <T> String marshal(T v) {
        if (v instanceof LocalDateTime localDateTime) {
            return JavaTimeUtil.marshalLocalDateTime(localDateTime);
        } else if (v instanceof ZonedDateTime zonedDateTime) {
            return JavaTimeUtil.marshalZonedDateTime(zonedDateTime);
        } else if (v instanceof org.joda.time.LocalDateTime jodaLocalDateTime) {
            return JodaTimeUtil.marshalLocalDateTime(jodaLocalDateTime);
        } else if (v instanceof org.joda.time.DateTime jodaDateTime) {
            return JodaTimeUtil.marshalDateTime(jodaDateTime);
        }
        throw new UnsupportedOperationException(generateErrorMessageAndLogIt(v));
    }

    public static <T> void serialize(T v, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        if (v instanceof LocalDateTime localDateTime) {
            JavaTimeUtil.serializeLocalDateTime(localDateTime, gen, arg2);
            return;
        } else if (v instanceof ZonedDateTime zonedDateTime) {
            JavaTimeUtil.serializeZonedDateTime(zonedDateTime, gen, arg2);
            return;
        } else if (v instanceof org.joda.time.LocalDateTime jodaLocalDateTime) {
            JodaTimeUtil.serializeLocalDateTime(jodaLocalDateTime, gen, arg2);
            return;
        } else if (v instanceof org.joda.time.DateTime jodaDateTime) {
            JodaTimeUtil.serializeDateTime(jodaDateTime, gen, arg2);
            return;
        }
        throw new UnsupportedOperationException(generateErrorMessageAndLogIt(v));
    }

    public static <T> T deserialize(JsonParser jsonparser, DeserializationContext context, @Nullable Class<T> type)
            throws IOException {
        if (type != null) {
            if (type == LocalDateTime.class) {
                return type.cast(JavaTimeUtil.deserializeLocalDateTime(jsonparser, context));
            } else if (type == ZonedDateTime.class) {
                return type.cast(JavaTimeUtil.deserializeZonedDateTime(jsonparser, context));
            } else if (type == org.joda.time.LocalDateTime.class) {
                return type.cast(JodaTimeUtil.deserializeLocalDateTime(jsonparser, context));
            } else if (type == org.joda.time.DateTime.class) {
                return type.cast(JodaTimeUtil.deserializeDateTime(jsonparser, context));
            }
        }
        throw new UnsupportedOperationException(generateErrorMessageAndLogIt(type));
    }

    private static <T> String generateErrorMessageAndLogIt(T v) {
        return generateErrorMessageAndLogIt(v != null ? v.getClass().getName() : null);
    }

    private static <T> String generateErrorMessageAndLogIt(@Nullable Class<T> type) {
        return generateErrorMessageAndLogIt(type != null ? type.getName() : null);
    }

    private String generateErrorMessageAndLogIt(@Nullable String addOn) {
        String errorMessage = UNSUPPORTED_TYPE + addOn;
        log.error(errorMessage);
        return errorMessage;
    }
}
