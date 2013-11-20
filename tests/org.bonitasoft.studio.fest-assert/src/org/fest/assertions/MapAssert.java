/*
 * Created on Jan 23, 2008
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
 * Copyright @2008-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.util.Strings.quote;
import static org.fest.util.ToString.toStringOf;

import java.util.*;

/**
 * Assertions for <code>{@link Map}</code>s.
 * <p>
 * To create a new instance of this class invoke <code>{@link Assertions#assertThat(Map)}</code>.
 * </p>
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class MapAssert extends GroupAssert<MapAssert, Map<?, ?>> {

  private static final String ENTRY = "entry";
  private static final String ENTRIES= "entries";

  /**
   * Creates a new </code>{@link MapAssert}</code>.
   * @param actual the target to verify.
   */
  protected MapAssert(Map<?, ?> actual) {
    super(MapAssert.class, actual);
  }

  /**
   * Verifies that the actual <code>{@link Map}</code> contains the given entries.
   * <p>
   * Example:
   * <pre>
   * // static import org.fest.assertions.Assertions.*;
   * // static import org.fest.assertions.MapAssert.*;
   *
   * assertThat(myMap).{@link #includes(org.fest.assertions.MapAssert.Entry...) includes}({@link #entry(Object, Object) entry}(&quot;jedi&quot;, yoda), {@link #entry(Object, Object) entry}(&quot;sith&quot;, anakin));
   * </pre>
   * </p>
   * @param entries the given entries.
   * @return this assertion error.
   * @throws AssertionError if the actual map is {@code null}.
   * @throws AssertionError if the actual {@code Map} does not contain any of the given entries.
   * @throws NullPointerException if the given array of entries is {@code null}.
   * @throws NullPointerException if any of the entries in the given array is {@code null}.
   */
  public MapAssert includes(Entry...entries) {
    isNotNull();
    validate(ENTRIES, entries);
    List<Entry> notFound = new ArrayList<Entry>();
    for (Entry e : entries) if (!containsEntry(e)) notFound.add(e);
    if (!notFound.isEmpty()) failIfNotFound(entryOrEntries(notFound), notFound);
    return this;
  }

  /**
   * Verifies that the actual <code>{@link Map}</code> does not contain the given entries.
   * <p>
   * Example:
   * <pre>
   * // static import org.fest.assertions.Assertions.*;
   * // static import org.fest.assertions.MapAssert.*;
   *
   * assertThat(myMap).{@link #excludes(org.fest.assertions.MapAssert.Entry...) excludes}({@link #entry(Object, Object) entry}(&quot;jedi&quot;, yoda), {@link #entry(Object, Object) entry}(&quot;sith&quot;, anakin));
   * </pre>
   * </p>
   * @param entries the given entries.
   * @return this assertion error.
   * @throws AssertionError if the actual map is {@code null}.
   * @throws AssertionError if the actual {@code Map} contains any of the given entries.
   * @throws NullPointerException if the given array of entries is {@code null}.
   * @throws NullPointerException if any of the entries in the given array is {@code null}.
   */
  public MapAssert excludes(Entry...entries) {
    isNotNull();
    validate(ENTRIES, entries);
    List<Entry> found = new ArrayList<Entry>();
    for (Entry e : entries) if (containsEntry(e)) found.add(e);
    if (!found.isEmpty()) failIfFound(entryOrEntries(found), found);
    return this;
  }

  private boolean containsEntry(Entry e) {
    if (e == null)
      throw new NullPointerException(formattedErrorMessage("Entries to check should not contain null"));
    if (!actual.containsKey(e.key)) return false;
    return actual.get(e.key).equals(e.value);
  }

  private String entryOrEntries(List<Entry> found) {
    return found.size() == 1 ? ENTRY : ENTRIES;
  }

  /**
   * Creates a new map entry.
   * @param key the key of the entry.
   * @param value the value of the entry.
   * @return the created entry.
   * @see #includes(org.fest.assertions.MapAssert.Entry...)
   */
  public static Entry entry(Object key, Object value) {
    return new Entry(key, value);
  }

  /**
   * An entry in a <code>{@link Map}</code>.
   *
   * @author Yvonne Wang
   */
  public static class Entry {
    final Object key;
    final Object value;

    Entry(Object key, Object value) {
      this.key = key;
      this.value = value;
    }

    /** @see java.lang.Object#toString() */
    @Override public String toString() {
      return String.format("%s=%s", quote(key), quote(value));
    }
  }

  private void failIfNotFound(String description, Collection<?> notFound) {
    failIfCustomMessageIsSet();
    fail(String.format(
        "the map:<%s> does not contain the %s:<%s>", formattedActual(), description, toStringOf(notFound)));
  }

  private void validate(String description, Object[] objects) {
    if (objects == null)
      throw new NullPointerException(
          formattedErrorMessage(String.format("The given array of %s should not be null", description)));
  }

  private void failIfFound(String description, Collection<?> found) {
    failIfCustomMessageIsSet();
    fail(String.format("the map:<%s> contains the %s:<%s>", formattedActual(), description, toStringOf(found)));
  }

  private String formattedActual() {
    return toStringOf(actual);
  }

  /**
   * Returns the number of elements in the actual <code>{@link Map}</code>.
   * @return the number of elements in the actual <code>{@link Map}</code>.
   */
  @Override protected int actualGroupSize() {
    isNotNull();
    return actual.size();
  }
}