/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Scope</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *       Scope is simpler than headers attribute for common tables
 *       
 * <!-- end-model-doc -->
 * @see org.w3._1999.xhtml.XhtmlPackage#getScope()
 * @model extendedMetaData="name='Scope'"
 * @generated
 */
public enum Scope implements Enumerator {
	/**
	 * The '<em><b>Row</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROW_VALUE
	 * @generated
	 * @ordered
	 */
	ROW(0, "row", "row"),

	/**
	 * The '<em><b>Col</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COL_VALUE
	 * @generated
	 * @ordered
	 */
	COL(1, "col", "col"),

	/**
	 * The '<em><b>Rowgroup</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROWGROUP_VALUE
	 * @generated
	 * @ordered
	 */
	ROWGROUP(2, "rowgroup", "rowgroup"),

	/**
	 * The '<em><b>Colgroup</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COLGROUP_VALUE
	 * @generated
	 * @ordered
	 */
	COLGROUP(3, "colgroup", "colgroup");

	/**
	 * The '<em><b>Row</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Row</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROW
	 * @model name="row"
	 * @generated
	 * @ordered
	 */
	public static final int ROW_VALUE = 0;

	/**
	 * The '<em><b>Col</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Col</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COL
	 * @model name="col"
	 * @generated
	 * @ordered
	 */
	public static final int COL_VALUE = 1;

	/**
	 * The '<em><b>Rowgroup</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Rowgroup</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROWGROUP
	 * @model name="rowgroup"
	 * @generated
	 * @ordered
	 */
	public static final int ROWGROUP_VALUE = 2;

	/**
	 * The '<em><b>Colgroup</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Colgroup</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COLGROUP
	 * @model name="colgroup"
	 * @generated
	 * @ordered
	 */
	public static final int COLGROUP_VALUE = 3;

	/**
	 * An array of all the '<em><b>Scope</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Scope[] VALUES_ARRAY =
		new Scope[] {
			ROW,
			COL,
			ROWGROUP,
			COLGROUP,
		};

	/**
	 * A public read-only list of all the '<em><b>Scope</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Scope> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Scope</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Scope get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Scope result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Scope</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Scope getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Scope result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Scope</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Scope get(int value) {
		switch (value) {
			case ROW_VALUE: return ROW;
			case COL_VALUE: return COL;
			case ROWGROUP_VALUE: return ROWGROUP;
			case COLGROUP_VALUE: return COLGROUP;
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
	private Scope(int value, String name, String literal) {
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
	
} //Scope
