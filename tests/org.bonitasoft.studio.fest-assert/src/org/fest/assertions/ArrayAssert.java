/*
 * Created on Feb 16, 2008
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
 * Copyright @2008-2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayInspection.*;

import java.util.List;
import java.util.Set;

/**
 * Assertions for arrays.
 * @param <S> used to simulate "self types." For more information please read &quot;<a
 * href="http://passion.forco.de/content/emulating-self-types-using-java-generics-simplify-fluent-api-implementation"
 * target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>.&quot;
 * @param <A> the type the "actual" value.
 *
 * @author Alex Ruiz
 */
public abstract class ArrayAssert<S, A> extends ItemGroupAssert<S, A> {

  /**
   * Creates a new </code>{@link ArrayAssert}</code>.
   * @param selfType the "self type."
   * @param actual the target to verify.
   */
  protected ArrayAssert(Class<S> selfType, A actual) {
    super(selfType, actual);
  }

  /**
   * Returns the size of the actual array.
   * @return the size of the actual array.
   * @throws NullPointerException if the actual array is {@code null}.
   */
  @Override protected final int actualGroupSize() {
    isNotNull();
    return sizeOf(actual);
  }

  /** {@inheritDoc} */
  @Override protected Set<Object> actualAsSet() {
    return toSet(actual);
  }

  /** {@inheritDoc} */
  @Override protected List<Object> actualAsList() {
    return toList(actual);
  }
}
