/*******************************************************************************
 * Copyright (C) 2017 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.util;

import java.util.List;

public class StringIncrementer {

    public static String getNextIncrement(String defaultString, List<String> existingStringList) {
        return getNextIncrement(defaultString, existingStringList, true);
    }

    public static String getNextIncrementIgnoringCase(String defaultString, List<String> existingStringList) {
        return getNextIncrement(defaultString, existingStringList, false);
    }

    private static String getNextIncrement(String defaultString, List<String> existingStringList, boolean caseSensitive) {
        int id = existingStringList.stream()
                .map(existingToken -> caseSensitive ? existingToken : existingToken.toLowerCase())
                .filter(fileName -> caseSensitive ? fileName.startsWith(defaultString)
                        : fileName.toLowerCase().startsWith(defaultString.toLowerCase()))
                .map(fileName -> getEndString(fileName, defaultString))
                .filter(StringIncrementer::isInt)
                .mapToInt(Integer::parseInt)
                .reduce(-1, Integer::max);
        return id < 0 ? defaultString : defaultString + (id + 1);
    }

    private static String getEndString(String string, String defaultString) {
        String subString = string.substring(defaultString.length());
        return subString.isEmpty() ? "0" : subString;
    }

    private static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
