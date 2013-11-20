/*
 * Created on Jul 1, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Collections.*;
import static org.fest.assertions.Formatting.format;
import static org.fest.util.Collections.*;

import java.util.*;

/**
 * Template for assertions for groups of items (e.g. collections or arrays.)
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> the type the "actual" value.
 *
 * @author Yvonne Wang
 *
 * @since 1.3
 */
public abstract class ItemGroupAssert<S, A> extends GroupAssert<S, A> {

  /**
   * Creates a new </code>{@link ItemGroupAssert}</code>.
   * @param selfType the "self type."
   * @param actual the actual group.
   */
  public ItemGroupAssert(Class<S> selfType, A actual) {
    super(selfType, actual);
  }

  /**
   * Verifies that the actual actual group of objects contains the given objects, in any order.
   * @param objects the objects to look for.
   * @throws AssertionError if the actual actual group of objects is {@code null}.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws AssertionError if the actual actual group of objects does not contain the given objects.
   */
  protected final void assertContains(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    Collection<Object> notFound = notFoundInActual(objects);
    if (notFound.isEmpty()) return;
    throw failureIfExpectedElementsNotFound(notFound);
  }

  private Collection<Object> notFoundInActual(Object... objects) {
    return notFound(actualAsSet(), objects);
  }

  /**
   * Verifies that the actual group of objects contains the given objects <strong>only</strong>, in any order.
   * @param objects the objects to look for.
   * @throws AssertionError if the actual group of objects is {@code null}.
   * @throws NullPointerException if the given group of objects is {@code null}.
   * @throws AssertionError if the actual group of objects does not contain the given objects, or if the actual
   * group of objects contains elements other than the ones specified.
   */
  protected final void assertContainsOnly(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    Set<Object> copy = actualAsSet();
    List<Object> notFound = notFoundInCopy(copy, set(objects));
    if (!notFound.isEmpty()) throw failureIfExpectedElementsNotFound(notFound);
    if (copy.isEmpty()) return;
    throw failureIfUnexpectedElementsFound(copy);
  }

  /**
   * Returns the actual value as a {@code Set}.
   * @return the actual value as a {@code Set}.
   */
  protected abstract Set<Object> actualAsSet();

  private List<Object> notFoundInCopy(Set<Object> copy, Set<Object> objects) {
    List<Object> notFound = new ArrayList<Object>();
    for (Object o : objects) {
      if (!copy.contains(o)) {
        notFound.add(o);
        continue;
      }
      copy.remove(o);
    }
    return notFound;
  }

  private AssertionError failureIfExpectedElementsNotFound(Collection<Object> notFound) {
    failIfCustomMessageIsSet();
    return failure(format("<%s> does not contain element(s):<%s>", actual, notFound));
  }

  private AssertionError failureIfUnexpectedElementsFound(Collection<Object> unexpected) {
    failIfCustomMessageIsSet();
    return failure(format("unexpected element(s):<%s> in <%s>", unexpected, actual));
  }

  /**
   * Verifies that the actual group of objects does not contain the given objects.
   * @param objects the objects that the group of objects should exclude.
   * @throws AssertionError if the actual group of objects is {@code null}.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws AssertionError if the actual group of objects contains any of the given objects.
   */
  protected final void assertExcludes(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    Collection<Object> found = found(actualAsSet(), objects);
    if (found.isEmpty()) return;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> does not exclude element(s):<%s>", actual, found));
  }

  /**
   * Validates that the given array of objects is not {@code null}.
   * @param objects the array of objects to verify.
   * @throws NullPointerException if the given array of objects is {@code null}.
   */
  protected final void validateIsNotNull(Object[] objects) {
    if (objects == null)
      throw new NullPointerException(formattedErrorMessage("The given array should not be null"));
  }

  /**
   * Verifies that the actual group of objects does not have duplicates.
   * @throws AssertionError if the actual group of objects is {@code null}.
   * @throws AssertionError if the actual group of objects has duplicates.
   */
  protected final void assertDoesNotHaveDuplicates() {
    isNotNull();
    Collection<?> duplicates = duplicatesFrom(actualAsList());
    if (duplicates.isEmpty()) return;
    failIfCustomMessageIsSet();
    throw failure(format("<%s> contains duplicate(s):<%s>", actual, duplicates));
  }

  /**
   * Returns the actual value as a {@code List}.
   * @return the actual value as a {@code List}.
   */
  protected abstract List<Object> actualAsList();

}