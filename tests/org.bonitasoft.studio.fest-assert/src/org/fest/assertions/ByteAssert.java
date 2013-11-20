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

import static java.lang.Byte.valueOf;
import static org.fest.assertions.ErrorMessages.*;

import org.fest.util.VisibleForTesting;

/**
 * Assertions for {@code Byte}s and {@code byte}s.
 * <p>
 * To create a new instance of this class invoke either <code>{@link Assertions#assertThat(Byte)}</code> or
 * <code>{@link Assertions#assertThat(byte)}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public class ByteAssert extends GenericAssert<ByteAssert, Byte> implements NumberAssert {

  private static final byte ZERO = (byte) 0;

  @VisibleForTesting
  ByteAssert(int actual) {
    this((byte)actual);
  }

  /**
   * Creates a new <code>{@link ByteAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected ByteAssert(byte actual) {
    super(ByteAssert.class, actual);
  }

  /**
   * Creates a new <code>{@link ByteAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected ByteAssert(Byte actual) {
    super(ByteAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code Byte} value is equal to the given one.
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not equal to the given one.
   */
  public ByteAssert isEqualTo(byte expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Byte} value is not equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is equal to the given one.
   */
  public ByteAssert isNotEqualTo(byte other) {
    return isNotEqualTo(valueOf(other));
  }

  /**
   * Verifies that the actual {@code Byte} value is greater than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not greater than the given one.
   */
  public ByteAssert isGreaterThan(byte other) {
    if (actual > other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is less than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not less than the given one.
   */
  public ByteAssert isLessThan(byte other) {
    if (actual < other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is greater or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not greater than or equal to the given one.
   */
  public ByteAssert isGreaterThanOrEqualTo(byte other) {
    if (actual >= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is less or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not less than or equal to the given one.
   */
  public ByteAssert isLessThanOrEqualTo(byte other) {
    if (actual <= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Byte} value is equal to zero.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not equal to zero.
   */
  public ByteAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual {@code Byte} value is positive.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not positive.
   */
  public ByteAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Byte} value is negative.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Byte} value is not negative.
   */
  public ByteAssert isNegative() {
    return isLessThan(ZERO);
  }
}
