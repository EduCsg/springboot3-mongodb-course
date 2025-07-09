package com.du.mongoDbCourse.resource.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

    public static String decodeParam(String text) {
        try {
            return URLDecoder.decode(text, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }

    public static Date convertDate(String strDate, Date defaultValue) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            return defaultValue;
        }

    }

}
