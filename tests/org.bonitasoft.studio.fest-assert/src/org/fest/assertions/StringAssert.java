/*
 * Created on Dec 26, 2006
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
 * Copyright @2006-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Formatting.format;

/**
 * Assertions for {@code String}s.
 * <p>
 * To create a new instance of this class invoke <code>{@link Assertions#assertThat(String)}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 */
public class StringAssert extends GroupAssert<StringAssert, String> {

  /**
   * Creates a new </code>{@link StringAssert}</code>.
   * @param actual the target to verify.
   */
  protected StringAssert(String actual) {
    super(StringAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code String} is equal to the given one ignoring case.
   * @param expected the given {@code String} to compare the actual {@code String} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code String} is {@code null}.
   * @throws AssertionError if the actual {@code String} is not equal to the given one ignoring case.
   */
  public StringAssert isEqualToIgnoringCase(String expected) {
    if (actual == null && expected == null) return this;
    isNotNull();
    if (actual.equalsIgnoreCase(expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> should be equal to :<%s> ignoring case", actual, expected));
  }

  /**
   * Returns the number of elements in the actual {@code String}.
   * @return the number of elements in the actual {@code String}.
   */
  @Override protected int actualGroupSize() {
    isNotNull();
    return actual.length();
  }

  /**
   * Verifies that the actual {@code String} contains the given one.
   * @param expected the given {@code String} expected to be contained in the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code String} is {@code null}.
   * @throws AssertionError if the actual {@code String} does not contain the given one.
   */
  public StringAssert contains(String expected) {
    isNotNull();
    if (actual.indexOf(expected) != -1) return this;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> should contain the String:<%s>", actual, expected));
  }

  /**
   * Verifies that the actual {@code String} ends with the given one.
   * @param expected the given {@code String} expected to be at the end of the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code String} is {@code null}.
   * @throws AssertionError if the actual {@code String} does not end with the given one.
   */
  public StringAssert endsWith(String expected) {
    isNotNull();
    if (actual.endsWith(expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> should end with:<%s>", actual, expected));
  }

  /**
   * Verifies that the actual {@code String} starts with the given one.
   * @param expected the given {@code String} expected to be at the beginning of the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code String} is {@code null}.
   * @throws AssertionError if the actual {@code String} does not start with the given one.
   */
  public StringAssert startsWith(String expected) {
    isNotNull();
    if (actual.startsWith(expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> should start with:<%s>", actual, expected));
  }

  /**
   * Verifies that the actual {@code String} does not contains the given one.
   * @param s the given {@code String} expected not to be contained in the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code String} is {@code null}.
   * @throws AssertionError if the actual {@code String} does contain the given one.
   */
  public StringAssert excludes(String s) {
    isNotNull();
    if (actual.indexOf(s) == -1) return this;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> should not contain the String:<%s>", actual, s));
  }

  /**
   * Verifies that the actual {@code String} matches the given one.
   * @param regex the given regular expression expected to be matched by the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code String} is {@code null}.
   * @throws AssertionError if the actual {@code String} does not match the given regular expression.
   */
  public StringAssert matches(String regex) {
    isNotNull();
    if (actual.matches(regex)) return this;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> should match the regular expression:<%s>", actual, regex));
  }

  /**
   * Verifies that the actual {@code String} does not match the given one.
   * @param regex the given regular expression expected not to be matched by the actual one.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code String} is {@code null}.
   * @throws AssertionError if the actual {@code String} matches the given regular expression.
   */
  public StringAssert doesNotMatch(String regex) {
    isNotNull();
    if (!actual.matches(regex)) return this;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> should not match the regular expression:<%s>", actual, regex));
  }

  /**
   * Verifies that the actual {@code String} contains the given text regardless of the case.
   * @param text the given text.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code String} is {@code null}.
   * @throws AssertionError if the actual {@code String} does not contain the given text.
   * @throws NullPointerException if the given {@code String} is {@code null}.
   * @since 1.3
   */
  public StringAssert containsIgnoringCase(String text) {
    validateNotNull(text);
    isNotNull();
    if (actual.toLowerCase().contains(text.toLowerCase())) return this;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> does not contain <%s>", actual, text));
  }

  /**
   * Verifies that the actual {@code String} does not contain the given text.
   * @param text the given text.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code String} is {@code null}.
   * @throws AssertionError if the actual {@code String} contains the given text.
   * @throws NullPointerException if the given {@code String} is {@code null}.
   * @since 1.3
   */
  public StringAssert doesNotContain(String text) {
    validateNotNull(text);
    isNotNull();
    if (!actual.contains(text)) return this;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> should not contain <%s>", actual, text));
  }

  private static void validateNotNull(String text) {
    if (text == null) throw new NullPointerException("The given String should not be null");
  }
}
