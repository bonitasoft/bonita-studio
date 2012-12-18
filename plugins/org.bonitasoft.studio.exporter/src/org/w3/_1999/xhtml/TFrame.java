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
 * A representation of the literals of the enumeration '<em><b>TFrame</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 *       The border attribute sets the thickness of the frame around the
 *       table. The default units are screen pixels.
 * 
 *       The frame attribute specifies which parts of the frame around
 *       the table should be rendered. The values are not the same as
 *       CALS to avoid a name clash with the valign attribute.
 *       
 * <!-- end-model-doc -->
 * @see org.w3._1999.xhtml.XhtmlPackage#getTFrame()
 * @model extendedMetaData="name='TFrame'"
 * @generated
 */
public enum TFrame implements Enumerator {
	/**
	 * The '<em><b>Void</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VOID_VALUE
	 * @generated
	 * @ordered
	 */
	VOID(0, "void", "void"),

	/**
	 * The '<em><b>Above</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ABOVE_VALUE
	 * @generated
	 * @ordered
	 */
	ABOVE(1, "above", "above"),

	/**
	 * The '<em><b>Below</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BELOW_VALUE
	 * @generated
	 * @ordered
	 */
	BELOW(2, "below", "below"),

	/**
	 * The '<em><b>Hsides</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HSIDES_VALUE
	 * @generated
	 * @ordered
	 */
	HSIDES(3, "hsides", "hsides"),

	/**
	 * The '<em><b>Lhs</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LHS_VALUE
	 * @generated
	 * @ordered
	 */
	LHS(4, "lhs", "lhs"),

	/**
	 * The '<em><b>Rhs</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RHS_VALUE
	 * @generated
	 * @ordered
	 */
	RHS(5, "rhs", "rhs"),

	/**
	 * The '<em><b>Vsides</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VSIDES_VALUE
	 * @generated
	 * @ordered
	 */
	VSIDES(6, "vsides", "vsides"),

	/**
	 * The '<em><b>Box</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BOX_VALUE
	 * @generated
	 * @ordered
	 */
	BOX(7, "box", "box"),

	/**
	 * The '<em><b>Border</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BORDER_VALUE
	 * @generated
	 * @ordered
	 */
	BORDER(8, "border", "border");

	/**
	 * The '<em><b>Void</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Void</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VOID
	 * @model name="void"
	 * @generated
	 * @ordered
	 */
	public static final int VOID_VALUE = 0;

	/**
	 * The '<em><b>Above</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Above</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ABOVE
	 * @model name="above"
	 * @generated
	 * @ordered
	 */
	public static final int ABOVE_VALUE = 1;

	/**
	 * The '<em><b>Below</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Below</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BELOW
	 * @model name="below"
	 * @generated
	 * @ordered
	 */
	public static final int BELOW_VALUE = 2;

	/**
	 * The '<em><b>Hsides</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Hsides</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HSIDES
	 * @model name="hsides"
	 * @generated
	 * @ordered
	 */
	public static final int HSIDES_VALUE = 3;

	/**
	 * The '<em><b>Lhs</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Lhs</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LHS
	 * @model name="lhs"
	 * @generated
	 * @ordered
	 */
	public static final int LHS_VALUE = 4;

	/**
	 * The '<em><b>Rhs</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Rhs</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #RHS
	 * @model name="rhs"
	 * @generated
	 * @ordered
	 */
	public static final int RHS_VALUE = 5;

	/**
	 * The '<em><b>Vsides</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Vsides</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #VSIDES
	 * @model name="vsides"
	 * @generated
	 * @ordered
	 */
	public static final int VSIDES_VALUE = 6;

	/**
	 * The '<em><b>Box</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Box</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BOX
	 * @model name="box"
	 * @generated
	 * @ordered
	 */
	public static final int BOX_VALUE = 7;

	/**
	 * The '<em><b>Border</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Border</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #BORDER
	 * @model name="border"
	 * @generated
	 * @ordered
	 */
	public static final int BORDER_VALUE = 8;

	/**
	 * An array of all the '<em><b>TFrame</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TFrame[] VALUES_ARRAY =
		new TFrame[] {
			VOID,
			ABOVE,
			BELOW,
			HSIDES,
			LHS,
			RHS,
			VSIDES,
			BOX,
			BORDER,
		};

	/**
	 * A public read-only list of all the '<em><b>TFrame</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TFrame> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>TFrame</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TFrame get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TFrame result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>TFrame</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TFrame getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TFrame result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>TFrame</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TFrame get(int value) {
		switch (value) {
			case VOID_VALUE: return VOID;
			case ABOVE_VALUE: return ABOVE;
			case BELOW_VALUE: return BELOW;
			case HSIDES_VALUE: return HSIDES;
			case LHS_VALUE: return LHS;
			case RHS_VALUE: return RHS;
			case VSIDES_VALUE: return VSIDES;
			case BOX_VALUE: return BOX;
			case BORDER_VALUE: return BORDER;
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
	private TFrame(int value, String name, String literal) {
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
	
} //TFrame
