/*
 * Created on Jan 22, 2011
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2011 the original author or authors.
 */
package org.fest.util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Tomasz Nurkiewicz
 * @author Joel Costigliola
 */
public class Dates {

  /*
   * ISO 8601 date-time format, example: "2003-04-01T13:01:02"
   */
  private static final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

  /**
   * Formats the given date using the ISO 8601 date-time format.
   * @param date the date to format.
   * @return the formatted date.
   */
	public static String format(Date date) {
		return new SimpleDateFormat(ISO_DATE_TIME_FORMAT).format(date);
	}

  /**
   * Formats the date of the given calendar using the ISO 8601 date-time format.
   * @param calendar the calendar to format.
   * @return the formatted calendar.
   */
	public static String format(Calendar calendar) {
		return format(calendar.getTime());
	}
}
