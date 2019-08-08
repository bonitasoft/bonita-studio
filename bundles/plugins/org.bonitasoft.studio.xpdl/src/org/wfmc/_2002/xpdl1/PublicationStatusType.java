/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Publication Status Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getPublicationStatusType()
 * @model extendedMetaData="name='PublicationStatus_._type'"
 * @generated
 */
public enum PublicationStatusType implements Enumerator {
	/**
	 * The '<em><b>UNDERREVISION</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDERREVISION_VALUE
	 * @generated
	 * @ordered
	 */
	UNDERREVISION(0, "UNDERREVISION", "UNDER_REVISION"),

	/**
	 * The '<em><b>RELEASED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RELEASED_VALUE
	 * @generated
	 * @ordered
	 */
	RELEASED(1, "RELEASED", "RELEASED"),

	/**
	 * The '<em><b>UNDERTEST</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDERTEST_VALUE
	 * @generated
	 * @ordered
	 */
	UNDERTEST(2, "UNDERTEST", "UNDER_TEST");

	/**
	 * The '<em><b>UNDERREVISION</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNDERREVISION</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNDERREVISION
	 * @model literal="UNDER_REVISION"
	 * @generated
	 * @ordered
	 */
	public static final int UNDERREVISION_VALUE = 0;

	/**
	 * The '<em><b>RELEASED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>RELEASED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RELEASED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RELEASED_VALUE = 1;

	/**
	 * The '<em><b>UNDERTEST</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>UNDERTEST</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNDERTEST
	 * @model literal="UNDER_TEST"
	 * @generated
	 * @ordered
	 */
	public static final int UNDERTEST_VALUE = 2;

	/**
	 * An array of all the '<em><b>Publication Status Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final PublicationStatusType[] VALUES_ARRAY =
		new PublicationStatusType[] {
			UNDERREVISION,
			RELEASED,
			UNDERTEST,
		};

	/**
	 * A public read-only list of all the '<em><b>Publication Status Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<PublicationStatusType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Publication Status Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PublicationStatusType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PublicationStatusType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Publication Status Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PublicationStatusType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			PublicationStatusType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Publication Status Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PublicationStatusType get(int value) {
		switch (value) {
			case UNDERREVISION_VALUE: return UNDERREVISION;
			case RELEASED_VALUE: return RELEASED;
			case UNDERTEST_VALUE: return UNDERTEST;
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
	private PublicationStatusType(int value, String name, String literal) {
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
	
} //PublicationStatusType
