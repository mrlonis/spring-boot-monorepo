package com.mrlonis.xml.shared.util;

import com.mrlonis.xml.shared.time.JavaTimeUtil;
import com.mrlonis.xml.shared.time.JodaTimeUtil;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;

@UtilityClass
public class Constants {
    public static final String SLASH = "/";
    public static final String XML_PATH = SLASH + "xml";
    public static final String DESERIALIZE_PATH = SLASH + "deserialize";

    public static final long ID = 1;
    public static final String NAME = "name";
    public static final String AUTHOR = "author";
    public static final String DATE_STRING = "2024-07-05T14:06:07.617";
    public static final String DATE_STRING_ZONED = String.format("%sZ", DATE_STRING);

    public static final DateTime JODA_DATE_TIME = JodaTimeUtil.unmarshalDateTime(DATE_STRING_ZONED);
    public static final org.joda.time.LocalDateTime JODA_LOCAL_DATE_TIME =
            JodaTimeUtil.unmarshalLocalDateTime(DATE_STRING_ZONED);
    public static final LocalDateTime JAVA_TIME_LOCAL_DATE_TIME =
            JavaTimeUtil.unmarshalLocalDateTime(DATE_STRING_ZONED);
    public static final ZonedDateTime JAVA_TIME_ZONED_DATE_TIME =
            JavaTimeUtil.unmarshalZonedDateTime(DATE_STRING_ZONED);

    public static final List<String> TAGS = Arrays.asList("tag1", "tag2", "tag3");
}
