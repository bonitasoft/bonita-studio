/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Signal Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.jbpm.jpdl32.jpdl32Package#getSignalType()
 * @model extendedMetaData="name='signal_._type'"
 * @generated
 */
public enum SignalType implements Enumerator {
	/**
	 * The '<em><b>Unsynchronized</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNSYNCHRONIZED_VALUE
	 * @generated
	 * @ordered
	 */
	UNSYNCHRONIZED(0, "unsynchronized", "unsynchronized"),

	/**
	 * The '<em><b>Never</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEVER_VALUE
	 * @generated
	 * @ordered
	 */
	NEVER(1, "never", "never"),

	/**
	 * The '<em><b>First</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIRST_VALUE
	 * @generated
	 * @ordered
	 */
	FIRST(2, "first", "first"),

	/**
	 * The '<em><b>First Wait</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FIRST_WAIT_VALUE
	 * @generated
	 * @ordered
	 */
	FIRST_WAIT(3, "firstWait", "first-wait"),

	/**
	 * The '<em><b>Last</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LAST_VALUE
	 * @generated
	 * @ordered
	 */
	LAST(4, "last", "last"),

	/**
	 * The '<em><b>Last Wait</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LAST_WAIT_VALUE
	 * @generated
	 * @ordered
	 */
	LAST_WAIT(5, "lastWait", "last-wait");

	/**
	 * The '<em><b>Unsynchronized</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unsynchronized</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSYNCHRONIZED
	 * @model name="unsynchronized"
	 * @generated
	 * @ordered
	 */
	public static final int UNSYNCHRONIZED_VALUE = 0;

	/**
	 * The '<em><b>Never</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Never</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEVER
	 * @model name="never"
	 * @generated
	 * @ordered
	 */
	public static final int NEVER_VALUE = 1;

	/**
	 * The '<em><b>First</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>First</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FIRST
	 * @model name="first"
	 * @generated
	 * @ordered
	 */
	public static final int FIRST_VALUE = 2;

	/**
	 * The '<em><b>First Wait</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>First Wait</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FIRST_WAIT
	 * @model name="firstWait" literal="first-wait"
	 * @generated
	 * @ordered
	 */
	public static final int FIRST_WAIT_VALUE = 3;

	/**
	 * The '<em><b>Last</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Last</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LAST
	 * @model name="last"
	 * @generated
	 * @ordered
	 */
	public static final int LAST_VALUE = 4;

	/**
	 * The '<em><b>Last Wait</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Last Wait</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LAST_WAIT
	 * @model name="lastWait" literal="last-wait"
	 * @generated
	 * @ordered
	 */
	public static final int LAST_WAIT_VALUE = 5;

	/**
	 * An array of all the '<em><b>Signal Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SignalType[] VALUES_ARRAY =
		new SignalType[] {
			UNSYNCHRONIZED,
			NEVER,
			FIRST,
			FIRST_WAIT,
			LAST,
			LAST_WAIT,
		};

	/**
	 * A public read-only list of all the '<em><b>Signal Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SignalType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Signal Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SignalType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SignalType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Signal Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SignalType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SignalType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Signal Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SignalType get(int value) {
		switch (value) {
			case UNSYNCHRONIZED_VALUE: return UNSYNCHRONIZED;
			case NEVER_VALUE: return NEVER;
			case FIRST_VALUE: return FIRST;
			case FIRST_WAIT_VALUE: return FIRST_WAIT;
			case LAST_VALUE: return LAST;
			case LAST_WAIT_VALUE: return LAST_WAIT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SignalType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //SignalType
