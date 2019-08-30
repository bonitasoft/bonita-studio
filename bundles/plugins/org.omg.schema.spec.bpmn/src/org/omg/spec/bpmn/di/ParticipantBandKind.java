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
 * A representation of the literals of the enumeration '<em><b>Participant Band Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.omg.spec.bpmn.di.DiPackage#getParticipantBandKind()
 * @model extendedMetaData="name='ParticipantBandKind'"
 * @generated
 */
public enum ParticipantBandKind implements Enumerator {
	/**
	 * The '<em><b>Top Initiating</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TOP_INITIATING_VALUE
	 * @generated
	 * @ordered
	 */
	TOP_INITIATING(0, "topInitiating", "top_initiating"),

	/**
	 * The '<em><b>Middle Initiating</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MIDDLE_INITIATING_VALUE
	 * @generated
	 * @ordered
	 */
	MIDDLE_INITIATING(1, "middleInitiating", "middle_initiating"),

	/**
	 * The '<em><b>Bottom Initiating</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOTTOM_INITIATING_VALUE
	 * @generated
	 * @ordered
	 */
	BOTTOM_INITIATING(2, "bottomInitiating", "bottom_initiating"),

	/**
	 * The '<em><b>Top Non Initiating</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TOP_NON_INITIATING_VALUE
	 * @generated
	 * @ordered
	 */
	TOP_NON_INITIATING(3, "topNonInitiating", "top_non_initiating"),

	/**
	 * The '<em><b>Middle Non Initiating</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MIDDLE_NON_INITIATING_VALUE
	 * @generated
	 * @ordered
	 */
	MIDDLE_NON_INITIATING(4, "middleNonInitiating", "middle_non_initiating"),

	/**
	 * The '<em><b>Bottom Non Initiating</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOTTOM_NON_INITIATING_VALUE
	 * @generated
	 * @ordered
	 */
	BOTTOM_NON_INITIATING(5, "bottomNonInitiating", "bottom_non_initiating");

	/**
	 * The '<em><b>Top Initiating</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Top Initiating</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TOP_INITIATING
	 * @model name="topInitiating" literal="top_initiating"
	 * @generated
	 * @ordered
	 */
	public static final int TOP_INITIATING_VALUE = 0;

	/**
	 * The '<em><b>Middle Initiating</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Middle Initiating</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MIDDLE_INITIATING
	 * @model name="middleInitiating" literal="middle_initiating"
	 * @generated
	 * @ordered
	 */
	public static final int MIDDLE_INITIATING_VALUE = 1;

	/**
	 * The '<em><b>Bottom Initiating</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bottom Initiating</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BOTTOM_INITIATING
	 * @model name="bottomInitiating" literal="bottom_initiating"
	 * @generated
	 * @ordered
	 */
	public static final int BOTTOM_INITIATING_VALUE = 2;

	/**
	 * The '<em><b>Top Non Initiating</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Top Non Initiating</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TOP_NON_INITIATING
	 * @model name="topNonInitiating" literal="top_non_initiating"
	 * @generated
	 * @ordered
	 */
	public static final int TOP_NON_INITIATING_VALUE = 3;

	/**
	 * The '<em><b>Middle Non Initiating</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Middle Non Initiating</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MIDDLE_NON_INITIATING
	 * @model name="middleNonInitiating" literal="middle_non_initiating"
	 * @generated
	 * @ordered
	 */
	public static final int MIDDLE_NON_INITIATING_VALUE = 4;

	/**
	 * The '<em><b>Bottom Non Initiating</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Bottom Non Initiating</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BOTTOM_NON_INITIATING
	 * @model name="bottomNonInitiating" literal="bottom_non_initiating"
	 * @generated
	 * @ordered
	 */
	public static final int BOTTOM_NON_INITIATING_VALUE = 5;

	/**
	 * An array of all the '<em><b>Participant Band Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ParticipantBandKind[] VALUES_ARRAY =
		new ParticipantBandKind[] {
			TOP_INITIATING,
			MIDDLE_INITIATING,
			BOTTOM_INITIATING,
			TOP_NON_INITIATING,
			MIDDLE_NON_INITIATING,
			BOTTOM_NON_INITIATING,
		};

	/**
	 * A public read-only list of all the '<em><b>Participant Band Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ParticipantBandKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Participant Band Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParticipantBandKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParticipantBandKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Participant Band Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParticipantBandKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParticipantBandKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Participant Band Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParticipantBandKind get(int value) {
		switch (value) {
			case TOP_INITIATING_VALUE: return TOP_INITIATING;
			case MIDDLE_INITIATING_VALUE: return MIDDLE_INITIATING;
			case BOTTOM_INITIATING_VALUE: return BOTTOM_INITIATING;
			case TOP_NON_INITIATING_VALUE: return TOP_NON_INITIATING;
			case MIDDLE_NON_INITIATING_VALUE: return MIDDLE_NON_INITIATING;
			case BOTTOM_NON_INITIATING_VALUE: return BOTTOM_NON_INITIATING;
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
	private ParticipantBandKind(int value, String name, String literal) {
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
	
} //ParticipantBandKind
