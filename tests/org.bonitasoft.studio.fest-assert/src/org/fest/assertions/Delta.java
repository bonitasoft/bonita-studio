/*
 * Created on Mar 30, 2009
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
 * Copyright @2009-2011 the original author or authors.
 */
package org.fest.assertions;

/**
 * A finite increment in a variable.
 *
 * @author Alex Ruiz
 *
 * @since 1.1
 */
public final class Delta {

  /**
   * Creates a new <code>{@link Delta}</code>.
   * @param value the value of the delta.
   * @return the created <code>Delta</code>.
   */
  public static Delta delta(double value) {
    return new Delta(value);
  }

  /**
   * Creates a new <code>{@link Delta}</code>.
   * @param value the value of the delta.
   * @return the created <code>Delta</code>.
   */
  public static Delta delta(float value) {
    return new Delta(value);
  }

  private final Double value;

  private Delta(double value) {
    this.value = value;
  }

  /**
   * Returns the value of this delta.
   * @return the value of this delta.
   * @deprecated use <code>{@link #doubleValue()}</code> instead.
   */
  @Deprecated
  public double value() {
    return doubleValue();
  }

  /**
   * Returns the value of this delta as a {@code double}.
   * @return the value of this delta as a {@code double}.
   * @since 1.2
   */
  public double doubleValue() {
    return value.doubleValue();
  }

  /**
   * Returns the value of this delta as a {@code float}.
   * @return the value of this delta as a {@code float}.
   * @since 1.2
   */
  public float floatValue() {
    return value.floatValue();
  }
}
