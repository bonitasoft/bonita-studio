/*
 * Created on Dec 27, 2006
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2006-2011 the original author or authors.
 */
package org.fest.assertions;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;

/**
 * Assertions for <code>{@link BigDecimal}</code>s.
 * <p>
 * To create a new instance of this class invoke <code>{@link Assertions#assertThat(BigDecimal)}</code>.
 * </p>
 *
 * @author David DIDIER
 * @author Ted M. Young
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BigDecimalAssert extends ComparableAssert<BigDecimalAssert, BigDecimal> implements NumberAssert {

  /**
   * Creates a new </code>{@link BigDecimalAssert}</code>.
   * @param actual the target to verify.
   */
  protected BigDecimalAssert(BigDecimal actual) {
    super(BigDecimalAssert.class, actual);
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is positive.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is {@code null}.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not positive.
   */
  public BigDecimalAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is negative.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is {@code null}.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not negative.
   */
  public BigDecimalAssert isNegative() {
    return isLessThan(ZERO);
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is equal to zero, regardless of precision.
   * Essentially, this is the same as
   * <code>{@link #isEqualByComparingTo(BigDecimal) isEqualByComparingTo}</code>(<code>{@link BigDecimal#ZERO BigDecimal.ZERO}</code>).
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is {@code null}.
   * @throws AssertionError if the actual <code>BigDecimal</code> value is not equal to zero.
   */
  public BigDecimalAssert isZero() {
    return isEqualByComparingTo(ZERO);
  }

  /**
   * Verifies that the actual <code>{@link BigDecimal}</code> is not equal to zero, regardless of precision.
   * Essentially, this is the same as
   * <code>{@link #isEqualByComparingTo(BigDecimal) isNotEqualByComparingTo}</code>(<code>{@link BigDecimal#ZERO BigDecimal.ZERO}</code>).
   * @return this assertion object.
   * @throws AssertionError if the actual <code>BigDecimal</code> is {@code null}.
   * @throws AssertionError if the actual <code>BigDecimal</code> is equal to zero.
   */
  public BigDecimalAssert isNotZero() {
    return isNotEqualByComparingTo(ZERO);
  }
}
