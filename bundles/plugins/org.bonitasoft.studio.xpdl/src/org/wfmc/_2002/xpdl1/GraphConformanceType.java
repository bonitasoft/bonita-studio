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
 * A representation of the literals of the enumeration '<em><b>Graph Conformance Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getGraphConformanceType()
 * @model extendedMetaData="name='GraphConformance_._type'"
 * @generated
 */
public enum GraphConformanceType implements Enumerator {
	/**
	 * The '<em><b>FULLBLOCKED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FULLBLOCKED_VALUE
	 * @generated
	 * @ordered
	 */
	FULLBLOCKED(0, "FULLBLOCKED", "FULL_BLOCKED"),

	/**
	 * The '<em><b>LOOPBLOCKED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOOPBLOCKED_VALUE
	 * @generated
	 * @ordered
	 */
	LOOPBLOCKED(1, "LOOPBLOCKED", "LOOP_BLOCKED"),

	/**
	 * The '<em><b>NONBLOCKED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NONBLOCKED_VALUE
	 * @generated
	 * @ordered
	 */
	NONBLOCKED(2, "NONBLOCKED", "NON_BLOCKED");

	/**
	 * The '<em><b>FULLBLOCKED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FULLBLOCKED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #FULLBLOCKED
	 * @model literal="FULL_BLOCKED"
	 * @generated
	 * @ordered
	 */
	public static final int FULLBLOCKED_VALUE = 0;

	/**
	 * The '<em><b>LOOPBLOCKED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOOPBLOCKED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOOPBLOCKED
	 * @model literal="LOOP_BLOCKED"
	 * @generated
	 * @ordered
	 */
	public static final int LOOPBLOCKED_VALUE = 1;

	/**
	 * The '<em><b>NONBLOCKED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONBLOCKED</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NONBLOCKED
	 * @model literal="NON_BLOCKED"
	 * @generated
	 * @ordered
	 */
	public static final int NONBLOCKED_VALUE = 2;

	/**
	 * An array of all the '<em><b>Graph Conformance Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final GraphConformanceType[] VALUES_ARRAY =
		new GraphConformanceType[] {
			FULLBLOCKED,
			LOOPBLOCKED,
			NONBLOCKED,
		};

	/**
	 * A public read-only list of all the '<em><b>Graph Conformance Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<GraphConformanceType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Graph Conformance Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GraphConformanceType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GraphConformanceType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Graph Conformance Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GraphConformanceType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			GraphConformanceType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Graph Conformance Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GraphConformanceType get(int value) {
		switch (value) {
			case FULLBLOCKED_VALUE: return FULLBLOCKED;
			case LOOPBLOCKED_VALUE: return LOOPBLOCKED;
			case NONBLOCKED_VALUE: return NONBLOCKED;
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
	private GraphConformanceType(int value, String name, String literal) {
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
	
} //GraphConformanceType
