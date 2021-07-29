/**
 */
package org.bonitasoft.studio.businessobject.editor.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Field Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getFieldType()
 * @model
 * @generated
 */
public enum FieldType implements Enumerator {
    /**
     * The '<em><b>STRING</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #STRING_VALUE
     * @generated
     * @ordered
     */
    STRING(0, "STRING", "STRING"),

    /**
     * The '<em><b>TEXT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #TEXT_VALUE
     * @generated
     * @ordered
     */
    TEXT(1, "TEXT", "TEXT"),

    /**
     * The '<em><b>INTEGER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTEGER_VALUE
     * @generated
     * @ordered
     */
    INTEGER(2, "INTEGER", "INTEGER"),

    /**
     * The '<em><b>DOUBLE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DOUBLE_VALUE
     * @generated
     * @ordered
     */
    DOUBLE(3, "DOUBLE", "DOUBLE"),

    /**
     * The '<em><b>LONG</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LONG_VALUE
     * @generated
     * @ordered
     */
    LONG(4, "LONG", "LONG"),

    /**
     * The '<em><b>FLOAT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FLOAT_VALUE
     * @generated
     * @ordered
     */
    FLOAT(5, "FLOAT", "FLOAT"),

    /**
     * The '<em><b>DATE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DATE_VALUE
     * @generated
     * @ordered
     */
    DATE(6, "DATE", "DATE"),

    /**
     * The '<em><b>BOOLEAN</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BOOLEAN_VALUE
     * @generated
     * @ordered
     */
    BOOLEAN(7, "BOOLEAN", "BOOLEAN"),

    /**
     * The '<em><b>BYTE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #BYTE_VALUE
     * @generated
     * @ordered
     */
    BYTE(8, "BYTE", "BYTE"),

    /**
     * The '<em><b>SHORT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SHORT_VALUE
     * @generated
     * @ordered
     */
    SHORT(9, "SHORT", "SHORT"),

    /**
     * The '<em><b>CHAR</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CHAR_VALUE
     * @generated
     * @ordered
     */
    CHAR(10, "CHAR", "CHAR"),

    /**
     * The '<em><b>LOCALDATETIME</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOCALDATETIME_VALUE
     * @generated
     * @ordered
     */
    LOCALDATETIME(11, "LOCALDATETIME", "LOCALDATETIME"),

    /**
     * The '<em><b>LOCALDATE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LOCALDATE_VALUE
     * @generated
     * @ordered
     */
    LOCALDATE(12, "LOCALDATE", "LOCALDATE"),

    /**
     * The '<em><b>OFFSETDATETIME</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OFFSETDATETIME_VALUE
     * @generated
     * @ordered
     */
    OFFSETDATETIME(13, "OFFSETDATETIME", "OFFSETDATETIME");

    /**
     * The '<em><b>STRING</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>STRING</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #STRING
     * @model
     * @generated
     * @ordered
     */
    public static final int STRING_VALUE = 0;

    /**
     * The '<em><b>TEXT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TEXT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #TEXT
     * @model
     * @generated
     * @ordered
     */
    public static final int TEXT_VALUE = 1;

    /**
     * The '<em><b>INTEGER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>INTEGER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INTEGER
     * @model
     * @generated
     * @ordered
     */
    public static final int INTEGER_VALUE = 2;

    /**
     * The '<em><b>DOUBLE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DOUBLE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DOUBLE
     * @model
     * @generated
     * @ordered
     */
    public static final int DOUBLE_VALUE = 3;

    /**
     * The '<em><b>LONG</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LONG</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LONG
     * @model
     * @generated
     * @ordered
     */
    public static final int LONG_VALUE = 4;

    /**
     * The '<em><b>FLOAT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>FLOAT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FLOAT
     * @model
     * @generated
     * @ordered
     */
    public static final int FLOAT_VALUE = 5;

    /**
     * The '<em><b>DATE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DATE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DATE
     * @model
     * @generated
     * @ordered
     */
    public static final int DATE_VALUE = 6;

    /**
     * The '<em><b>BOOLEAN</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>BOOLEAN</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BOOLEAN
     * @model
     * @generated
     * @ordered
     */
    public static final int BOOLEAN_VALUE = 7;

    /**
     * The '<em><b>BYTE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>BYTE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #BYTE
     * @model
     * @generated
     * @ordered
     */
    public static final int BYTE_VALUE = 8;

    /**
     * The '<em><b>SHORT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SHORT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SHORT
     * @model
     * @generated
     * @ordered
     */
    public static final int SHORT_VALUE = 9;

    /**
     * The '<em><b>CHAR</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CHAR</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CHAR
     * @model
     * @generated
     * @ordered
     */
    public static final int CHAR_VALUE = 10;

    /**
     * The '<em><b>LOCALDATETIME</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LOCALDATETIME</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LOCALDATETIME
     * @model
     * @generated
     * @ordered
     */
    public static final int LOCALDATETIME_VALUE = 11;

    /**
     * The '<em><b>LOCALDATE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LOCALDATE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LOCALDATE
     * @model
     * @generated
     * @ordered
     */
    public static final int LOCALDATE_VALUE = 12;

    /**
     * The '<em><b>OFFSETDATETIME</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>OFFSETDATETIME</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OFFSETDATETIME
     * @model
     * @generated
     * @ordered
     */
    public static final int OFFSETDATETIME_VALUE = 13;

    /**
     * An array of all the '<em><b>Field Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final FieldType[] VALUES_ARRAY =
        new FieldType[] {
            STRING,
            TEXT,
            INTEGER,
            DOUBLE,
            LONG,
            FLOAT,
            DATE,
            BOOLEAN,
            BYTE,
            SHORT,
            CHAR,
            LOCALDATETIME,
            LOCALDATE,
            OFFSETDATETIME,
        };

    /**
     * A public read-only list of all the '<em><b>Field Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<FieldType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Field Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FieldType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            FieldType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Field Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FieldType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            FieldType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Field Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FieldType get(int value) {
        switch (value) {
            case STRING_VALUE: return STRING;
            case TEXT_VALUE: return TEXT;
            case INTEGER_VALUE: return INTEGER;
            case DOUBLE_VALUE: return DOUBLE;
            case LONG_VALUE: return LONG;
            case FLOAT_VALUE: return FLOAT;
            case DATE_VALUE: return DATE;
            case BOOLEAN_VALUE: return BOOLEAN;
            case BYTE_VALUE: return BYTE;
            case SHORT_VALUE: return SHORT;
            case CHAR_VALUE: return CHAR;
            case LOCALDATETIME_VALUE: return LOCALDATETIME;
            case LOCALDATE_VALUE: return LOCALDATE;
            case OFFSETDATETIME_VALUE: return OFFSETDATETIME;
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
    private FieldType(int value, String name, String literal) {
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
    
} //FieldType
