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

import static java.lang.Long.valueOf;
import static org.fest.assertions.ErrorMessages.*;

/**
 * Assertions for {@code Long}s and {@code long}s.
 * <p>
 * To create a new instance of this class invoke either <code>{@link Assertions#assertThat(Long)}</code> or
 * <code>{@link Assertions#assertThat(long)}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class LongAssert extends GenericAssert<LongAssert, Long> implements NumberAssert {

  private static final long ZERO = 0L;

  /**
   * Creates a new <code>{@link LongAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected LongAssert(long actual) {
    super(LongAssert.class, actual);
  }

  /**
   * Creates a new <code>{@link LongAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected LongAssert(Long actual) {
    super(LongAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code Long} is equal to the given one.
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not equal to the given one.
   */
  public LongAssert isEqualTo(long expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Long} is not equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is equal to the given one.
   */
  public LongAssert isNotEqualTo(long other) {
    return isNotEqualTo(valueOf(other));
  }

  /**
   * Verifies that the actual {@code Long} is greater than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not greater than the given one.
   */
  public LongAssert isGreaterThan(long other) {
    if (actual > other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Long} is less than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not less than the given one.
   */
  public LongAssert isLessThan(long other) {
    if (actual < other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Long} is greater or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not greater than or equal to the given one.
   */
  public LongAssert isGreaterThanOrEqualTo(long other) {
    if (actual >= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Long} is less or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not less than or equal to the given one.
   */
  public LongAssert isLessThanOrEqualTo(long other) {
    if (actual <= other) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Long} is equal to zero.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not equal to zero.
   */
  public LongAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual {@code Long} is positive.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not positive.
   */
  public LongAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Long} is negative.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Long} is not negative.
   */
  public LongAssert isNegative() {
    return isLessThan(ZERO);
  }
}
