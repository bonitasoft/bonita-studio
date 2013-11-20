/*
 * Created on Mar 19, 2007
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
 * Copyright @2007-2011 the original author or authors.
 */
package org.fest.assertions;

import static java.lang.Boolean.valueOf;

/**
 * Assertions for {@code Boolean}s and {@code boolean}s.
 * <p>
 * To create a new instance of this class invoke either <code>{@link Assertions#assertThat(Boolean)}</code> or
 * <code>{@link Assertions#assertThat(boolean)}</code>.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 */
public class BooleanAssert extends GenericAssert<BooleanAssert, Boolean> {

  /**
   * Creates a new <code>{@link BooleanAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected BooleanAssert(boolean actual) {
    super(BooleanAssert.class, actual);
  }

  /**
   * Creates a new <code>{@link BooleanAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected BooleanAssert(Boolean actual) {
    super(BooleanAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code Boolean} value is {@code true}.
   * @throws AssertionError if the actual {@code Boolean} value is {@code false}.
   */
  public void isTrue() {
    isEqualTo(true);
  }

  /**
   * Verifies that the actual {@code Boolean} value is {@code false}.
   * @throws AssertionError if the actual {@code Boolean} value is {@code true}.
   */
  public void isFalse() {
    isEqualTo(false);
  }

  /**
   * Verifies that the actual {@code Boolean} is equal to the given one.
   * @param expected the given {@code boolean} to compare the actual {@code Boolean} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Boolean} is not equal to the given one.
   */
  public BooleanAssert isEqualTo(boolean expected) {
    return isEqualTo(valueOf(expected));
  }

  /**
   * Verifies that the actual {@code Boolean} is not equal to the given one.
   * @param other the given {@code boolean} to compare the actual {@code Boolean} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Boolean} is equal to the given one.
   */
  public BooleanAssert isNotEqualTo(boolean other) {
    return isNotEqualTo(valueOf(other));
  }
}
