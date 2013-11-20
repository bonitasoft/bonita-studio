/*
 * Created on Oct 7, 2009
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
 * Copyright @2009-2011-2010 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Arrays.isArray;
import static org.fest.util.Strings.quote;

import java.awt.Dimension;
import java.io.File;
import java.util.*;

/**
 * Obtains the {@code toString} representation of an object.
 *
 * @author Alex Ruiz
 * @author Joel Costigliola
 * @author Yvonne Wang
 */
public final class ToString {

  /**
   * Returns the {@code toString} representation of the given object. It may or not the object's own implementation of
   * {@code toString}.
   * @param o the given object.
   * @return the {@code toString} representation of the given object.
   */
  public static String toStringOf(Object o) {
    if (isArray(o)) return Arrays.format(o);
    if (o instanceof Calendar) return toStringOf((Calendar) o);
    if (o instanceof Class<?>) return toStringOf((Class<?>) o);
    if (o instanceof Collection<?>) return toStringOf((Collection<?>) o);
    if (o instanceof Date) return toStringOf((Date) o);
    if (o instanceof Dimension) return toStringOf((Dimension) o);
    if (o instanceof File) return toStringOf((File) o);
    if (o instanceof Map<?, ?>) return toStringOf((Map<?, ?>) o);
    if (o instanceof String) return quote((String) o);
    return o == null ? null : o.toString();
  }

  private static String toStringOf(Calendar c) {
	  return Dates.format(c);
  }

  private static String toStringOf(Class<?> c) {
    return c.getName();
  }

  private static String toStringOf(Collection<?> c) {
    return Collections.format(c);
  }

  private static String toStringOf(Date d) {
	  return Dates.format(d);
  }

  private static String toStringOf(Dimension d) {
    return String.format("(w=%s, h=%s)", d.width, d.height);
  }

  private static String toStringOf(File f) {
    return f.getAbsolutePath();
  }

  private static String toStringOf(Map<?, ?> m) {
    return Maps.format(m);
  }

  private ToString() {}
}
