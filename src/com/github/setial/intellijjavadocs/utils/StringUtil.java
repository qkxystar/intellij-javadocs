package com.github.setial.intellijjavadocs.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by bxy on 17-9-10.
 */
public class StringUtil {

    private static final String LOWER_CAMEL_FORMAT = "^[a-z][\\d\\w]+";

    private static final String CAMEL_FORMAT = "[\\d\\w]+";

    public static Boolean isLowerCamelFormat(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return str.matches(LOWER_CAMEL_FORMAT);
    }

    public static Boolean isCamelFormat(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return str.matches(CAMEL_FORMAT);
    }

    public static String[] splitByCharacterTypeUnderscore(String str) {
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }

        return str.split("_");
    }
}
