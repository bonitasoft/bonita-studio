/*
 * Created on Jan 11, 2011
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
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.util.ToString.toStringOf;

import java.util.*;

import org.fest.util.VisibleForTesting;

/**
 * Assertions for <code>{@link Iterator}</code>s.
 * <p>
 * To create a new instance of this class invoke <code>{@link Assertions#assertThat(Iterator)}</code>.
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 1.4
 */
public class IteratorAssert extends ObjectGroupAssert<IteratorAssert, Iterator<?>> {

  /**
   * Creates a new </code>{@link IteratorAssert}</code>.
   * @param actual the target to verify.
   */
  protected IteratorAssert(Iterator<?> actual) {
    super(IteratorAssert.class, wrap(actual));
  }

  private static Iterator<?> wrap(Iterator<?> actual) {
    if (actual == null) return null;
    return new PrettyPrintIterator(actual);
  }

  /** {@inheritDoc} */
  @Override protected IteratorAssert onProperty(String propertyName) {
    isNotNull();
    List<Object> subset = PropertySupport.instance().propertyValues(propertyName, contentOfActual());
    return new IteratorAssert(subset.iterator());
  }

  /** {@inheritDoc} */
  @Override protected Set<Object> actualAsSet() {
    return new LinkedHashSet<Object>(contentOfActual());
  }

  /** {@inheritDoc} */
  @Override protected List<Object> actualAsList() {
    return new ArrayList<Object>(contentOfActual());
  }

  /** {@inheritDoc} */
  @Override protected int actualGroupSize() {
    isNotNull();
    return contentOfActual().size();
  }

  private List<Object> contentOfActual() {
    PrettyPrintIterator wrapped = (PrettyPrintIterator) actual;
    return wrapped.contents();
  }

  @VisibleForTesting static class PrettyPrintIterator implements Iterator<Object> {
    private final Iterator<?> wrapped;

    boolean wrappedWasConsumed;
    List<Object> wrappedContents;
    Iterator<Object> iterator;

    PrettyPrintIterator(Iterator<?> wrapped) {
      this.wrapped = wrapped;
    }

    List<Object> contents() {
      consumeIterator();
      return wrappedContents;
    }

    /** {@inheritDoc} */
    public boolean hasNext() {
      consumeIterator();
      return iterator.hasNext();
    }

    /** {@inheritDoc} */
    public Object next() {
      consumeIterator();
      return iterator.next();
    }

    private synchronized void consumeIterator() {
      if (wrappedWasConsumed) return;
      wrappedContents = new ArrayList<Object>();
      while (wrapped.hasNext()) wrappedContents.add(wrapped.next());
      wrappedWasConsumed = true;
      iterator = wrappedContents.iterator();
    }

    /** {@inheritDoc} */
    public void remove() {
      throw new UnsupportedOperationException();
    }

    /** {@inheritDoc} */
    @Override public String toString() {
      consumeIterator();
      return toStringOf(wrappedContents);
    }
  }
}