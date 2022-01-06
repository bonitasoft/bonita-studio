/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.di;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Message Visible Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.omg.spec.bpmn.di.DiPackage#getMessageVisibleKind()
 * @model extendedMetaData="name='MessageVisibleKind'"
 * @generated
 */
public enum MessageVisibleKind implements Enumerator {
	/**
	 * The '<em><b>Initiating</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INITIATING_VALUE
	 * @generated
	 * @ordered
	 */
	INITIATING(0, "initiating", "initiating"),

	/**
	 * The '<em><b>Non Initiating</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NON_INITIATING_VALUE
	 * @generated
	 * @ordered
	 */
	NON_INITIATING(1, "nonInitiating", "non_initiating");

	/**
	 * The '<em><b>Initiating</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Initiating</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #INITIATING
	 * @model name="initiating"
	 * @generated
	 * @ordered
	 */
	public static final int INITIATING_VALUE = 0;

	/**
	 * The '<em><b>Non Initiating</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Non Initiating</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NON_INITIATING
	 * @model name="nonInitiating" literal="non_initiating"
	 * @generated
	 * @ordered
	 */
	public static final int NON_INITIATING_VALUE = 1;

	/**
	 * An array of all the '<em><b>Message Visible Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MessageVisibleKind[] VALUES_ARRAY =
		new MessageVisibleKind[] {
			INITIATING,
			NON_INITIATING,
		};

	/**
	 * A public read-only list of all the '<em><b>Message Visible Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<MessageVisibleKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Message Visible Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MessageVisibleKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MessageVisibleKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Message Visible Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MessageVisibleKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MessageVisibleKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Message Visible Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MessageVisibleKind get(int value) {
		switch (value) {
			case INITIATING_VALUE: return INITIATING;
			case NON_INITIATING_VALUE: return NON_INITIATING;
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
	private MessageVisibleKind(int value, String name, String literal) {
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
	
} //MessageVisibleKind
