/*
 * Created on Mar 29, 2009
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
 * Copyright @2009-2011 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;
import static org.fest.assertions.Formatting.format;
import static org.fest.util.Collections.list;
import static org.fest.util.Objects.areEqual;

import java.util.*;

import org.fest.util.IntrospectionError;

/**
 * Assertions for <code>{@link List}</code>s.
 * <p>
 * To create a new instance of this class invoke <code>{@link Assertions#assertThat(List)}</code>.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 *
 * @since 1.1
 */
public class ListAssert extends ObjectGroupAssert<ListAssert, List<?>> {

  /**
   * Creates a new </code>{@link ListAssert}</code>.
   * @param actual the target to verify.
   */
  protected ListAssert(List<?> actual) {
    super(ListAssert.class, actual);
  }

  /**
   * Verifies that the actual <code>{@link List}</code> contains the given object at the given index.
   * @param o the object to look for.
   * @param index the index where the object should be stored in the actual {@code List}.
   * @return this assertion object.
   * @throws NullPointerException if the given <code>Index</code> is {@code null}.
   * @throws IndexOutOfBoundsException if the value of the given <code>Index</code> is negative, or equal to or greater
   * than the size of the actual {@code List}.
   * @throws AssertionError if the given {@code List} does not contain the given object at the given index.
   */
  public ListAssert contains(Object o, Index index) {
    if (index == null) throw new NullPointerException(formattedErrorMessage("The given index should not be null"));
    isNotNull().isNotEmpty();
    int indexValue = index.value();
    int listSize = actualGroupSize();
    if (indexValue < 0 || indexValue >= listSize) failIndexOutOfBounds(indexValue);
    Object actualElement = actual.get(indexValue);
    if (!areEqual(actualElement, o)) failElementNotFound(o, actualElement, indexValue);
    return this;
  }

  private void failElementNotFound(Object e, Object a, int index) {
    failIfCustomMessageIsSet();
    fail(format("expecting <%s> at index <%s> but found <%s>", e, index, a));
  }

  private void failIndexOutOfBounds(int index) {
    throw new IndexOutOfBoundsException(formattedErrorMessage(
        format("The index <%s> should be greater than or equal to zero and less than %s", index, actualGroupSize())));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> contains the given sequence of objects, without any other
   * objects between them.
   * @param sequence the sequence of objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code List} is {@code null}.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the actual {@code List} does not contain the given sequence of objects.
   */
  public ListAssert containsSequence(Object... sequence) {
    isNotNull();
    validateIsNotNull(sequence);
    int sequenceSize = sequence.length;
    if (sequenceSize == 0) return this;
    int indexOfFirst = actual.indexOf(sequence[0]);
    if (indexOfFirst == -1) failIfSequenceNotFound(sequence);
    int listSize = actualGroupSize();
    for (int i = 0; i < sequenceSize; i++) {
      int actualIndex = indexOfFirst + i;
      if (actualIndex > listSize - 1) failIfSequenceNotFound(sequence);
      if (!areEqual(sequence[i], actual.get(actualIndex))) failIfSequenceNotFound(sequence);
    }
    return this;
  }

