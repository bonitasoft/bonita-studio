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

import static org.fest.assertions.ErrorMessages.*;
import static org.fest.assertions.Fail.*;
import static org.fest.assertions.Formatting.format;
import static org.fest.util.Collections.list;
import static org.fest.util.Objects.areEqual;

import java.util.Collection;

/**
 * Template for assertions.
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> the type the "actual" value.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class GenericAssert<S, A> extends Assert {

  protected final A actual;
  protected final S myself;

  /**
   * Creates a new <code>{@link GenericAssert}</code>.
   * @param selfType the "self type."
   * @param actual the actual value to verify.
   */
  protected GenericAssert(Class<S> selfType, A actual) {
    this.actual = actual;
    myself = selfType.cast(this);
  }

  /**
   * Asserts that the actual value (specified in the constructor of this class) is {@code null}.
   * @throws AssertionError if the actual value is not {@code null}.
   */
  public final void isNull() {
    failIfNotNull(customErrorMessage(), rawDescription(), actual);
  }

  /**
   * Verifies that the actual value satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value does not satisfy the given condition.
   * @see #is(Condition)
   */
  public final S satisfies(Condition<A> condition) {
    if (matches(condition)) return myself;
    failIfCustomMessageIsSet();
    throw failure(errorMessageIfConditionNotSatisfied(condition));
  }

  private String errorMessageIfConditionNotSatisfied(Condition<A> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should satisfy condition", actual));
  }

  /**
   * Verifies that the actual value does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value satisfies the given condition.
   * @see #isNot(Condition)
   */
  public final S doesNotSatisfy(Condition<A> condition) {
    if (!matches(condition)) return myself;
    failIfCustomMessageIsSet();
    throw failure(errorMessageIfConditionSatisfied(condition));
  }

  private String errorMessageIfConditionSatisfied(Condition<A> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should not satisfy condition", actual));
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value does not satisfy the given condition.
   * @since 1.2
   */
  public final S is(Condition<A> condition) {
    if (matches(condition)) return myself;
    failIfCustomMessageIsSet();
    throw failure(errorMessageIfIsNot(condition));
  }

  private String errorMessageIfIsNot(Condition<A> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should be", actual));
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is {@code null}.
   * @throws AssertionError if the actual value satisfies the given condition.
   * @since 1.2
   */
  public final S isNot(Condition<A> condition) {
    if (!matches(condition)) return myself;
    failIfCustomMessageIsSet();
    throw failure(errorMessageIfIs(condition));
  }

  private boolean matches(Condition<A> condition) {
    validateIsNotNull(condition);
    return condition.matches(actual);
  }

  private void validateIsNotNull(Condition<A> condition) {
    if (condition == null) throw new NullPointerException("Condition to check should not be null");
  }

  private String errorMessageIfIs(Condition<A> condition) {
    return condition.addDescriptionTo(format("actual value:<%s> should not be", actual));
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>as</strong>(&quot;name&quot;).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public S as(String description) {
    description(description);
    return myself;
  }

  /**
   * Alias for <code>{@link #as(String)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>describedAs</strong>(&quot;name&quot;).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public S describedAs(String description) {
    return as(description);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>as</strong>(new BasicDescription(&quot;name&quot;)).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public S as(Description description) {
    description(description);
    return myself;
  }

  /**
   * Alias for <code>{@link #as(Description)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>describedAs</strong>(new BasicDescription(&quot;name&quot;)).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public S describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual value is equal to the given one.
   * @param expected the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not equal to the given one.
   */
  public S isEqualTo(A expected) {
    failIfNotEqual(customErrorMessage(), rawDescription(), actual, expected);
    return myself;
  }

  /**
   * Verifies that the actual value is not equal to the given one.
   * @param other the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is equal to the given one.
   */
  public S isNotEqualTo(A other) {
    failIfEqual(customErrorMessage(), rawDescription(), actual, other);
    return myself;
  }

  /**
   * Verifies that the actual value is not {@code null}.
   * @return this assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   */
  public final S isNotNull() {
    failIfActualIsNull(customErrorMessage(), rawDescription(), actual);
    return myself;
  }

  /**
   * Verifies that the actual value is the same as the given one.
   * @param expected the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not the same as the given one.
   */
  public final S isSameAs(A expected) {
    failIfNotSame(customErrorMessage(), rawDescription(), actual, expected);
    return myself;
  }

  /**
   * Verifies that the actual value is not the same as the given one.
   * @param other the given value to compare the actual value to.
   * @return this assertion object.
   * @throws AssertionError if the actual value is the same as the given one.
   */
  public final S isNotSameAs(A other) {
    failIfSame(customErrorMessage(), rawDescription(), actual, other);
    return myself;
  }

  /**
   * Verifies that the actual value is in the given values.
   * @param values the given values to search the actual value in.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not in the given values.
   * @throws NullPointerException if the given parameter is null.
   */
  public final S isIn(Object... values) {
    return isIn(list(values));
  }

  /**
   * Verifies that the actual value is in the given collection.
   * @param values the given collection to search the actual value in. must not be null.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not in the given collection.
   * @throws NullPointerException if the given collection is null.
   */
  public final S isIn(Collection<?> values) {
    if (values == null) throw new NullPointerException(formattedErrorMessage("expecting values parameter not to be null"));
    if (isActualIn(values)) return myself;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotIn(customErrorMessage(), actual, values));
  }

  /**
   * Verifies that the actual value is in the given values.
   * @param values the given values to search the actual value in.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not in the given values.
   * @throws NullPointerException if the given parameter is null.
   */
  public final S isNotIn(Object... values) {
    return isNotIn(list(values));
  }

  /**
   * Verifies that the actual value is in the given collection.
   * @param values the given collection to search the actual value in. must not be null.
   * @return this assertion object.
   * @throws AssertionError if the actual value is not in the given collection.
   * @throws NullPointerException if the given collection is null.
   */
  public final S isNotIn(Collection<?> values) {
    if (values == null) throw new NullPointerException(formattedErrorMessage("expecting values parameter not to be null"));
    if (!isActualIn(values)) return myself;
    failIfCustomMessageIsSet();
    throw failure(unexpectedIn(customErrorMessage(), actual, values));
  }

  private boolean isActualIn(Collection<?> values) {
    if (values.isEmpty()) return false;
    for (Object value : values)
      if (areEqual(actual, value)) return true;
    return false;
  }

  /**
   * Replaces the default message displayed in case of a failure with the given one.
   * <p>
   * For example, the following assertion:
   * <pre>
   * assertThat("Hello").isEqualTo("Bye");
   * </pre>
   * will fail with the default message "<em>expected:<'[Bye]'> but was:<'[Hello]'></em>."
   * </p>
   * <p>
   * We can replace this message with our own:
   * <pre>
   * assertThat("Hello").overridingErrorMessage("'Hello' should be equal to 'Bye'").isEqualTo("Bye");
   * </pre>
   * in this case, the assertion will fail showing the message "<em>'Hello' should be equal to 'Bye'</em>".
   * </p>
   * @param message the given error message, which will replace the default one.
   * @return this assertion.
   * @since 1.2
   */
  public S overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return myself;
  }
}
