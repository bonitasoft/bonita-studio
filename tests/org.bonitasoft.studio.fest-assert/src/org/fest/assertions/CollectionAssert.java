/*
 * Created on Dec 27, 2006
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
 * Copyright @2006-2011 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;

import java.util.*;

import org.fest.util.IntrospectionError;

/**
 * Assertions for <code>{@link Collection}</code>s.
 * <p>
 * To create a new instance of this class invoke <code>{@link Assertions#assertThat(Collection)}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CollectionAssert extends ObjectGroupAssert<CollectionAssert, Collection<?>> {

  /**
   * Creates a new </code>{@link CollectionAssert}</code>.
   * @param actual the target to verify.
   */
  protected CollectionAssert(Collection<?> actual) {
    super(CollectionAssert.class, actual);
  }

  /**
   * Returns the number of elements in the actual collection.
   * @return the number of elements in the actual collection.
   * @throws AssertionError if the actual collection is {@code null}.
   */
  @Override protected int actualGroupSize() {
    isNotNull();
    return actual.size();
  }

  /**
   * Creates a new instance of <code>{@link CollectionAssert}</code> whose target collection contains the values of the
   * given property name from the elements of this {@code CollectionAssert}'s collection. Property access works with
   * both simple properties like {@code Person.age} and nested properties {@code Person.father.age}.
   * <p>
   * For example, let's say we have a collection of {@code Person} objects and you want to verify their age:
   * <pre>
   * assertThat(persons).onProperty("age").containsOnly(25, 16, 44, 37); // simple property
   * assertThat(persons).onProperty("father.age").containsOnly(55, 46, 74, 62); // nested property
   * </p>
   * @param propertyName the name of the property to extract values from the actual collection to build a new
   * {@code CollectionAssert}.
   * @return a new {@code CollectionAssert} containing the values of the given property name from the elements of this
   * {@code CollectionAssert}'s collection.
   * @throws AssertionError if the actual collection is {@code null}.
   * @throws IntrospectionError if an element in the given collection does not have a matching property.
   * @since 1.3
   */
  @Override public CollectionAssert onProperty(String propertyName) {
    isNotNull();
    if (actual.isEmpty()) return new CollectionAssert(emptyList());
    return new CollectionAssert(PropertySupport.instance().propertyValues(propertyName, actual));
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