  private void failIfSequenceNotFound(Object[] notFound) {
    failIfCustomMessageIsSet();
    fail(format("list:<%s> does not contain the sequence:<%s>", actual, notFound));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> starts with the given sequence of objects, without any other
   * objects between them. Same as <code>{@link #containsSequence}</code>, but verifies also that first given object is
   * also first element of {@code List}.
   * @param sequence the sequence of objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code List} is {@code null}.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the actual {@code List} is not empty and with the given sequence of objects is
   * empty.
   * @throws AssertionError if the actual {@code List} does not start with the given sequence of objects.
   */
  public ListAssert startsWith(Object... sequence) {
    isNotNull();
    validateIsNotNull(sequence);
    int sequenceSize = sequence.length;
    int listSize = actualGroupSize();
    if (sequenceSize == 0 && listSize == 0) return this;
    if (sequenceSize == 0 && listSize != 0) failIfNotStartingWithSequence(sequence);
    if (listSize < sequenceSize) failIfNotStartingWithSequence(sequence);
    for (int i = 0; i < sequenceSize; i++)
      if (!areEqual(sequence[i], actual.get(i))) failIfNotStartingWithSequence(sequence);
    return this;
  }

  private void failIfNotStartingWithSequence(Object[] notFound) {
    failIfCustomMessageIsSet();
    fail(format("list:<%s> does not start with the sequence:<%s>", actual, notFound));
  }

  /**
   * Verifies that the actual <code>{@link List}</code> ends with the given sequence of objects, without any other
   * objects between them. Same as <code>{@link #containsSequence}</code>, but verifies also that last given object is
   * also last element of {@code List}.
   * @param sequence the sequence of objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code List} is {@code null}.
   * @throws AssertionError if the given array is {@code null}.
   * @throws AssertionError if the actual {@code List} is not empty and with the given sequence of objects is
   * empty.
   * @throws AssertionError if the actual {@code List} does not end with the given sequence of objects.
   */
  public ListAssert endsWith(Object... sequence) {
    isNotNull();
    validateIsNotNull(sequence);
    int sequenceSize = sequence.length;
    int listSize = actualGroupSize();
    if (sequenceSize == 0 && listSize == 0) return this;
    if (sequenceSize == 0 && listSize != 0) failIfNotEndingWithSequence(sequence);
    if (listSize < sequenceSize) failIfNotEndingWithSequence(sequence);
    for (int i = 0; i < sequenceSize; i++) {
      int sequenceIndex = sequenceSize - 1 - i;
      int listIndex = listSize - 1 - i;
      if (!areEqual(sequence[sequenceIndex], actual.get(listIndex))) failIfNotEndingWithSequence(sequence);
    }
    return this;
  }

  private void failIfNotEndingWithSequence(Object[] notFound) {
    failIfCustomMessageIsSet();
    fail(format("list:<%s> does not end with the sequence:<%s>", actual, notFound));
  }

  /**
   * Returns the number of elements in the actual <code>{@link List}</code>.
   * @return the number of elements in the actual {@code List}.
   * @throws AssertionError if the actual {@code List} is {@code null}.
   */
  @Override protected int actualGroupSize() {
    isNotNull();
    return actual.size();
  }

  /**
   * Verifies that the actual <code>{@link List}</code> contains the given objects, in the same order. This method works
   * just like <code>{@link #isEqualTo(List)}</code>, with the difference that internally the given array is converted
   * to a {@code List}.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code List} is {@code null}.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws AssertionError if the actual {@code List} does not contain the given objects.
   */
  public ListAssert containsExactly(Object... objects) {
    validateIsNotNull(objects);
    return isNotNull().isEqualTo(list(objects));
  }

  /**
   * Creates a new instance of <code>{@link ListAssert}</code> whose target list contains the values of the given
   * property name from the elements of this {@code ListAssert}'s list. Property access works with both simple
   * properties like {@code Person.age} and nested properties {@code Person.father.age}.
   * </p>
   * <p>
   * For example, let's say we have a list of {@code Person} objects and you want to verify their age:
   * <pre>
   * assertThat(persons).onProperty("age").containsOnly(25, 16, 44, 37); // simple property
   * assertThat(persons).onProperty("father.age").containsOnly(55, 46, 74, 62); // nested property
   * </p>
   * @param propertyName the name of the property to extract values from the actual list to build a new
   * {@code ListAssert}.
   * @return a new {@code ListAssert} containing the values of the given property name from the elements of this
   * {@code ListAssert}'s list.
   * @throws AssertionError if the actual list is {@code null}.
   * @throws IntrospectionError if an element in the given list does not have a matching property.
   * @since 1.3
   */
  @Override public ListAssert onProperty(String propertyName) {
    isNotNull();
    if (actual.isEmpty()) return new ListAssert(emptyList());
    return new ListAssert(PropertySupport.instance().propertyValues(propertyName, actual));
  }

  /** {@inheritDoc} */
  @Override protected Set<Object> actualAsSet() {
    return new LinkedHashSet<Object>(actual);
  }

  /** {@inheritDoc} */
  @Override protected List<Object> actualAsList() {
    return new ArrayList<Object>(actual);
  }
}
