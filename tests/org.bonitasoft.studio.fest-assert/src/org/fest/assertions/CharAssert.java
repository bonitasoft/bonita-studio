/*
 * Created on Jun 18, 2007
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
 * Copyright @2007-2011 the original author or authors.
 */
package org.fest.assertions;

import static java.lang.Character.valueOf;
import static org.fest.assertions.ErrorMessages.*;

/**
 * Assertions for {@code Character}s and {@code char}s.
 * <p>
 * To create a new instance of this class invoke either <code>{@link Assertions#assertThat(Character)}</code> or
 * <code>{@link Assertions#assertThat(char)}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class CharAssert extends GenericAssert<CharAssert, Character> {

  /**
   * Creates a new <code>{@link CharAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected CharAssert(char actual) {
    super(CharAssert.class, actual);
  }

  /**
   * Creates a new <code>{@link CharAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected CharAssert(Character actual) {
    super(CharAssert.class, actual);
  }

  /**
   * Verifies that the {@code Character} value is equal to the given one.
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not equal to the given one.
   */
  public CharAssert isEqualTo(char expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the {@code Character} value is not equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is equal to the given one.
   */
  public CharAssert isNotEqualTo(char other) {
    return isNotEqualTo(valueOf(other));
  }

  /**
   * Verifies that the {@code Character} value is greater than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not greater than the given one.
   */
  public CharAssert isGreaterThan(char other) {
    if (actual > other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the {@code Character} value is less than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not less than the given one.
   */
  public CharAssert isLessThan(char other) {
    if (actual < other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the {@code Character} value is greater or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not greater than or equal to the given one.
   */
  public CharAssert isGreaterThanOrEqualTo(char other) {
    if (actual >= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the {@code Character} value is less or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not less than or equal to the given one.
   */
  public CharAssert isLessThanOrEqualTo(char other) {
    if (actual <= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the {@code Character} value is an upper-case value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not an upper-case value.
   */
  public CharAssert isUpperCase() {
    if (Character.isUpperCase(actual)) return this;
    failIfCustomMessageIsSet();
    throw failure(String.format("<%s> should be an upper-case character", actual));
  }

  /**
   * Verifies that the {@code Character} value is an lower-case value.
   * @return this assertion object.
   * @throws AssertionError if the {@code Character} value is not an lower-case value.
   */
  public CharAssert isLowerCase() {
    if (Character.isLowerCase(actual)) return this;
    failIfCustomMessageIsSet();
    throw failure(String.format("<%s> should be a lower-case character", actual));
  }
}
