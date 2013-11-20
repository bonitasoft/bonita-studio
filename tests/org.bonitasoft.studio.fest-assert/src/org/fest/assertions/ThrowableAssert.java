/*
 * Created on Dec 23, 2007
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

import static org.fest.assertions.Fail.failIfNotEqual;
import static org.fest.assertions.Formatting.format;

import org.fest.util.VisibleForTesting;

/**
 * Assertions for <code>{@link Throwable}</code>.
 * <p>
 * To create a new instance of this class invoke <code>{@link Assertions#assertThat(Throwable)}</code>.
 * </p>
 *
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ThrowableAssert extends GenericAssert<ThrowableAssert, Throwable> {

  // TODO remove dependency on ObjectAssert.
  @VisibleForTesting final ObjectAssert objectAssert;

  /**
   * Creates a new <code>ThrowableAssert</code>.
   * @param actual the target to verify.
   */
  protected ThrowableAssert(Throwable actual) {
    super(ThrowableAssert.class, actual);
    objectAssert = new ObjectAssert(actual);
  }

  /**
   * Verifies that the actual {@code Throwable} is an instance of the given type.
   * @param type the type to check the actual {@code Throwable} against.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Throwable} is {@code null}.
   * @throws AssertionError if the actual {@code Throwable} is not an instance of the given type.
   * @throws NullPointerException if the given type is {@code null}.
   */
  public ThrowableAssert isInstanceOf(Class<? extends Throwable> type) {
    objectAssert.isInstanceOf(type);
    return this;
  }

  /**
   * Verifies that the actual {@code Throwable} is an instance of the given type. In order for the assertion to
   * pass, the type of the actual {@code Throwable} has to be exactly the same as the given type.
   * @param type the type to check the actual {@code Throwable} against.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Throwable} is {@code null}.
   * @throws AssertionError if the actual {@code Throwable} is not an instance of the given type.
   * @throws NullPointerException if the given type is {@code null}.
   */
  public ThrowableAssert isExactlyInstanceOf(Class<?> type) {
    isNotNull();
    objectAssert.validateNotNull(type);
    Class<?> current = actual.getClass();
    if (type.equals(current)) return this;
    failIfCustomMessageIsSet();
    throw failure(format("expected exactly the same type:<%s> but was:<%s>", type, current));
  }

  /**
   * Verifies that the message of the actual {@code Throwable} is equal to the given one.
   * @param message the expected message.
   * @return this assertion error.
   * @throws AssertionError if the actual {@code Throwable} is {@code null}.
   * @throws AssertionError if the message of the actual {@code Throwable} is not equal to the given one.
   */
  public ThrowableAssert hasMessage(String message) {
    isNotNull();
    failIfNotEqual(customErrorMessage(), rawDescription(), actual.getMessage(), message);
    return this;
  }

  /**
   * Verifies that the actual {@code Throwable} does not have a cause.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Throwable} is {@code null}.
   * @throws AssertionError if the actual {@code Throwable} has a cause.
   */
  public ThrowableAssert hasNoCause() {
    isNotNull();
    Throwable actualCause = actual.getCause();
    if (actualCause == null) return this;
    failIfCustomMessageIsSet();
    throw failure(format("expected exception without cause, but cause was:<%s>", actualCause.getClass()));
  }

  /** {@inheritDoc} */
  @Override public ThrowableAssert as(String description) {
    objectAssert.as(description);
    return super.as(description);
  }

  /** {@inheritDoc} */
  @Override public ThrowableAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override public ThrowableAssert as(Description description) {
    objectAssert.as(description);
    return super.as(description);
  }

  /** {@inheritDoc} */
  @Override public ThrowableAssert describedAs(Description description) {
    return as(description);
  }

  /** {@inheritDoc} */
  @Override public ThrowableAssert overridingErrorMessage(String message) {
    objectAssert.overridingErrorMessage(message);
    return super.overridingErrorMessage(message);
  }
}
