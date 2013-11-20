/*
 * Created on May 21, 2007
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

import static org.fest.assertions.Formatting.format;

/**
 * Template for assertions for classes representing groups of values.
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> the type the "actual" value.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class GroupAssert<S, A> extends GenericAssert<S, A> {

  /**
   * Creates a new <code>{@link GroupAssert}</code>.
   * @param selfType the "self type."
   * @param actual the target to verify.
   */
  protected GroupAssert(Class<S> selfType, A actual) {
    super(selfType, actual);
  }

  /**
   * Verifies that the actual group of values is {@code null} or empty.
   * @throws AssertionError if the actual group of values is not {@code null} or not empty.
   */
  public final void isNullOrEmpty() {
    if (actual == null || !hasElements()) return;
    failIfCustomMessageIsSet();
    fail(format("expecting null or empty, but was:<%s>", actual));
  }

  /**
   * Verifies that the actual group of values is empty.
   * @throws AssertionError if the actual group of values is {@code null} or not empty.
   */
  public final void isEmpty() {
    isNotNull();
    if (!hasElements()) return;
    failIfCustomMessageIsSet();
    fail(format("expecting empty, but was:<%s>", actual));
  }

  private boolean hasElements() {
    return actualGroupSize() > 0;
  }

  /**
   * Verifies that the actual group contains at least on value.
   * @return this assertion object.
   * @throws AssertionError if the actual group is {@code null} or empty.
   */
  public final S isNotEmpty() {
    isNotNull();
    if (hasElements()) return myself;
    failIfCustomMessageIsSet();
    throw failure("expecting non-empty, but it was empty");
  }

  /**
   * Verifies that the number of values in the actual group is equal to the given one.
   * @param expected the expected number of values in the actual group.
   * @return this assertion object.
   * @throws AssertionError if the number of values of the actual group is not equal to the given one.
   */
  public final S hasSize(int expected) {
    isNotNull();
    int size = actualGroupSize();
    if (size == expected) return myself;
    failIfCustomMessageIsSet();
    throw failure(format("expected size:<%s> but was:<%s> for <%s>", expected, size, actual));
  }

  /**
   * Returns the size of the actual group of values (array, collection, etc.)
   * @return the size of the actual group of values.
   */
  protected abstract int actualGroupSize();
}
