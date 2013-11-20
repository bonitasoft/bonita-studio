/*
 * Created on Feb 14, 2008
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
 * Copyright @2008-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayInspection.copy;
import static org.fest.assertions.ErrorMessages.*;

import java.util.Arrays;

/**
 * Assertions for {@code float} arrays.
 * <p>
 * To create a new instance of this class invoke <code>{@link Assertions#assertThat(float[])}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FloatArrayAssert extends ArrayAssert<FloatArrayAssert, float[]> {

  /**
   * Creates a new </code>{@link FloatArrayAssert}</code>.
   * @param actual the target to verify.
   */
  protected FloatArrayAssert(float... actual) {
    super(FloatArrayAssert.class, actual);
  }

  /**
   * Verifies that the actual {@code float} array contains the given values.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code float} array is {@code null}.
   * @throws NullPointerException if the given {@code float} array is {@code null}.
   * @throws AssertionError if the actual {@code float} array does not contain the given values.
   */
  public FloatArrayAssert contains(float...values) {
    assertContains(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code float} array contains the given values <strong>only</strong>.
   * @param values the values to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code float} array is {@code null}.
   * @throws NullPointerException if the given {@code float} array is {@code null}.
   * @throws AssertionError if the actual {@code float} array does not contain the given objects, or if the actual
   * {@code float} array contains elements other than the ones specified.
   */
  public FloatArrayAssert containsOnly(float...values) {
    assertContainsOnly(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code float} array does not contain the given values.
   * @param values the values the array should exclude.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code float} array is {@code null}.
   * @throws NullPointerException if the given {@code float} array is {@code null}.
   * @throws AssertionError if the actual {@code Object} array contains any of the given values.
   */
  public FloatArrayAssert excludes(float...values) {
    assertExcludes(copy(values));
    return this;
  }

  /**
   * Verifies that the actual {@code float} array is equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(float[], float[])}</code>.
   * @param expected the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code float} array is not equal to the given one.
   */
  @Override public FloatArrayAssert isEqualTo(float[] expected) {
    if (Arrays.equals(actual, expected)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual {@code float} array is not equal to the given array. Array equality is checked by
   * <code>{@link Arrays#equals(float[], float[])}</code>.
   * @param array the given array to compare the actual array to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code float} array is equal to the given one.
   */
  @Override public FloatArrayAssert isNotEqualTo(float[] array) {
    if (!Arrays.equals(actual, array)) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, array));
  }
}
