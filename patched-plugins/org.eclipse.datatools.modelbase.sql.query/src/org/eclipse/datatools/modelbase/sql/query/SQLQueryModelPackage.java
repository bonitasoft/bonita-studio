/**
 * <copyright>
 * </copyright>
 *
 * $Id: SQLQueryModelPackage.java,v 1.6 2010/02/25 01:57:23 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelFactory
 * @model kind="package"
 * @generated
 */
public interface SQLQueryModelPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "query";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http:///org/eclipse/datatools/modelbase/sql/query/SQLQueryModel.ecore";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "org.eclipse.datatools.modelbase.sql.query";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	SQLQueryModelPackage eINSTANCE = org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl.init();

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl <em>SQL Query Object</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSQLQueryObject()
     * @generated
     */
	int SQL_QUERY_OBJECT = 66;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SQL_QUERY_OBJECT__EANNOTATIONS = SQLSchemaPackage.SQL_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SQL_QUERY_OBJECT__NAME = SQLSchemaPackage.SQL_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SQL_QUERY_OBJECT__DEPENDENCIES = SQLSchemaPackage.SQL_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SQL_QUERY_OBJECT__DESCRIPTION = SQLSchemaPackage.SQL_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SQL_QUERY_OBJECT__LABEL = SQLSchemaPackage.SQL_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SQL_QUERY_OBJECT__COMMENTS = SQLSchemaPackage.SQL_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQL_QUERY_OBJECT__EXTENSIONS = SQLSchemaPackage.SQL_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SQL_QUERY_OBJECT__PRIVILEGES = SQLSchemaPackage.SQL_OBJECT__PRIVILEGES;

	/**
     * The number of structural features of the '<em>SQL Query Object</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SQL_QUERY_OBJECT_FEATURE_COUNT = SQLSchemaPackage.SQL_OBJECT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryStatementImpl <em>Query Statement</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryStatementImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryStatement()
     * @generated
     */
	int QUERY_STATEMENT = 0;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_STATEMENT__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_STATEMENT__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_STATEMENT__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_STATEMENT__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_STATEMENT__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_STATEMENT__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_STATEMENT__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_STATEMENT__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The number of structural features of the '<em>Query Statement</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_STATEMENT_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryChangeStatementImpl <em>Query Change Statement</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryChangeStatementImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryChangeStatement()
     * @generated
     */
	int QUERY_CHANGE_STATEMENT = 67;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_CHANGE_STATEMENT__EANNOTATIONS = QUERY_STATEMENT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_CHANGE_STATEMENT__NAME = QUERY_STATEMENT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_CHANGE_STATEMENT__DEPENDENCIES = QUERY_STATEMENT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_CHANGE_STATEMENT__DESCRIPTION = QUERY_STATEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_CHANGE_STATEMENT__LABEL = QUERY_STATEMENT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_CHANGE_STATEMENT__COMMENTS = QUERY_STATEMENT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_CHANGE_STATEMENT__EXTENSIONS = QUERY_STATEMENT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_CHANGE_STATEMENT__PRIVILEGES = QUERY_STATEMENT__PRIVILEGES;

	/**
     * The number of structural features of the '<em>Query Change Statement</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_CHANGE_STATEMENT_FEATURE_COUNT = QUERY_STATEMENT_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryDeleteStatementImpl <em>Query Delete Statement</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryDeleteStatementImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryDeleteStatement()
     * @generated
     */
	int QUERY_DELETE_STATEMENT = 1;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT__EANNOTATIONS = QUERY_CHANGE_STATEMENT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT__NAME = QUERY_CHANGE_STATEMENT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT__DEPENDENCIES = QUERY_CHANGE_STATEMENT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT__DESCRIPTION = QUERY_CHANGE_STATEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT__LABEL = QUERY_CHANGE_STATEMENT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT__COMMENTS = QUERY_CHANGE_STATEMENT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_DELETE_STATEMENT__EXTENSIONS = QUERY_CHANGE_STATEMENT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT__PRIVILEGES = QUERY_CHANGE_STATEMENT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Where Current Of Clause</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Where Clause</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT__WHERE_CLAUSE = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Target Table</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT__TARGET_TABLE = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Query Delete Statement</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_DELETE_STATEMENT_FEATURE_COUNT = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl <em>Query Insert Statement</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryInsertStatement()
     * @generated
     */
	int QUERY_INSERT_STATEMENT = 2;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__EANNOTATIONS = QUERY_CHANGE_STATEMENT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__NAME = QUERY_CHANGE_STATEMENT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__DEPENDENCIES = QUERY_CHANGE_STATEMENT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__DESCRIPTION = QUERY_CHANGE_STATEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__LABEL = QUERY_CHANGE_STATEMENT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__COMMENTS = QUERY_CHANGE_STATEMENT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_INSERT_STATEMENT__EXTENSIONS = QUERY_CHANGE_STATEMENT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__PRIVILEGES = QUERY_CHANGE_STATEMENT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Source Query</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__SOURCE_QUERY = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Source Values Row List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Target Table</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__TARGET_TABLE = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Target Column List</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Query Insert Statement</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_INSERT_STATEMENT_FEATURE_COUNT = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectStatementImpl <em>Query Select Statement</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectStatementImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQuerySelectStatement()
     * @generated
     */
	int QUERY_SELECT_STATEMENT = 3;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_STATEMENT__EANNOTATIONS = QUERY_STATEMENT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_STATEMENT__NAME = QUERY_STATEMENT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_STATEMENT__DEPENDENCIES = QUERY_STATEMENT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_STATEMENT__DESCRIPTION = QUERY_STATEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_STATEMENT__LABEL = QUERY_STATEMENT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_STATEMENT__COMMENTS = QUERY_STATEMENT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_SELECT_STATEMENT__EXTENSIONS = QUERY_STATEMENT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_STATEMENT__PRIVILEGES = QUERY_STATEMENT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Query Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_STATEMENT__QUERY_EXPR = QUERY_STATEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Order By Clause</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE = QUERY_STATEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Updatability Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR = QUERY_STATEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Query Select Statement</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_STATEMENT_FEATURE_COUNT = QUERY_STATEMENT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryUpdateStatementImpl <em>Query Update Statement</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryUpdateStatementImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryUpdateStatement()
     * @generated
     */
	int QUERY_UPDATE_STATEMENT = 4;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__EANNOTATIONS = QUERY_CHANGE_STATEMENT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__NAME = QUERY_CHANGE_STATEMENT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__DEPENDENCIES = QUERY_CHANGE_STATEMENT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__DESCRIPTION = QUERY_CHANGE_STATEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__LABEL = QUERY_CHANGE_STATEMENT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__COMMENTS = QUERY_CHANGE_STATEMENT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_UPDATE_STATEMENT__EXTENSIONS = QUERY_CHANGE_STATEMENT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__PRIVILEGES = QUERY_CHANGE_STATEMENT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Assignment Clause</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__ASSIGNMENT_CLAUSE = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Where Current Of Clause</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__WHERE_CURRENT_OF_CLAUSE = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Where Clause</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__WHERE_CLAUSE = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Target Table</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT__TARGET_TABLE = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Query Update Statement</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_UPDATE_STATEMENT_FEATURE_COUNT = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl <em>Update Assignment Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdateAssignmentExpression()
     * @generated
     */
	int UPDATE_ASSIGNMENT_EXPRESSION = 5;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_ASSIGNMENT_EXPRESSION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Target Column List</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Update Source</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Merge Update Spec</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Update Assignment Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_ASSIGNMENT_EXPRESSION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.CursorReferenceImpl <em>Cursor Reference</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.CursorReferenceImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getCursorReference()
     * @generated
     */
	int CURSOR_REFERENCE = 6;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURSOR_REFERENCE__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURSOR_REFERENCE__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURSOR_REFERENCE__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURSOR_REFERENCE__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURSOR_REFERENCE__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURSOR_REFERENCE__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CURSOR_REFERENCE__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURSOR_REFERENCE__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURSOR_REFERENCE__UPDATE_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURSOR_REFERENCE__DELETE_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Cursor Reference</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CURSOR_REFERENCE_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl <em>Query Search Condition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQuerySearchCondition()
     * @generated
     */
	int QUERY_SEARCH_CONDITION = 7;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_SEARCH_CONDITION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__NEGATED_CONDITION = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__UPDATE_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__DELETE_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__TABLE_JOINED = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__COMBINED_LEFT = SQL_QUERY_OBJECT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__COMBINED_RIGHT = SQL_QUERY_OBJECT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING = SQL_QUERY_OBJECT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE = SQL_QUERY_OBJECT_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION__NEST = SQL_QUERY_OBJECT_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION = SQL_QUERY_OBJECT_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>Query Search Condition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SEARCH_CONDITION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 11;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl <em>Table Reference</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableReference()
     * @generated
     */
	int TABLE_REFERENCE = 13;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_REFERENCE__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__TABLE_JOINED_RIGHT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__TABLE_JOINED_LEFT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__QUERY_SELECT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE__NEST = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_REFERENCE__MERGE_SOURCE_TABLE = SQL_QUERY_OBJECT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Table Reference</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_REFERENCE_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl <em>Table Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableExpression()
     * @generated
     */
	int TABLE_EXPRESSION = 14;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__EANNOTATIONS = TABLE_REFERENCE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__NAME = TABLE_REFERENCE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__DEPENDENCIES = TABLE_REFERENCE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__DESCRIPTION = TABLE_REFERENCE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__LABEL = TABLE_REFERENCE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__COMMENTS = TABLE_REFERENCE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_EXPRESSION__EXTENSIONS = TABLE_REFERENCE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__PRIVILEGES = TABLE_REFERENCE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__TABLE_JOINED_RIGHT = TABLE_REFERENCE__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__TABLE_JOINED_LEFT = TABLE_REFERENCE__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__QUERY_SELECT = TABLE_REFERENCE__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__NEST = TABLE_REFERENCE__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_EXPRESSION__MERGE_SOURCE_TABLE = TABLE_REFERENCE__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__COLUMN_LIST = TABLE_REFERENCE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__TABLE_CORRELATION = TABLE_REFERENCE_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS = TABLE_REFERENCE_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION__VALUE_EXPR_COLUMNS = TABLE_REFERENCE_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_EXPRESSION__MERGE_TARGET_TABLE = TABLE_REFERENCE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Table Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_EXPRESSION_FEATURE_COUNT = TABLE_REFERENCE_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl <em>Query Expression Body</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryExpressionBody()
     * @generated
     */
	int QUERY_EXPRESSION_BODY = 8;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__EANNOTATIONS = TABLE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__NAME = TABLE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__DEPENDENCIES = TABLE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__DESCRIPTION = TABLE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__LABEL = TABLE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__COMMENTS = TABLE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_EXPRESSION_BODY__EXTENSIONS = TABLE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__PRIVILEGES = TABLE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT = TABLE_EXPRESSION__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT = TABLE_EXPRESSION__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__QUERY_SELECT = TABLE_EXPRESSION__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__NEST = TABLE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_EXPRESSION_BODY__MERGE_SOURCE_TABLE = TABLE_EXPRESSION__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__COLUMN_LIST = TABLE_EXPRESSION__COLUMN_LIST;

	/**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__TABLE_CORRELATION = TABLE_EXPRESSION__TABLE_CORRELATION;

	/**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS = TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS;

	/**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS = TABLE_EXPRESSION__VALUE_EXPR_COLUMNS;

	/**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_EXPRESSION_BODY__MERGE_TARGET_TABLE = TABLE_EXPRESSION__MERGE_TARGET_TABLE;

    /**
     * The feature id for the '<em><b>Row Fetch Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT = TABLE_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Query Expression</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__QUERY_EXPRESSION = TABLE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__COMBINED_LEFT = TABLE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__COMBINED_RIGHT = TABLE_EXPRESSION_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Predicate Exists</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__PREDICATE_EXISTS = TABLE_EXPRESSION_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Update Source Query</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY = TABLE_EXPRESSION_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>With Table Specification</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION = TABLE_EXPRESSION_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Query Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_EXPRESSION_BODY__QUERY_NEST = TABLE_EXPRESSION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Sort Spec List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_EXPRESSION_BODY__SORT_SPEC_LIST = TABLE_EXPRESSION_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Query Expression Body</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_BODY_FEATURE_COUNT = TABLE_EXPRESSION_FEATURE_COUNT + 9;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl <em>Query Value Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryValueExpression()
     * @generated
     */
	int QUERY_VALUE_EXPRESSION = 9;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_VALUE_EXPRESSION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__UNARY_OPERATOR = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__DATA_TYPE = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUES_ROW = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__RESULT_COLUMN = SQL_QUERY_OBJECT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__BASIC_RIGHT = SQL_QUERY_OBJECT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__BASIC_LEFT = SQL_QUERY_OBJECT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__LIKE_PATTERN = SQL_QUERY_OBJECT_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__LIKE_MATCHING = SQL_QUERY_OBJECT_FEATURE_COUNT + 8;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__PREDICATE_NULL = SQL_QUERY_OBJECT_FEATURE_COUNT + 9;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT = SQL_QUERY_OBJECT_FEATURE_COUNT + 10;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT = SQL_QUERY_OBJECT_FEATURE_COUNT + 11;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT = SQL_QUERY_OBJECT_FEATURE_COUNT + 12;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT = SQL_QUERY_OBJECT_FEATURE_COUNT + 13;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT = SQL_QUERY_OBJECT_FEATURE_COUNT + 14;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT = SQL_QUERY_OBJECT_FEATURE_COUNT + 15;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__BETWEEN_LEFT = SQL_QUERY_OBJECT_FEATURE_COUNT + 16;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1 = SQL_QUERY_OBJECT_FEATURE_COUNT + 17;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2 = SQL_QUERY_OBJECT_FEATURE_COUNT + 18;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST = SQL_QUERY_OBJECT_FEATURE_COUNT + 19;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION = SQL_QUERY_OBJECT_FEATURE_COUNT + 20;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT = SQL_QUERY_OBJECT_FEATURE_COUNT + 21;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT = SQL_QUERY_OBJECT_FEATURE_COUNT + 22;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__GROUPING_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 23;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE = SQL_QUERY_OBJECT_FEATURE_COUNT + 24;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE = SQL_QUERY_OBJECT_FEATURE_COUNT + 25;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = SQL_QUERY_OBJECT_FEATURE_COUNT + 26;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = SQL_QUERY_OBJECT_FEATURE_COUNT + 27;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 28;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__LIKE_ESCAPE = SQL_QUERY_OBJECT_FEATURE_COUNT + 29;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION = SQL_QUERY_OBJECT_FEATURE_COUNT + 30;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__NEST = SQL_QUERY_OBJECT_FEATURE_COUNT + 31;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST = SQL_QUERY_OBJECT_FEATURE_COUNT + 32;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_VALUE_EXPRESSION__TABLE_FUNCTION = SQL_QUERY_OBJECT_FEATURE_COUNT + 33;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW = SQL_QUERY_OBJECT_FEATURE_COUNT + 34;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_VALUE_EXPRESSION__CALL_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 35;

    /**
     * The number of structural features of the '<em>Query Value Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUE_EXPRESSION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 36;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl <em>Query Expression Root</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryExpressionRoot()
     * @generated
     */
	int QUERY_EXPRESSION_ROOT = 10;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_EXPRESSION_ROOT__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Insert Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__INSERT_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Select Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__SELECT_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>With Clause</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__WITH_CLAUSE = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Query</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__QUERY = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>In Value Row Select Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT = SQL_QUERY_OBJECT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>In Value Select Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT = SQL_QUERY_OBJECT_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Quantified Row Select Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT = SQL_QUERY_OBJECT_FEATURE_COUNT + 6;

	/**
     * The feature id for the '<em><b>Quantified Value Select Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT = SQL_QUERY_OBJECT_FEATURE_COUNT + 7;

	/**
     * The feature id for the '<em><b>Val Expr Scalar Select</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT = SQL_QUERY_OBJECT_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Query Expression Root</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_EXPRESSION_ROOT_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 9;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValuesRowImpl <em>Values Row</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValuesRowImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValuesRow()
     * @generated
     */
	int VALUES_ROW = 11;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUES_ROW__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Insert Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW__INSERT_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Expr List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW__EXPR_LIST = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Query Values</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW__QUERY_VALUES = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Values Row</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUES_ROW_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValuesImpl <em>Query Values</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryValuesImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryValues()
     * @generated
     */
	int QUERY_VALUES = 12;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__EANNOTATIONS = QUERY_EXPRESSION_BODY__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__NAME = QUERY_EXPRESSION_BODY__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__DEPENDENCIES = QUERY_EXPRESSION_BODY__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__DESCRIPTION = QUERY_EXPRESSION_BODY__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__LABEL = QUERY_EXPRESSION_BODY__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__COMMENTS = QUERY_EXPRESSION_BODY__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_VALUES__EXTENSIONS = QUERY_EXPRESSION_BODY__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__PRIVILEGES = QUERY_EXPRESSION_BODY__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__TABLE_JOINED_RIGHT = QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__TABLE_JOINED_LEFT = QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__QUERY_SELECT = QUERY_EXPRESSION_BODY__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__NEST = QUERY_EXPRESSION_BODY__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_VALUES__MERGE_SOURCE_TABLE = QUERY_EXPRESSION_BODY__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__COLUMN_LIST = QUERY_EXPRESSION_BODY__COLUMN_LIST;

	/**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__TABLE_CORRELATION = QUERY_EXPRESSION_BODY__TABLE_CORRELATION;

	/**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__RESULT_TABLE_ALL_COLUMNS = QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS;

	/**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__VALUE_EXPR_COLUMNS = QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS;

	/**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_VALUES__MERGE_TARGET_TABLE = QUERY_EXPRESSION_BODY__MERGE_TARGET_TABLE;

    /**
     * The feature id for the '<em><b>Row Fetch Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_VALUES__ROW_FETCH_LIMIT = QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT;

    /**
     * The feature id for the '<em><b>Query Expression</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__QUERY_EXPRESSION = QUERY_EXPRESSION_BODY__QUERY_EXPRESSION;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__COMBINED_LEFT = QUERY_EXPRESSION_BODY__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__COMBINED_RIGHT = QUERY_EXPRESSION_BODY__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Predicate Exists</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__PREDICATE_EXISTS = QUERY_EXPRESSION_BODY__PREDICATE_EXISTS;

	/**
     * The feature id for the '<em><b>Update Source Query</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__UPDATE_SOURCE_QUERY = QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY;

	/**
     * The feature id for the '<em><b>With Table Specification</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__WITH_TABLE_SPECIFICATION = QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION;

	/**
     * The feature id for the '<em><b>Query Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_VALUES__QUERY_NEST = QUERY_EXPRESSION_BODY__QUERY_NEST;

    /**
     * The feature id for the '<em><b>Sort Spec List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_VALUES__SORT_SPEC_LIST = QUERY_EXPRESSION_BODY__SORT_SPEC_LIST;

    /**
     * The feature id for the '<em><b>Values Row List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES__VALUES_ROW_LIST = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Query Values</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_VALUES_FEATURE_COUNT = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl <em>Table Joined</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableJoined()
     * @generated
     */
	int TABLE_JOINED = 15;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__EANNOTATIONS = TABLE_REFERENCE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__NAME = TABLE_REFERENCE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__DEPENDENCIES = TABLE_REFERENCE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__DESCRIPTION = TABLE_REFERENCE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__LABEL = TABLE_REFERENCE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__COMMENTS = TABLE_REFERENCE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_JOINED__EXTENSIONS = TABLE_REFERENCE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__PRIVILEGES = TABLE_REFERENCE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__TABLE_JOINED_RIGHT = TABLE_REFERENCE__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__TABLE_JOINED_LEFT = TABLE_REFERENCE__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__QUERY_SELECT = TABLE_REFERENCE__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__NEST = TABLE_REFERENCE__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_JOINED__MERGE_SOURCE_TABLE = TABLE_REFERENCE__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Join Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__JOIN_OPERATOR = TABLE_REFERENCE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Join Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__JOIN_CONDITION = TABLE_REFERENCE_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Table Ref Right</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__TABLE_REF_RIGHT = TABLE_REFERENCE_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Table Ref Left</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED__TABLE_REF_LEFT = TABLE_REFERENCE_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Table Joined</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_JOINED_FEATURE_COUNT = TABLE_REFERENCE_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl <em>With Table Specification</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getWithTableSpecification()
     * @generated
     */
	int WITH_TABLE_SPECIFICATION = 16;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WITH_TABLE_SPECIFICATION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Query Expression Root</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>With Table Query Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>With Table References</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Column Name List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>With Table Specification</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_SPECIFICATION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl <em>Predicate</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicate()
     * @generated
     */
	int PREDICATE = 17;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__EANNOTATIONS = QUERY_SEARCH_CONDITION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__NAME = QUERY_SEARCH_CONDITION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__DEPENDENCIES = QUERY_SEARCH_CONDITION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__DESCRIPTION = QUERY_SEARCH_CONDITION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__LABEL = QUERY_SEARCH_CONDITION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__COMMENTS = QUERY_SEARCH_CONDITION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE__EXTENSIONS = QUERY_SEARCH_CONDITION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__PRIVILEGES = QUERY_SEARCH_CONDITION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__NEGATED_CONDITION = QUERY_SEARCH_CONDITION__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__UPDATE_STATEMENT = QUERY_SEARCH_CONDITION__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__DELETE_STATEMENT = QUERY_SEARCH_CONDITION__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__TABLE_JOINED = QUERY_SEARCH_CONDITION__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__COMBINED_LEFT = QUERY_SEARCH_CONDITION__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__COMBINED_RIGHT = QUERY_SEARCH_CONDITION__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__QUERY_SELECT_HAVING = QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__QUERY_SELECT_WHERE = QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT = QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__NEST = QUERY_SEARCH_CONDITION__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE__MERGE_ON_CONDITION = QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__NEGATED_PREDICATE = QUERY_SEARCH_CONDITION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__HAS_SELECTIVITY = QUERY_SEARCH_CONDITION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE__SELECTIVITY_VALUE = QUERY_SEARCH_CONDITION_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Predicate</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_FEATURE_COUNT = QUERY_SEARCH_CONDITION_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionCombinedImpl <em>Search Condition Combined</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionCombinedImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSearchConditionCombined()
     * @generated
     */
	int SEARCH_CONDITION_COMBINED = 18;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__EANNOTATIONS = QUERY_SEARCH_CONDITION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__NAME = QUERY_SEARCH_CONDITION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__DEPENDENCIES = QUERY_SEARCH_CONDITION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__DESCRIPTION = QUERY_SEARCH_CONDITION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__LABEL = QUERY_SEARCH_CONDITION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__COMMENTS = QUERY_SEARCH_CONDITION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEARCH_CONDITION_COMBINED__EXTENSIONS = QUERY_SEARCH_CONDITION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__PRIVILEGES = QUERY_SEARCH_CONDITION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__NEGATED_CONDITION = QUERY_SEARCH_CONDITION__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__UPDATE_STATEMENT = QUERY_SEARCH_CONDITION__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__DELETE_STATEMENT = QUERY_SEARCH_CONDITION__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__TABLE_JOINED = QUERY_SEARCH_CONDITION__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__COMBINED_LEFT = QUERY_SEARCH_CONDITION__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__COMBINED_RIGHT = QUERY_SEARCH_CONDITION__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__QUERY_SELECT_HAVING = QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__QUERY_SELECT_WHERE = QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__VALUE_EXPR_CASE_SEARCH_CONTENT = QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__NEST = QUERY_SEARCH_CONDITION__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEARCH_CONDITION_COMBINED__MERGE_ON_CONDITION = QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Combined Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__COMBINED_OPERATOR = QUERY_SEARCH_CONDITION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Left Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__LEFT_CONDITION = QUERY_SEARCH_CONDITION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Right Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED__RIGHT_CONDITION = QUERY_SEARCH_CONDITION_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Search Condition Combined</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_COMBINED_FEATURE_COUNT = QUERY_SEARCH_CONDITION_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl <em>Order By Specification</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getOrderBySpecification()
     * @generated
     */
	int ORDER_BY_SPECIFICATION = 74;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORDER_BY_SPECIFICATION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Descending</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__DESCENDING = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Ordering Spec Option</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Null Ordering Option</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Select Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION__SELECT_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORDER_BY_SPECIFICATION__QUERY = SQL_QUERY_OBJECT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Order By Specification</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_SPECIFICATION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderByValueExpressionImpl <em>Order By Value Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.OrderByValueExpressionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getOrderByValueExpression()
     * @generated
     */
	int ORDER_BY_VALUE_EXPRESSION = 19;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__EANNOTATIONS = ORDER_BY_SPECIFICATION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__NAME = ORDER_BY_SPECIFICATION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__DEPENDENCIES = ORDER_BY_SPECIFICATION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__DESCRIPTION = ORDER_BY_SPECIFICATION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__LABEL = ORDER_BY_SPECIFICATION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__COMMENTS = ORDER_BY_SPECIFICATION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORDER_BY_VALUE_EXPRESSION__EXTENSIONS = ORDER_BY_SPECIFICATION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__PRIVILEGES = ORDER_BY_SPECIFICATION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Descending</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__DESCENDING = ORDER_BY_SPECIFICATION__DESCENDING;

	/**
     * The feature id for the '<em><b>Ordering Spec Option</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__ORDERING_SPEC_OPTION = ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION;

	/**
     * The feature id for the '<em><b>Null Ordering Option</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__NULL_ORDERING_OPTION = ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION;

	/**
     * The feature id for the '<em><b>Select Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__SELECT_STATEMENT = ORDER_BY_SPECIFICATION__SELECT_STATEMENT;

	/**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORDER_BY_VALUE_EXPRESSION__QUERY = ORDER_BY_SPECIFICATION__QUERY;

    /**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION__VALUE_EXPR = ORDER_BY_SPECIFICATION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Order By Value Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_VALUE_EXPRESSION_FEATURE_COUNT = ORDER_BY_SPECIFICATION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryCombinedImpl <em>Query Combined</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryCombinedImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryCombined()
     * @generated
     */
	int QUERY_COMBINED = 20;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__EANNOTATIONS = QUERY_EXPRESSION_BODY__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__NAME = QUERY_EXPRESSION_BODY__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__DEPENDENCIES = QUERY_EXPRESSION_BODY__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__DESCRIPTION = QUERY_EXPRESSION_BODY__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__LABEL = QUERY_EXPRESSION_BODY__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__COMMENTS = QUERY_EXPRESSION_BODY__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_COMBINED__EXTENSIONS = QUERY_EXPRESSION_BODY__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__PRIVILEGES = QUERY_EXPRESSION_BODY__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__TABLE_JOINED_RIGHT = QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__TABLE_JOINED_LEFT = QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__QUERY_SELECT = QUERY_EXPRESSION_BODY__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__NEST = QUERY_EXPRESSION_BODY__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_COMBINED__MERGE_SOURCE_TABLE = QUERY_EXPRESSION_BODY__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__COLUMN_LIST = QUERY_EXPRESSION_BODY__COLUMN_LIST;

	/**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__TABLE_CORRELATION = QUERY_EXPRESSION_BODY__TABLE_CORRELATION;

	/**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__RESULT_TABLE_ALL_COLUMNS = QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS;

	/**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__VALUE_EXPR_COLUMNS = QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS;

	/**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_COMBINED__MERGE_TARGET_TABLE = QUERY_EXPRESSION_BODY__MERGE_TARGET_TABLE;

    /**
     * The feature id for the '<em><b>Row Fetch Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_COMBINED__ROW_FETCH_LIMIT = QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT;

    /**
     * The feature id for the '<em><b>Query Expression</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__QUERY_EXPRESSION = QUERY_EXPRESSION_BODY__QUERY_EXPRESSION;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__COMBINED_LEFT = QUERY_EXPRESSION_BODY__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__COMBINED_RIGHT = QUERY_EXPRESSION_BODY__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Predicate Exists</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__PREDICATE_EXISTS = QUERY_EXPRESSION_BODY__PREDICATE_EXISTS;

	/**
     * The feature id for the '<em><b>Update Source Query</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__UPDATE_SOURCE_QUERY = QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY;

	/**
     * The feature id for the '<em><b>With Table Specification</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__WITH_TABLE_SPECIFICATION = QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION;

	/**
     * The feature id for the '<em><b>Query Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_COMBINED__QUERY_NEST = QUERY_EXPRESSION_BODY__QUERY_NEST;

    /**
     * The feature id for the '<em><b>Sort Spec List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_COMBINED__SORT_SPEC_LIST = QUERY_EXPRESSION_BODY__SORT_SPEC_LIST;

    /**
     * The feature id for the '<em><b>Combined Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__COMBINED_OPERATOR = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Left Query</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__LEFT_QUERY = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Right Query</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED__RIGHT_QUERY = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Query Combined</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_COMBINED_FEATURE_COUNT = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl <em>Query Select</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQuerySelect()
     * @generated
     */
	int QUERY_SELECT = 21;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__EANNOTATIONS = QUERY_EXPRESSION_BODY__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__NAME = QUERY_EXPRESSION_BODY__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__DEPENDENCIES = QUERY_EXPRESSION_BODY__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__DESCRIPTION = QUERY_EXPRESSION_BODY__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__LABEL = QUERY_EXPRESSION_BODY__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__COMMENTS = QUERY_EXPRESSION_BODY__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_SELECT__EXTENSIONS = QUERY_EXPRESSION_BODY__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__PRIVILEGES = QUERY_EXPRESSION_BODY__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__TABLE_JOINED_RIGHT = QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__TABLE_JOINED_LEFT = QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__QUERY_SELECT = QUERY_EXPRESSION_BODY__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__NEST = QUERY_EXPRESSION_BODY__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_SELECT__MERGE_SOURCE_TABLE = QUERY_EXPRESSION_BODY__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__COLUMN_LIST = QUERY_EXPRESSION_BODY__COLUMN_LIST;

	/**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__TABLE_CORRELATION = QUERY_EXPRESSION_BODY__TABLE_CORRELATION;

	/**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__RESULT_TABLE_ALL_COLUMNS = QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS;

	/**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__VALUE_EXPR_COLUMNS = QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS;

	/**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_SELECT__MERGE_TARGET_TABLE = QUERY_EXPRESSION_BODY__MERGE_TARGET_TABLE;

    /**
     * The feature id for the '<em><b>Row Fetch Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_SELECT__ROW_FETCH_LIMIT = QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT;

    /**
     * The feature id for the '<em><b>Query Expression</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__QUERY_EXPRESSION = QUERY_EXPRESSION_BODY__QUERY_EXPRESSION;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__COMBINED_LEFT = QUERY_EXPRESSION_BODY__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__COMBINED_RIGHT = QUERY_EXPRESSION_BODY__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Predicate Exists</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__PREDICATE_EXISTS = QUERY_EXPRESSION_BODY__PREDICATE_EXISTS;

	/**
     * The feature id for the '<em><b>Update Source Query</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__UPDATE_SOURCE_QUERY = QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY;

	/**
     * The feature id for the '<em><b>With Table Specification</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__WITH_TABLE_SPECIFICATION = QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION;

	/**
     * The feature id for the '<em><b>Query Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_SELECT__QUERY_NEST = QUERY_EXPRESSION_BODY__QUERY_NEST;

    /**
     * The feature id for the '<em><b>Sort Spec List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_SELECT__SORT_SPEC_LIST = QUERY_EXPRESSION_BODY__SORT_SPEC_LIST;

    /**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__DISTINCT = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Having Clause</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__HAVING_CLAUSE = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Where Clause</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__WHERE_CLAUSE = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Group By Clause</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__GROUP_BY_CLAUSE = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Select Clause</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__SELECT_CLAUSE = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>From Clause</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__FROM_CLAUSE = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Into Clause</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT__INTO_CLAUSE = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 6;

	/**
     * The number of structural features of the '<em>Query Select</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_SELECT_FEATURE_COUNT = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 7;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSpecificationImpl <em>Grouping Specification</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingSpecificationImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingSpecification()
     * @generated
     */
	int GROUPING_SPECIFICATION = 22;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SPECIFICATION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SPECIFICATION__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SPECIFICATION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SPECIFICATION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SPECIFICATION__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SPECIFICATION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUPING_SPECIFICATION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SPECIFICATION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SPECIFICATION__QUERY_SELECT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Grouping Specification</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SPECIFICATION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryResultSpecificationImpl <em>Query Result Specification</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryResultSpecificationImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryResultSpecification()
     * @generated
     */
	int QUERY_RESULT_SPECIFICATION = 23;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_RESULT_SPECIFICATION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_RESULT_SPECIFICATION__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_RESULT_SPECIFICATION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_RESULT_SPECIFICATION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_RESULT_SPECIFICATION__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_RESULT_SPECIFICATION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_RESULT_SPECIFICATION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_RESULT_SPECIFICATION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_RESULT_SPECIFICATION__QUERY_SELECT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Query Result Specification</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_RESULT_SPECIFICATION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ResultTableAllColumnsImpl <em>Result Table All Columns</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ResultTableAllColumnsImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getResultTableAllColumns()
     * @generated
     */
	int RESULT_TABLE_ALL_COLUMNS = 24;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_TABLE_ALL_COLUMNS__EANNOTATIONS = QUERY_RESULT_SPECIFICATION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_TABLE_ALL_COLUMNS__NAME = QUERY_RESULT_SPECIFICATION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_TABLE_ALL_COLUMNS__DEPENDENCIES = QUERY_RESULT_SPECIFICATION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_TABLE_ALL_COLUMNS__DESCRIPTION = QUERY_RESULT_SPECIFICATION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_TABLE_ALL_COLUMNS__LABEL = QUERY_RESULT_SPECIFICATION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_TABLE_ALL_COLUMNS__COMMENTS = QUERY_RESULT_SPECIFICATION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESULT_TABLE_ALL_COLUMNS__EXTENSIONS = QUERY_RESULT_SPECIFICATION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_TABLE_ALL_COLUMNS__PRIVILEGES = QUERY_RESULT_SPECIFICATION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_TABLE_ALL_COLUMNS__QUERY_SELECT = QUERY_RESULT_SPECIFICATION__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Table Expr</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR = QUERY_RESULT_SPECIFICATION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Result Table All Columns</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_TABLE_ALL_COLUMNS_FEATURE_COUNT = QUERY_RESULT_SPECIFICATION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ResultColumnImpl <em>Result Column</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ResultColumnImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getResultColumn()
     * @generated
     */
	int RESULT_COLUMN = 25;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN__EANNOTATIONS = QUERY_RESULT_SPECIFICATION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN__NAME = QUERY_RESULT_SPECIFICATION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN__DEPENDENCIES = QUERY_RESULT_SPECIFICATION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN__DESCRIPTION = QUERY_RESULT_SPECIFICATION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN__LABEL = QUERY_RESULT_SPECIFICATION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN__COMMENTS = QUERY_RESULT_SPECIFICATION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESULT_COLUMN__EXTENSIONS = QUERY_RESULT_SPECIFICATION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN__PRIVILEGES = QUERY_RESULT_SPECIFICATION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN__QUERY_SELECT = QUERY_RESULT_SPECIFICATION__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN__VALUE_EXPR = QUERY_RESULT_SPECIFICATION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Order By Result Col</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN__ORDER_BY_RESULT_COL = QUERY_RESULT_SPECIFICATION_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Result Column</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESULT_COLUMN_FEATURE_COUNT = QUERY_RESULT_SPECIFICATION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBasicImpl <em>Predicate Basic</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateBasicImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateBasic()
     * @generated
     */
	int PREDICATE_BASIC = 26;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__EANNOTATIONS = PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__NAME = PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__DEPENDENCIES = PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__DESCRIPTION = PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__LABEL = PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__COMMENTS = PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_BASIC__EXTENSIONS = PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__PRIVILEGES = PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__NEGATED_CONDITION = PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__UPDATE_STATEMENT = PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__DELETE_STATEMENT = PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__TABLE_JOINED = PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__COMBINED_LEFT = PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__COMBINED_RIGHT = PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__QUERY_SELECT_HAVING = PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__QUERY_SELECT_WHERE = PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__NEST = PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_BASIC__MERGE_ON_CONDITION = PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__NEGATED_PREDICATE = PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__HAS_SELECTIVITY = PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__SELECTIVITY_VALUE = PREDICATE__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Comparison Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__COMPARISON_OPERATOR = PREDICATE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Right Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__RIGHT_VALUE_EXPR = PREDICATE_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Left Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC__LEFT_VALUE_EXPR = PREDICATE_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Predicate Basic</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BASIC_FEATURE_COUNT = PREDICATE_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedImpl <em>Predicate Quantified</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateQuantified()
     * @generated
     */
	int PREDICATE_QUANTIFIED = 27;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__EANNOTATIONS = PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__NAME = PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__DEPENDENCIES = PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__DESCRIPTION = PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__LABEL = PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__COMMENTS = PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_QUANTIFIED__EXTENSIONS = PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__PRIVILEGES = PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__NEGATED_CONDITION = PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__UPDATE_STATEMENT = PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__DELETE_STATEMENT = PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__TABLE_JOINED = PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__COMBINED_LEFT = PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__COMBINED_RIGHT = PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__QUERY_SELECT_HAVING = PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__QUERY_SELECT_WHERE = PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__NEST = PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_QUANTIFIED__MERGE_ON_CONDITION = PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__NEGATED_PREDICATE = PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__HAS_SELECTIVITY = PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED__SELECTIVITY_VALUE = PREDICATE__SELECTIVITY_VALUE;

	/**
     * The number of structural features of the '<em>Predicate Quantified</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_FEATURE_COUNT = PREDICATE_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl <em>Predicate Between</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateBetween()
     * @generated
     */
	int PREDICATE_BETWEEN = 28;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__EANNOTATIONS = PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__NAME = PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__DEPENDENCIES = PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__DESCRIPTION = PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__LABEL = PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__COMMENTS = PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_BETWEEN__EXTENSIONS = PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__PRIVILEGES = PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__NEGATED_CONDITION = PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__UPDATE_STATEMENT = PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__DELETE_STATEMENT = PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__TABLE_JOINED = PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__COMBINED_LEFT = PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__COMBINED_RIGHT = PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__QUERY_SELECT_HAVING = PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__QUERY_SELECT_WHERE = PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__NEST = PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_BETWEEN__MERGE_ON_CONDITION = PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__NEGATED_PREDICATE = PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__HAS_SELECTIVITY = PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__SELECTIVITY_VALUE = PREDICATE__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Not Between</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__NOT_BETWEEN = PREDICATE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Left Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__LEFT_VALUE_EXPR = PREDICATE_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Right Value Expr1</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1 = PREDICATE_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Right Value Expr2</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2 = PREDICATE_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Predicate Between</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_BETWEEN_FEATURE_COUNT = PREDICATE_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateExistsImpl <em>Predicate Exists</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateExistsImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateExists()
     * @generated
     */
	int PREDICATE_EXISTS = 29;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__EANNOTATIONS = PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__NAME = PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__DEPENDENCIES = PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__DESCRIPTION = PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__LABEL = PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__COMMENTS = PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_EXISTS__EXTENSIONS = PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__PRIVILEGES = PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__NEGATED_CONDITION = PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__UPDATE_STATEMENT = PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__DELETE_STATEMENT = PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__TABLE_JOINED = PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__COMBINED_LEFT = PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__COMBINED_RIGHT = PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__QUERY_SELECT_HAVING = PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__QUERY_SELECT_WHERE = PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__NEST = PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_EXISTS__MERGE_ON_CONDITION = PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__NEGATED_PREDICATE = PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__HAS_SELECTIVITY = PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__SELECTIVITY_VALUE = PREDICATE__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Query Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS__QUERY_EXPR = PREDICATE_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Predicate Exists</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_EXISTS_FEATURE_COUNT = PREDICATE_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInImpl <em>Predicate In</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateInImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateIn()
     * @generated
     */
	int PREDICATE_IN = 30;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__EANNOTATIONS = PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__NAME = PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__DEPENDENCIES = PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__DESCRIPTION = PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__LABEL = PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__COMMENTS = PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_IN__EXTENSIONS = PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__PRIVILEGES = PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__NEGATED_CONDITION = PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__UPDATE_STATEMENT = PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__DELETE_STATEMENT = PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__TABLE_JOINED = PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__COMBINED_LEFT = PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__COMBINED_RIGHT = PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__QUERY_SELECT_HAVING = PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__QUERY_SELECT_WHERE = PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__NEST = PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_IN__MERGE_ON_CONDITION = PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__NEGATED_PREDICATE = PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__HAS_SELECTIVITY = PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__SELECTIVITY_VALUE = PREDICATE__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Not In</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN__NOT_IN = PREDICATE_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Predicate In</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_FEATURE_COUNT = PREDICATE_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl <em>Predicate Like</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateLike()
     * @generated
     */
	int PREDICATE_LIKE = 31;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__EANNOTATIONS = PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__NAME = PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__DEPENDENCIES = PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__DESCRIPTION = PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__LABEL = PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__COMMENTS = PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_LIKE__EXTENSIONS = PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__PRIVILEGES = PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__NEGATED_CONDITION = PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__UPDATE_STATEMENT = PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__DELETE_STATEMENT = PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__TABLE_JOINED = PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__COMBINED_LEFT = PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__COMBINED_RIGHT = PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__QUERY_SELECT_HAVING = PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__QUERY_SELECT_WHERE = PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__NEST = PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_LIKE__MERGE_ON_CONDITION = PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__NEGATED_PREDICATE = PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__HAS_SELECTIVITY = PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__SELECTIVITY_VALUE = PREDICATE__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Not Like</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__NOT_LIKE = PREDICATE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Pattern Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__PATTERN_VALUE_EXPR = PREDICATE_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Matching Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__MATCHING_VALUE_EXPR = PREDICATE_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Escape Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE__ESCAPE_VALUE_EXPR = PREDICATE_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Predicate Like</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_LIKE_FEATURE_COUNT = PREDICATE_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateIsNullImpl <em>Predicate Is Null</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateIsNullImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateIsNull()
     * @generated
     */
	int PREDICATE_IS_NULL = 32;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__EANNOTATIONS = PREDICATE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__NAME = PREDICATE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__DEPENDENCIES = PREDICATE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__DESCRIPTION = PREDICATE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__LABEL = PREDICATE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__COMMENTS = PREDICATE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_IS_NULL__EXTENSIONS = PREDICATE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__PRIVILEGES = PREDICATE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__NEGATED_CONDITION = PREDICATE__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__UPDATE_STATEMENT = PREDICATE__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__DELETE_STATEMENT = PREDICATE__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__TABLE_JOINED = PREDICATE__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__COMBINED_LEFT = PREDICATE__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__COMBINED_RIGHT = PREDICATE__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__QUERY_SELECT_HAVING = PREDICATE__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__QUERY_SELECT_WHERE = PREDICATE__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__NEST = PREDICATE__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_IS_NULL__MERGE_ON_CONDITION = PREDICATE__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__NEGATED_PREDICATE = PREDICATE__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__HAS_SELECTIVITY = PREDICATE__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__SELECTIVITY_VALUE = PREDICATE__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Not Null</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__NOT_NULL = PREDICATE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL__VALUE_EXPR = PREDICATE_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Predicate Is Null</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IS_NULL_FEATURE_COUNT = PREDICATE_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl <em>Predicate Quantified Value Select</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateQuantifiedValueSelect()
     * @generated
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT = 33;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__EANNOTATIONS = PREDICATE_QUANTIFIED__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__NAME = PREDICATE_QUANTIFIED__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__DEPENDENCIES = PREDICATE_QUANTIFIED__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__DESCRIPTION = PREDICATE_QUANTIFIED__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__LABEL = PREDICATE_QUANTIFIED__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__COMMENTS = PREDICATE_QUANTIFIED__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_QUANTIFIED_VALUE_SELECT__EXTENSIONS = PREDICATE_QUANTIFIED__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__PRIVILEGES = PREDICATE_QUANTIFIED__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__NEGATED_CONDITION = PREDICATE_QUANTIFIED__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__UPDATE_STATEMENT = PREDICATE_QUANTIFIED__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__DELETE_STATEMENT = PREDICATE_QUANTIFIED__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__TABLE_JOINED = PREDICATE_QUANTIFIED__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_LEFT = PREDICATE_QUANTIFIED__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__COMBINED_RIGHT = PREDICATE_QUANTIFIED__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_HAVING = PREDICATE_QUANTIFIED__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_SELECT_WHERE = PREDICATE_QUANTIFIED__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE_QUANTIFIED__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__NEST = PREDICATE_QUANTIFIED__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_QUANTIFIED_VALUE_SELECT__MERGE_ON_CONDITION = PREDICATE_QUANTIFIED__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__NEGATED_PREDICATE = PREDICATE_QUANTIFIED__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__HAS_SELECTIVITY = PREDICATE_QUANTIFIED__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__SELECTIVITY_VALUE = PREDICATE_QUANTIFIED__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Quantified Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE = PREDICATE_QUANTIFIED_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Comparison Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR = PREDICATE_QUANTIFIED_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Query Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR = PREDICATE_QUANTIFIED_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR = PREDICATE_QUANTIFIED_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Predicate Quantified Value Select</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_VALUE_SELECT_FEATURE_COUNT = PREDICATE_QUANTIFIED_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedRowSelectImpl <em>Predicate Quantified Row Select</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedRowSelectImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateQuantifiedRowSelect()
     * @generated
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT = 34;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__EANNOTATIONS = PREDICATE_QUANTIFIED__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__NAME = PREDICATE_QUANTIFIED__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__DEPENDENCIES = PREDICATE_QUANTIFIED__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__DESCRIPTION = PREDICATE_QUANTIFIED__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__LABEL = PREDICATE_QUANTIFIED__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__COMMENTS = PREDICATE_QUANTIFIED__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_QUANTIFIED_ROW_SELECT__EXTENSIONS = PREDICATE_QUANTIFIED__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__PRIVILEGES = PREDICATE_QUANTIFIED__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__NEGATED_CONDITION = PREDICATE_QUANTIFIED__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__UPDATE_STATEMENT = PREDICATE_QUANTIFIED__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__DELETE_STATEMENT = PREDICATE_QUANTIFIED__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__TABLE_JOINED = PREDICATE_QUANTIFIED__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_LEFT = PREDICATE_QUANTIFIED__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__COMBINED_RIGHT = PREDICATE_QUANTIFIED__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_HAVING = PREDICATE_QUANTIFIED__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_SELECT_WHERE = PREDICATE_QUANTIFIED__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE_QUANTIFIED__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__NEST = PREDICATE_QUANTIFIED__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_QUANTIFIED_ROW_SELECT__MERGE_ON_CONDITION = PREDICATE_QUANTIFIED__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__NEGATED_PREDICATE = PREDICATE_QUANTIFIED__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__HAS_SELECTIVITY = PREDICATE_QUANTIFIED__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__SELECTIVITY_VALUE = PREDICATE_QUANTIFIED__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Quantified Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__QUANTIFIED_TYPE = PREDICATE_QUANTIFIED_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Query Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR = PREDICATE_QUANTIFIED_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Value Expr List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST = PREDICATE_QUANTIFIED_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Predicate Quantified Row Select</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_QUANTIFIED_ROW_SELECT_FEATURE_COUNT = PREDICATE_QUANTIFIED_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueSelectImpl <em>Predicate In Value Select</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueSelectImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateInValueSelect()
     * @generated
     */
	int PREDICATE_IN_VALUE_SELECT = 35;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__EANNOTATIONS = PREDICATE_IN__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__NAME = PREDICATE_IN__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__DEPENDENCIES = PREDICATE_IN__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__DESCRIPTION = PREDICATE_IN__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__LABEL = PREDICATE_IN__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__COMMENTS = PREDICATE_IN__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_IN_VALUE_SELECT__EXTENSIONS = PREDICATE_IN__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__PRIVILEGES = PREDICATE_IN__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__NEGATED_CONDITION = PREDICATE_IN__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__UPDATE_STATEMENT = PREDICATE_IN__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__DELETE_STATEMENT = PREDICATE_IN__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__TABLE_JOINED = PREDICATE_IN__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__COMBINED_LEFT = PREDICATE_IN__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__COMBINED_RIGHT = PREDICATE_IN__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__QUERY_SELECT_HAVING = PREDICATE_IN__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__QUERY_SELECT_WHERE = PREDICATE_IN__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__NEST = PREDICATE_IN__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_IN_VALUE_SELECT__MERGE_ON_CONDITION = PREDICATE_IN__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__NEGATED_PREDICATE = PREDICATE_IN__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__HAS_SELECTIVITY = PREDICATE_IN__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__SELECTIVITY_VALUE = PREDICATE_IN__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Not In</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__NOT_IN = PREDICATE_IN__NOT_IN;

	/**
     * The feature id for the '<em><b>Query Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__QUERY_EXPR = PREDICATE_IN_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT__VALUE_EXPR = PREDICATE_IN_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Predicate In Value Select</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_SELECT_FEATURE_COUNT = PREDICATE_IN_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueListImpl <em>Predicate In Value List</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueListImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateInValueList()
     * @generated
     */
	int PREDICATE_IN_VALUE_LIST = 36;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__EANNOTATIONS = PREDICATE_IN__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__NAME = PREDICATE_IN__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__DEPENDENCIES = PREDICATE_IN__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__DESCRIPTION = PREDICATE_IN__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__LABEL = PREDICATE_IN__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__COMMENTS = PREDICATE_IN__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_IN_VALUE_LIST__EXTENSIONS = PREDICATE_IN__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__PRIVILEGES = PREDICATE_IN__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__NEGATED_CONDITION = PREDICATE_IN__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__UPDATE_STATEMENT = PREDICATE_IN__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__DELETE_STATEMENT = PREDICATE_IN__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__TABLE_JOINED = PREDICATE_IN__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__COMBINED_LEFT = PREDICATE_IN__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__COMBINED_RIGHT = PREDICATE_IN__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__QUERY_SELECT_HAVING = PREDICATE_IN__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__QUERY_SELECT_WHERE = PREDICATE_IN__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__NEST = PREDICATE_IN__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_IN_VALUE_LIST__MERGE_ON_CONDITION = PREDICATE_IN__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__NEGATED_PREDICATE = PREDICATE_IN__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__HAS_SELECTIVITY = PREDICATE_IN__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__SELECTIVITY_VALUE = PREDICATE_IN__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Not In</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__NOT_IN = PREDICATE_IN__NOT_IN;

	/**
     * The feature id for the '<em><b>Value Expr List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST = PREDICATE_IN_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST__VALUE_EXPR = PREDICATE_IN_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Predicate In Value List</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_LIST_FEATURE_COUNT = PREDICATE_IN_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueRowSelectImpl <em>Predicate In Value Row Select</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueRowSelectImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateInValueRowSelect()
     * @generated
     */
	int PREDICATE_IN_VALUE_ROW_SELECT = 37;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__EANNOTATIONS = PREDICATE_IN__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__NAME = PREDICATE_IN__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__DEPENDENCIES = PREDICATE_IN__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__DESCRIPTION = PREDICATE_IN__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__LABEL = PREDICATE_IN__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__COMMENTS = PREDICATE_IN__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_IN_VALUE_ROW_SELECT__EXTENSIONS = PREDICATE_IN__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__PRIVILEGES = PREDICATE_IN__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__NEGATED_CONDITION = PREDICATE_IN__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__UPDATE_STATEMENT = PREDICATE_IN__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__DELETE_STATEMENT = PREDICATE_IN__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__TABLE_JOINED = PREDICATE_IN__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__COMBINED_LEFT = PREDICATE_IN__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__COMBINED_RIGHT = PREDICATE_IN__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__QUERY_SELECT_HAVING = PREDICATE_IN__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__QUERY_SELECT_WHERE = PREDICATE_IN__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT = PREDICATE_IN__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__NEST = PREDICATE_IN__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PREDICATE_IN_VALUE_ROW_SELECT__MERGE_ON_CONDITION = PREDICATE_IN__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Negated Predicate</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__NEGATED_PREDICATE = PREDICATE_IN__NEGATED_PREDICATE;

	/**
     * The feature id for the '<em><b>Has Selectivity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__HAS_SELECTIVITY = PREDICATE_IN__HAS_SELECTIVITY;

	/**
     * The feature id for the '<em><b>Selectivity Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__SELECTIVITY_VALUE = PREDICATE_IN__SELECTIVITY_VALUE;

	/**
     * The feature id for the '<em><b>Not In</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__NOT_IN = PREDICATE_IN__NOT_IN;

	/**
     * The feature id for the '<em><b>Value Expr List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST = PREDICATE_IN_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Query Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR = PREDICATE_IN_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Predicate In Value Row Select</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PREDICATE_IN_VALUE_ROW_SELECT_FEATURE_COUNT = PREDICATE_IN_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionAtomicImpl <em>Value Expression Atomic</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionAtomicImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionAtomic()
     * @generated
     */
	int VALUE_EXPRESSION_ATOMIC = 73;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__EANNOTATIONS = QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__NAME = QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__DEPENDENCIES = QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__DESCRIPTION = QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__LABEL = QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__COMMENTS = QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ATOMIC__EXTENSIONS = QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__PRIVILEGES = QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR = QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__DATA_TYPE = QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUES_ROW = QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR = QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN = QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT = QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__BASIC_LEFT = QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN = QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING = QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL = QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT = QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT = QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT = QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT = QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1 = QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2 = QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION = QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR = QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE = QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION = QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__NEST = QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST = QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION = QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW = QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT = QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The number of structural features of the '<em>Value Expression Atomic</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT = QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionSimpleImpl <em>Value Expression Simple</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionSimpleImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionSimple()
     * @generated
     */
	int VALUE_EXPRESSION_SIMPLE = 38;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__EANNOTATIONS = VALUE_EXPRESSION_ATOMIC__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__NAME = VALUE_EXPRESSION_ATOMIC__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__DEPENDENCIES = VALUE_EXPRESSION_ATOMIC__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__DESCRIPTION = VALUE_EXPRESSION_ATOMIC__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__LABEL = VALUE_EXPRESSION_ATOMIC__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__COMMENTS = VALUE_EXPRESSION_ATOMIC__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_SIMPLE__EXTENSIONS = VALUE_EXPRESSION_ATOMIC__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__PRIVILEGES = VALUE_EXPRESSION_ATOMIC__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__UNARY_OPERATOR = VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__DATA_TYPE = VALUE_EXPRESSION_ATOMIC__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUES_ROW = VALUE_EXPRESSION_ATOMIC__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__RESULT_COLUMN = VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__BASIC_RIGHT = VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__BASIC_LEFT = VALUE_EXPRESSION_ATOMIC__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__LIKE_PATTERN = VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__LIKE_MATCHING = VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__PREDICATE_NULL = VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__BETWEEN_LEFT = VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__BETWEEN_RIGHT1 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__BETWEEN_RIGHT2 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_CAST = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__GROUPING_EXPR = VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__LIKE_ESCAPE = VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__NEST = VALUE_EXPRESSION_ATOMIC__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_SIMPLE__TABLE_FUNCTION = VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_SIMPLE__VALUE_EXPR_ROW = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_SIMPLE__CALL_STATEMENT = VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE__VALUE = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Value Expression Simple</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SIMPLE_FEATURE_COUNT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionColumnImpl <em>Value Expression Column</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionColumnImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionColumn()
     * @generated
     */
	int VALUE_EXPRESSION_COLUMN = 39;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__EANNOTATIONS = VALUE_EXPRESSION_ATOMIC__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__NAME = VALUE_EXPRESSION_ATOMIC__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__DEPENDENCIES = VALUE_EXPRESSION_ATOMIC__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__DESCRIPTION = VALUE_EXPRESSION_ATOMIC__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__LABEL = VALUE_EXPRESSION_ATOMIC__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__COMMENTS = VALUE_EXPRESSION_ATOMIC__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_COLUMN__EXTENSIONS = VALUE_EXPRESSION_ATOMIC__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__PRIVILEGES = VALUE_EXPRESSION_ATOMIC__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__UNARY_OPERATOR = VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__DATA_TYPE = VALUE_EXPRESSION_ATOMIC__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUES_ROW = VALUE_EXPRESSION_ATOMIC__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__RESULT_COLUMN = VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__BASIC_RIGHT = VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__BASIC_LEFT = VALUE_EXPRESSION_ATOMIC__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__LIKE_PATTERN = VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__LIKE_MATCHING = VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__PREDICATE_NULL = VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__BETWEEN_LEFT = VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__BETWEEN_RIGHT1 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__BETWEEN_RIGHT2 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_CAST = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__GROUPING_EXPR = VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__LIKE_ESCAPE = VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__NEST = VALUE_EXPRESSION_ATOMIC__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_COLUMN__TABLE_FUNCTION = VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_COLUMN__VALUE_EXPR_ROW = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_COLUMN__CALL_STATEMENT = VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Assignment Expr Target</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Parent Table Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Insert Statement</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Table Expr</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__TABLE_EXPR = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Table In Database</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Merge Insert Spec</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Value Expression Column</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COLUMN_FEATURE_COUNT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 6;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionVariableImpl <em>Value Expression Variable</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionVariableImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionVariable()
     * @generated
     */
	int VALUE_EXPRESSION_VARIABLE = 40;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__EANNOTATIONS = VALUE_EXPRESSION_ATOMIC__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__NAME = VALUE_EXPRESSION_ATOMIC__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__DEPENDENCIES = VALUE_EXPRESSION_ATOMIC__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__DESCRIPTION = VALUE_EXPRESSION_ATOMIC__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__LABEL = VALUE_EXPRESSION_ATOMIC__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__COMMENTS = VALUE_EXPRESSION_ATOMIC__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_VARIABLE__EXTENSIONS = VALUE_EXPRESSION_ATOMIC__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__PRIVILEGES = VALUE_EXPRESSION_ATOMIC__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__UNARY_OPERATOR = VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__DATA_TYPE = VALUE_EXPRESSION_ATOMIC__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUES_ROW = VALUE_EXPRESSION_ATOMIC__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__RESULT_COLUMN = VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__BASIC_RIGHT = VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__BASIC_LEFT = VALUE_EXPRESSION_ATOMIC__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__LIKE_PATTERN = VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__LIKE_MATCHING = VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__PREDICATE_NULL = VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__BETWEEN_LEFT = VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__BETWEEN_RIGHT1 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__BETWEEN_RIGHT2 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_CAST = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__GROUPING_EXPR = VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__LIKE_ESCAPE = VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__NEST = VALUE_EXPRESSION_ATOMIC__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_VARIABLE__TABLE_FUNCTION = VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_VARIABLE__VALUE_EXPR_ROW = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_VARIABLE__CALL_STATEMENT = VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE__QUERY_SELECT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Value Expression Variable</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_VARIABLE_FEATURE_COUNT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionScalarSelectImpl <em>Value Expression Scalar Select</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionScalarSelectImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionScalarSelect()
     * @generated
     */
	int VALUE_EXPRESSION_SCALAR_SELECT = 41;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__EANNOTATIONS = VALUE_EXPRESSION_ATOMIC__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__NAME = VALUE_EXPRESSION_ATOMIC__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__DEPENDENCIES = VALUE_EXPRESSION_ATOMIC__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__DESCRIPTION = VALUE_EXPRESSION_ATOMIC__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__LABEL = VALUE_EXPRESSION_ATOMIC__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__COMMENTS = VALUE_EXPRESSION_ATOMIC__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_SCALAR_SELECT__EXTENSIONS = VALUE_EXPRESSION_ATOMIC__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__PRIVILEGES = VALUE_EXPRESSION_ATOMIC__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__UNARY_OPERATOR = VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__DATA_TYPE = VALUE_EXPRESSION_ATOMIC__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUES_ROW = VALUE_EXPRESSION_ATOMIC__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__RESULT_COLUMN = VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__BASIC_RIGHT = VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__BASIC_LEFT = VALUE_EXPRESSION_ATOMIC__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__LIKE_PATTERN = VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__LIKE_MATCHING = VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__PREDICATE_NULL = VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__BETWEEN_LEFT = VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__BETWEEN_RIGHT1 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__BETWEEN_RIGHT2 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_CAST = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__GROUPING_EXPR = VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__LIKE_ESCAPE = VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__NEST = VALUE_EXPRESSION_ATOMIC__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_SCALAR_SELECT__TABLE_FUNCTION = VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_SCALAR_SELECT__VALUE_EXPR_ROW = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_SCALAR_SELECT__CALL_STATEMENT = VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Query Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT__QUERY_EXPR = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Value Expression Scalar Select</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_SCALAR_SELECT_FEATURE_COUNT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionLabeledDurationImpl <em>Value Expression Labeled Duration</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionLabeledDurationImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionLabeledDuration()
     * @generated
     */
	int VALUE_EXPRESSION_LABELED_DURATION = 42;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__EANNOTATIONS = VALUE_EXPRESSION_ATOMIC__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__NAME = VALUE_EXPRESSION_ATOMIC__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__DEPENDENCIES = VALUE_EXPRESSION_ATOMIC__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__DESCRIPTION = VALUE_EXPRESSION_ATOMIC__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__LABEL = VALUE_EXPRESSION_ATOMIC__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__COMMENTS = VALUE_EXPRESSION_ATOMIC__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_LABELED_DURATION__EXTENSIONS = VALUE_EXPRESSION_ATOMIC__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__PRIVILEGES = VALUE_EXPRESSION_ATOMIC__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__UNARY_OPERATOR = VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__DATA_TYPE = VALUE_EXPRESSION_ATOMIC__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUES_ROW = VALUE_EXPRESSION_ATOMIC__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__RESULT_COLUMN = VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__BASIC_RIGHT = VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__BASIC_LEFT = VALUE_EXPRESSION_ATOMIC__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__LIKE_PATTERN = VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__LIKE_MATCHING = VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__PREDICATE_NULL = VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__BETWEEN_LEFT = VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__BETWEEN_RIGHT1 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__BETWEEN_RIGHT2 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_CAST = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__GROUPING_EXPR = VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__LIKE_ESCAPE = VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__NEST = VALUE_EXPRESSION_ATOMIC__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_LABELED_DURATION__TABLE_FUNCTION = VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR_ROW = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_LABELED_DURATION__CALL_STATEMENT = VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Labeled Duration Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__LABELED_DURATION_TYPE = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Value Expression Labeled Duration</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_LABELED_DURATION_FEATURE_COUNT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseImpl <em>Value Expression Case</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCase()
     * @generated
     */
	int VALUE_EXPRESSION_CASE = 43;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__EANNOTATIONS = VALUE_EXPRESSION_ATOMIC__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__NAME = VALUE_EXPRESSION_ATOMIC__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__DEPENDENCIES = VALUE_EXPRESSION_ATOMIC__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__DESCRIPTION = VALUE_EXPRESSION_ATOMIC__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__LABEL = VALUE_EXPRESSION_ATOMIC__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__COMMENTS = VALUE_EXPRESSION_ATOMIC__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE__EXTENSIONS = VALUE_EXPRESSION_ATOMIC__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__PRIVILEGES = VALUE_EXPRESSION_ATOMIC__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__UNARY_OPERATOR = VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__DATA_TYPE = VALUE_EXPRESSION_ATOMIC__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUES_ROW = VALUE_EXPRESSION_ATOMIC__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__RESULT_COLUMN = VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__BASIC_RIGHT = VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__BASIC_LEFT = VALUE_EXPRESSION_ATOMIC__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__LIKE_PATTERN = VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__LIKE_MATCHING = VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__PREDICATE_NULL = VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__BETWEEN_LEFT = VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__BETWEEN_RIGHT1 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__BETWEEN_RIGHT2 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUE_EXPR_CAST = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__GROUPING_EXPR = VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__LIKE_ESCAPE = VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__NEST = VALUE_EXPRESSION_ATOMIC__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE__TABLE_FUNCTION = VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE__VALUE_EXPR_ROW = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE__CALL_STATEMENT = VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Case Else</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE__CASE_ELSE = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Value Expression Case</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_FEATURE_COUNT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCastImpl <em>Value Expression Cast</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCastImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCast()
     * @generated
     */
	int VALUE_EXPRESSION_CAST = 44;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__EANNOTATIONS = VALUE_EXPRESSION_ATOMIC__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__NAME = VALUE_EXPRESSION_ATOMIC__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__DEPENDENCIES = VALUE_EXPRESSION_ATOMIC__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__DESCRIPTION = VALUE_EXPRESSION_ATOMIC__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__LABEL = VALUE_EXPRESSION_ATOMIC__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__COMMENTS = VALUE_EXPRESSION_ATOMIC__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CAST__EXTENSIONS = VALUE_EXPRESSION_ATOMIC__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__PRIVILEGES = VALUE_EXPRESSION_ATOMIC__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__UNARY_OPERATOR = VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__DATA_TYPE = VALUE_EXPRESSION_ATOMIC__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUES_ROW = VALUE_EXPRESSION_ATOMIC__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__RESULT_COLUMN = VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__BASIC_RIGHT = VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__BASIC_LEFT = VALUE_EXPRESSION_ATOMIC__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__LIKE_PATTERN = VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__LIKE_MATCHING = VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__PREDICATE_NULL = VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__BETWEEN_LEFT = VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__BETWEEN_RIGHT1 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__BETWEEN_RIGHT2 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR_CAST = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__GROUPING_EXPR = VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__LIKE_ESCAPE = VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__NEST = VALUE_EXPRESSION_ATOMIC__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CAST__TABLE_FUNCTION = VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CAST__VALUE_EXPR_ROW = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CAST__CALL_STATEMENT = VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST__VALUE_EXPR = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Value Expression Cast</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CAST_FEATURE_COUNT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionNullValueImpl <em>Value Expression Null Value</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionNullValueImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionNullValue()
     * @generated
     */
	int VALUE_EXPRESSION_NULL_VALUE = 45;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__EANNOTATIONS = VALUE_EXPRESSION_ATOMIC__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__NAME = VALUE_EXPRESSION_ATOMIC__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__DEPENDENCIES = VALUE_EXPRESSION_ATOMIC__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__DESCRIPTION = VALUE_EXPRESSION_ATOMIC__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__LABEL = VALUE_EXPRESSION_ATOMIC__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__COMMENTS = VALUE_EXPRESSION_ATOMIC__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_NULL_VALUE__EXTENSIONS = VALUE_EXPRESSION_ATOMIC__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__PRIVILEGES = VALUE_EXPRESSION_ATOMIC__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__UNARY_OPERATOR = VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__DATA_TYPE = VALUE_EXPRESSION_ATOMIC__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUES_ROW = VALUE_EXPRESSION_ATOMIC__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__RESULT_COLUMN = VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__BASIC_RIGHT = VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__BASIC_LEFT = VALUE_EXPRESSION_ATOMIC__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__LIKE_PATTERN = VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__LIKE_MATCHING = VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__PREDICATE_NULL = VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__BETWEEN_LEFT = VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__BETWEEN_RIGHT1 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__BETWEEN_RIGHT2 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_CAST = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__GROUPING_EXPR = VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__LIKE_ESCAPE = VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__NEST = VALUE_EXPRESSION_ATOMIC__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_NULL_VALUE__TABLE_FUNCTION = VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_NULL_VALUE__VALUE_EXPR_ROW = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_NULL_VALUE__CALL_STATEMENT = VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT;

    /**
     * The number of structural features of the '<em>Value Expression Null Value</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NULL_VALUE_FEATURE_COUNT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionDefaultValueImpl <em>Value Expression Default Value</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionDefaultValueImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionDefaultValue()
     * @generated
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE = 46;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__EANNOTATIONS = VALUE_EXPRESSION_ATOMIC__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__NAME = VALUE_EXPRESSION_ATOMIC__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__DEPENDENCIES = VALUE_EXPRESSION_ATOMIC__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__DESCRIPTION = VALUE_EXPRESSION_ATOMIC__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__LABEL = VALUE_EXPRESSION_ATOMIC__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__COMMENTS = VALUE_EXPRESSION_ATOMIC__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_DEFAULT_VALUE__EXTENSIONS = VALUE_EXPRESSION_ATOMIC__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__PRIVILEGES = VALUE_EXPRESSION_ATOMIC__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__UNARY_OPERATOR = VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__DATA_TYPE = VALUE_EXPRESSION_ATOMIC__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUES_ROW = VALUE_EXPRESSION_ATOMIC__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__RESULT_COLUMN = VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__BASIC_RIGHT = VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__BASIC_LEFT = VALUE_EXPRESSION_ATOMIC__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__LIKE_PATTERN = VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__LIKE_MATCHING = VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__PREDICATE_NULL = VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__BETWEEN_LEFT = VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__BETWEEN_RIGHT1 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__BETWEEN_RIGHT2 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_CAST = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__GROUPING_EXPR = VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__LIKE_ESCAPE = VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__NEST = VALUE_EXPRESSION_ATOMIC__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_DEFAULT_VALUE__TABLE_FUNCTION = VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_DEFAULT_VALUE__VALUE_EXPR_ROW = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_DEFAULT_VALUE__CALL_STATEMENT = VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT;

    /**
     * The number of structural features of the '<em>Value Expression Default Value</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_DEFAULT_VALUE_FEATURE_COUNT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 0;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl <em>Value Expression Function</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionFunction()
     * @generated
     */
	int VALUE_EXPRESSION_FUNCTION = 47;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__EANNOTATIONS = VALUE_EXPRESSION_ATOMIC__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__NAME = VALUE_EXPRESSION_ATOMIC__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__DEPENDENCIES = VALUE_EXPRESSION_ATOMIC__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__DESCRIPTION = VALUE_EXPRESSION_ATOMIC__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__LABEL = VALUE_EXPRESSION_ATOMIC__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__COMMENTS = VALUE_EXPRESSION_ATOMIC__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_FUNCTION__EXTENSIONS = VALUE_EXPRESSION_ATOMIC__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__PRIVILEGES = VALUE_EXPRESSION_ATOMIC__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__UNARY_OPERATOR = VALUE_EXPRESSION_ATOMIC__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__DATA_TYPE = VALUE_EXPRESSION_ATOMIC__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUES_ROW = VALUE_EXPRESSION_ATOMIC__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_ATOMIC__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__RESULT_COLUMN = VALUE_EXPRESSION_ATOMIC__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__BASIC_RIGHT = VALUE_EXPRESSION_ATOMIC__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__BASIC_LEFT = VALUE_EXPRESSION_ATOMIC__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__LIKE_PATTERN = VALUE_EXPRESSION_ATOMIC__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__LIKE_MATCHING = VALUE_EXPRESSION_ATOMIC__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__PREDICATE_NULL = VALUE_EXPRESSION_ATOMIC__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_ATOMIC__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__BETWEEN_LEFT = VALUE_EXPRESSION_ATOMIC__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__BETWEEN_RIGHT1 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__BETWEEN_RIGHT2 = VALUE_EXPRESSION_ATOMIC__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CAST = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__GROUPING_EXPR = VALUE_EXPRESSION_ATOMIC__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__LIKE_ESCAPE = VALUE_EXPRESSION_ATOMIC__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__NEST = VALUE_EXPRESSION_ATOMIC__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_ATOMIC__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_FUNCTION__TABLE_FUNCTION = VALUE_EXPRESSION_ATOMIC__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_FUNCTION__VALUE_EXPR_ROW = VALUE_EXPRESSION_ATOMIC__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_FUNCTION__CALL_STATEMENT = VALUE_EXPRESSION_ATOMIC__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__DISTINCT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION__FUNCTION = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>Value Expression Function</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_FUNCTION_FEATURE_COUNT = VALUE_EXPRESSION_ATOMIC_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCombinedImpl <em>Value Expression Combined</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCombinedImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCombined()
     * @generated
     */
	int VALUE_EXPRESSION_COMBINED = 48;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__EANNOTATIONS = QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__NAME = QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__DEPENDENCIES = QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__DESCRIPTION = QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__LABEL = QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__COMMENTS = QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_COMBINED__EXTENSIONS = QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__PRIVILEGES = QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__UNARY_OPERATOR = QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__DATA_TYPE = QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUES_ROW = QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__ORDER_BY_VALUE_EXPR = QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__RESULT_COLUMN = QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__BASIC_RIGHT = QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__BASIC_LEFT = QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__LIKE_PATTERN = QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__LIKE_MATCHING = QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__PREDICATE_NULL = QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_RIGHT = QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__IN_VALUE_LIST_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__IN_VALUE_ROW_SELECT_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__IN_VALUE_SELECT_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__QUANTIFIED_ROW_SELECT_LEFT = QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__QUANTIFIED_VALUE_SELECT_LEFT = QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__BETWEEN_LEFT = QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT1 = QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__BETWEEN_RIGHT2 = QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CAST = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_FUNCTION = QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_LEFT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_COMBINED_RIGHT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__GROUPING_EXPR = QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_ELSE = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_CASE_SEARCH_CONTENT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__LIKE_ESCAPE = QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_LABELED_DURATION = QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__NEST = QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__UPDATE_SOURCE_EXPR_LIST = QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_COMBINED__TABLE_FUNCTION = QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_COMBINED__VALUE_EXPR_ROW = QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_COMBINED__CALL_STATEMENT = QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Combined Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR = QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Left Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR = QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Right Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR = QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Value Expression Combined</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_COMBINED_FEATURE_COUNT = QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsImpl <em>Grouping Sets</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingSets()
     * @generated
     */
	int GROUPING_SETS = 49;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS__EANNOTATIONS = GROUPING_SPECIFICATION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS__NAME = GROUPING_SPECIFICATION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS__DEPENDENCIES = GROUPING_SPECIFICATION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS__DESCRIPTION = GROUPING_SPECIFICATION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS__LABEL = GROUPING_SPECIFICATION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS__COMMENTS = GROUPING_SPECIFICATION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUPING_SETS__EXTENSIONS = GROUPING_SPECIFICATION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS__PRIVILEGES = GROUPING_SPECIFICATION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS__QUERY_SELECT = GROUPING_SPECIFICATION__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Grouping Sets Element List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST = GROUPING_SPECIFICATION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Grouping Sets</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_FEATURE_COUNT = GROUPING_SPECIFICATION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingImpl <em>Grouping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGrouping()
     * @generated
     */
	int GROUPING = 50;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING__EANNOTATIONS = GROUPING_SPECIFICATION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING__NAME = GROUPING_SPECIFICATION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING__DEPENDENCIES = GROUPING_SPECIFICATION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING__DESCRIPTION = GROUPING_SPECIFICATION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING__LABEL = GROUPING_SPECIFICATION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING__COMMENTS = GROUPING_SPECIFICATION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUPING__EXTENSIONS = GROUPING_SPECIFICATION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING__PRIVILEGES = GROUPING_SPECIFICATION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING__QUERY_SELECT = GROUPING_SPECIFICATION__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Grouping Sets Element Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING__GROUPING_SETS_ELEMENT_EXPR = GROUPING_SPECIFICATION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Grouping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_FEATURE_COUNT = GROUPING_SPECIFICATION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementImpl <em>Grouping Sets Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingSetsElement()
     * @generated
     */
	int GROUPING_SETS_ELEMENT = 51;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUPING_SETS_ELEMENT__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Grouping Sets</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT__GROUPING_SETS = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Grouping Sets Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementSublistImpl <em>Grouping Sets Element Sublist</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementSublistImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingSetsElementSublist()
     * @generated
     */
	int GROUPING_SETS_ELEMENT_SUBLIST = 52;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_SUBLIST__EANNOTATIONS = GROUPING_SETS_ELEMENT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_SUBLIST__NAME = GROUPING_SETS_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_SUBLIST__DEPENDENCIES = GROUPING_SETS_ELEMENT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_SUBLIST__DESCRIPTION = GROUPING_SETS_ELEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_SUBLIST__LABEL = GROUPING_SETS_ELEMENT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_SUBLIST__COMMENTS = GROUPING_SETS_ELEMENT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUPING_SETS_ELEMENT_SUBLIST__EXTENSIONS = GROUPING_SETS_ELEMENT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_SUBLIST__PRIVILEGES = GROUPING_SETS_ELEMENT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Grouping Sets</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS = GROUPING_SETS_ELEMENT__GROUPING_SETS;

	/**
     * The feature id for the '<em><b>Grouping Sets Element Expr List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST = GROUPING_SETS_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Grouping Sets Element Sublist</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_SUBLIST_FEATURE_COUNT = GROUPING_SETS_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementExpressionImpl <em>Grouping Sets Element Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementExpressionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingSetsElementExpression()
     * @generated
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION = 53;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION__EANNOTATIONS = GROUPING_SETS_ELEMENT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION__NAME = GROUPING_SETS_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION__DEPENDENCIES = GROUPING_SETS_ELEMENT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION__DESCRIPTION = GROUPING_SETS_ELEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION__LABEL = GROUPING_SETS_ELEMENT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION__COMMENTS = GROUPING_SETS_ELEMENT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUPING_SETS_ELEMENT_EXPRESSION__EXTENSIONS = GROUPING_SETS_ELEMENT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION__PRIVILEGES = GROUPING_SETS_ELEMENT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Grouping Sets</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS = GROUPING_SETS_ELEMENT__GROUPING_SETS;

	/**
     * The feature id for the '<em><b>Grouping Sets Element Sublist</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST = GROUPING_SETS_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Grouping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING = GROUPING_SETS_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Grouping Sets Element Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_SETS_ELEMENT_EXPRESSION_FEATURE_COUNT = GROUPING_SETS_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupImpl <em>Super Group</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSuperGroup()
     * @generated
     */
	int SUPER_GROUP = 54;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__EANNOTATIONS = GROUPING__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__NAME = GROUPING__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__DEPENDENCIES = GROUPING__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__DESCRIPTION = GROUPING__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__LABEL = GROUPING__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__COMMENTS = GROUPING__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUPER_GROUP__EXTENSIONS = GROUPING__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__PRIVILEGES = GROUPING__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__QUERY_SELECT = GROUPING__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Grouping Sets Element Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__GROUPING_SETS_ELEMENT_EXPR = GROUPING__GROUPING_SETS_ELEMENT_EXPR;

	/**
     * The feature id for the '<em><b>Super Group Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__SUPER_GROUP_TYPE = GROUPING_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Super Group Element List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST = GROUPING_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Super Group</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_FEATURE_COUNT = GROUPING_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingExpressionImpl <em>Grouping Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingExpressionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingExpression()
     * @generated
     */
	int GROUPING_EXPRESSION = 55;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__EANNOTATIONS = GROUPING__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__NAME = GROUPING__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__DEPENDENCIES = GROUPING__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__DESCRIPTION = GROUPING__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__LABEL = GROUPING__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__COMMENTS = GROUPING__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GROUPING_EXPRESSION__EXTENSIONS = GROUPING__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__PRIVILEGES = GROUPING__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__QUERY_SELECT = GROUPING__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Grouping Sets Element Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__GROUPING_SETS_ELEMENT_EXPR = GROUPING__GROUPING_SETS_ELEMENT_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__VALUE_EXPR = GROUPING_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Super Group Element Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR = GROUPING_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Grouping Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GROUPING_EXPRESSION_FEATURE_COUNT = GROUPING_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementImpl <em>Super Group Element</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSuperGroupElement()
     * @generated
     */
	int SUPER_GROUP_ELEMENT = 56;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUPER_GROUP_ELEMENT__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Super Group</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT__SUPER_GROUP = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Super Group Element</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementSublistImpl <em>Super Group Element Sublist</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementSublistImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSuperGroupElementSublist()
     * @generated
     */
	int SUPER_GROUP_ELEMENT_SUBLIST = 57;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_SUBLIST__EANNOTATIONS = SUPER_GROUP_ELEMENT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_SUBLIST__NAME = SUPER_GROUP_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_SUBLIST__DEPENDENCIES = SUPER_GROUP_ELEMENT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_SUBLIST__DESCRIPTION = SUPER_GROUP_ELEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_SUBLIST__LABEL = SUPER_GROUP_ELEMENT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_SUBLIST__COMMENTS = SUPER_GROUP_ELEMENT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUPER_GROUP_ELEMENT_SUBLIST__EXTENSIONS = SUPER_GROUP_ELEMENT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_SUBLIST__PRIVILEGES = SUPER_GROUP_ELEMENT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Super Group</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP = SUPER_GROUP_ELEMENT__SUPER_GROUP;

	/**
     * The feature id for the '<em><b>Super Group Element Expr List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST = SUPER_GROUP_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Super Group Element Sublist</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_SUBLIST_FEATURE_COUNT = SUPER_GROUP_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementExpressionImpl <em>Super Group Element Expression</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementExpressionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSuperGroupElementExpression()
     * @generated
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION = 58;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION__EANNOTATIONS = SUPER_GROUP_ELEMENT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION__NAME = SUPER_GROUP_ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION__DEPENDENCIES = SUPER_GROUP_ELEMENT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION__DESCRIPTION = SUPER_GROUP_ELEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION__LABEL = SUPER_GROUP_ELEMENT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION__COMMENTS = SUPER_GROUP_ELEMENT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SUPER_GROUP_ELEMENT_EXPRESSION__EXTENSIONS = SUPER_GROUP_ELEMENT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION__PRIVILEGES = SUPER_GROUP_ELEMENT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Super Group</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP = SUPER_GROUP_ELEMENT__SUPER_GROUP;

	/**
     * The feature id for the '<em><b>Super Group Element Sublist</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST = SUPER_GROUP_ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR = SUPER_GROUP_ELEMENT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Super Group Element Expression</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SUPER_GROUP_ELEMENT_EXPRESSION_FEATURE_COUNT = SUPER_GROUP_ELEMENT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchImpl <em>Value Expression Case Search</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCaseSearch()
     * @generated
     */
	int VALUE_EXPRESSION_CASE_SEARCH = 59;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__EANNOTATIONS = VALUE_EXPRESSION_CASE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__NAME = VALUE_EXPRESSION_CASE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__DEPENDENCIES = VALUE_EXPRESSION_CASE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__DESCRIPTION = VALUE_EXPRESSION_CASE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__LABEL = VALUE_EXPRESSION_CASE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__COMMENTS = VALUE_EXPRESSION_CASE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_SEARCH__EXTENSIONS = VALUE_EXPRESSION_CASE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__PRIVILEGES = VALUE_EXPRESSION_CASE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__UNARY_OPERATOR = VALUE_EXPRESSION_CASE__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__DATA_TYPE = VALUE_EXPRESSION_CASE__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUES_ROW = VALUE_EXPRESSION_CASE__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_CASE__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__RESULT_COLUMN = VALUE_EXPRESSION_CASE__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__BASIC_RIGHT = VALUE_EXPRESSION_CASE__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__BASIC_LEFT = VALUE_EXPRESSION_CASE__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__LIKE_PATTERN = VALUE_EXPRESSION_CASE__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__LIKE_MATCHING = VALUE_EXPRESSION_CASE__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__PREDICATE_NULL = VALUE_EXPRESSION_CASE__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_CASE__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_CASE__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_CASE__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_CASE__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_CASE__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_CASE__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__BETWEEN_LEFT = VALUE_EXPRESSION_CASE__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__BETWEEN_RIGHT1 = VALUE_EXPRESSION_CASE__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__BETWEEN_RIGHT2 = VALUE_EXPRESSION_CASE__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_CAST = VALUE_EXPRESSION_CASE__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_CASE__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_CASE__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_CASE__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__GROUPING_EXPR = VALUE_EXPRESSION_CASE__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__LIKE_ESCAPE = VALUE_EXPRESSION_CASE__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_CASE__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__NEST = VALUE_EXPRESSION_CASE__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_CASE__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_SEARCH__TABLE_FUNCTION = VALUE_EXPRESSION_CASE__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_SEARCH__VALUE_EXPR_ROW = VALUE_EXPRESSION_CASE__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_SEARCH__CALL_STATEMENT = VALUE_EXPRESSION_CASE__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Case Else</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__CASE_ELSE = VALUE_EXPRESSION_CASE__CASE_ELSE;

	/**
     * The feature id for the '<em><b>Search Content List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH__SEARCH_CONTENT_LIST = VALUE_EXPRESSION_CASE_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Value Expression Case Search</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_FEATURE_COUNT = VALUE_EXPRESSION_CASE_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleImpl <em>Value Expression Case Simple</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCaseSimple()
     * @generated
     */
	int VALUE_EXPRESSION_CASE_SIMPLE = 60;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__EANNOTATIONS = VALUE_EXPRESSION_CASE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__NAME = VALUE_EXPRESSION_CASE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__DEPENDENCIES = VALUE_EXPRESSION_CASE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__DESCRIPTION = VALUE_EXPRESSION_CASE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__LABEL = VALUE_EXPRESSION_CASE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__COMMENTS = VALUE_EXPRESSION_CASE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_SIMPLE__EXTENSIONS = VALUE_EXPRESSION_CASE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__PRIVILEGES = VALUE_EXPRESSION_CASE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__UNARY_OPERATOR = VALUE_EXPRESSION_CASE__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__DATA_TYPE = VALUE_EXPRESSION_CASE__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUES_ROW = VALUE_EXPRESSION_CASE__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__ORDER_BY_VALUE_EXPR = VALUE_EXPRESSION_CASE__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__RESULT_COLUMN = VALUE_EXPRESSION_CASE__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__BASIC_RIGHT = VALUE_EXPRESSION_CASE__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__BASIC_LEFT = VALUE_EXPRESSION_CASE__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__LIKE_PATTERN = VALUE_EXPRESSION_CASE__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__LIKE_MATCHING = VALUE_EXPRESSION_CASE__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__PREDICATE_NULL = VALUE_EXPRESSION_CASE__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__IN_VALUE_LIST_RIGHT = VALUE_EXPRESSION_CASE__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__IN_VALUE_LIST_LEFT = VALUE_EXPRESSION_CASE__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__IN_VALUE_ROW_SELECT_LEFT = VALUE_EXPRESSION_CASE__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__IN_VALUE_SELECT_LEFT = VALUE_EXPRESSION_CASE__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__QUANTIFIED_ROW_SELECT_LEFT = VALUE_EXPRESSION_CASE__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__QUANTIFIED_VALUE_SELECT_LEFT = VALUE_EXPRESSION_CASE__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__BETWEEN_LEFT = VALUE_EXPRESSION_CASE__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__BETWEEN_RIGHT1 = VALUE_EXPRESSION_CASE__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__BETWEEN_RIGHT2 = VALUE_EXPRESSION_CASE__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_CAST = VALUE_EXPRESSION_CASE__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_FUNCTION = VALUE_EXPRESSION_CASE__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_COMBINED_LEFT = VALUE_EXPRESSION_CASE__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_COMBINED_RIGHT = VALUE_EXPRESSION_CASE__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__GROUPING_EXPR = VALUE_EXPRESSION_CASE__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_CASE_ELSE = VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_CASE_SIMPLE = VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_CASE_SEARCH_CONTENT = VALUE_EXPRESSION_CASE__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__LIKE_ESCAPE = VALUE_EXPRESSION_CASE__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_LABELED_DURATION = VALUE_EXPRESSION_CASE__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__NEST = VALUE_EXPRESSION_CASE__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__UPDATE_SOURCE_EXPR_LIST = VALUE_EXPRESSION_CASE__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_SIMPLE__TABLE_FUNCTION = VALUE_EXPRESSION_CASE__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR_ROW = VALUE_EXPRESSION_CASE__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_SIMPLE__CALL_STATEMENT = VALUE_EXPRESSION_CASE__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Case Else</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__CASE_ELSE = VALUE_EXPRESSION_CASE__CASE_ELSE;

	/**
     * The feature id for the '<em><b>Content List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__CONTENT_LIST = VALUE_EXPRESSION_CASE_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR = VALUE_EXPRESSION_CASE_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Value Expression Case Simple</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_FEATURE_COUNT = VALUE_EXPRESSION_CASE_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseElseImpl <em>Value Expression Case Else</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseElseImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCaseElse()
     * @generated
     */
	int VALUE_EXPRESSION_CASE_ELSE = 61;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_ELSE__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_ELSE__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_ELSE__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_ELSE__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_ELSE__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_ELSE__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_ELSE__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_ELSE__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Value Expr Case</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Value Expression Case Else</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_ELSE_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchContentImpl <em>Value Expression Case Search Content</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchContentImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCaseSearchContent()
     * @generated
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT = 62;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Search Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Value Expr Case Search</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Value Expression Case Search Content</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SEARCH_CONTENT_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleContentImpl <em>Value Expression Case Simple Content</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleContentImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCaseSimpleContent()
     * @generated
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT = 63;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>When Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Result Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Value Expression Case Simple Content</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_CASE_SIMPLE_CONTENT_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl <em>Table In Database</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableInDatabase()
     * @generated
     */
	int TABLE_IN_DATABASE = 64;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__EANNOTATIONS = TABLE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__NAME = TABLE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__DEPENDENCIES = TABLE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__DESCRIPTION = TABLE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__LABEL = TABLE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__COMMENTS = TABLE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_IN_DATABASE__EXTENSIONS = TABLE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__PRIVILEGES = TABLE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__TABLE_JOINED_RIGHT = TABLE_EXPRESSION__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__TABLE_JOINED_LEFT = TABLE_EXPRESSION__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__QUERY_SELECT = TABLE_EXPRESSION__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__NEST = TABLE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_IN_DATABASE__MERGE_SOURCE_TABLE = TABLE_EXPRESSION__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__COLUMN_LIST = TABLE_EXPRESSION__COLUMN_LIST;

	/**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__TABLE_CORRELATION = TABLE_EXPRESSION__TABLE_CORRELATION;

	/**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__RESULT_TABLE_ALL_COLUMNS = TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS;

	/**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__VALUE_EXPR_COLUMNS = TABLE_EXPRESSION__VALUE_EXPR_COLUMNS;

	/**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_IN_DATABASE__MERGE_TARGET_TABLE = TABLE_EXPRESSION__MERGE_TARGET_TABLE;

    /**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__UPDATE_STATEMENT = TABLE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__DELETE_STATEMENT = TABLE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Insert Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__INSERT_STATEMENT = TABLE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Database Table</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__DATABASE_TABLE = TABLE_EXPRESSION_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Derived Column List</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE__DERIVED_COLUMN_LIST = TABLE_EXPRESSION_FEATURE_COUNT + 4;

	/**
     * The number of structural features of the '<em>Table In Database</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_IN_DATABASE_FEATURE_COUNT = TABLE_EXPRESSION_FEATURE_COUNT + 5;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableFunctionImpl <em>Table Function</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.TableFunctionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableFunction()
     * @generated
     */
	int TABLE_FUNCTION = 65;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__EANNOTATIONS = TABLE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__NAME = TABLE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__DEPENDENCIES = TABLE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__DESCRIPTION = TABLE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__LABEL = TABLE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__COMMENTS = TABLE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_FUNCTION__EXTENSIONS = TABLE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__PRIVILEGES = TABLE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__TABLE_JOINED_RIGHT = TABLE_EXPRESSION__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__TABLE_JOINED_LEFT = TABLE_EXPRESSION__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__QUERY_SELECT = TABLE_EXPRESSION__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__NEST = TABLE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_FUNCTION__MERGE_SOURCE_TABLE = TABLE_EXPRESSION__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__COLUMN_LIST = TABLE_EXPRESSION__COLUMN_LIST;

	/**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__TABLE_CORRELATION = TABLE_EXPRESSION__TABLE_CORRELATION;

	/**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__RESULT_TABLE_ALL_COLUMNS = TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS;

	/**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION__VALUE_EXPR_COLUMNS = TABLE_EXPRESSION__VALUE_EXPR_COLUMNS;

	/**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_FUNCTION__MERGE_TARGET_TABLE = TABLE_EXPRESSION__MERGE_TARGET_TABLE;

    /**
     * The feature id for the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_FUNCTION__FUNCTION = TABLE_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Parameter List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_FUNCTION__PARAMETER_LIST = TABLE_EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Table Function</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FUNCTION_FEATURE_COUNT = TABLE_EXPRESSION_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ColumnNameImpl <em>Column Name</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ColumnNameImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getColumnName()
     * @generated
     */
	int COLUMN_NAME = 68;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_NAME__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_NAME__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_NAME__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_NAME__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_NAME__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_NAME__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COLUMN_NAME__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_NAME__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Correlation</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_NAME__TABLE_CORRELATION = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>With Table Specification</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_NAME__WITH_TABLE_SPECIFICATION = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Column Name</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COLUMN_NAME_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableNestedImpl <em>Table Nested</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.TableNestedImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableNested()
     * @generated
     */
	int TABLE_NESTED = 69;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__EANNOTATIONS = TABLE_REFERENCE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__NAME = TABLE_REFERENCE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__DEPENDENCIES = TABLE_REFERENCE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__DESCRIPTION = TABLE_REFERENCE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__LABEL = TABLE_REFERENCE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__COMMENTS = TABLE_REFERENCE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_NESTED__EXTENSIONS = TABLE_REFERENCE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__PRIVILEGES = TABLE_REFERENCE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__TABLE_JOINED_RIGHT = TABLE_REFERENCE__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__TABLE_JOINED_LEFT = TABLE_REFERENCE__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__QUERY_SELECT = TABLE_REFERENCE__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__NEST = TABLE_REFERENCE__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_NESTED__MERGE_SOURCE_TABLE = TABLE_REFERENCE__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Nested Table Ref</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED__NESTED_TABLE_REF = TABLE_REFERENCE_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Table Nested</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_NESTED_FEATURE_COUNT = TABLE_REFERENCE_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryMergeStatementImpl <em>Query Merge Statement</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryMergeStatementImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryMergeStatement()
     * @generated
     */
	int QUERY_MERGE_STATEMENT = 70;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_MERGE_STATEMENT__EANNOTATIONS = QUERY_CHANGE_STATEMENT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_MERGE_STATEMENT__NAME = QUERY_CHANGE_STATEMENT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_MERGE_STATEMENT__DEPENDENCIES = QUERY_CHANGE_STATEMENT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_MERGE_STATEMENT__DESCRIPTION = QUERY_CHANGE_STATEMENT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_MERGE_STATEMENT__LABEL = QUERY_CHANGE_STATEMENT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_MERGE_STATEMENT__COMMENTS = QUERY_CHANGE_STATEMENT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_MERGE_STATEMENT__EXTENSIONS = QUERY_CHANGE_STATEMENT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_MERGE_STATEMENT__PRIVILEGES = QUERY_CHANGE_STATEMENT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Target Table</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_MERGE_STATEMENT__TARGET_TABLE = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Table</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_MERGE_STATEMENT__SOURCE_TABLE = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>On Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_MERGE_STATEMENT__ON_CONDITION = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Operation Spec List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Query Merge Statement</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_MERGE_STATEMENT_FEATURE_COUNT = QUERY_CHANGE_STATEMENT_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionNestedImpl <em>Search Condition Nested</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionNestedImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSearchConditionNested()
     * @generated
     */
	int SEARCH_CONDITION_NESTED = 71;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__EANNOTATIONS = QUERY_SEARCH_CONDITION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__NAME = QUERY_SEARCH_CONDITION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__DEPENDENCIES = QUERY_SEARCH_CONDITION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__DESCRIPTION = QUERY_SEARCH_CONDITION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__LABEL = QUERY_SEARCH_CONDITION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__COMMENTS = QUERY_SEARCH_CONDITION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEARCH_CONDITION_NESTED__EXTENSIONS = QUERY_SEARCH_CONDITION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__PRIVILEGES = QUERY_SEARCH_CONDITION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Negated Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__NEGATED_CONDITION = QUERY_SEARCH_CONDITION__NEGATED_CONDITION;

	/**
     * The feature id for the '<em><b>Update Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__UPDATE_STATEMENT = QUERY_SEARCH_CONDITION__UPDATE_STATEMENT;

	/**
     * The feature id for the '<em><b>Delete Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__DELETE_STATEMENT = QUERY_SEARCH_CONDITION__DELETE_STATEMENT;

	/**
     * The feature id for the '<em><b>Table Joined</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__TABLE_JOINED = QUERY_SEARCH_CONDITION__TABLE_JOINED;

	/**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__COMBINED_LEFT = QUERY_SEARCH_CONDITION__COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__COMBINED_RIGHT = QUERY_SEARCH_CONDITION__COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Query Select Having</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__QUERY_SELECT_HAVING = QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING;

	/**
     * The feature id for the '<em><b>Query Select Where</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__QUERY_SELECT_WHERE = QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT = QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__NEST = QUERY_SEARCH_CONDITION__NEST;

	/**
     * The feature id for the '<em><b>Merge On Condition</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SEARCH_CONDITION_NESTED__MERGE_ON_CONDITION = QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION;

    /**
     * The feature id for the '<em><b>Nested Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED__NESTED_CONDITION = QUERY_SEARCH_CONDITION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Search Condition Nested</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SEARCH_CONDITION_NESTED_FEATURE_COUNT = QUERY_SEARCH_CONDITION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionNestedImpl <em>Value Expression Nested</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionNestedImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionNested()
     * @generated
     */
	int VALUE_EXPRESSION_NESTED = 72;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__EANNOTATIONS = QUERY_VALUE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__NAME = QUERY_VALUE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__DEPENDENCIES = QUERY_VALUE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__DESCRIPTION = QUERY_VALUE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__LABEL = QUERY_VALUE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__COMMENTS = QUERY_VALUE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_NESTED__EXTENSIONS = QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__PRIVILEGES = QUERY_VALUE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__UNARY_OPERATOR = QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

	/**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__DATA_TYPE = QUERY_VALUE_EXPRESSION__DATA_TYPE;

	/**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUES_ROW = QUERY_VALUE_EXPRESSION__VALUES_ROW;

	/**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__ORDER_BY_VALUE_EXPR = QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

	/**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__RESULT_COLUMN = QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

	/**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__BASIC_RIGHT = QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

	/**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__BASIC_LEFT = QUERY_VALUE_EXPRESSION__BASIC_LEFT;

	/**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__LIKE_PATTERN = QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

	/**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__LIKE_MATCHING = QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

	/**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__PREDICATE_NULL = QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

	/**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_RIGHT = QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

	/**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__IN_VALUE_LIST_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

	/**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__IN_VALUE_ROW_SELECT_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__IN_VALUE_SELECT_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__QUANTIFIED_ROW_SELECT_LEFT = QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__QUANTIFIED_VALUE_SELECT_LEFT = QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

	/**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__BETWEEN_LEFT = QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

	/**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT1 = QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

	/**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__BETWEEN_RIGHT2 = QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

	/**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUE_EXPR_CAST = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

	/**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUE_EXPR_FUNCTION = QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

	/**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_LEFT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

	/**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUE_EXPR_COMBINED_RIGHT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

	/**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__GROUPING_EXPR = QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_ELSE = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

	/**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

	/**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUE_EXPR_CASE_SEARCH_CONTENT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

	/**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__LIKE_ESCAPE = QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

	/**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__VALUE_EXPR_LABELED_DURATION = QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__NEST = QUERY_VALUE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__UPDATE_SOURCE_EXPR_LIST = QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

	/**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_NESTED__TABLE_FUNCTION = QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_NESTED__VALUE_EXPR_ROW = QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_NESTED__CALL_STATEMENT = QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Nested Value Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR = QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Value Expression Nested</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VALUE_EXPRESSION_NESTED_FEATURE_COUNT = QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderByOrdinalImpl <em>Order By Ordinal</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.OrderByOrdinalImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getOrderByOrdinal()
     * @generated
     */
	int ORDER_BY_ORDINAL = 75;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__EANNOTATIONS = ORDER_BY_SPECIFICATION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__NAME = ORDER_BY_SPECIFICATION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__DEPENDENCIES = ORDER_BY_SPECIFICATION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__DESCRIPTION = ORDER_BY_SPECIFICATION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__LABEL = ORDER_BY_SPECIFICATION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__COMMENTS = ORDER_BY_SPECIFICATION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORDER_BY_ORDINAL__EXTENSIONS = ORDER_BY_SPECIFICATION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__PRIVILEGES = ORDER_BY_SPECIFICATION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Descending</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__DESCENDING = ORDER_BY_SPECIFICATION__DESCENDING;

	/**
     * The feature id for the '<em><b>Ordering Spec Option</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__ORDERING_SPEC_OPTION = ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION;

	/**
     * The feature id for the '<em><b>Null Ordering Option</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__NULL_ORDERING_OPTION = ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION;

	/**
     * The feature id for the '<em><b>Select Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__SELECT_STATEMENT = ORDER_BY_SPECIFICATION__SELECT_STATEMENT;

	/**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORDER_BY_ORDINAL__QUERY = ORDER_BY_SPECIFICATION__QUERY;

    /**
     * The feature id for the '<em><b>Ordinal Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL__ORDINAL_VALUE = ORDER_BY_SPECIFICATION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Order By Ordinal</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_ORDINAL_FEATURE_COUNT = ORDER_BY_SPECIFICATION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableCorrelationImpl <em>Table Correlation</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.TableCorrelationImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableCorrelation()
     * @generated
     */
	int TABLE_CORRELATION = 76;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_CORRELATION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_CORRELATION__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_CORRELATION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_CORRELATION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_CORRELATION__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_CORRELATION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_CORRELATION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_CORRELATION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_CORRELATION__TABLE_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Column Name List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_CORRELATION__COLUMN_NAME_LIST = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Table Correlation</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_CORRELATION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceImpl <em>Update Source</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdateSource()
     * @generated
     */
	int UPDATE_SOURCE = 77;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE__NAME = SQL_QUERY_OBJECT__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE__LABEL = SQL_QUERY_OBJECT__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_SOURCE__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Update Assignment Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Update Source</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceExprListImpl <em>Update Source Expr List</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceExprListImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdateSourceExprList()
     * @generated
     */
	int UPDATE_SOURCE_EXPR_LIST = 78;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_EXPR_LIST__EANNOTATIONS = UPDATE_SOURCE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_EXPR_LIST__NAME = UPDATE_SOURCE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_EXPR_LIST__DEPENDENCIES = UPDATE_SOURCE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_EXPR_LIST__DESCRIPTION = UPDATE_SOURCE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_EXPR_LIST__LABEL = UPDATE_SOURCE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_EXPR_LIST__COMMENTS = UPDATE_SOURCE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_SOURCE_EXPR_LIST__EXTENSIONS = UPDATE_SOURCE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_EXPR_LIST__PRIVILEGES = UPDATE_SOURCE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Update Assignment Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_EXPR_LIST__UPDATE_ASSIGNMENT_EXPR = UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR;

	/**
     * The feature id for the '<em><b>Value Expr List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST = UPDATE_SOURCE_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Update Source Expr List</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_EXPR_LIST_FEATURE_COUNT = UPDATE_SOURCE_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceQueryImpl <em>Update Source Query</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceQueryImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdateSourceQuery()
     * @generated
     */
	int UPDATE_SOURCE_QUERY = 79;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_QUERY__EANNOTATIONS = UPDATE_SOURCE__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_QUERY__NAME = UPDATE_SOURCE__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_QUERY__DEPENDENCIES = UPDATE_SOURCE__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_QUERY__DESCRIPTION = UPDATE_SOURCE__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_QUERY__LABEL = UPDATE_SOURCE__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_QUERY__COMMENTS = UPDATE_SOURCE__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_SOURCE_QUERY__EXTENSIONS = UPDATE_SOURCE__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_QUERY__PRIVILEGES = UPDATE_SOURCE__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Update Assignment Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_QUERY__UPDATE_ASSIGNMENT_EXPR = UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR;

	/**
     * The feature id for the '<em><b>Query Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_QUERY__QUERY_EXPR = UPDATE_SOURCE_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Update Source Query</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UPDATE_SOURCE_QUERY_FEATURE_COUNT = UPDATE_SOURCE_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderByResultColumnImpl <em>Order By Result Column</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.OrderByResultColumnImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getOrderByResultColumn()
     * @generated
     */
	int ORDER_BY_RESULT_COLUMN = 80;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__EANNOTATIONS = ORDER_BY_SPECIFICATION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__NAME = ORDER_BY_SPECIFICATION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__DEPENDENCIES = ORDER_BY_SPECIFICATION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__DESCRIPTION = ORDER_BY_SPECIFICATION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__LABEL = ORDER_BY_SPECIFICATION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__COMMENTS = ORDER_BY_SPECIFICATION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORDER_BY_RESULT_COLUMN__EXTENSIONS = ORDER_BY_SPECIFICATION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__PRIVILEGES = ORDER_BY_SPECIFICATION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Descending</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__DESCENDING = ORDER_BY_SPECIFICATION__DESCENDING;

	/**
     * The feature id for the '<em><b>Ordering Spec Option</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__ORDERING_SPEC_OPTION = ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION;

	/**
     * The feature id for the '<em><b>Null Ordering Option</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__NULL_ORDERING_OPTION = ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION;

	/**
     * The feature id for the '<em><b>Select Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__SELECT_STATEMENT = ORDER_BY_SPECIFICATION__SELECT_STATEMENT;

	/**
     * The feature id for the '<em><b>Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ORDER_BY_RESULT_COLUMN__QUERY = ORDER_BY_SPECIFICATION__QUERY;

    /**
     * The feature id for the '<em><b>Result Col</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN__RESULT_COL = ORDER_BY_SPECIFICATION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Order By Result Column</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ORDER_BY_RESULT_COLUMN_FEATURE_COUNT = ORDER_BY_SPECIFICATION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableReferenceImpl <em>With Table Reference</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.WithTableReferenceImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getWithTableReference()
     * @generated
     */
	int WITH_TABLE_REFERENCE = 81;

	/**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__EANNOTATIONS = TABLE_EXPRESSION__EANNOTATIONS;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__NAME = TABLE_EXPRESSION__NAME;

	/**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__DEPENDENCIES = TABLE_EXPRESSION__DEPENDENCIES;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__DESCRIPTION = TABLE_EXPRESSION__DESCRIPTION;

	/**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__LABEL = TABLE_EXPRESSION__LABEL;

	/**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__COMMENTS = TABLE_EXPRESSION__COMMENTS;

	/**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WITH_TABLE_REFERENCE__EXTENSIONS = TABLE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__PRIVILEGES = TABLE_EXPRESSION__PRIVILEGES;

	/**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__TABLE_JOINED_RIGHT = TABLE_EXPRESSION__TABLE_JOINED_RIGHT;

	/**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__TABLE_JOINED_LEFT = TABLE_EXPRESSION__TABLE_JOINED_LEFT;

	/**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__QUERY_SELECT = TABLE_EXPRESSION__QUERY_SELECT;

	/**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__NEST = TABLE_EXPRESSION__NEST;

	/**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WITH_TABLE_REFERENCE__MERGE_SOURCE_TABLE = TABLE_EXPRESSION__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__COLUMN_LIST = TABLE_EXPRESSION__COLUMN_LIST;

	/**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__TABLE_CORRELATION = TABLE_EXPRESSION__TABLE_CORRELATION;

	/**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__RESULT_TABLE_ALL_COLUMNS = TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS;

	/**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__VALUE_EXPR_COLUMNS = TABLE_EXPRESSION__VALUE_EXPR_COLUMNS;

	/**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WITH_TABLE_REFERENCE__MERGE_TARGET_TABLE = TABLE_EXPRESSION__MERGE_TARGET_TABLE;

    /**
     * The feature id for the '<em><b>With Table Specification</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION = TABLE_EXPRESSION_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>With Table Reference</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WITH_TABLE_REFERENCE_FEATURE_COUNT = TABLE_EXPRESSION_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryNestedImpl <em>Query Nested</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryNestedImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryNested()
     * @generated
     */
    int QUERY_NESTED = 82;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__EANNOTATIONS = QUERY_EXPRESSION_BODY__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__NAME = QUERY_EXPRESSION_BODY__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__DEPENDENCIES = QUERY_EXPRESSION_BODY__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__DESCRIPTION = QUERY_EXPRESSION_BODY__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__LABEL = QUERY_EXPRESSION_BODY__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__COMMENTS = QUERY_EXPRESSION_BODY__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__EXTENSIONS = QUERY_EXPRESSION_BODY__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__PRIVILEGES = QUERY_EXPRESSION_BODY__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__TABLE_JOINED_RIGHT = QUERY_EXPRESSION_BODY__TABLE_JOINED_RIGHT;

    /**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__TABLE_JOINED_LEFT = QUERY_EXPRESSION_BODY__TABLE_JOINED_LEFT;

    /**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__QUERY_SELECT = QUERY_EXPRESSION_BODY__QUERY_SELECT;

    /**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__NEST = QUERY_EXPRESSION_BODY__NEST;

    /**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__MERGE_SOURCE_TABLE = QUERY_EXPRESSION_BODY__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__COLUMN_LIST = QUERY_EXPRESSION_BODY__COLUMN_LIST;

    /**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__TABLE_CORRELATION = QUERY_EXPRESSION_BODY__TABLE_CORRELATION;

    /**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__RESULT_TABLE_ALL_COLUMNS = QUERY_EXPRESSION_BODY__RESULT_TABLE_ALL_COLUMNS;

    /**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__VALUE_EXPR_COLUMNS = QUERY_EXPRESSION_BODY__VALUE_EXPR_COLUMNS;

    /**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__MERGE_TARGET_TABLE = QUERY_EXPRESSION_BODY__MERGE_TARGET_TABLE;

    /**
     * The feature id for the '<em><b>Row Fetch Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__ROW_FETCH_LIMIT = QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT;

    /**
     * The feature id for the '<em><b>Query Expression</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__QUERY_EXPRESSION = QUERY_EXPRESSION_BODY__QUERY_EXPRESSION;

    /**
     * The feature id for the '<em><b>Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__COMBINED_LEFT = QUERY_EXPRESSION_BODY__COMBINED_LEFT;

    /**
     * The feature id for the '<em><b>Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__COMBINED_RIGHT = QUERY_EXPRESSION_BODY__COMBINED_RIGHT;

    /**
     * The feature id for the '<em><b>Predicate Exists</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__PREDICATE_EXISTS = QUERY_EXPRESSION_BODY__PREDICATE_EXISTS;

    /**
     * The feature id for the '<em><b>Update Source Query</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__UPDATE_SOURCE_QUERY = QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY;

    /**
     * The feature id for the '<em><b>With Table Specification</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__WITH_TABLE_SPECIFICATION = QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION;

    /**
     * The feature id for the '<em><b>Query Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__QUERY_NEST = QUERY_EXPRESSION_BODY__QUERY_NEST;

    /**
     * The feature id for the '<em><b>Sort Spec List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__SORT_SPEC_LIST = QUERY_EXPRESSION_BODY__SORT_SPEC_LIST;

    /**
     * The feature id for the '<em><b>Nested Query</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED__NESTED_QUERY = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Query Nested</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY_NESTED_FEATURE_COUNT = QUERY_EXPRESSION_BODY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionRowImpl <em>Value Expression Row</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionRowImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionRow()
     * @generated
     */
    int VALUE_EXPRESSION_ROW = 83;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__EANNOTATIONS = QUERY_VALUE_EXPRESSION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__NAME = QUERY_VALUE_EXPRESSION__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__DEPENDENCIES = QUERY_VALUE_EXPRESSION__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__DESCRIPTION = QUERY_VALUE_EXPRESSION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__LABEL = QUERY_VALUE_EXPRESSION__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__COMMENTS = QUERY_VALUE_EXPRESSION__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__EXTENSIONS = QUERY_VALUE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__PRIVILEGES = QUERY_VALUE_EXPRESSION__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Unary Operator</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__UNARY_OPERATOR = QUERY_VALUE_EXPRESSION__UNARY_OPERATOR;

    /**
     * The feature id for the '<em><b>Data Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__DATA_TYPE = QUERY_VALUE_EXPRESSION__DATA_TYPE;

    /**
     * The feature id for the '<em><b>Values Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUES_ROW = QUERY_VALUE_EXPRESSION__VALUES_ROW;

    /**
     * The feature id for the '<em><b>Order By Value Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__ORDER_BY_VALUE_EXPR = QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR;

    /**
     * The feature id for the '<em><b>Result Column</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__RESULT_COLUMN = QUERY_VALUE_EXPRESSION__RESULT_COLUMN;

    /**
     * The feature id for the '<em><b>Basic Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__BASIC_RIGHT = QUERY_VALUE_EXPRESSION__BASIC_RIGHT;

    /**
     * The feature id for the '<em><b>Basic Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__BASIC_LEFT = QUERY_VALUE_EXPRESSION__BASIC_LEFT;

    /**
     * The feature id for the '<em><b>Like Pattern</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__LIKE_PATTERN = QUERY_VALUE_EXPRESSION__LIKE_PATTERN;

    /**
     * The feature id for the '<em><b>Like Matching</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__LIKE_MATCHING = QUERY_VALUE_EXPRESSION__LIKE_MATCHING;

    /**
     * The feature id for the '<em><b>Predicate Null</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__PREDICATE_NULL = QUERY_VALUE_EXPRESSION__PREDICATE_NULL;

    /**
     * The feature id for the '<em><b>In Value List Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__IN_VALUE_LIST_RIGHT = QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT;

    /**
     * The feature id for the '<em><b>In Value List Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__IN_VALUE_LIST_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT;

    /**
     * The feature id for the '<em><b>In Value Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__IN_VALUE_ROW_SELECT_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT;

    /**
     * The feature id for the '<em><b>In Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__IN_VALUE_SELECT_LEFT = QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT;

    /**
     * The feature id for the '<em><b>Quantified Row Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__QUANTIFIED_ROW_SELECT_LEFT = QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT;

    /**
     * The feature id for the '<em><b>Quantified Value Select Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__QUANTIFIED_VALUE_SELECT_LEFT = QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT;

    /**
     * The feature id for the '<em><b>Between Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__BETWEEN_LEFT = QUERY_VALUE_EXPRESSION__BETWEEN_LEFT;

    /**
     * The feature id for the '<em><b>Between Right1</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__BETWEEN_RIGHT1 = QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1;

    /**
     * The feature id for the '<em><b>Between Right2</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__BETWEEN_RIGHT2 = QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2;

    /**
     * The feature id for the '<em><b>Value Expr Cast</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_CAST = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST;

    /**
     * The feature id for the '<em><b>Value Expr Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_FUNCTION = QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Combined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_COMBINED_LEFT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT;

    /**
     * The feature id for the '<em><b>Value Expr Combined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_COMBINED_RIGHT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT;

    /**
     * The feature id for the '<em><b>Grouping Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__GROUPING_EXPR = QUERY_VALUE_EXPRESSION__GROUPING_EXPR;

    /**
     * The feature id for the '<em><b>Value Expr Case Else</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_CASE_ELSE = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE;

    /**
     * The feature id for the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_CASE_SIMPLE = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE;

    /**
     * The feature id for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN;

    /**
     * The feature id for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT;

    /**
     * The feature id for the '<em><b>Value Expr Case Search Content</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_CASE_SEARCH_CONTENT = QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT;

    /**
     * The feature id for the '<em><b>Like Escape</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__LIKE_ESCAPE = QUERY_VALUE_EXPRESSION__LIKE_ESCAPE;

    /**
     * The feature id for the '<em><b>Value Expr Labeled Duration</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_LABELED_DURATION = QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION;

    /**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__NEST = QUERY_VALUE_EXPRESSION__NEST;

    /**
     * The feature id for the '<em><b>Update Source Expr List</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__UPDATE_SOURCE_EXPR_LIST = QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST;

    /**
     * The feature id for the '<em><b>Table Function</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__TABLE_FUNCTION = QUERY_VALUE_EXPRESSION__TABLE_FUNCTION;

    /**
     * The feature id for the '<em><b>Value Expr Row</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_ROW = QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__CALL_STATEMENT = QUERY_VALUE_EXPRESSION__CALL_STATEMENT;

    /**
     * The feature id for the '<em><b>Value Expr List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW__VALUE_EXPR_LIST = QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Value Expression Row</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int VALUE_EXPRESSION_ROW_FEATURE_COUNT = QUERY_VALUE_EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeTargetTableImpl <em>Merge Target Table</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeTargetTableImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeTargetTable()
     * @generated
     */
    int MERGE_TARGET_TABLE = 84;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE__NAME = SQL_QUERY_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE__LABEL = SQL_QUERY_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Merge Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE__MERGE_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Table Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE__TABLE_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Merge Target Table</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_TARGET_TABLE_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeSourceTableImpl <em>Merge Source Table</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeSourceTableImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeSourceTable()
     * @generated
     */
    int MERGE_SOURCE_TABLE = 85;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__NAME = SQL_QUERY_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__LABEL = SQL_QUERY_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Query Merge Statement</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__QUERY_MERGE_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Merge Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__MERGE_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Table Ref</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE__TABLE_REF = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Merge Source Table</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_SOURCE_TABLE_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeOnConditionImpl <em>Merge On Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeOnConditionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeOnCondition()
     * @generated
     */
    int MERGE_ON_CONDITION = 86;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION__NAME = SQL_QUERY_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION__LABEL = SQL_QUERY_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Merge Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION__MERGE_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Search Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION__SEARCH_CONDITION = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Merge On Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_ON_CONDITION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeOperationSpecificationImpl <em>Merge Operation Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeOperationSpecificationImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeOperationSpecification()
     * @generated
     */
    int MERGE_OPERATION_SPECIFICATION = 89;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_OPERATION_SPECIFICATION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_OPERATION_SPECIFICATION__NAME = SQL_QUERY_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_OPERATION_SPECIFICATION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_OPERATION_SPECIFICATION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_OPERATION_SPECIFICATION__LABEL = SQL_QUERY_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_OPERATION_SPECIFICATION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_OPERATION_SPECIFICATION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_OPERATION_SPECIFICATION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Merge Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Merge Operation Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_OPERATION_SPECIFICATION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeUpdateSpecificationImpl <em>Merge Update Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeUpdateSpecificationImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeUpdateSpecification()
     * @generated
     */
    int MERGE_UPDATE_SPECIFICATION = 87;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION__EANNOTATIONS = MERGE_OPERATION_SPECIFICATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION__NAME = MERGE_OPERATION_SPECIFICATION__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION__DEPENDENCIES = MERGE_OPERATION_SPECIFICATION__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION__DESCRIPTION = MERGE_OPERATION_SPECIFICATION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION__LABEL = MERGE_OPERATION_SPECIFICATION__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION__COMMENTS = MERGE_OPERATION_SPECIFICATION__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION__EXTENSIONS = MERGE_OPERATION_SPECIFICATION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION__PRIVILEGES = MERGE_OPERATION_SPECIFICATION__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Merge Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION__MERGE_STATEMENT = MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT;

    /**
     * The feature id for the '<em><b>Assignement Expr List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST = MERGE_OPERATION_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Merge Update Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_UPDATE_SPECIFICATION_FEATURE_COUNT = MERGE_OPERATION_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeInsertSpecificationImpl <em>Merge Insert Specification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeInsertSpecificationImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeInsertSpecification()
     * @generated
     */
    int MERGE_INSERT_SPECIFICATION = 88;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__EANNOTATIONS = MERGE_OPERATION_SPECIFICATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__NAME = MERGE_OPERATION_SPECIFICATION__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__DEPENDENCIES = MERGE_OPERATION_SPECIFICATION__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__DESCRIPTION = MERGE_OPERATION_SPECIFICATION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__LABEL = MERGE_OPERATION_SPECIFICATION__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__COMMENTS = MERGE_OPERATION_SPECIFICATION__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__EXTENSIONS = MERGE_OPERATION_SPECIFICATION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__PRIVILEGES = MERGE_OPERATION_SPECIFICATION__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Merge Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__MERGE_STATEMENT = MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT;

    /**
     * The feature id for the '<em><b>Target Column List</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST = MERGE_OPERATION_SPECIFICATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Values Row</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW = MERGE_OPERATION_SPECIFICATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Merge Insert Specification</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MERGE_INSERT_SPECIFICATION_FEATURE_COUNT = MERGE_OPERATION_SPECIFICATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateOfColumnImpl <em>Update Of Column</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdateOfColumnImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdateOfColumn()
     * @generated
     */
    int UPDATE_OF_COLUMN = 90;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_OF_COLUMN__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_OF_COLUMN__NAME = SQL_QUERY_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_OF_COLUMN__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_OF_COLUMN__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_OF_COLUMN__LABEL = SQL_QUERY_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_OF_COLUMN__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_OF_COLUMN__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_OF_COLUMN__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Updatability Expr</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_OF_COLUMN__UPDATABILITY_EXPR = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Update Of Column</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATE_OF_COLUMN_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdatabilityExpressionImpl <em>Updatability Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdatabilityExpressionImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdatabilityExpression()
     * @generated
     */
    int UPDATABILITY_EXPRESSION = 91;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__NAME = SQL_QUERY_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__LABEL = SQL_QUERY_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Updatability Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__UPDATABILITY_TYPE = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Update Of Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Select Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION__SELECT_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Updatability Expression</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UPDATABILITY_EXPRESSION_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.CallStatementImpl <em>Call Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.CallStatementImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getCallStatement()
     * @generated
     */
    int CALL_STATEMENT = 92;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT__NAME = SQL_QUERY_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT__LABEL = SQL_QUERY_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Argument List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT__ARGUMENT_LIST = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Procedure Ref</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT__PROCEDURE_REF = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Call Statement</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CALL_STATEMENT_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ProcedureReferenceImpl <em>Procedure Reference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.ProcedureReferenceImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getProcedureReference()
     * @generated
     */
    int PROCEDURE_REFERENCE = 93;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE__EANNOTATIONS = SQL_QUERY_OBJECT__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE__NAME = SQL_QUERY_OBJECT__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE__DEPENDENCIES = SQL_QUERY_OBJECT__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE__DESCRIPTION = SQL_QUERY_OBJECT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE__LABEL = SQL_QUERY_OBJECT__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE__COMMENTS = SQL_QUERY_OBJECT__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE__EXTENSIONS = SQL_QUERY_OBJECT__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE__PRIVILEGES = SQL_QUERY_OBJECT__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Call Statement</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE__CALL_STATEMENT = SQL_QUERY_OBJECT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Procedure</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE__PROCEDURE = SQL_QUERY_OBJECT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Procedure Reference</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PROCEDURE_REFERENCE_FEATURE_COUNT = SQL_QUERY_OBJECT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableQueryLateralImpl <em>Table Query Lateral</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.impl.TableQueryLateralImpl
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableQueryLateral()
     * @generated
     */
    int TABLE_QUERY_LATERAL = 94;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__EANNOTATIONS = TABLE_EXPRESSION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__NAME = TABLE_EXPRESSION__NAME;

    /**
     * The feature id for the '<em><b>Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__DEPENDENCIES = TABLE_EXPRESSION__DEPENDENCIES;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__DESCRIPTION = TABLE_EXPRESSION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__LABEL = TABLE_EXPRESSION__LABEL;

    /**
     * The feature id for the '<em><b>Comments</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__COMMENTS = TABLE_EXPRESSION__COMMENTS;

    /**
     * The feature id for the '<em><b>Extensions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__EXTENSIONS = TABLE_EXPRESSION__EXTENSIONS;

    /**
     * The feature id for the '<em><b>Privileges</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__PRIVILEGES = TABLE_EXPRESSION__PRIVILEGES;

    /**
     * The feature id for the '<em><b>Table Joined Right</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__TABLE_JOINED_RIGHT = TABLE_EXPRESSION__TABLE_JOINED_RIGHT;

    /**
     * The feature id for the '<em><b>Table Joined Left</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__TABLE_JOINED_LEFT = TABLE_EXPRESSION__TABLE_JOINED_LEFT;

    /**
     * The feature id for the '<em><b>Query Select</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__QUERY_SELECT = TABLE_EXPRESSION__QUERY_SELECT;

    /**
     * The feature id for the '<em><b>Nest</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__NEST = TABLE_EXPRESSION__NEST;

    /**
     * The feature id for the '<em><b>Merge Source Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__MERGE_SOURCE_TABLE = TABLE_EXPRESSION__MERGE_SOURCE_TABLE;

    /**
     * The feature id for the '<em><b>Column List</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__COLUMN_LIST = TABLE_EXPRESSION__COLUMN_LIST;

    /**
     * The feature id for the '<em><b>Table Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__TABLE_CORRELATION = TABLE_EXPRESSION__TABLE_CORRELATION;

    /**
     * The feature id for the '<em><b>Result Table All Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__RESULT_TABLE_ALL_COLUMNS = TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS;

    /**
     * The feature id for the '<em><b>Value Expr Columns</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__VALUE_EXPR_COLUMNS = TABLE_EXPRESSION__VALUE_EXPR_COLUMNS;

    /**
     * The feature id for the '<em><b>Merge Target Table</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__MERGE_TARGET_TABLE = TABLE_EXPRESSION__MERGE_TARGET_TABLE;

    /**
     * The feature id for the '<em><b>Query</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL__QUERY = TABLE_EXPRESSION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Table Query Lateral</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_QUERY_LATERAL_FEATURE_COUNT = TABLE_EXPRESSION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupType <em>Super Group Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupType
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSuperGroupType()
     * @generated
     */
	int SUPER_GROUP_TYPE = 95;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType <em>Predicate Quantified Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateQuantifiedType()
     * @generated
     */
	int PREDICATE_QUANTIFIED_TYPE = 96;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator <em>Predicate Comparison Operator</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateComparisonOperator()
     * @generated
     */
	int PREDICATE_COMPARISON_OPERATOR = 97;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator <em>Search Condition Combined Operator</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSearchConditionCombinedOperator()
     * @generated
     */
	int SEARCH_CONDITION_COMBINED_OPERATOR = 98;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator <em>Table Joined Operator</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableJoinedOperator()
     * @generated
     */
	int TABLE_JOINED_OPERATOR = 99;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator <em>Query Combined Operator</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryCombinedOperator()
     * @generated
     */
	int QUERY_COMBINED_OPERATOR = 100;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator <em>Value Expression Unary Operator</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionUnaryOperator()
     * @generated
     */
	int VALUE_EXPRESSION_UNARY_OPERATOR = 101;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator <em>Value Expression Combined Operator</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCombinedOperator()
     * @generated
     */
	int VALUE_EXPRESSION_COMBINED_OPERATOR = 102;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType <em>Value Expression Labeled Duration Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionLabeledDurationType()
     * @generated
     */
	int VALUE_EXPRESSION_LABELED_DURATION_TYPE = 103;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.NullOrderingType <em>Null Ordering Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.NullOrderingType
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getNullOrderingType()
     * @generated
     */
	int NULL_ORDERING_TYPE = 104;

	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.OrderingSpecType <em>Ordering Spec Type</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.OrderingSpecType
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getOrderingSpecType()
     * @generated
     */
	int ORDERING_SPEC_TYPE = 105;


	/**
     * The meta object id for the '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityType <em>Updatability Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityType
     * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdatabilityType()
     * @generated
     */
    int UPDATABILITY_TYPE = 106;


    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryStatement <em>Query Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryStatement
     * @generated
     */
	EClass getQueryStatement();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement <em>Query Delete Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Delete Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement
     * @generated
     */
	EClass getQueryDeleteStatement();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereCurrentOfClause <em>Where Current Of Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Where Current Of Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereCurrentOfClause()
     * @see #getQueryDeleteStatement()
     * @generated
     */
	EReference getQueryDeleteStatement_WhereCurrentOfClause();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereClause <em>Where Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Where Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getWhereClause()
     * @see #getQueryDeleteStatement()
     * @generated
     */
	EReference getQueryDeleteStatement_WhereClause();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getTargetTable <em>Target Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Table</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement#getTargetTable()
     * @see #getQueryDeleteStatement()
     * @generated
     */
	EReference getQueryDeleteStatement_TargetTable();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement <em>Query Insert Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Insert Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement
     * @generated
     */
	EClass getQueryInsertStatement();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceQuery <em>Source Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Source Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceQuery()
     * @see #getQueryInsertStatement()
     * @generated
     */
	EReference getQueryInsertStatement_SourceQuery();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceValuesRowList <em>Source Values Row List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Source Values Row List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getSourceValuesRowList()
     * @see #getQueryInsertStatement()
     * @generated
     */
	EReference getQueryInsertStatement_SourceValuesRowList();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetTable <em>Target Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Table</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetTable()
     * @see #getQueryInsertStatement()
     * @generated
     */
	EReference getQueryInsertStatement_TargetTable();

	/**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetColumnList <em>Target Column List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Target Column List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement#getTargetColumnList()
     * @see #getQueryInsertStatement()
     * @generated
     */
	EReference getQueryInsertStatement_TargetColumnList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement <em>Query Select Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Select Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement
     * @generated
     */
	EClass getQuerySelectStatement();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getQueryExpr()
     * @see #getQuerySelectStatement()
     * @generated
     */
	EReference getQuerySelectStatement_QueryExpr();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getOrderByClause <em>Order By Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Order By Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getOrderByClause()
     * @see #getQuerySelectStatement()
     * @generated
     */
	EReference getQuerySelectStatement_OrderByClause();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getUpdatabilityExpr <em>Updatability Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Updatability Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getUpdatabilityExpr()
     * @see #getQuerySelectStatement()
     * @generated
     */
    EReference getQuerySelectStatement_UpdatabilityExpr();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement <em>Query Update Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Update Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement
     * @generated
     */
	EClass getQueryUpdateStatement();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getAssignmentClause <em>Assignment Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Assignment Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getAssignmentClause()
     * @see #getQueryUpdateStatement()
     * @generated
     */
	EReference getQueryUpdateStatement_AssignmentClause();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getWhereCurrentOfClause <em>Where Current Of Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Where Current Of Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getWhereCurrentOfClause()
     * @see #getQueryUpdateStatement()
     * @generated
     */
	EReference getQueryUpdateStatement_WhereCurrentOfClause();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getWhereClause <em>Where Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Where Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getWhereClause()
     * @see #getQueryUpdateStatement()
     * @generated
     */
	EReference getQueryUpdateStatement_WhereClause();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getTargetTable <em>Target Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Table</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getTargetTable()
     * @see #getQueryUpdateStatement()
     * @generated
     */
	EReference getQueryUpdateStatement_TargetTable();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression <em>Update Assignment Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Update Assignment Expression</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression
     * @generated
     */
	EClass getUpdateAssignmentExpression();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getUpdateStatement <em>Update Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Update Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getUpdateStatement()
     * @see #getUpdateAssignmentExpression()
     * @generated
     */
	EReference getUpdateAssignmentExpression_UpdateStatement();

	/**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getTargetColumnList <em>Target Column List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Target Column List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getTargetColumnList()
     * @see #getUpdateAssignmentExpression()
     * @generated
     */
	EReference getUpdateAssignmentExpression_TargetColumnList();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getUpdateSource <em>Update Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Update Source</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getUpdateSource()
     * @see #getUpdateAssignmentExpression()
     * @generated
     */
	EReference getUpdateAssignmentExpression_UpdateSource();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getMergeUpdateSpec <em>Merge Update Spec</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Merge Update Spec</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getMergeUpdateSpec()
     * @see #getUpdateAssignmentExpression()
     * @generated
     */
    EReference getUpdateAssignmentExpression_MergeUpdateSpec();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.CursorReference <em>Cursor Reference</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Cursor Reference</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.CursorReference
     * @generated
     */
	EClass getCursorReference();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.CursorReference#getUpdateStatement <em>Update Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Update Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.CursorReference#getUpdateStatement()
     * @see #getCursorReference()
     * @generated
     */
	EReference getCursorReference_UpdateStatement();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.CursorReference#getDeleteStatement <em>Delete Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Delete Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.CursorReference#getDeleteStatement()
     * @see #getCursorReference()
     * @generated
     */
	EReference getCursorReference_DeleteStatement();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition <em>Query Search Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Search Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition
     * @generated
     */
	EClass getQuerySearchCondition();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#isNegatedCondition <em>Negated Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Negated Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#isNegatedCondition()
     * @see #getQuerySearchCondition()
     * @generated
     */
	EAttribute getQuerySearchCondition_NegatedCondition();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getUpdateStatement <em>Update Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Update Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getUpdateStatement()
     * @see #getQuerySearchCondition()
     * @generated
     */
	EReference getQuerySearchCondition_UpdateStatement();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getDeleteStatement <em>Delete Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Delete Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getDeleteStatement()
     * @see #getQuerySearchCondition()
     * @generated
     */
	EReference getQuerySearchCondition_DeleteStatement();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getTableJoined <em>Table Joined</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Table Joined</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getTableJoined()
     * @see #getQuerySearchCondition()
     * @generated
     */
	EReference getQuerySearchCondition_TableJoined();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedLeft <em>Combined Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Combined Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedLeft()
     * @see #getQuerySearchCondition()
     * @generated
     */
	EReference getQuerySearchCondition_CombinedLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedRight <em>Combined Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Combined Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getCombinedRight()
     * @see #getQuerySearchCondition()
     * @generated
     */
	EReference getQuerySearchCondition_CombinedRight();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectHaving <em>Query Select Having</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query Select Having</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectHaving()
     * @see #getQuerySearchCondition()
     * @generated
     */
	EReference getQuerySearchCondition_QuerySelectHaving();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectWhere <em>Query Select Where</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query Select Where</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getQuerySelectWhere()
     * @see #getQuerySearchCondition()
     * @generated
     */
	EReference getQuerySearchCondition_QuerySelectWhere();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Case Search Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getValueExprCaseSearchContent()
     * @see #getQuerySearchCondition()
     * @generated
     */
	EReference getQuerySearchCondition_ValueExprCaseSearchContent();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getNest <em>Nest</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Nest</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getNest()
     * @see #getQuerySearchCondition()
     * @generated
     */
	EReference getQuerySearchCondition_Nest();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getMergeOnCondition <em>Merge On Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Merge On Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getMergeOnCondition()
     * @see #getQuerySearchCondition()
     * @generated
     */
    EReference getQuerySearchCondition_MergeOnCondition();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody <em>Query Expression Body</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Expression Body</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody
     * @generated
     */
	EClass getQueryExpressionBody();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getRowFetchLimit <em>Row Fetch Limit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Row Fetch Limit</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getRowFetchLimit()
     * @see #getQueryExpressionBody()
     * @generated
     */
    EAttribute getQueryExpressionBody_RowFetchLimit();

    /**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryExpression <em>Query Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query Expression</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryExpression()
     * @see #getQueryExpressionBody()
     * @generated
     */
	EReference getQueryExpressionBody_QueryExpression();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedLeft <em>Combined Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Combined Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedLeft()
     * @see #getQueryExpressionBody()
     * @generated
     */
	EReference getQueryExpressionBody_CombinedLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedRight <em>Combined Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Combined Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getCombinedRight()
     * @see #getQueryExpressionBody()
     * @generated
     */
	EReference getQueryExpressionBody_CombinedRight();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getPredicateExists <em>Predicate Exists</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Predicate Exists</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getPredicateExists()
     * @see #getQueryExpressionBody()
     * @generated
     */
	EReference getQueryExpressionBody_PredicateExists();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getUpdateSourceQuery <em>Update Source Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Update Source Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getUpdateSourceQuery()
     * @see #getQueryExpressionBody()
     * @generated
     */
	EReference getQueryExpressionBody_UpdateSourceQuery();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getWithTableSpecification <em>With Table Specification</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>With Table Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getWithTableSpecification()
     * @see #getQueryExpressionBody()
     * @generated
     */
	EReference getQueryExpressionBody_WithTableSpecification();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryNest <em>Query Nest</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query Nest</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryNest()
     * @see #getQueryExpressionBody()
     * @generated
     */
    EReference getQueryExpressionBody_QueryNest();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getSortSpecList <em>Sort Spec List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Sort Spec List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getSortSpecList()
     * @see #getQueryExpressionBody()
     * @generated
     */
    EReference getQueryExpressionBody_SortSpecList();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression <em>Query Value Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Value Expression</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression
     * @generated
     */
	EClass getQueryValueExpression();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getUnaryOperator <em>Unary Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Unary Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getUnaryOperator()
     * @see #getQueryValueExpression()
     * @generated
     */
	EAttribute getQueryValueExpression_UnaryOperator();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getDataType <em>Data Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Data Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getDataType()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_DataType();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValuesRow <em>Values Row</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Values Row</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValuesRow()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValuesRow();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getOrderByValueExpr <em>Order By Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Order By Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getOrderByValueExpr()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_OrderByValueExpr();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getResultColumn <em>Result Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Result Column</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getResultColumn()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ResultColumn();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicRight <em>Basic Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Basic Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicRight()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_BasicRight();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicLeft <em>Basic Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Basic Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicLeft()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_BasicLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikePattern <em>Like Pattern</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Like Pattern</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikePattern()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_LikePattern();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeMatching <em>Like Matching</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Like Matching</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeMatching()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_LikeMatching();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getPredicateNull <em>Predicate Null</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Predicate Null</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getPredicateNull()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_PredicateNull();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListRight <em>In Value List Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>In Value List Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListRight()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_InValueListRight();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListLeft <em>In Value List Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>In Value List Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListLeft()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_InValueListLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueRowSelectLeft <em>In Value Row Select Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>In Value Row Select Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueRowSelectLeft()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_InValueRowSelectLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueSelectLeft <em>In Value Select Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>In Value Select Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueSelectLeft()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_InValueSelectLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedRowSelectLeft <em>Quantified Row Select Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Quantified Row Select Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedRowSelectLeft()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_QuantifiedRowSelectLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedValueSelectLeft <em>Quantified Value Select Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Quantified Value Select Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedValueSelectLeft()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_QuantifiedValueSelectLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenLeft <em>Between Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Between Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenLeft()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_BetweenLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight1 <em>Between Right1</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Between Right1</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight1()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_BetweenRight1();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight2 <em>Between Right2</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Between Right2</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight2()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_BetweenRight2();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCast <em>Value Expr Cast</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Cast</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCast()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValueExprCast();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprFunction <em>Value Expr Function</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprFunction()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValueExprFunction();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedLeft <em>Value Expr Combined Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Combined Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedLeft()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValueExprCombinedLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedRight <em>Value Expr Combined Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Combined Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedRight()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValueExprCombinedRight();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getGroupingExpr <em>Grouping Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Grouping Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getGroupingExpr()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_GroupingExpr();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseElse <em>Value Expr Case Else</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Case Else</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseElse()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValueExprCaseElse();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimple <em>Value Expr Case Simple</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Case Simple</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimple()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValueExprCaseSimple();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentWhen <em>Value Expr Case Simple Content When</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Case Simple Content When</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentWhen()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValueExprCaseSimpleContentWhen();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentResult <em>Value Expr Case Simple Content Result</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Case Simple Content Result</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentResult()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValueExprCaseSimpleContentResult();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSearchContent <em>Value Expr Case Search Content</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Case Search Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSearchContent()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValueExprCaseSearchContent();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeEscape <em>Like Escape</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Like Escape</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getLikeEscape()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_LikeEscape();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprLabeledDuration <em>Value Expr Labeled Duration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Labeled Duration</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprLabeledDuration()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_ValueExprLabeledDuration();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getNest <em>Nest</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Nest</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getNest()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_Nest();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getUpdateSourceExprList <em>Update Source Expr List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Update Source Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getUpdateSourceExprList()
     * @see #getQueryValueExpression()
     * @generated
     */
	EReference getQueryValueExpression_UpdateSourceExprList();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getTableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Table Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getTableFunction()
     * @see #getQueryValueExpression()
     * @generated
     */
    EReference getQueryValueExpression_TableFunction();

    /**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprRow <em>Value Expr Row</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Row</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprRow()
     * @see #getQueryValueExpression()
     * @generated
     */
    EReference getQueryValueExpression_ValueExprRow();

    /**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getCallStatement <em>Call Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Call Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getCallStatement()
     * @see #getQueryValueExpression()
     * @generated
     */
    EReference getQueryValueExpression_CallStatement();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot <em>Query Expression Root</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Expression Root</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot
     * @generated
     */
	EClass getQueryExpressionRoot();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInsertStatement <em>Insert Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Insert Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInsertStatement()
     * @see #getQueryExpressionRoot()
     * @generated
     */
	EReference getQueryExpressionRoot_InsertStatement();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getSelectStatement <em>Select Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Select Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getSelectStatement()
     * @see #getQueryExpressionRoot()
     * @generated
     */
	EReference getQueryExpressionRoot_SelectStatement();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getWithClause <em>With Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>With Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getWithClause()
     * @see #getQueryExpressionRoot()
     * @generated
     */
	EReference getQueryExpressionRoot_WithClause();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuery()
     * @see #getQueryExpressionRoot()
     * @generated
     */
	EReference getQueryExpressionRoot_Query();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInValueRowSelectRight <em>In Value Row Select Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>In Value Row Select Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInValueRowSelectRight()
     * @see #getQueryExpressionRoot()
     * @generated
     */
	EReference getQueryExpressionRoot_InValueRowSelectRight();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInValueSelectRight <em>In Value Select Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>In Value Select Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInValueSelectRight()
     * @see #getQueryExpressionRoot()
     * @generated
     */
	EReference getQueryExpressionRoot_InValueSelectRight();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedRowSelectRight <em>Quantified Row Select Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Quantified Row Select Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedRowSelectRight()
     * @see #getQueryExpressionRoot()
     * @generated
     */
	EReference getQueryExpressionRoot_QuantifiedRowSelectRight();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedValueSelectRight <em>Quantified Value Select Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Quantified Value Select Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedValueSelectRight()
     * @see #getQueryExpressionRoot()
     * @generated
     */
	EReference getQueryExpressionRoot_QuantifiedValueSelectRight();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getValExprScalarSelect <em>Val Expr Scalar Select</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Val Expr Scalar Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getValExprScalarSelect()
     * @see #getQueryExpressionRoot()
     * @generated
     */
    EReference getQueryExpressionRoot_ValExprScalarSelect();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow <em>Values Row</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Values Row</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValuesRow
     * @generated
     */
	EClass getValuesRow();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getInsertStatement <em>Insert Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Insert Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValuesRow#getInsertStatement()
     * @see #getValuesRow()
     * @generated
     */
	EReference getValuesRow_InsertStatement();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getExprList <em>Expr List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValuesRow#getExprList()
     * @see #getValuesRow()
     * @generated
     */
	EReference getValuesRow_ExprList();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.ValuesRow#getQueryValues <em>Query Values</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query Values</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValuesRow#getQueryValues()
     * @see #getValuesRow()
     * @generated
     */
	EReference getValuesRow_QueryValues();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryValues <em>Query Values</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Values</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValues
     * @generated
     */
	EClass getQueryValues();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QueryValues#getValuesRowList <em>Values Row List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Values Row List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValues#getValuesRowList()
     * @see #getQueryValues()
     * @generated
     */
	EReference getQueryValues_ValuesRowList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.TableReference <em>Table Reference</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Reference</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference
     * @generated
     */
	EClass getTableReference();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedRight <em>Table Joined Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Table Joined Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedRight()
     * @see #getTableReference()
     * @generated
     */
	EReference getTableReference_TableJoinedRight();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedLeft <em>Table Joined Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Table Joined Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference#getTableJoinedLeft()
     * @see #getTableReference()
     * @generated
     */
	EReference getTableReference_TableJoinedLeft();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getQuerySelect <em>Query Select</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference#getQuerySelect()
     * @see #getTableReference()
     * @generated
     */
	EReference getTableReference_QuerySelect();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getNest <em>Nest</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Nest</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference#getNest()
     * @see #getTableReference()
     * @generated
     */
	EReference getTableReference_Nest();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getMergeSourceTable <em>Merge Source Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Merge Source Table</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference#getMergeSourceTable()
     * @see #getTableReference()
     * @generated
     */
    EReference getTableReference_MergeSourceTable();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression <em>Table Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Expression</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression
     * @generated
     */
	EClass getTableExpression();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getColumnList <em>Column List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Column List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression#getColumnList()
     * @see #getTableExpression()
     * @generated
     */
	EReference getTableExpression_ColumnList();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getTableCorrelation <em>Table Correlation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Table Correlation</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression#getTableCorrelation()
     * @see #getTableExpression()
     * @generated
     */
	EReference getTableExpression_TableCorrelation();

	/**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getResultTableAllColumns <em>Result Table All Columns</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Result Table All Columns</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression#getResultTableAllColumns()
     * @see #getTableExpression()
     * @generated
     */
	EReference getTableExpression_ResultTableAllColumns();

	/**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getValueExprColumns <em>Value Expr Columns</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Value Expr Columns</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression#getValueExprColumns()
     * @see #getTableExpression()
     * @generated
     */
	EReference getTableExpression_ValueExprColumns();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getMergeTargetTable <em>Merge Target Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Merge Target Table</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression#getMergeTargetTable()
     * @see #getTableExpression()
     * @generated
     */
    EReference getTableExpression_MergeTargetTable();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined <em>Table Joined</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Joined</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoined
     * @generated
     */
	EClass getTableJoined();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getJoinOperator <em>Join Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Join Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoined#getJoinOperator()
     * @see #getTableJoined()
     * @generated
     */
	EAttribute getTableJoined_JoinOperator();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getJoinCondition <em>Join Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Join Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoined#getJoinCondition()
     * @see #getTableJoined()
     * @generated
     */
	EReference getTableJoined_JoinCondition();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefRight <em>Table Ref Right</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Table Ref Right</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefRight()
     * @see #getTableJoined()
     * @generated
     */
	EReference getTableJoined_TableRefRight();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefLeft <em>Table Ref Left</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Table Ref Left</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoined#getTableRefLeft()
     * @see #getTableJoined()
     * @generated
     */
	EReference getTableJoined_TableRefLeft();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification <em>With Table Specification</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>With Table Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableSpecification
     * @generated
     */
	EClass getWithTableSpecification();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getQueryExpressionRoot <em>Query Expression Root</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query Expression Root</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getQueryExpressionRoot()
     * @see #getWithTableSpecification()
     * @generated
     */
	EReference getWithTableSpecification_QueryExpressionRoot();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableQueryExpr <em>With Table Query Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>With Table Query Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableQueryExpr()
     * @see #getWithTableSpecification()
     * @generated
     */
	EReference getWithTableSpecification_WithTableQueryExpr();

	/**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableReferences <em>With Table References</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>With Table References</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getWithTableReferences()
     * @see #getWithTableSpecification()
     * @generated
     */
	EReference getWithTableSpecification_WithTableReferences();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getColumnNameList <em>Column Name List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Column Name List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableSpecification#getColumnNameList()
     * @see #getWithTableSpecification()
     * @generated
     */
	EReference getWithTableSpecification_ColumnNameList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.Predicate <em>Predicate</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.Predicate
     * @generated
     */
	EClass getPredicate();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.Predicate#isNegatedPredicate <em>Negated Predicate</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Negated Predicate</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.Predicate#isNegatedPredicate()
     * @see #getPredicate()
     * @generated
     */
	EAttribute getPredicate_NegatedPredicate();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.Predicate#isHasSelectivity <em>Has Selectivity</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Has Selectivity</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.Predicate#isHasSelectivity()
     * @see #getPredicate()
     * @generated
     */
	EAttribute getPredicate_HasSelectivity();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.Predicate#getSelectivityValue <em>Selectivity Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Selectivity Value</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.Predicate#getSelectivityValue()
     * @see #getPredicate()
     * @generated
     */
	EAttribute getPredicate_SelectivityValue();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined <em>Search Condition Combined</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Search Condition Combined</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined
     * @generated
     */
	EClass getSearchConditionCombined();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getCombinedOperator <em>Combined Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Combined Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getCombinedOperator()
     * @see #getSearchConditionCombined()
     * @generated
     */
	EAttribute getSearchConditionCombined_CombinedOperator();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getLeftCondition <em>Left Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Left Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getLeftCondition()
     * @see #getSearchConditionCombined()
     * @generated
     */
	EReference getSearchConditionCombined_LeftCondition();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getRightCondition <em>Right Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Right Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombined#getRightCondition()
     * @see #getSearchConditionCombined()
     * @generated
     */
	EReference getSearchConditionCombined_RightCondition();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression <em>Order By Value Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Order By Value Expression</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression
     * @generated
     */
	EClass getOrderByValueExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByValueExpression#getValueExpr()
     * @see #getOrderByValueExpression()
     * @generated
     */
	EReference getOrderByValueExpression_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined <em>Query Combined</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Combined</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombined
     * @generated
     */
	EClass getQueryCombined();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getCombinedOperator <em>Combined Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Combined Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombined#getCombinedOperator()
     * @see #getQueryCombined()
     * @generated
     */
	EAttribute getQueryCombined_CombinedOperator();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getLeftQuery <em>Left Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Left Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombined#getLeftQuery()
     * @see #getQueryCombined()
     * @generated
     */
	EReference getQueryCombined_LeftQuery();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombined#getRightQuery <em>Right Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Right Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombined#getRightQuery()
     * @see #getQueryCombined()
     * @generated
     */
	EReference getQueryCombined_RightQuery();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect <em>Query Select</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect
     * @generated
     */
	EClass getQuerySelect();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#isDistinct <em>Distinct</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Distinct</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#isDistinct()
     * @see #getQuerySelect()
     * @generated
     */
	EAttribute getQuerySelect_Distinct();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getHavingClause <em>Having Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Having Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getHavingClause()
     * @see #getQuerySelect()
     * @generated
     */
	EReference getQuerySelect_HavingClause();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getWhereClause <em>Where Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Where Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getWhereClause()
     * @see #getQuerySelect()
     * @generated
     */
	EReference getQuerySelect_WhereClause();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getGroupByClause <em>Group By Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Group By Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getGroupByClause()
     * @see #getQuerySelect()
     * @generated
     */
	EReference getQuerySelect_GroupByClause();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getSelectClause <em>Select Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Select Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getSelectClause()
     * @see #getQuerySelect()
     * @generated
     */
	EReference getQuerySelect_SelectClause();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getFromClause <em>From Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>From Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getFromClause()
     * @see #getQuerySelect()
     * @generated
     */
	EReference getQuerySelect_FromClause();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelect#getIntoClause <em>Into Clause</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Into Clause</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelect#getIntoClause()
     * @see #getQuerySelect()
     * @generated
     */
	EReference getQuerySelect_IntoClause();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSpecification <em>Grouping Specification</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Grouping Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSpecification
     * @generated
     */
	EClass getGroupingSpecification();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSpecification#getQuerySelect <em>Query Select</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSpecification#getQuerySelect()
     * @see #getGroupingSpecification()
     * @generated
     */
	EReference getGroupingSpecification_QuerySelect();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification <em>Query Result Specification</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Result Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification
     * @generated
     */
	EClass getQueryResultSpecification();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification#getQuerySelect <em>Query Select</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryResultSpecification#getQuerySelect()
     * @see #getQueryResultSpecification()
     * @generated
     */
	EReference getQueryResultSpecification_QuerySelect();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns <em>Result Table All Columns</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Result Table All Columns</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns
     * @generated
     */
	EClass getResultTableAllColumns();

	/**
     * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns#getTableExpr <em>Table Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Table Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns#getTableExpr()
     * @see #getResultTableAllColumns()
     * @generated
     */
	EReference getResultTableAllColumns_TableExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ResultColumn <em>Result Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Result Column</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ResultColumn
     * @generated
     */
	EClass getResultColumn();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ResultColumn#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ResultColumn#getValueExpr()
     * @see #getResultColumn()
     * @generated
     */
	EReference getResultColumn_ValueExpr();

	/**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.ResultColumn#getOrderByResultCol <em>Order By Result Col</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Order By Result Col</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ResultColumn#getOrderByResultCol()
     * @see #getResultColumn()
     * @generated
     */
	EReference getResultColumn_OrderByResultCol();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic <em>Predicate Basic</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate Basic</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBasic
     * @generated
     */
	EClass getPredicateBasic();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getComparisonOperator <em>Comparison Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Comparison Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getComparisonOperator()
     * @see #getPredicateBasic()
     * @generated
     */
	EAttribute getPredicateBasic_ComparisonOperator();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getRightValueExpr <em>Right Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Right Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getRightValueExpr()
     * @see #getPredicateBasic()
     * @generated
     */
	EReference getPredicateBasic_RightValueExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getLeftValueExpr <em>Left Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Left Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getLeftValueExpr()
     * @see #getPredicateBasic()
     * @generated
     */
	EReference getPredicateBasic_LeftValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantified <em>Predicate Quantified</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate Quantified</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantified
     * @generated
     */
	EClass getPredicateQuantified();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween <em>Predicate Between</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate Between</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBetween
     * @generated
     */
	EClass getPredicateBetween();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#isNotBetween <em>Not Between</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Not Between</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBetween#isNotBetween()
     * @see #getPredicateBetween()
     * @generated
     */
	EAttribute getPredicateBetween_NotBetween();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getLeftValueExpr <em>Left Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Left Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getLeftValueExpr()
     * @see #getPredicateBetween()
     * @generated
     */
	EReference getPredicateBetween_LeftValueExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr1 <em>Right Value Expr1</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Right Value Expr1</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr1()
     * @see #getPredicateBetween()
     * @generated
     */
	EReference getPredicateBetween_RightValueExpr1();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr2 <em>Right Value Expr2</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Right Value Expr2</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr2()
     * @see #getPredicateBetween()
     * @generated
     */
	EReference getPredicateBetween_RightValueExpr2();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateExists <em>Predicate Exists</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate Exists</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateExists
     * @generated
     */
	EClass getPredicateExists();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateExists#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateExists#getQueryExpr()
     * @see #getPredicateExists()
     * @generated
     */
	EReference getPredicateExists_QueryExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIn <em>Predicate In</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate In</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateIn
     * @generated
     */
	EClass getPredicateIn();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIn#isNotIn <em>Not In</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Not In</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateIn#isNotIn()
     * @see #getPredicateIn()
     * @generated
     */
	EAttribute getPredicateIn_NotIn();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike <em>Predicate Like</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate Like</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateLike
     * @generated
     */
	EClass getPredicateLike();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#isNotLike <em>Not Like</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Not Like</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateLike#isNotLike()
     * @see #getPredicateLike()
     * @generated
     */
	EAttribute getPredicateLike_NotLike();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getPatternValueExpr <em>Pattern Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Pattern Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateLike#getPatternValueExpr()
     * @see #getPredicateLike()
     * @generated
     */
	EReference getPredicateLike_PatternValueExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getMatchingValueExpr <em>Matching Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Matching Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateLike#getMatchingValueExpr()
     * @see #getPredicateLike()
     * @generated
     */
	EReference getPredicateLike_MatchingValueExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateLike#getEscapeValueExpr <em>Escape Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Escape Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateLike#getEscapeValueExpr()
     * @see #getPredicateLike()
     * @generated
     */
	EReference getPredicateLike_EscapeValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIsNull <em>Predicate Is Null</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate Is Null</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateIsNull
     * @generated
     */
	EClass getPredicateIsNull();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#isNotNull <em>Not Null</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Not Null</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#isNotNull()
     * @see #getPredicateIsNull()
     * @generated
     */
	EAttribute getPredicateIsNull_NotNull();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateIsNull#getValueExpr()
     * @see #getPredicateIsNull()
     * @generated
     */
	EReference getPredicateIsNull_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect <em>Predicate Quantified Value Select</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate Quantified Value Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect
     * @generated
     */
	EClass getPredicateQuantifiedValueSelect();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getQuantifiedType <em>Quantified Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Quantified Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getQuantifiedType()
     * @see #getPredicateQuantifiedValueSelect()
     * @generated
     */
	EAttribute getPredicateQuantifiedValueSelect_QuantifiedType();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getComparisonOperator <em>Comparison Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Comparison Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getComparisonOperator()
     * @see #getPredicateQuantifiedValueSelect()
     * @generated
     */
	EAttribute getPredicateQuantifiedValueSelect_ComparisonOperator();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getQueryExpr()
     * @see #getPredicateQuantifiedValueSelect()
     * @generated
     */
	EReference getPredicateQuantifiedValueSelect_QueryExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getValueExpr()
     * @see #getPredicateQuantifiedValueSelect()
     * @generated
     */
	EReference getPredicateQuantifiedValueSelect_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect <em>Predicate Quantified Row Select</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate Quantified Row Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect
     * @generated
     */
	EClass getPredicateQuantifiedRowSelect();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getQuantifiedType <em>Quantified Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Quantified Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getQuantifiedType()
     * @see #getPredicateQuantifiedRowSelect()
     * @generated
     */
	EAttribute getPredicateQuantifiedRowSelect_QuantifiedType();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getQueryExpr()
     * @see #getPredicateQuantifiedRowSelect()
     * @generated
     */
	EReference getPredicateQuantifiedRowSelect_QueryExpr();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getValueExprList <em>Value Expr List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Value Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getValueExprList()
     * @see #getPredicateQuantifiedRowSelect()
     * @generated
     */
	EReference getPredicateQuantifiedRowSelect_ValueExprList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect <em>Predicate In Value Select</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate In Value Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect
     * @generated
     */
	EClass getPredicateInValueSelect();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect#getQueryExpr()
     * @see #getPredicateInValueSelect()
     * @generated
     */
	EReference getPredicateInValueSelect_QueryExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueSelect#getValueExpr()
     * @see #getPredicateInValueSelect()
     * @generated
     */
	EReference getPredicateInValueSelect_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueList <em>Predicate In Value List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate In Value List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueList
     * @generated
     */
	EClass getPredicateInValueList();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExprList <em>Value Expr List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Value Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExprList()
     * @see #getPredicateInValueList()
     * @generated
     */
	EReference getPredicateInValueList_ValueExprList();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExpr()
     * @see #getPredicateInValueList()
     * @generated
     */
	EReference getPredicateInValueList_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect <em>Predicate In Value Row Select</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Predicate In Value Row Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect
     * @generated
     */
	EClass getPredicateInValueRowSelect();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getValueExprList <em>Value Expr List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Value Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getValueExprList()
     * @see #getPredicateInValueRowSelect()
     * @generated
     */
	EReference getPredicateInValueRowSelect_ValueExprList();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getQueryExpr()
     * @see #getPredicateInValueRowSelect()
     * @generated
     */
	EReference getPredicateInValueRowSelect_QueryExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple <em>Value Expression Simple</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Simple</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple
     * @generated
     */
	EClass getValueExpressionSimple();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionSimple#getValue()
     * @see #getValueExpressionSimple()
     * @generated
     */
	EAttribute getValueExpressionSimple_Value();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn <em>Value Expression Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Column</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn
     * @generated
     */
	EClass getValueExpressionColumn();

	/**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getAssignmentExprTarget <em>Assignment Expr Target</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Assignment Expr Target</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getAssignmentExprTarget()
     * @see #getValueExpressionColumn()
     * @generated
     */
	EReference getValueExpressionColumn_AssignmentExprTarget();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getParentTableExpr <em>Parent Table Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent Table Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getParentTableExpr()
     * @see #getValueExpressionColumn()
     * @generated
     */
	EReference getValueExpressionColumn_ParentTableExpr();

	/**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getInsertStatement <em>Insert Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Insert Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getInsertStatement()
     * @see #getValueExpressionColumn()
     * @generated
     */
	EReference getValueExpressionColumn_InsertStatement();

	/**
     * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getTableExpr <em>Table Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Table Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getTableExpr()
     * @see #getValueExpressionColumn()
     * @generated
     */
	EReference getValueExpressionColumn_TableExpr();

	/**
     * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getTableInDatabase <em>Table In Database</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Table In Database</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getTableInDatabase()
     * @see #getValueExpressionColumn()
     * @generated
     */
	EReference getValueExpressionColumn_TableInDatabase();

	/**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getMergeInsertSpec <em>Merge Insert Spec</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Merge Insert Spec</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getMergeInsertSpec()
     * @see #getValueExpressionColumn()
     * @generated
     */
    EReference getValueExpressionColumn_MergeInsertSpec();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable <em>Value Expression Variable</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Variable</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable
     * @generated
     */
	EClass getValueExpressionVariable();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable#getQuerySelect <em>Query Select</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionVariable#getQuerySelect()
     * @see #getValueExpressionVariable()
     * @generated
     */
	EReference getValueExpressionVariable_QuerySelect();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect <em>Value Expression Scalar Select</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Scalar Select</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect
     * @generated
     */
	EClass getValueExpressionScalarSelect();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionScalarSelect#getQueryExpr()
     * @see #getValueExpressionScalarSelect()
     * @generated
     */
	EReference getValueExpressionScalarSelect_QueryExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration <em>Value Expression Labeled Duration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Labeled Duration</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration
     * @generated
     */
	EClass getValueExpressionLabeledDuration();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getLabeledDurationType <em>Labeled Duration Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Labeled Duration Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getLabeledDurationType()
     * @see #getValueExpressionLabeledDuration()
     * @generated
     */
	EAttribute getValueExpressionLabeledDuration_LabeledDurationType();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getValueExpr()
     * @see #getValueExpressionLabeledDuration()
     * @generated
     */
	EReference getValueExpressionLabeledDuration_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase <em>Value Expression Case</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Case</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase
     * @generated
     */
	EClass getValueExpressionCase();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase#getCaseElse <em>Case Else</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Case Else</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase#getCaseElse()
     * @see #getValueExpressionCase()
     * @generated
     */
	EReference getValueExpressionCase_CaseElse();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast <em>Value Expression Cast</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Cast</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast
     * @generated
     */
	EClass getValueExpressionCast();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCast#getValueExpr()
     * @see #getValueExpressionCast()
     * @generated
     */
	EReference getValueExpressionCast_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue <em>Value Expression Null Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Null Value</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionNullValue
     * @generated
     */
	EClass getValueExpressionNullValue();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionDefaultValue <em>Value Expression Default Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Default Value</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionDefaultValue
     * @generated
     */
	EClass getValueExpressionDefaultValue();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction <em>Value Expression Function</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction
     * @generated
     */
	EClass getValueExpressionFunction();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isSpecialRegister <em>Special Register</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Special Register</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isSpecialRegister()
     * @see #getValueExpressionFunction()
     * @generated
     */
	EAttribute getValueExpressionFunction_SpecialRegister();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isDistinct <em>Distinct</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Distinct</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isDistinct()
     * @see #getValueExpressionFunction()
     * @generated
     */
	EAttribute getValueExpressionFunction_Distinct();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isColumnFunction <em>Column Function</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Column Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isColumnFunction()
     * @see #getValueExpressionFunction()
     * @generated
     */
	EAttribute getValueExpressionFunction_ColumnFunction();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#getParameterList <em>Parameter List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parameter List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#getParameterList()
     * @see #getValueExpressionFunction()
     * @generated
     */
	EReference getValueExpressionFunction_ParameterList();

	/**
     * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#getFunction <em>Function</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#getFunction()
     * @see #getValueExpressionFunction()
     * @generated
     */
	EReference getValueExpressionFunction_Function();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined <em>Value Expression Combined</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Combined</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined
     * @generated
     */
	EClass getValueExpressionCombined();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getCombinedOperator <em>Combined Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Combined Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getCombinedOperator()
     * @see #getValueExpressionCombined()
     * @generated
     */
	EAttribute getValueExpressionCombined_CombinedOperator();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getLeftValueExpr <em>Left Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Left Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getLeftValueExpr()
     * @see #getValueExpressionCombined()
     * @generated
     */
	EReference getValueExpressionCombined_LeftValueExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getRightValueExpr <em>Right Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Right Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getRightValueExpr()
     * @see #getValueExpressionCombined()
     * @generated
     */
	EReference getValueExpressionCombined_RightValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSets <em>Grouping Sets</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Grouping Sets</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSets
     * @generated
     */
	EClass getGroupingSets();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSets#getGroupingSetsElementList <em>Grouping Sets Element List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Grouping Sets Element List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSets#getGroupingSetsElementList()
     * @see #getGroupingSets()
     * @generated
     */
	EReference getGroupingSets_GroupingSetsElementList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.Grouping <em>Grouping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Grouping</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.Grouping
     * @generated
     */
	EClass getGrouping();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.Grouping#getGroupingSetsElementExpr <em>Grouping Sets Element Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Grouping Sets Element Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.Grouping#getGroupingSetsElementExpr()
     * @see #getGrouping()
     * @generated
     */
	EReference getGrouping_GroupingSetsElementExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement <em>Grouping Sets Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Grouping Sets Element</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement
     * @generated
     */
	EClass getGroupingSetsElement();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement#getGroupingSets <em>Grouping Sets</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Grouping Sets</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElement#getGroupingSets()
     * @see #getGroupingSetsElement()
     * @generated
     */
	EReference getGroupingSetsElement_GroupingSets();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist <em>Grouping Sets Element Sublist</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Grouping Sets Element Sublist</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist
     * @generated
     */
	EClass getGroupingSetsElementSublist();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist#getGroupingSetsElementExprList <em>Grouping Sets Element Expr List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Grouping Sets Element Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist#getGroupingSetsElementExprList()
     * @see #getGroupingSetsElementSublist()
     * @generated
     */
	EReference getGroupingSetsElementSublist_GroupingSetsElementExprList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression <em>Grouping Sets Element Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Grouping Sets Element Expression</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression
     * @generated
     */
	EClass getGroupingSetsElementExpression();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGroupingSetsElementSublist <em>Grouping Sets Element Sublist</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Grouping Sets Element Sublist</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGroupingSetsElementSublist()
     * @see #getGroupingSetsElementExpression()
     * @generated
     */
	EReference getGroupingSetsElementExpression_GroupingSetsElementSublist();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGrouping <em>Grouping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Grouping</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGrouping()
     * @see #getGroupingSetsElementExpression()
     * @generated
     */
	EReference getGroupingSetsElementExpression_Grouping();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroup <em>Super Group</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Super Group</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroup
     * @generated
     */
	EClass getSuperGroup();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroup#getSuperGroupType <em>Super Group Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Super Group Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroup#getSuperGroupType()
     * @see #getSuperGroup()
     * @generated
     */
	EAttribute getSuperGroup_SuperGroupType();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroup#getSuperGroupElementList <em>Super Group Element List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Super Group Element List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroup#getSuperGroupElementList()
     * @see #getSuperGroup()
     * @generated
     */
	EReference getSuperGroup_SuperGroupElementList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.GroupingExpression <em>Grouping Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Grouping Expression</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingExpression
     * @generated
     */
	EClass getGroupingExpression();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getValueExpr()
     * @see #getGroupingExpression()
     * @generated
     */
	EReference getGroupingExpression_ValueExpr();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getSuperGroupElementExpr <em>Super Group Element Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Super Group Element Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getSuperGroupElementExpr()
     * @see #getGroupingExpression()
     * @generated
     */
	EReference getGroupingExpression_SuperGroupElementExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElement <em>Super Group Element</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Super Group Element</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElement
     * @generated
     */
	EClass getSuperGroupElement();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElement#getSuperGroup <em>Super Group</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Super Group</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElement#getSuperGroup()
     * @see #getSuperGroupElement()
     * @generated
     */
	EReference getSuperGroupElement_SuperGroup();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist <em>Super Group Element Sublist</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Super Group Element Sublist</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist
     * @generated
     */
	EClass getSuperGroupElementSublist();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist#getSuperGroupElementExprList <em>Super Group Element Expr List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Super Group Element Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist#getSuperGroupElementExprList()
     * @see #getSuperGroupElementSublist()
     * @generated
     */
	EReference getSuperGroupElementSublist_SuperGroupElementExprList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression <em>Super Group Element Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Super Group Element Expression</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression
     * @generated
     */
	EClass getSuperGroupElementExpression();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getSuperGroupElementSublist <em>Super Group Element Sublist</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Super Group Element Sublist</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getSuperGroupElementSublist()
     * @see #getSuperGroupElementExpression()
     * @generated
     */
	EReference getSuperGroupElementExpression_SuperGroupElementSublist();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getGroupingExpr <em>Grouping Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Grouping Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getGroupingExpr()
     * @see #getSuperGroupElementExpression()
     * @generated
     */
	EReference getSuperGroupElementExpression_GroupingExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch <em>Value Expression Case Search</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Case Search</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch
     * @generated
     */
	EClass getValueExpressionCaseSearch();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch#getSearchContentList <em>Search Content List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Search Content List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearch#getSearchContentList()
     * @see #getValueExpressionCaseSearch()
     * @generated
     */
	EReference getValueExpressionCaseSearch_SearchContentList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple <em>Value Expression Case Simple</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Case Simple</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple
     * @generated
     */
	EClass getValueExpressionCaseSimple();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getContentList <em>Content List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Content List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getContentList()
     * @see #getValueExpressionCaseSimple()
     * @generated
     */
	EReference getValueExpressionCaseSimple_ContentList();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getValueExpr()
     * @see #getValueExpressionCaseSimple()
     * @generated
     */
	EReference getValueExpressionCaseSimple_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse <em>Value Expression Case Else</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Case Else</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse
     * @generated
     */
	EClass getValueExpressionCaseElse();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse#getValueExprCase <em>Value Expr Case</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Case</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse#getValueExprCase()
     * @see #getValueExpressionCaseElse()
     * @generated
     */
	EReference getValueExpressionCaseElse_ValueExprCase();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse#getValueExpr()
     * @see #getValueExpressionCaseElse()
     * @generated
     */
	EReference getValueExpressionCaseElse_ValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent <em>Value Expression Case Search Content</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Case Search Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent
     * @generated
     */
	EClass getValueExpressionCaseSearchContent();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExpr <em>Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExpr()
     * @see #getValueExpressionCaseSearchContent()
     * @generated
     */
	EReference getValueExpressionCaseSearchContent_ValueExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getSearchCondition <em>Search Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Search Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getSearchCondition()
     * @see #getValueExpressionCaseSearchContent()
     * @generated
     */
	EReference getValueExpressionCaseSearchContent_SearchCondition();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExprCaseSearch <em>Value Expr Case Search</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Case Search</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSearchContent#getValueExprCaseSearch()
     * @see #getValueExpressionCaseSearchContent()
     * @generated
     */
	EReference getValueExpressionCaseSearchContent_ValueExprCaseSearch();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent <em>Value Expression Case Simple Content</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Case Simple Content</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent
     * @generated
     */
	EClass getValueExpressionCaseSimpleContent();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getValueExprCaseSimple <em>Value Expr Case Simple</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Value Expr Case Simple</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getValueExprCaseSimple()
     * @see #getValueExpressionCaseSimpleContent()
     * @generated
     */
	EReference getValueExpressionCaseSimpleContent_ValueExprCaseSimple();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getWhenValueExpr <em>When Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>When Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getWhenValueExpr()
     * @see #getValueExpressionCaseSimpleContent()
     * @generated
     */
	EReference getValueExpressionCaseSimpleContent_WhenValueExpr();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getResultValueExpr <em>Result Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Result Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getResultValueExpr()
     * @see #getValueExpressionCaseSimpleContent()
     * @generated
     */
	EReference getValueExpressionCaseSimpleContent_ResultValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase <em>Table In Database</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table In Database</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableInDatabase
     * @generated
     */
	EClass getTableInDatabase();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getUpdateStatement <em>Update Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Update Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getUpdateStatement()
     * @see #getTableInDatabase()
     * @generated
     */
	EReference getTableInDatabase_UpdateStatement();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDeleteStatement <em>Delete Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Delete Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDeleteStatement()
     * @see #getTableInDatabase()
     * @generated
     */
	EReference getTableInDatabase_DeleteStatement();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getInsertStatement <em>Insert Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Insert Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getInsertStatement()
     * @see #getTableInDatabase()
     * @generated
     */
	EReference getTableInDatabase_InsertStatement();

	/**
     * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDatabaseTable <em>Database Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Database Table</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDatabaseTable()
     * @see #getTableInDatabase()
     * @generated
     */
	EReference getTableInDatabase_DatabaseTable();

	/**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDerivedColumnList <em>Derived Column List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Derived Column List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableInDatabase#getDerivedColumnList()
     * @see #getTableInDatabase()
     * @generated
     */
	EReference getTableInDatabase_DerivedColumnList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.TableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableFunction
     * @generated
     */
	EClass getTableFunction();

	/**
     * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.query.TableFunction#getFunction <em>Function</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Function</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableFunction#getFunction()
     * @see #getTableFunction()
     * @generated
     */
    EReference getTableFunction_Function();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.TableFunction#getParameterList <em>Parameter List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parameter List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableFunction#getParameterList()
     * @see #getTableFunction()
     * @generated
     */
    EReference getTableFunction_ParameterList();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.SQLQueryObject <em>SQL Query Object</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>SQL Query Object</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryObject
     * @generated
     */
	EClass getSQLQueryObject();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryChangeStatement <em>Query Change Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Change Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryChangeStatement
     * @generated
     */
	EClass getQueryChangeStatement();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ColumnName <em>Column Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Column Name</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ColumnName
     * @generated
     */
	EClass getColumnName();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.ColumnName#getTableCorrelation <em>Table Correlation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Table Correlation</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ColumnName#getTableCorrelation()
     * @see #getColumnName()
     * @generated
     */
	EReference getColumnName_TableCorrelation();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.ColumnName#getWithTableSpecification <em>With Table Specification</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>With Table Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ColumnName#getWithTableSpecification()
     * @see #getColumnName()
     * @generated
     */
	EReference getColumnName_WithTableSpecification();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.TableNested <em>Table Nested</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Nested</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableNested
     * @generated
     */
	EClass getTableNested();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.TableNested#getNestedTableRef <em>Nested Table Ref</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Nested Table Ref</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableNested#getNestedTableRef()
     * @see #getTableNested()
     * @generated
     */
	EReference getTableNested_NestedTableRef();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement <em>Query Merge Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Merge Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement
     * @generated
     */
	EClass getQueryMergeStatement();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getTargetTable <em>Target Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Target Table</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getTargetTable()
     * @see #getQueryMergeStatement()
     * @generated
     */
    EReference getQueryMergeStatement_TargetTable();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getSourceTable <em>Source Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Source Table</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getSourceTable()
     * @see #getQueryMergeStatement()
     * @generated
     */
    EReference getQueryMergeStatement_SourceTable();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOnCondition <em>On Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>On Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOnCondition()
     * @see #getQueryMergeStatement()
     * @generated
     */
    EReference getQueryMergeStatement_OnCondition();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOperationSpecList <em>Operation Spec List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Operation Spec List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOperationSpecList()
     * @see #getQueryMergeStatement()
     * @generated
     */
    EReference getQueryMergeStatement_OperationSpecList();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionNested <em>Search Condition Nested</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Search Condition Nested</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionNested
     * @generated
     */
	EClass getSearchConditionNested();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionNested#getNestedCondition <em>Nested Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Nested Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionNested#getNestedCondition()
     * @see #getSearchConditionNested()
     * @generated
     */
	EReference getSearchConditionNested_NestedCondition();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested <em>Value Expression Nested</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Nested</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested
     * @generated
     */
	EClass getValueExpressionNested();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested#getNestedValueExpr <em>Nested Value Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Nested Value Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested#getNestedValueExpr()
     * @see #getValueExpressionNested()
     * @generated
     */
	EReference getValueExpressionNested_NestedValueExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic <em>Value Expression Atomic</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Atomic</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionAtomic
     * @generated
     */
	EClass getValueExpressionAtomic();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification <em>Order By Specification</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Order By Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification
     * @generated
     */
	EClass getOrderBySpecification();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#isDescending <em>Descending</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Descending</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#isDescending()
     * @see #getOrderBySpecification()
     * @generated
     */
	EAttribute getOrderBySpecification_Descending();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getOrderingSpecOption <em>Ordering Spec Option</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ordering Spec Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getOrderingSpecOption()
     * @see #getOrderBySpecification()
     * @generated
     */
	EAttribute getOrderBySpecification_OrderingSpecOption();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getNullOrderingOption <em>Null Ordering Option</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Null Ordering Option</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getNullOrderingOption()
     * @see #getOrderBySpecification()
     * @generated
     */
	EAttribute getOrderBySpecification_NullOrderingOption();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getSelectStatement <em>Select Statement</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Select Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getSelectStatement()
     * @see #getOrderBySpecification()
     * @generated
     */
	EReference getOrderBySpecification_SelectStatement();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderBySpecification#getQuery()
     * @see #getOrderBySpecification()
     * @generated
     */
    EReference getOrderBySpecification_Query();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal <em>Order By Ordinal</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Order By Ordinal</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal
     * @generated
     */
	EClass getOrderByOrdinal();

	/**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal#getOrdinalValue <em>Ordinal Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ordinal Value</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByOrdinal#getOrdinalValue()
     * @see #getOrderByOrdinal()
     * @generated
     */
	EAttribute getOrderByOrdinal_OrdinalValue();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.TableCorrelation <em>Table Correlation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Correlation</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableCorrelation
     * @generated
     */
	EClass getTableCorrelation();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getTableExpr <em>Table Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Table Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getTableExpr()
     * @see #getTableCorrelation()
     * @generated
     */
	EReference getTableCorrelation_TableExpr();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getColumnNameList <em>Column Name List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Column Name List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getColumnNameList()
     * @see #getTableCorrelation()
     * @generated
     */
	EReference getTableCorrelation_ColumnNameList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSource <em>Update Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Update Source</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSource
     * @generated
     */
	EClass getUpdateSource();

	/**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSource#getUpdateAssignmentExpr <em>Update Assignment Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Update Assignment Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSource#getUpdateAssignmentExpr()
     * @see #getUpdateSource()
     * @generated
     */
	EReference getUpdateSource_UpdateAssignmentExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList <em>Update Source Expr List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Update Source Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList
     * @generated
     */
	EClass getUpdateSourceExprList();

	/**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList#getValueExprList <em>Value Expr List</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Value Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList#getValueExprList()
     * @see #getUpdateSourceExprList()
     * @generated
     */
	EReference getUpdateSourceExprList_ValueExprList();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery <em>Update Source Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Update Source Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery
     * @generated
     */
	EClass getUpdateSourceQuery();

	/**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery#getQueryExpr <em>Query Expr</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery#getQueryExpr()
     * @see #getUpdateSourceQuery()
     * @generated
     */
	EReference getUpdateSourceQuery_QueryExpr();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn <em>Order By Result Column</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Order By Result Column</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn
     * @generated
     */
	EClass getOrderByResultColumn();

	/**
     * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn#getResultCol <em>Result Col</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Result Col</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderByResultColumn#getResultCol()
     * @see #getOrderByResultColumn()
     * @generated
     */
	EReference getOrderByResultColumn_ResultCol();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.WithTableReference <em>With Table Reference</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>With Table Reference</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableReference
     * @generated
     */
	EClass getWithTableReference();

	/**
     * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.query.WithTableReference#getWithTableSpecification <em>With Table Specification</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>With Table Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.WithTableReference#getWithTableSpecification()
     * @see #getWithTableReference()
     * @generated
     */
	EReference getWithTableReference_WithTableSpecification();

	/**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.QueryNested <em>Query Nested</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query Nested</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryNested
     * @generated
     */
    EClass getQueryNested();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.QueryNested#getNestedQuery <em>Nested Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Nested Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryNested#getNestedQuery()
     * @see #getQueryNested()
     * @generated
     */
    EReference getQueryNested_NestedQuery();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow <em>Value Expression Row</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Value Expression Row</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow
     * @generated
     */
    EClass getValueExpressionRow();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow#getValueExprList <em>Value Expr List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Value Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow#getValueExprList()
     * @see #getValueExpressionRow()
     * @generated
     */
    EReference getValueExpressionRow_ValueExprList();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.MergeTargetTable <em>Merge Target Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Merge Target Table</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeTargetTable
     * @generated
     */
    EClass getMergeTargetTable();

    /**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getMergeStatement <em>Merge Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Merge Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getMergeStatement()
     * @see #getMergeTargetTable()
     * @generated
     */
    EReference getMergeTargetTable_MergeStatement();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getTableExpr <em>Table Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Table Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getTableExpr()
     * @see #getMergeTargetTable()
     * @generated
     */
    EReference getMergeTargetTable_TableExpr();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable <em>Merge Source Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Merge Source Table</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeSourceTable
     * @generated
     */
    EClass getMergeSourceTable();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getQueryMergeStatement <em>Query Merge Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Query Merge Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getQueryMergeStatement()
     * @see #getMergeSourceTable()
     * @generated
     */
    EReference getMergeSourceTable_QueryMergeStatement();

    /**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getMergeStatement <em>Merge Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Merge Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getMergeStatement()
     * @see #getMergeSourceTable()
     * @generated
     */
    EReference getMergeSourceTable_MergeStatement();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getTableRef <em>Table Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Table Ref</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getTableRef()
     * @see #getMergeSourceTable()
     * @generated
     */
    EReference getMergeSourceTable_TableRef();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.MergeOnCondition <em>Merge On Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Merge On Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOnCondition
     * @generated
     */
    EClass getMergeOnCondition();

    /**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getMergeStatement <em>Merge Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Merge Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getMergeStatement()
     * @see #getMergeOnCondition()
     * @generated
     */
    EReference getMergeOnCondition_MergeStatement();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getSearchCondition <em>Search Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Search Condition</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getSearchCondition()
     * @see #getMergeOnCondition()
     * @generated
     */
    EReference getMergeOnCondition_SearchCondition();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification <em>Merge Update Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Merge Update Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification
     * @generated
     */
    EClass getMergeUpdateSpecification();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification#getAssignementExprList <em>Assignement Expr List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Assignement Expr List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification#getAssignementExprList()
     * @see #getMergeUpdateSpecification()
     * @generated
     */
    EReference getMergeUpdateSpecification_AssignementExprList();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification <em>Merge Insert Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Merge Insert Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification
     * @generated
     */
    EClass getMergeInsertSpecification();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification#getTargetColumnList <em>Target Column List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Target Column List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification#getTargetColumnList()
     * @see #getMergeInsertSpecification()
     * @generated
     */
    EReference getMergeInsertSpecification_TargetColumnList();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification#getSourceValuesRow <em>Source Values Row</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Source Values Row</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification#getSourceValuesRow()
     * @see #getMergeInsertSpecification()
     * @generated
     */
    EReference getMergeInsertSpecification_SourceValuesRow();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification <em>Merge Operation Specification</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Merge Operation Specification</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification
     * @generated
     */
    EClass getMergeOperationSpecification();

    /**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification#getMergeStatement <em>Merge Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Merge Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification#getMergeStatement()
     * @see #getMergeOperationSpecification()
     * @generated
     */
    EReference getMergeOperationSpecification_MergeStatement();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn <em>Update Of Column</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Update Of Column</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn
     * @generated
     */
    EClass getUpdateOfColumn();

    /**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn#getUpdatabilityExpr <em>Updatability Expr</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Updatability Expr</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn#getUpdatabilityExpr()
     * @see #getUpdateOfColumn()
     * @generated
     */
    EReference getUpdateOfColumn_UpdatabilityExpr();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression <em>Updatability Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Updatability Expression</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression
     * @generated
     */
    EClass getUpdatabilityExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getUpdatabilityType <em>Updatability Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Updatability Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getUpdatabilityType()
     * @see #getUpdatabilityExpression()
     * @generated
     */
    EAttribute getUpdatabilityExpression_UpdatabilityType();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getUpdateOfColumnList <em>Update Of Column List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Update Of Column List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getUpdateOfColumnList()
     * @see #getUpdatabilityExpression()
     * @generated
     */
    EReference getUpdatabilityExpression_UpdateOfColumnList();

    /**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getSelectStatement <em>Select Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Select Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getSelectStatement()
     * @see #getUpdatabilityExpression()
     * @generated
     */
    EReference getUpdatabilityExpression_SelectStatement();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.CallStatement <em>Call Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Call Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.CallStatement
     * @generated
     */
    EClass getCallStatement();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.datatools.modelbase.sql.query.CallStatement#getArgumentList <em>Argument List</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Argument List</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.CallStatement#getArgumentList()
     * @see #getCallStatement()
     * @generated
     */
    EReference getCallStatement_ArgumentList();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.CallStatement#getProcedureRef <em>Procedure Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Procedure Ref</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.CallStatement#getProcedureRef()
     * @see #getCallStatement()
     * @generated
     */
    EReference getCallStatement_ProcedureRef();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.ProcedureReference <em>Procedure Reference</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Procedure Reference</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ProcedureReference
     * @generated
     */
    EClass getProcedureReference();

    /**
     * Returns the meta object for the container reference '{@link org.eclipse.datatools.modelbase.sql.query.ProcedureReference#getCallStatement <em>Call Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Call Statement</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ProcedureReference#getCallStatement()
     * @see #getProcedureReference()
     * @generated
     */
    EReference getProcedureReference_CallStatement();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.datatools.modelbase.sql.query.ProcedureReference#getProcedure <em>Procedure</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Procedure</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ProcedureReference#getProcedure()
     * @see #getProcedureReference()
     * @generated
     */
    EReference getProcedureReference_Procedure();

    /**
     * Returns the meta object for class '{@link org.eclipse.datatools.modelbase.sql.query.TableQueryLateral <em>Table Query Lateral</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Query Lateral</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableQueryLateral
     * @generated
     */
    EClass getTableQueryLateral();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.datatools.modelbase.sql.query.TableQueryLateral#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Query</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableQueryLateral#getQuery()
     * @see #getTableQueryLateral()
     * @generated
     */
    EReference getTableQueryLateral_Query();

    /**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupType <em>Super Group Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Super Group Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupType
     * @generated
     */
	EEnum getSuperGroupType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType <em>Predicate Quantified Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Predicate Quantified Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType
     * @generated
     */
	EEnum getPredicateQuantifiedType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator <em>Predicate Comparison Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Predicate Comparison Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator
     * @generated
     */
	EEnum getPredicateComparisonOperator();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator <em>Search Condition Combined Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Search Condition Combined Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator
     * @generated
     */
	EEnum getSearchConditionCombinedOperator();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator <em>Table Joined Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Table Joined Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator
     * @generated
     */
	EEnum getTableJoinedOperator();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator <em>Query Combined Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Query Combined Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator
     * @generated
     */
	EEnum getQueryCombinedOperator();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator <em>Value Expression Unary Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Value Expression Unary Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator
     * @generated
     */
	EEnum getValueExpressionUnaryOperator();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator <em>Value Expression Combined Operator</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Value Expression Combined Operator</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator
     * @generated
     */
	EEnum getValueExpressionCombinedOperator();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType <em>Value Expression Labeled Duration Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Value Expression Labeled Duration Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType
     * @generated
     */
	EEnum getValueExpressionLabeledDurationType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.NullOrderingType <em>Null Ordering Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Null Ordering Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.NullOrderingType
     * @generated
     */
	EEnum getNullOrderingType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.OrderingSpecType <em>Ordering Spec Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Ordering Spec Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.OrderingSpecType
     * @generated
     */
	EEnum getOrderingSpecType();

	/**
     * Returns the meta object for enum '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityType <em>Updatability Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Updatability Type</em>'.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityType
     * @generated
     */
    EEnum getUpdatabilityType();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	SQLQueryModelFactory getSQLQueryModelFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals  {
		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryStatementImpl <em>Query Statement</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryStatementImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryStatement()
         * @generated
         */
		EClass QUERY_STATEMENT = eINSTANCE.getQueryStatement();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryDeleteStatementImpl <em>Query Delete Statement</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryDeleteStatementImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryDeleteStatement()
         * @generated
         */
		EClass QUERY_DELETE_STATEMENT = eINSTANCE.getQueryDeleteStatement();

		/**
         * The meta object literal for the '<em><b>Where Current Of Clause</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_DELETE_STATEMENT__WHERE_CURRENT_OF_CLAUSE = eINSTANCE.getQueryDeleteStatement_WhereCurrentOfClause();

		/**
         * The meta object literal for the '<em><b>Where Clause</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_DELETE_STATEMENT__WHERE_CLAUSE = eINSTANCE.getQueryDeleteStatement_WhereClause();

		/**
         * The meta object literal for the '<em><b>Target Table</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_DELETE_STATEMENT__TARGET_TABLE = eINSTANCE.getQueryDeleteStatement_TargetTable();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl <em>Query Insert Statement</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryInsertStatementImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryInsertStatement()
         * @generated
         */
		EClass QUERY_INSERT_STATEMENT = eINSTANCE.getQueryInsertStatement();

		/**
         * The meta object literal for the '<em><b>Source Query</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_INSERT_STATEMENT__SOURCE_QUERY = eINSTANCE.getQueryInsertStatement_SourceQuery();

		/**
         * The meta object literal for the '<em><b>Source Values Row List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_INSERT_STATEMENT__SOURCE_VALUES_ROW_LIST = eINSTANCE.getQueryInsertStatement_SourceValuesRowList();

		/**
         * The meta object literal for the '<em><b>Target Table</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_INSERT_STATEMENT__TARGET_TABLE = eINSTANCE.getQueryInsertStatement_TargetTable();

		/**
         * The meta object literal for the '<em><b>Target Column List</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_INSERT_STATEMENT__TARGET_COLUMN_LIST = eINSTANCE.getQueryInsertStatement_TargetColumnList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectStatementImpl <em>Query Select Statement</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectStatementImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQuerySelectStatement()
         * @generated
         */
		EClass QUERY_SELECT_STATEMENT = eINSTANCE.getQuerySelectStatement();

		/**
         * The meta object literal for the '<em><b>Query Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SELECT_STATEMENT__QUERY_EXPR = eINSTANCE.getQuerySelectStatement_QueryExpr();

		/**
         * The meta object literal for the '<em><b>Order By Clause</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SELECT_STATEMENT__ORDER_BY_CLAUSE = eINSTANCE.getQuerySelectStatement_OrderByClause();

		/**
         * The meta object literal for the '<em><b>Updatability Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_SELECT_STATEMENT__UPDATABILITY_EXPR = eINSTANCE.getQuerySelectStatement_UpdatabilityExpr();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryUpdateStatementImpl <em>Query Update Statement</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryUpdateStatementImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryUpdateStatement()
         * @generated
         */
		EClass QUERY_UPDATE_STATEMENT = eINSTANCE.getQueryUpdateStatement();

		/**
         * The meta object literal for the '<em><b>Assignment Clause</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_UPDATE_STATEMENT__ASSIGNMENT_CLAUSE = eINSTANCE.getQueryUpdateStatement_AssignmentClause();

		/**
         * The meta object literal for the '<em><b>Where Current Of Clause</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_UPDATE_STATEMENT__WHERE_CURRENT_OF_CLAUSE = eINSTANCE.getQueryUpdateStatement_WhereCurrentOfClause();

		/**
         * The meta object literal for the '<em><b>Where Clause</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_UPDATE_STATEMENT__WHERE_CLAUSE = eINSTANCE.getQueryUpdateStatement_WhereClause();

		/**
         * The meta object literal for the '<em><b>Target Table</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_UPDATE_STATEMENT__TARGET_TABLE = eINSTANCE.getQueryUpdateStatement_TargetTable();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl <em>Update Assignment Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdateAssignmentExpressionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdateAssignmentExpression()
         * @generated
         */
		EClass UPDATE_ASSIGNMENT_EXPRESSION = eINSTANCE.getUpdateAssignmentExpression();

		/**
         * The meta object literal for the '<em><b>Update Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_STATEMENT = eINSTANCE.getUpdateAssignmentExpression_UpdateStatement();

		/**
         * The meta object literal for the '<em><b>Target Column List</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference UPDATE_ASSIGNMENT_EXPRESSION__TARGET_COLUMN_LIST = eINSTANCE.getUpdateAssignmentExpression_TargetColumnList();

		/**
         * The meta object literal for the '<em><b>Update Source</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference UPDATE_ASSIGNMENT_EXPRESSION__UPDATE_SOURCE = eINSTANCE.getUpdateAssignmentExpression_UpdateSource();

		/**
         * The meta object literal for the '<em><b>Merge Update Spec</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference UPDATE_ASSIGNMENT_EXPRESSION__MERGE_UPDATE_SPEC = eINSTANCE.getUpdateAssignmentExpression_MergeUpdateSpec();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.CursorReferenceImpl <em>Cursor Reference</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.CursorReferenceImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getCursorReference()
         * @generated
         */
		EClass CURSOR_REFERENCE = eINSTANCE.getCursorReference();

		/**
         * The meta object literal for the '<em><b>Update Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CURSOR_REFERENCE__UPDATE_STATEMENT = eINSTANCE.getCursorReference_UpdateStatement();

		/**
         * The meta object literal for the '<em><b>Delete Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CURSOR_REFERENCE__DELETE_STATEMENT = eINSTANCE.getCursorReference_DeleteStatement();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl <em>Query Search Condition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QuerySearchConditionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQuerySearchCondition()
         * @generated
         */
		EClass QUERY_SEARCH_CONDITION = eINSTANCE.getQuerySearchCondition();

		/**
         * The meta object literal for the '<em><b>Negated Condition</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute QUERY_SEARCH_CONDITION__NEGATED_CONDITION = eINSTANCE.getQuerySearchCondition_NegatedCondition();

		/**
         * The meta object literal for the '<em><b>Update Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SEARCH_CONDITION__UPDATE_STATEMENT = eINSTANCE.getQuerySearchCondition_UpdateStatement();

		/**
         * The meta object literal for the '<em><b>Delete Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SEARCH_CONDITION__DELETE_STATEMENT = eINSTANCE.getQuerySearchCondition_DeleteStatement();

		/**
         * The meta object literal for the '<em><b>Table Joined</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SEARCH_CONDITION__TABLE_JOINED = eINSTANCE.getQuerySearchCondition_TableJoined();

		/**
         * The meta object literal for the '<em><b>Combined Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SEARCH_CONDITION__COMBINED_LEFT = eINSTANCE.getQuerySearchCondition_CombinedLeft();

		/**
         * The meta object literal for the '<em><b>Combined Right</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SEARCH_CONDITION__COMBINED_RIGHT = eINSTANCE.getQuerySearchCondition_CombinedRight();

		/**
         * The meta object literal for the '<em><b>Query Select Having</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SEARCH_CONDITION__QUERY_SELECT_HAVING = eINSTANCE.getQuerySearchCondition_QuerySelectHaving();

		/**
         * The meta object literal for the '<em><b>Query Select Where</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SEARCH_CONDITION__QUERY_SELECT_WHERE = eINSTANCE.getQuerySearchCondition_QuerySelectWhere();

		/**
         * The meta object literal for the '<em><b>Value Expr Case Search Content</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SEARCH_CONDITION__VALUE_EXPR_CASE_SEARCH_CONTENT = eINSTANCE.getQuerySearchCondition_ValueExprCaseSearchContent();

		/**
         * The meta object literal for the '<em><b>Nest</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SEARCH_CONDITION__NEST = eINSTANCE.getQuerySearchCondition_Nest();

		/**
         * The meta object literal for the '<em><b>Merge On Condition</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_SEARCH_CONDITION__MERGE_ON_CONDITION = eINSTANCE.getQuerySearchCondition_MergeOnCondition();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl <em>Query Expression Body</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionBodyImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryExpressionBody()
         * @generated
         */
		EClass QUERY_EXPRESSION_BODY = eINSTANCE.getQueryExpressionBody();

		/**
         * The meta object literal for the '<em><b>Row Fetch Limit</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute QUERY_EXPRESSION_BODY__ROW_FETCH_LIMIT = eINSTANCE.getQueryExpressionBody_RowFetchLimit();

        /**
         * The meta object literal for the '<em><b>Query Expression</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_BODY__QUERY_EXPRESSION = eINSTANCE.getQueryExpressionBody_QueryExpression();

		/**
         * The meta object literal for the '<em><b>Combined Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_BODY__COMBINED_LEFT = eINSTANCE.getQueryExpressionBody_CombinedLeft();

		/**
         * The meta object literal for the '<em><b>Combined Right</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_BODY__COMBINED_RIGHT = eINSTANCE.getQueryExpressionBody_CombinedRight();

		/**
         * The meta object literal for the '<em><b>Predicate Exists</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_BODY__PREDICATE_EXISTS = eINSTANCE.getQueryExpressionBody_PredicateExists();

		/**
         * The meta object literal for the '<em><b>Update Source Query</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_BODY__UPDATE_SOURCE_QUERY = eINSTANCE.getQueryExpressionBody_UpdateSourceQuery();

		/**
         * The meta object literal for the '<em><b>With Table Specification</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_BODY__WITH_TABLE_SPECIFICATION = eINSTANCE.getQueryExpressionBody_WithTableSpecification();

		/**
         * The meta object literal for the '<em><b>Query Nest</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_EXPRESSION_BODY__QUERY_NEST = eINSTANCE.getQueryExpressionBody_QueryNest();

        /**
         * The meta object literal for the '<em><b>Sort Spec List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_EXPRESSION_BODY__SORT_SPEC_LIST = eINSTANCE.getQueryExpressionBody_SortSpecList();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl <em>Query Value Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryValueExpressionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryValueExpression()
         * @generated
         */
		EClass QUERY_VALUE_EXPRESSION = eINSTANCE.getQueryValueExpression();

		/**
         * The meta object literal for the '<em><b>Unary Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute QUERY_VALUE_EXPRESSION__UNARY_OPERATOR = eINSTANCE.getQueryValueExpression_UnaryOperator();

		/**
         * The meta object literal for the '<em><b>Data Type</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__DATA_TYPE = eINSTANCE.getQueryValueExpression_DataType();

		/**
         * The meta object literal for the '<em><b>Values Row</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUES_ROW = eINSTANCE.getQueryValueExpression_ValuesRow();

		/**
         * The meta object literal for the '<em><b>Order By Value Expr</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__ORDER_BY_VALUE_EXPR = eINSTANCE.getQueryValueExpression_OrderByValueExpr();

		/**
         * The meta object literal for the '<em><b>Result Column</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__RESULT_COLUMN = eINSTANCE.getQueryValueExpression_ResultColumn();

		/**
         * The meta object literal for the '<em><b>Basic Right</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__BASIC_RIGHT = eINSTANCE.getQueryValueExpression_BasicRight();

		/**
         * The meta object literal for the '<em><b>Basic Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__BASIC_LEFT = eINSTANCE.getQueryValueExpression_BasicLeft();

		/**
         * The meta object literal for the '<em><b>Like Pattern</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__LIKE_PATTERN = eINSTANCE.getQueryValueExpression_LikePattern();

		/**
         * The meta object literal for the '<em><b>Like Matching</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__LIKE_MATCHING = eINSTANCE.getQueryValueExpression_LikeMatching();

		/**
         * The meta object literal for the '<em><b>Predicate Null</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__PREDICATE_NULL = eINSTANCE.getQueryValueExpression_PredicateNull();

		/**
         * The meta object literal for the '<em><b>In Value List Right</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_RIGHT = eINSTANCE.getQueryValueExpression_InValueListRight();

		/**
         * The meta object literal for the '<em><b>In Value List Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__IN_VALUE_LIST_LEFT = eINSTANCE.getQueryValueExpression_InValueListLeft();

		/**
         * The meta object literal for the '<em><b>In Value Row Select Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__IN_VALUE_ROW_SELECT_LEFT = eINSTANCE.getQueryValueExpression_InValueRowSelectLeft();

		/**
         * The meta object literal for the '<em><b>In Value Select Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__IN_VALUE_SELECT_LEFT = eINSTANCE.getQueryValueExpression_InValueSelectLeft();

		/**
         * The meta object literal for the '<em><b>Quantified Row Select Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__QUANTIFIED_ROW_SELECT_LEFT = eINSTANCE.getQueryValueExpression_QuantifiedRowSelectLeft();

		/**
         * The meta object literal for the '<em><b>Quantified Value Select Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__QUANTIFIED_VALUE_SELECT_LEFT = eINSTANCE.getQueryValueExpression_QuantifiedValueSelectLeft();

		/**
         * The meta object literal for the '<em><b>Between Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__BETWEEN_LEFT = eINSTANCE.getQueryValueExpression_BetweenLeft();

		/**
         * The meta object literal for the '<em><b>Between Right1</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT1 = eINSTANCE.getQueryValueExpression_BetweenRight1();

		/**
         * The meta object literal for the '<em><b>Between Right2</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__BETWEEN_RIGHT2 = eINSTANCE.getQueryValueExpression_BetweenRight2();

		/**
         * The meta object literal for the '<em><b>Value Expr Cast</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_CAST = eINSTANCE.getQueryValueExpression_ValueExprCast();

		/**
         * The meta object literal for the '<em><b>Value Expr Function</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_FUNCTION = eINSTANCE.getQueryValueExpression_ValueExprFunction();

		/**
         * The meta object literal for the '<em><b>Value Expr Combined Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_LEFT = eINSTANCE.getQueryValueExpression_ValueExprCombinedLeft();

		/**
         * The meta object literal for the '<em><b>Value Expr Combined Right</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_COMBINED_RIGHT = eINSTANCE.getQueryValueExpression_ValueExprCombinedRight();

		/**
         * The meta object literal for the '<em><b>Grouping Expr</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__GROUPING_EXPR = eINSTANCE.getQueryValueExpression_GroupingExpr();

		/**
         * The meta object literal for the '<em><b>Value Expr Case Else</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_ELSE = eINSTANCE.getQueryValueExpression_ValueExprCaseElse();

		/**
         * The meta object literal for the '<em><b>Value Expr Case Simple</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE = eINSTANCE.getQueryValueExpression_ValueExprCaseSimple();

		/**
         * The meta object literal for the '<em><b>Value Expr Case Simple Content When</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_WHEN = eINSTANCE.getQueryValueExpression_ValueExprCaseSimpleContentWhen();

		/**
         * The meta object literal for the '<em><b>Value Expr Case Simple Content Result</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SIMPLE_CONTENT_RESULT = eINSTANCE.getQueryValueExpression_ValueExprCaseSimpleContentResult();

		/**
         * The meta object literal for the '<em><b>Value Expr Case Search Content</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_CASE_SEARCH_CONTENT = eINSTANCE.getQueryValueExpression_ValueExprCaseSearchContent();

		/**
         * The meta object literal for the '<em><b>Like Escape</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__LIKE_ESCAPE = eINSTANCE.getQueryValueExpression_LikeEscape();

		/**
         * The meta object literal for the '<em><b>Value Expr Labeled Duration</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_LABELED_DURATION = eINSTANCE.getQueryValueExpression_ValueExprLabeledDuration();

		/**
         * The meta object literal for the '<em><b>Nest</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__NEST = eINSTANCE.getQueryValueExpression_Nest();

		/**
         * The meta object literal for the '<em><b>Update Source Expr List</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUE_EXPRESSION__UPDATE_SOURCE_EXPR_LIST = eINSTANCE.getQueryValueExpression_UpdateSourceExprList();

		/**
         * The meta object literal for the '<em><b>Table Function</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_VALUE_EXPRESSION__TABLE_FUNCTION = eINSTANCE.getQueryValueExpression_TableFunction();

        /**
         * The meta object literal for the '<em><b>Value Expr Row</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_VALUE_EXPRESSION__VALUE_EXPR_ROW = eINSTANCE.getQueryValueExpression_ValueExprRow();

        /**
         * The meta object literal for the '<em><b>Call Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_VALUE_EXPRESSION__CALL_STATEMENT = eINSTANCE.getQueryValueExpression_CallStatement();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl <em>Query Expression Root</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryExpressionRootImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryExpressionRoot()
         * @generated
         */
		EClass QUERY_EXPRESSION_ROOT = eINSTANCE.getQueryExpressionRoot();

		/**
         * The meta object literal for the '<em><b>Insert Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_ROOT__INSERT_STATEMENT = eINSTANCE.getQueryExpressionRoot_InsertStatement();

		/**
         * The meta object literal for the '<em><b>Select Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_ROOT__SELECT_STATEMENT = eINSTANCE.getQueryExpressionRoot_SelectStatement();

		/**
         * The meta object literal for the '<em><b>With Clause</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_ROOT__WITH_CLAUSE = eINSTANCE.getQueryExpressionRoot_WithClause();

		/**
         * The meta object literal for the '<em><b>Query</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_ROOT__QUERY = eINSTANCE.getQueryExpressionRoot_Query();

		/**
         * The meta object literal for the '<em><b>In Value Row Select Right</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_ROOT__IN_VALUE_ROW_SELECT_RIGHT = eINSTANCE.getQueryExpressionRoot_InValueRowSelectRight();

		/**
         * The meta object literal for the '<em><b>In Value Select Right</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_ROOT__IN_VALUE_SELECT_RIGHT = eINSTANCE.getQueryExpressionRoot_InValueSelectRight();

		/**
         * The meta object literal for the '<em><b>Quantified Row Select Right</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_ROOT__QUANTIFIED_ROW_SELECT_RIGHT = eINSTANCE.getQueryExpressionRoot_QuantifiedRowSelectRight();

		/**
         * The meta object literal for the '<em><b>Quantified Value Select Right</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_EXPRESSION_ROOT__QUANTIFIED_VALUE_SELECT_RIGHT = eINSTANCE.getQueryExpressionRoot_QuantifiedValueSelectRight();

		/**
         * The meta object literal for the '<em><b>Val Expr Scalar Select</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_EXPRESSION_ROOT__VAL_EXPR_SCALAR_SELECT = eINSTANCE.getQueryExpressionRoot_ValExprScalarSelect();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValuesRowImpl <em>Values Row</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValuesRowImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValuesRow()
         * @generated
         */
		EClass VALUES_ROW = eINSTANCE.getValuesRow();

		/**
         * The meta object literal for the '<em><b>Insert Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUES_ROW__INSERT_STATEMENT = eINSTANCE.getValuesRow_InsertStatement();

		/**
         * The meta object literal for the '<em><b>Expr List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUES_ROW__EXPR_LIST = eINSTANCE.getValuesRow_ExprList();

		/**
         * The meta object literal for the '<em><b>Query Values</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUES_ROW__QUERY_VALUES = eINSTANCE.getValuesRow_QueryValues();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryValuesImpl <em>Query Values</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryValuesImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryValues()
         * @generated
         */
		EClass QUERY_VALUES = eINSTANCE.getQueryValues();

		/**
         * The meta object literal for the '<em><b>Values Row List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_VALUES__VALUES_ROW_LIST = eINSTANCE.getQueryValues_ValuesRowList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl <em>Table Reference</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.TableReferenceImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableReference()
         * @generated
         */
		EClass TABLE_REFERENCE = eINSTANCE.getTableReference();

		/**
         * The meta object literal for the '<em><b>Table Joined Right</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_REFERENCE__TABLE_JOINED_RIGHT = eINSTANCE.getTableReference_TableJoinedRight();

		/**
         * The meta object literal for the '<em><b>Table Joined Left</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_REFERENCE__TABLE_JOINED_LEFT = eINSTANCE.getTableReference_TableJoinedLeft();

		/**
         * The meta object literal for the '<em><b>Query Select</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_REFERENCE__QUERY_SELECT = eINSTANCE.getTableReference_QuerySelect();

		/**
         * The meta object literal for the '<em><b>Nest</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_REFERENCE__NEST = eINSTANCE.getTableReference_Nest();

		/**
         * The meta object literal for the '<em><b>Merge Source Table</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TABLE_REFERENCE__MERGE_SOURCE_TABLE = eINSTANCE.getTableReference_MergeSourceTable();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl <em>Table Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.TableExpressionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableExpression()
         * @generated
         */
		EClass TABLE_EXPRESSION = eINSTANCE.getTableExpression();

		/**
         * The meta object literal for the '<em><b>Column List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_EXPRESSION__COLUMN_LIST = eINSTANCE.getTableExpression_ColumnList();

		/**
         * The meta object literal for the '<em><b>Table Correlation</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_EXPRESSION__TABLE_CORRELATION = eINSTANCE.getTableExpression_TableCorrelation();

		/**
         * The meta object literal for the '<em><b>Result Table All Columns</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_EXPRESSION__RESULT_TABLE_ALL_COLUMNS = eINSTANCE.getTableExpression_ResultTableAllColumns();

		/**
         * The meta object literal for the '<em><b>Value Expr Columns</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_EXPRESSION__VALUE_EXPR_COLUMNS = eINSTANCE.getTableExpression_ValueExprColumns();

		/**
         * The meta object literal for the '<em><b>Merge Target Table</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TABLE_EXPRESSION__MERGE_TARGET_TABLE = eINSTANCE.getTableExpression_MergeTargetTable();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl <em>Table Joined</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.TableJoinedImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableJoined()
         * @generated
         */
		EClass TABLE_JOINED = eINSTANCE.getTableJoined();

		/**
         * The meta object literal for the '<em><b>Join Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TABLE_JOINED__JOIN_OPERATOR = eINSTANCE.getTableJoined_JoinOperator();

		/**
         * The meta object literal for the '<em><b>Join Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_JOINED__JOIN_CONDITION = eINSTANCE.getTableJoined_JoinCondition();

		/**
         * The meta object literal for the '<em><b>Table Ref Right</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_JOINED__TABLE_REF_RIGHT = eINSTANCE.getTableJoined_TableRefRight();

		/**
         * The meta object literal for the '<em><b>Table Ref Left</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_JOINED__TABLE_REF_LEFT = eINSTANCE.getTableJoined_TableRefLeft();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl <em>With Table Specification</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.WithTableSpecificationImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getWithTableSpecification()
         * @generated
         */
		EClass WITH_TABLE_SPECIFICATION = eINSTANCE.getWithTableSpecification();

		/**
         * The meta object literal for the '<em><b>Query Expression Root</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WITH_TABLE_SPECIFICATION__QUERY_EXPRESSION_ROOT = eINSTANCE.getWithTableSpecification_QueryExpressionRoot();

		/**
         * The meta object literal for the '<em><b>With Table Query Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WITH_TABLE_SPECIFICATION__WITH_TABLE_QUERY_EXPR = eINSTANCE.getWithTableSpecification_WithTableQueryExpr();

		/**
         * The meta object literal for the '<em><b>With Table References</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WITH_TABLE_SPECIFICATION__WITH_TABLE_REFERENCES = eINSTANCE.getWithTableSpecification_WithTableReferences();

		/**
         * The meta object literal for the '<em><b>Column Name List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WITH_TABLE_SPECIFICATION__COLUMN_NAME_LIST = eINSTANCE.getWithTableSpecification_ColumnNameList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl <em>Predicate</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicate()
         * @generated
         */
		EClass PREDICATE = eINSTANCE.getPredicate();

		/**
         * The meta object literal for the '<em><b>Negated Predicate</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE__NEGATED_PREDICATE = eINSTANCE.getPredicate_NegatedPredicate();

		/**
         * The meta object literal for the '<em><b>Has Selectivity</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE__HAS_SELECTIVITY = eINSTANCE.getPredicate_HasSelectivity();

		/**
         * The meta object literal for the '<em><b>Selectivity Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE__SELECTIVITY_VALUE = eINSTANCE.getPredicate_SelectivityValue();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionCombinedImpl <em>Search Condition Combined</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionCombinedImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSearchConditionCombined()
         * @generated
         */
		EClass SEARCH_CONDITION_COMBINED = eINSTANCE.getSearchConditionCombined();

		/**
         * The meta object literal for the '<em><b>Combined Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SEARCH_CONDITION_COMBINED__COMBINED_OPERATOR = eINSTANCE.getSearchConditionCombined_CombinedOperator();

		/**
         * The meta object literal for the '<em><b>Left Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SEARCH_CONDITION_COMBINED__LEFT_CONDITION = eINSTANCE.getSearchConditionCombined_LeftCondition();

		/**
         * The meta object literal for the '<em><b>Right Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SEARCH_CONDITION_COMBINED__RIGHT_CONDITION = eINSTANCE.getSearchConditionCombined_RightCondition();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderByValueExpressionImpl <em>Order By Value Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.OrderByValueExpressionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getOrderByValueExpression()
         * @generated
         */
		EClass ORDER_BY_VALUE_EXPRESSION = eINSTANCE.getOrderByValueExpression();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ORDER_BY_VALUE_EXPRESSION__VALUE_EXPR = eINSTANCE.getOrderByValueExpression_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryCombinedImpl <em>Query Combined</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryCombinedImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryCombined()
         * @generated
         */
		EClass QUERY_COMBINED = eINSTANCE.getQueryCombined();

		/**
         * The meta object literal for the '<em><b>Combined Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute QUERY_COMBINED__COMBINED_OPERATOR = eINSTANCE.getQueryCombined_CombinedOperator();

		/**
         * The meta object literal for the '<em><b>Left Query</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_COMBINED__LEFT_QUERY = eINSTANCE.getQueryCombined_LeftQuery();

		/**
         * The meta object literal for the '<em><b>Right Query</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_COMBINED__RIGHT_QUERY = eINSTANCE.getQueryCombined_RightQuery();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl <em>Query Select</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QuerySelectImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQuerySelect()
         * @generated
         */
		EClass QUERY_SELECT = eINSTANCE.getQuerySelect();

		/**
         * The meta object literal for the '<em><b>Distinct</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute QUERY_SELECT__DISTINCT = eINSTANCE.getQuerySelect_Distinct();

		/**
         * The meta object literal for the '<em><b>Having Clause</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SELECT__HAVING_CLAUSE = eINSTANCE.getQuerySelect_HavingClause();

		/**
         * The meta object literal for the '<em><b>Where Clause</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SELECT__WHERE_CLAUSE = eINSTANCE.getQuerySelect_WhereClause();

		/**
         * The meta object literal for the '<em><b>Group By Clause</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SELECT__GROUP_BY_CLAUSE = eINSTANCE.getQuerySelect_GroupByClause();

		/**
         * The meta object literal for the '<em><b>Select Clause</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SELECT__SELECT_CLAUSE = eINSTANCE.getQuerySelect_SelectClause();

		/**
         * The meta object literal for the '<em><b>From Clause</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SELECT__FROM_CLAUSE = eINSTANCE.getQuerySelect_FromClause();

		/**
         * The meta object literal for the '<em><b>Into Clause</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_SELECT__INTO_CLAUSE = eINSTANCE.getQuerySelect_IntoClause();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSpecificationImpl <em>Grouping Specification</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingSpecificationImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingSpecification()
         * @generated
         */
		EClass GROUPING_SPECIFICATION = eINSTANCE.getGroupingSpecification();

		/**
         * The meta object literal for the '<em><b>Query Select</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUPING_SPECIFICATION__QUERY_SELECT = eINSTANCE.getGroupingSpecification_QuerySelect();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryResultSpecificationImpl <em>Query Result Specification</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryResultSpecificationImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryResultSpecification()
         * @generated
         */
		EClass QUERY_RESULT_SPECIFICATION = eINSTANCE.getQueryResultSpecification();

		/**
         * The meta object literal for the '<em><b>Query Select</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference QUERY_RESULT_SPECIFICATION__QUERY_SELECT = eINSTANCE.getQueryResultSpecification_QuerySelect();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ResultTableAllColumnsImpl <em>Result Table All Columns</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ResultTableAllColumnsImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getResultTableAllColumns()
         * @generated
         */
		EClass RESULT_TABLE_ALL_COLUMNS = eINSTANCE.getResultTableAllColumns();

		/**
         * The meta object literal for the '<em><b>Table Expr</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference RESULT_TABLE_ALL_COLUMNS__TABLE_EXPR = eINSTANCE.getResultTableAllColumns_TableExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ResultColumnImpl <em>Result Column</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ResultColumnImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getResultColumn()
         * @generated
         */
		EClass RESULT_COLUMN = eINSTANCE.getResultColumn();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference RESULT_COLUMN__VALUE_EXPR = eINSTANCE.getResultColumn_ValueExpr();

		/**
         * The meta object literal for the '<em><b>Order By Result Col</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference RESULT_COLUMN__ORDER_BY_RESULT_COL = eINSTANCE.getResultColumn_OrderByResultCol();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBasicImpl <em>Predicate Basic</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateBasicImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateBasic()
         * @generated
         */
		EClass PREDICATE_BASIC = eINSTANCE.getPredicateBasic();

		/**
         * The meta object literal for the '<em><b>Comparison Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE_BASIC__COMPARISON_OPERATOR = eINSTANCE.getPredicateBasic_ComparisonOperator();

		/**
         * The meta object literal for the '<em><b>Right Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_BASIC__RIGHT_VALUE_EXPR = eINSTANCE.getPredicateBasic_RightValueExpr();

		/**
         * The meta object literal for the '<em><b>Left Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_BASIC__LEFT_VALUE_EXPR = eINSTANCE.getPredicateBasic_LeftValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedImpl <em>Predicate Quantified</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateQuantified()
         * @generated
         */
		EClass PREDICATE_QUANTIFIED = eINSTANCE.getPredicateQuantified();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl <em>Predicate Between</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateBetweenImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateBetween()
         * @generated
         */
		EClass PREDICATE_BETWEEN = eINSTANCE.getPredicateBetween();

		/**
         * The meta object literal for the '<em><b>Not Between</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE_BETWEEN__NOT_BETWEEN = eINSTANCE.getPredicateBetween_NotBetween();

		/**
         * The meta object literal for the '<em><b>Left Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_BETWEEN__LEFT_VALUE_EXPR = eINSTANCE.getPredicateBetween_LeftValueExpr();

		/**
         * The meta object literal for the '<em><b>Right Value Expr1</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_BETWEEN__RIGHT_VALUE_EXPR1 = eINSTANCE.getPredicateBetween_RightValueExpr1();

		/**
         * The meta object literal for the '<em><b>Right Value Expr2</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_BETWEEN__RIGHT_VALUE_EXPR2 = eINSTANCE.getPredicateBetween_RightValueExpr2();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateExistsImpl <em>Predicate Exists</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateExistsImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateExists()
         * @generated
         */
		EClass PREDICATE_EXISTS = eINSTANCE.getPredicateExists();

		/**
         * The meta object literal for the '<em><b>Query Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_EXISTS__QUERY_EXPR = eINSTANCE.getPredicateExists_QueryExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInImpl <em>Predicate In</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateInImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateIn()
         * @generated
         */
		EClass PREDICATE_IN = eINSTANCE.getPredicateIn();

		/**
         * The meta object literal for the '<em><b>Not In</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE_IN__NOT_IN = eINSTANCE.getPredicateIn_NotIn();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl <em>Predicate Like</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateLikeImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateLike()
         * @generated
         */
		EClass PREDICATE_LIKE = eINSTANCE.getPredicateLike();

		/**
         * The meta object literal for the '<em><b>Not Like</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE_LIKE__NOT_LIKE = eINSTANCE.getPredicateLike_NotLike();

		/**
         * The meta object literal for the '<em><b>Pattern Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_LIKE__PATTERN_VALUE_EXPR = eINSTANCE.getPredicateLike_PatternValueExpr();

		/**
         * The meta object literal for the '<em><b>Matching Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_LIKE__MATCHING_VALUE_EXPR = eINSTANCE.getPredicateLike_MatchingValueExpr();

		/**
         * The meta object literal for the '<em><b>Escape Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_LIKE__ESCAPE_VALUE_EXPR = eINSTANCE.getPredicateLike_EscapeValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateIsNullImpl <em>Predicate Is Null</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateIsNullImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateIsNull()
         * @generated
         */
		EClass PREDICATE_IS_NULL = eINSTANCE.getPredicateIsNull();

		/**
         * The meta object literal for the '<em><b>Not Null</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE_IS_NULL__NOT_NULL = eINSTANCE.getPredicateIsNull_NotNull();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_IS_NULL__VALUE_EXPR = eINSTANCE.getPredicateIsNull_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl <em>Predicate Quantified Value Select</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedValueSelectImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateQuantifiedValueSelect()
         * @generated
         */
		EClass PREDICATE_QUANTIFIED_VALUE_SELECT = eINSTANCE.getPredicateQuantifiedValueSelect();

		/**
         * The meta object literal for the '<em><b>Quantified Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE_QUANTIFIED_VALUE_SELECT__QUANTIFIED_TYPE = eINSTANCE.getPredicateQuantifiedValueSelect_QuantifiedType();

		/**
         * The meta object literal for the '<em><b>Comparison Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE_QUANTIFIED_VALUE_SELECT__COMPARISON_OPERATOR = eINSTANCE.getPredicateQuantifiedValueSelect_ComparisonOperator();

		/**
         * The meta object literal for the '<em><b>Query Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_QUANTIFIED_VALUE_SELECT__QUERY_EXPR = eINSTANCE.getPredicateQuantifiedValueSelect_QueryExpr();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_QUANTIFIED_VALUE_SELECT__VALUE_EXPR = eINSTANCE.getPredicateQuantifiedValueSelect_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedRowSelectImpl <em>Predicate Quantified Row Select</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateQuantifiedRowSelectImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateQuantifiedRowSelect()
         * @generated
         */
		EClass PREDICATE_QUANTIFIED_ROW_SELECT = eINSTANCE.getPredicateQuantifiedRowSelect();

		/**
         * The meta object literal for the '<em><b>Quantified Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute PREDICATE_QUANTIFIED_ROW_SELECT__QUANTIFIED_TYPE = eINSTANCE.getPredicateQuantifiedRowSelect_QuantifiedType();

		/**
         * The meta object literal for the '<em><b>Query Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_QUANTIFIED_ROW_SELECT__QUERY_EXPR = eINSTANCE.getPredicateQuantifiedRowSelect_QueryExpr();

		/**
         * The meta object literal for the '<em><b>Value Expr List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_QUANTIFIED_ROW_SELECT__VALUE_EXPR_LIST = eINSTANCE.getPredicateQuantifiedRowSelect_ValueExprList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueSelectImpl <em>Predicate In Value Select</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueSelectImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateInValueSelect()
         * @generated
         */
		EClass PREDICATE_IN_VALUE_SELECT = eINSTANCE.getPredicateInValueSelect();

		/**
         * The meta object literal for the '<em><b>Query Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_IN_VALUE_SELECT__QUERY_EXPR = eINSTANCE.getPredicateInValueSelect_QueryExpr();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_IN_VALUE_SELECT__VALUE_EXPR = eINSTANCE.getPredicateInValueSelect_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueListImpl <em>Predicate In Value List</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueListImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateInValueList()
         * @generated
         */
		EClass PREDICATE_IN_VALUE_LIST = eINSTANCE.getPredicateInValueList();

		/**
         * The meta object literal for the '<em><b>Value Expr List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_IN_VALUE_LIST__VALUE_EXPR_LIST = eINSTANCE.getPredicateInValueList_ValueExprList();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_IN_VALUE_LIST__VALUE_EXPR = eINSTANCE.getPredicateInValueList_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueRowSelectImpl <em>Predicate In Value Row Select</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.PredicateInValueRowSelectImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateInValueRowSelect()
         * @generated
         */
		EClass PREDICATE_IN_VALUE_ROW_SELECT = eINSTANCE.getPredicateInValueRowSelect();

		/**
         * The meta object literal for the '<em><b>Value Expr List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_IN_VALUE_ROW_SELECT__VALUE_EXPR_LIST = eINSTANCE.getPredicateInValueRowSelect_ValueExprList();

		/**
         * The meta object literal for the '<em><b>Query Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference PREDICATE_IN_VALUE_ROW_SELECT__QUERY_EXPR = eINSTANCE.getPredicateInValueRowSelect_QueryExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionSimpleImpl <em>Value Expression Simple</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionSimpleImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionSimple()
         * @generated
         */
		EClass VALUE_EXPRESSION_SIMPLE = eINSTANCE.getValueExpressionSimple();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALUE_EXPRESSION_SIMPLE__VALUE = eINSTANCE.getValueExpressionSimple_Value();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionColumnImpl <em>Value Expression Column</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionColumnImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionColumn()
         * @generated
         */
		EClass VALUE_EXPRESSION_COLUMN = eINSTANCE.getValueExpressionColumn();

		/**
         * The meta object literal for the '<em><b>Assignment Expr Target</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_COLUMN__ASSIGNMENT_EXPR_TARGET = eINSTANCE.getValueExpressionColumn_AssignmentExprTarget();

		/**
         * The meta object literal for the '<em><b>Parent Table Expr</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_COLUMN__PARENT_TABLE_EXPR = eINSTANCE.getValueExpressionColumn_ParentTableExpr();

		/**
         * The meta object literal for the '<em><b>Insert Statement</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_COLUMN__INSERT_STATEMENT = eINSTANCE.getValueExpressionColumn_InsertStatement();

		/**
         * The meta object literal for the '<em><b>Table Expr</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_COLUMN__TABLE_EXPR = eINSTANCE.getValueExpressionColumn_TableExpr();

		/**
         * The meta object literal for the '<em><b>Table In Database</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_COLUMN__TABLE_IN_DATABASE = eINSTANCE.getValueExpressionColumn_TableInDatabase();

		/**
         * The meta object literal for the '<em><b>Merge Insert Spec</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VALUE_EXPRESSION_COLUMN__MERGE_INSERT_SPEC = eINSTANCE.getValueExpressionColumn_MergeInsertSpec();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionVariableImpl <em>Value Expression Variable</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionVariableImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionVariable()
         * @generated
         */
		EClass VALUE_EXPRESSION_VARIABLE = eINSTANCE.getValueExpressionVariable();

		/**
         * The meta object literal for the '<em><b>Query Select</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_VARIABLE__QUERY_SELECT = eINSTANCE.getValueExpressionVariable_QuerySelect();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionScalarSelectImpl <em>Value Expression Scalar Select</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionScalarSelectImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionScalarSelect()
         * @generated
         */
		EClass VALUE_EXPRESSION_SCALAR_SELECT = eINSTANCE.getValueExpressionScalarSelect();

		/**
         * The meta object literal for the '<em><b>Query Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_SCALAR_SELECT__QUERY_EXPR = eINSTANCE.getValueExpressionScalarSelect_QueryExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionLabeledDurationImpl <em>Value Expression Labeled Duration</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionLabeledDurationImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionLabeledDuration()
         * @generated
         */
		EClass VALUE_EXPRESSION_LABELED_DURATION = eINSTANCE.getValueExpressionLabeledDuration();

		/**
         * The meta object literal for the '<em><b>Labeled Duration Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALUE_EXPRESSION_LABELED_DURATION__LABELED_DURATION_TYPE = eINSTANCE.getValueExpressionLabeledDuration_LabeledDurationType();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_LABELED_DURATION__VALUE_EXPR = eINSTANCE.getValueExpressionLabeledDuration_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseImpl <em>Value Expression Case</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCase()
         * @generated
         */
		EClass VALUE_EXPRESSION_CASE = eINSTANCE.getValueExpressionCase();

		/**
         * The meta object literal for the '<em><b>Case Else</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE__CASE_ELSE = eINSTANCE.getValueExpressionCase_CaseElse();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCastImpl <em>Value Expression Cast</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCastImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCast()
         * @generated
         */
		EClass VALUE_EXPRESSION_CAST = eINSTANCE.getValueExpressionCast();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CAST__VALUE_EXPR = eINSTANCE.getValueExpressionCast_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionNullValueImpl <em>Value Expression Null Value</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionNullValueImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionNullValue()
         * @generated
         */
		EClass VALUE_EXPRESSION_NULL_VALUE = eINSTANCE.getValueExpressionNullValue();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionDefaultValueImpl <em>Value Expression Default Value</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionDefaultValueImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionDefaultValue()
         * @generated
         */
		EClass VALUE_EXPRESSION_DEFAULT_VALUE = eINSTANCE.getValueExpressionDefaultValue();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl <em>Value Expression Function</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionFunctionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionFunction()
         * @generated
         */
		EClass VALUE_EXPRESSION_FUNCTION = eINSTANCE.getValueExpressionFunction();

		/**
         * The meta object literal for the '<em><b>Special Register</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALUE_EXPRESSION_FUNCTION__SPECIAL_REGISTER = eINSTANCE.getValueExpressionFunction_SpecialRegister();

		/**
         * The meta object literal for the '<em><b>Distinct</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALUE_EXPRESSION_FUNCTION__DISTINCT = eINSTANCE.getValueExpressionFunction_Distinct();

		/**
         * The meta object literal for the '<em><b>Column Function</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALUE_EXPRESSION_FUNCTION__COLUMN_FUNCTION = eINSTANCE.getValueExpressionFunction_ColumnFunction();

		/**
         * The meta object literal for the '<em><b>Parameter List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_FUNCTION__PARAMETER_LIST = eINSTANCE.getValueExpressionFunction_ParameterList();

		/**
         * The meta object literal for the '<em><b>Function</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_FUNCTION__FUNCTION = eINSTANCE.getValueExpressionFunction_Function();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCombinedImpl <em>Value Expression Combined</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCombinedImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCombined()
         * @generated
         */
		EClass VALUE_EXPRESSION_COMBINED = eINSTANCE.getValueExpressionCombined();

		/**
         * The meta object literal for the '<em><b>Combined Operator</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute VALUE_EXPRESSION_COMBINED__COMBINED_OPERATOR = eINSTANCE.getValueExpressionCombined_CombinedOperator();

		/**
         * The meta object literal for the '<em><b>Left Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_COMBINED__LEFT_VALUE_EXPR = eINSTANCE.getValueExpressionCombined_LeftValueExpr();

		/**
         * The meta object literal for the '<em><b>Right Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_COMBINED__RIGHT_VALUE_EXPR = eINSTANCE.getValueExpressionCombined_RightValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsImpl <em>Grouping Sets</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingSets()
         * @generated
         */
		EClass GROUPING_SETS = eINSTANCE.getGroupingSets();

		/**
         * The meta object literal for the '<em><b>Grouping Sets Element List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUPING_SETS__GROUPING_SETS_ELEMENT_LIST = eINSTANCE.getGroupingSets_GroupingSetsElementList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingImpl <em>Grouping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGrouping()
         * @generated
         */
		EClass GROUPING = eINSTANCE.getGrouping();

		/**
         * The meta object literal for the '<em><b>Grouping Sets Element Expr</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUPING__GROUPING_SETS_ELEMENT_EXPR = eINSTANCE.getGrouping_GroupingSetsElementExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementImpl <em>Grouping Sets Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingSetsElement()
         * @generated
         */
		EClass GROUPING_SETS_ELEMENT = eINSTANCE.getGroupingSetsElement();

		/**
         * The meta object literal for the '<em><b>Grouping Sets</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUPING_SETS_ELEMENT__GROUPING_SETS = eINSTANCE.getGroupingSetsElement_GroupingSets();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementSublistImpl <em>Grouping Sets Element Sublist</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementSublistImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingSetsElementSublist()
         * @generated
         */
		EClass GROUPING_SETS_ELEMENT_SUBLIST = eINSTANCE.getGroupingSetsElementSublist();

		/**
         * The meta object literal for the '<em><b>Grouping Sets Element Expr List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUPING_SETS_ELEMENT_SUBLIST__GROUPING_SETS_ELEMENT_EXPR_LIST = eINSTANCE.getGroupingSetsElementSublist_GroupingSetsElementExprList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementExpressionImpl <em>Grouping Sets Element Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingSetsElementExpressionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingSetsElementExpression()
         * @generated
         */
		EClass GROUPING_SETS_ELEMENT_EXPRESSION = eINSTANCE.getGroupingSetsElementExpression();

		/**
         * The meta object literal for the '<em><b>Grouping Sets Element Sublist</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING_SETS_ELEMENT_SUBLIST = eINSTANCE.getGroupingSetsElementExpression_GroupingSetsElementSublist();

		/**
         * The meta object literal for the '<em><b>Grouping</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUPING_SETS_ELEMENT_EXPRESSION__GROUPING = eINSTANCE.getGroupingSetsElementExpression_Grouping();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupImpl <em>Super Group</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSuperGroup()
         * @generated
         */
		EClass SUPER_GROUP = eINSTANCE.getSuperGroup();

		/**
         * The meta object literal for the '<em><b>Super Group Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute SUPER_GROUP__SUPER_GROUP_TYPE = eINSTANCE.getSuperGroup_SuperGroupType();

		/**
         * The meta object literal for the '<em><b>Super Group Element List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SUPER_GROUP__SUPER_GROUP_ELEMENT_LIST = eINSTANCE.getSuperGroup_SuperGroupElementList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.GroupingExpressionImpl <em>Grouping Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.GroupingExpressionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getGroupingExpression()
         * @generated
         */
		EClass GROUPING_EXPRESSION = eINSTANCE.getGroupingExpression();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUPING_EXPRESSION__VALUE_EXPR = eINSTANCE.getGroupingExpression_ValueExpr();

		/**
         * The meta object literal for the '<em><b>Super Group Element Expr</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference GROUPING_EXPRESSION__SUPER_GROUP_ELEMENT_EXPR = eINSTANCE.getGroupingExpression_SuperGroupElementExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementImpl <em>Super Group Element</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSuperGroupElement()
         * @generated
         */
		EClass SUPER_GROUP_ELEMENT = eINSTANCE.getSuperGroupElement();

		/**
         * The meta object literal for the '<em><b>Super Group</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SUPER_GROUP_ELEMENT__SUPER_GROUP = eINSTANCE.getSuperGroupElement_SuperGroup();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementSublistImpl <em>Super Group Element Sublist</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementSublistImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSuperGroupElementSublist()
         * @generated
         */
		EClass SUPER_GROUP_ELEMENT_SUBLIST = eINSTANCE.getSuperGroupElementSublist();

		/**
         * The meta object literal for the '<em><b>Super Group Element Expr List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SUPER_GROUP_ELEMENT_SUBLIST__SUPER_GROUP_ELEMENT_EXPR_LIST = eINSTANCE.getSuperGroupElementSublist_SuperGroupElementExprList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementExpressionImpl <em>Super Group Element Expression</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SuperGroupElementExpressionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSuperGroupElementExpression()
         * @generated
         */
		EClass SUPER_GROUP_ELEMENT_EXPRESSION = eINSTANCE.getSuperGroupElementExpression();

		/**
         * The meta object literal for the '<em><b>Super Group Element Sublist</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SUPER_GROUP_ELEMENT_EXPRESSION__SUPER_GROUP_ELEMENT_SUBLIST = eINSTANCE.getSuperGroupElementExpression_SuperGroupElementSublist();

		/**
         * The meta object literal for the '<em><b>Grouping Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SUPER_GROUP_ELEMENT_EXPRESSION__GROUPING_EXPR = eINSTANCE.getSuperGroupElementExpression_GroupingExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchImpl <em>Value Expression Case Search</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCaseSearch()
         * @generated
         */
		EClass VALUE_EXPRESSION_CASE_SEARCH = eINSTANCE.getValueExpressionCaseSearch();

		/**
         * The meta object literal for the '<em><b>Search Content List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_SEARCH__SEARCH_CONTENT_LIST = eINSTANCE.getValueExpressionCaseSearch_SearchContentList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleImpl <em>Value Expression Case Simple</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCaseSimple()
         * @generated
         */
		EClass VALUE_EXPRESSION_CASE_SIMPLE = eINSTANCE.getValueExpressionCaseSimple();

		/**
         * The meta object literal for the '<em><b>Content List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_SIMPLE__CONTENT_LIST = eINSTANCE.getValueExpressionCaseSimple_ContentList();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_SIMPLE__VALUE_EXPR = eINSTANCE.getValueExpressionCaseSimple_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseElseImpl <em>Value Expression Case Else</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseElseImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCaseElse()
         * @generated
         */
		EClass VALUE_EXPRESSION_CASE_ELSE = eINSTANCE.getValueExpressionCaseElse();

		/**
         * The meta object literal for the '<em><b>Value Expr Case</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR_CASE = eINSTANCE.getValueExpressionCaseElse_ValueExprCase();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_ELSE__VALUE_EXPR = eINSTANCE.getValueExpressionCaseElse_ValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchContentImpl <em>Value Expression Case Search Content</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSearchContentImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCaseSearchContent()
         * @generated
         */
		EClass VALUE_EXPRESSION_CASE_SEARCH_CONTENT = eINSTANCE.getValueExpressionCaseSearchContent();

		/**
         * The meta object literal for the '<em><b>Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR = eINSTANCE.getValueExpressionCaseSearchContent_ValueExpr();

		/**
         * The meta object literal for the '<em><b>Search Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_SEARCH_CONTENT__SEARCH_CONDITION = eINSTANCE.getValueExpressionCaseSearchContent_SearchCondition();

		/**
         * The meta object literal for the '<em><b>Value Expr Case Search</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_SEARCH_CONTENT__VALUE_EXPR_CASE_SEARCH = eINSTANCE.getValueExpressionCaseSearchContent_ValueExprCaseSearch();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleContentImpl <em>Value Expression Case Simple Content</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionCaseSimpleContentImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCaseSimpleContent()
         * @generated
         */
		EClass VALUE_EXPRESSION_CASE_SIMPLE_CONTENT = eINSTANCE.getValueExpressionCaseSimpleContent();

		/**
         * The meta object literal for the '<em><b>Value Expr Case Simple</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__VALUE_EXPR_CASE_SIMPLE = eINSTANCE.getValueExpressionCaseSimpleContent_ValueExprCaseSimple();

		/**
         * The meta object literal for the '<em><b>When Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__WHEN_VALUE_EXPR = eINSTANCE.getValueExpressionCaseSimpleContent_WhenValueExpr();

		/**
         * The meta object literal for the '<em><b>Result Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_CASE_SIMPLE_CONTENT__RESULT_VALUE_EXPR = eINSTANCE.getValueExpressionCaseSimpleContent_ResultValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl <em>Table In Database</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.TableInDatabaseImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableInDatabase()
         * @generated
         */
		EClass TABLE_IN_DATABASE = eINSTANCE.getTableInDatabase();

		/**
         * The meta object literal for the '<em><b>Update Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_IN_DATABASE__UPDATE_STATEMENT = eINSTANCE.getTableInDatabase_UpdateStatement();

		/**
         * The meta object literal for the '<em><b>Delete Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_IN_DATABASE__DELETE_STATEMENT = eINSTANCE.getTableInDatabase_DeleteStatement();

		/**
         * The meta object literal for the '<em><b>Insert Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_IN_DATABASE__INSERT_STATEMENT = eINSTANCE.getTableInDatabase_InsertStatement();

		/**
         * The meta object literal for the '<em><b>Database Table</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_IN_DATABASE__DATABASE_TABLE = eINSTANCE.getTableInDatabase_DatabaseTable();

		/**
         * The meta object literal for the '<em><b>Derived Column List</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_IN_DATABASE__DERIVED_COLUMN_LIST = eINSTANCE.getTableInDatabase_DerivedColumnList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableFunctionImpl <em>Table Function</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.TableFunctionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableFunction()
         * @generated
         */
		EClass TABLE_FUNCTION = eINSTANCE.getTableFunction();

		/**
         * The meta object literal for the '<em><b>Function</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TABLE_FUNCTION__FUNCTION = eINSTANCE.getTableFunction_Function();

        /**
         * The meta object literal for the '<em><b>Parameter List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TABLE_FUNCTION__PARAMETER_LIST = eINSTANCE.getTableFunction_ParameterList();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl <em>SQL Query Object</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryObjectImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSQLQueryObject()
         * @generated
         */
		EClass SQL_QUERY_OBJECT = eINSTANCE.getSQLQueryObject();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryChangeStatementImpl <em>Query Change Statement</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryChangeStatementImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryChangeStatement()
         * @generated
         */
		EClass QUERY_CHANGE_STATEMENT = eINSTANCE.getQueryChangeStatement();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ColumnNameImpl <em>Column Name</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ColumnNameImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getColumnName()
         * @generated
         */
		EClass COLUMN_NAME = eINSTANCE.getColumnName();

		/**
         * The meta object literal for the '<em><b>Table Correlation</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference COLUMN_NAME__TABLE_CORRELATION = eINSTANCE.getColumnName_TableCorrelation();

		/**
         * The meta object literal for the '<em><b>With Table Specification</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference COLUMN_NAME__WITH_TABLE_SPECIFICATION = eINSTANCE.getColumnName_WithTableSpecification();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableNestedImpl <em>Table Nested</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.TableNestedImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableNested()
         * @generated
         */
		EClass TABLE_NESTED = eINSTANCE.getTableNested();

		/**
         * The meta object literal for the '<em><b>Nested Table Ref</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_NESTED__NESTED_TABLE_REF = eINSTANCE.getTableNested_NestedTableRef();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryMergeStatementImpl <em>Query Merge Statement</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryMergeStatementImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryMergeStatement()
         * @generated
         */
		EClass QUERY_MERGE_STATEMENT = eINSTANCE.getQueryMergeStatement();

		/**
         * The meta object literal for the '<em><b>Target Table</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_MERGE_STATEMENT__TARGET_TABLE = eINSTANCE.getQueryMergeStatement_TargetTable();

        /**
         * The meta object literal for the '<em><b>Source Table</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_MERGE_STATEMENT__SOURCE_TABLE = eINSTANCE.getQueryMergeStatement_SourceTable();

        /**
         * The meta object literal for the '<em><b>On Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_MERGE_STATEMENT__ON_CONDITION = eINSTANCE.getQueryMergeStatement_OnCondition();

        /**
         * The meta object literal for the '<em><b>Operation Spec List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_MERGE_STATEMENT__OPERATION_SPEC_LIST = eINSTANCE.getQueryMergeStatement_OperationSpecList();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionNestedImpl <em>Search Condition Nested</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SearchConditionNestedImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSearchConditionNested()
         * @generated
         */
		EClass SEARCH_CONDITION_NESTED = eINSTANCE.getSearchConditionNested();

		/**
         * The meta object literal for the '<em><b>Nested Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference SEARCH_CONDITION_NESTED__NESTED_CONDITION = eINSTANCE.getSearchConditionNested_NestedCondition();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionNestedImpl <em>Value Expression Nested</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionNestedImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionNested()
         * @generated
         */
		EClass VALUE_EXPRESSION_NESTED = eINSTANCE.getValueExpressionNested();

		/**
         * The meta object literal for the '<em><b>Nested Value Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference VALUE_EXPRESSION_NESTED__NESTED_VALUE_EXPR = eINSTANCE.getValueExpressionNested_NestedValueExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionAtomicImpl <em>Value Expression Atomic</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionAtomicImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionAtomic()
         * @generated
         */
		EClass VALUE_EXPRESSION_ATOMIC = eINSTANCE.getValueExpressionAtomic();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl <em>Order By Specification</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.OrderBySpecificationImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getOrderBySpecification()
         * @generated
         */
		EClass ORDER_BY_SPECIFICATION = eINSTANCE.getOrderBySpecification();

		/**
         * The meta object literal for the '<em><b>Descending</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ORDER_BY_SPECIFICATION__DESCENDING = eINSTANCE.getOrderBySpecification_Descending();

		/**
         * The meta object literal for the '<em><b>Ordering Spec Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ORDER_BY_SPECIFICATION__ORDERING_SPEC_OPTION = eINSTANCE.getOrderBySpecification_OrderingSpecOption();

		/**
         * The meta object literal for the '<em><b>Null Ordering Option</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ORDER_BY_SPECIFICATION__NULL_ORDERING_OPTION = eINSTANCE.getOrderBySpecification_NullOrderingOption();

		/**
         * The meta object literal for the '<em><b>Select Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ORDER_BY_SPECIFICATION__SELECT_STATEMENT = eINSTANCE.getOrderBySpecification_SelectStatement();

		/**
         * The meta object literal for the '<em><b>Query</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ORDER_BY_SPECIFICATION__QUERY = eINSTANCE.getOrderBySpecification_Query();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderByOrdinalImpl <em>Order By Ordinal</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.OrderByOrdinalImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getOrderByOrdinal()
         * @generated
         */
		EClass ORDER_BY_ORDINAL = eINSTANCE.getOrderByOrdinal();

		/**
         * The meta object literal for the '<em><b>Ordinal Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ORDER_BY_ORDINAL__ORDINAL_VALUE = eINSTANCE.getOrderByOrdinal_OrdinalValue();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableCorrelationImpl <em>Table Correlation</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.TableCorrelationImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableCorrelation()
         * @generated
         */
		EClass TABLE_CORRELATION = eINSTANCE.getTableCorrelation();

		/**
         * The meta object literal for the '<em><b>Table Expr</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_CORRELATION__TABLE_EXPR = eINSTANCE.getTableCorrelation_TableExpr();

		/**
         * The meta object literal for the '<em><b>Column Name List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE_CORRELATION__COLUMN_NAME_LIST = eINSTANCE.getTableCorrelation_ColumnNameList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceImpl <em>Update Source</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdateSource()
         * @generated
         */
		EClass UPDATE_SOURCE = eINSTANCE.getUpdateSource();

		/**
         * The meta object literal for the '<em><b>Update Assignment Expr</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference UPDATE_SOURCE__UPDATE_ASSIGNMENT_EXPR = eINSTANCE.getUpdateSource_UpdateAssignmentExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceExprListImpl <em>Update Source Expr List</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceExprListImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdateSourceExprList()
         * @generated
         */
		EClass UPDATE_SOURCE_EXPR_LIST = eINSTANCE.getUpdateSourceExprList();

		/**
         * The meta object literal for the '<em><b>Value Expr List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference UPDATE_SOURCE_EXPR_LIST__VALUE_EXPR_LIST = eINSTANCE.getUpdateSourceExprList_ValueExprList();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceQueryImpl <em>Update Source Query</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdateSourceQueryImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdateSourceQuery()
         * @generated
         */
		EClass UPDATE_SOURCE_QUERY = eINSTANCE.getUpdateSourceQuery();

		/**
         * The meta object literal for the '<em><b>Query Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference UPDATE_SOURCE_QUERY__QUERY_EXPR = eINSTANCE.getUpdateSourceQuery_QueryExpr();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.OrderByResultColumnImpl <em>Order By Result Column</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.OrderByResultColumnImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getOrderByResultColumn()
         * @generated
         */
		EClass ORDER_BY_RESULT_COLUMN = eINSTANCE.getOrderByResultColumn();

		/**
         * The meta object literal for the '<em><b>Result Col</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ORDER_BY_RESULT_COLUMN__RESULT_COL = eINSTANCE.getOrderByResultColumn_ResultCol();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.WithTableReferenceImpl <em>With Table Reference</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.WithTableReferenceImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getWithTableReference()
         * @generated
         */
		EClass WITH_TABLE_REFERENCE = eINSTANCE.getWithTableReference();

		/**
         * The meta object literal for the '<em><b>With Table Specification</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference WITH_TABLE_REFERENCE__WITH_TABLE_SPECIFICATION = eINSTANCE.getWithTableReference_WithTableSpecification();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.QueryNestedImpl <em>Query Nested</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.QueryNestedImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryNested()
         * @generated
         */
        EClass QUERY_NESTED = eINSTANCE.getQueryNested();

        /**
         * The meta object literal for the '<em><b>Nested Query</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference QUERY_NESTED__NESTED_QUERY = eINSTANCE.getQueryNested_NestedQuery();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionRowImpl <em>Value Expression Row</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ValueExpressionRowImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionRow()
         * @generated
         */
        EClass VALUE_EXPRESSION_ROW = eINSTANCE.getValueExpressionRow();

        /**
         * The meta object literal for the '<em><b>Value Expr List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference VALUE_EXPRESSION_ROW__VALUE_EXPR_LIST = eINSTANCE.getValueExpressionRow_ValueExprList();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeTargetTableImpl <em>Merge Target Table</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeTargetTableImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeTargetTable()
         * @generated
         */
        EClass MERGE_TARGET_TABLE = eINSTANCE.getMergeTargetTable();

        /**
         * The meta object literal for the '<em><b>Merge Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_TARGET_TABLE__MERGE_STATEMENT = eINSTANCE.getMergeTargetTable_MergeStatement();

        /**
         * The meta object literal for the '<em><b>Table Expr</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_TARGET_TABLE__TABLE_EXPR = eINSTANCE.getMergeTargetTable_TableExpr();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeSourceTableImpl <em>Merge Source Table</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeSourceTableImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeSourceTable()
         * @generated
         */
        EClass MERGE_SOURCE_TABLE = eINSTANCE.getMergeSourceTable();

        /**
         * The meta object literal for the '<em><b>Query Merge Statement</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_SOURCE_TABLE__QUERY_MERGE_STATEMENT = eINSTANCE.getMergeSourceTable_QueryMergeStatement();

        /**
         * The meta object literal for the '<em><b>Merge Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_SOURCE_TABLE__MERGE_STATEMENT = eINSTANCE.getMergeSourceTable_MergeStatement();

        /**
         * The meta object literal for the '<em><b>Table Ref</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_SOURCE_TABLE__TABLE_REF = eINSTANCE.getMergeSourceTable_TableRef();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeOnConditionImpl <em>Merge On Condition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeOnConditionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeOnCondition()
         * @generated
         */
        EClass MERGE_ON_CONDITION = eINSTANCE.getMergeOnCondition();

        /**
         * The meta object literal for the '<em><b>Merge Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_ON_CONDITION__MERGE_STATEMENT = eINSTANCE.getMergeOnCondition_MergeStatement();

        /**
         * The meta object literal for the '<em><b>Search Condition</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_ON_CONDITION__SEARCH_CONDITION = eINSTANCE.getMergeOnCondition_SearchCondition();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeUpdateSpecificationImpl <em>Merge Update Specification</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeUpdateSpecificationImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeUpdateSpecification()
         * @generated
         */
        EClass MERGE_UPDATE_SPECIFICATION = eINSTANCE.getMergeUpdateSpecification();

        /**
         * The meta object literal for the '<em><b>Assignement Expr List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_UPDATE_SPECIFICATION__ASSIGNEMENT_EXPR_LIST = eINSTANCE.getMergeUpdateSpecification_AssignementExprList();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeInsertSpecificationImpl <em>Merge Insert Specification</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeInsertSpecificationImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeInsertSpecification()
         * @generated
         */
        EClass MERGE_INSERT_SPECIFICATION = eINSTANCE.getMergeInsertSpecification();

        /**
         * The meta object literal for the '<em><b>Target Column List</b></em>' reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_INSERT_SPECIFICATION__TARGET_COLUMN_LIST = eINSTANCE.getMergeInsertSpecification_TargetColumnList();

        /**
         * The meta object literal for the '<em><b>Source Values Row</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_INSERT_SPECIFICATION__SOURCE_VALUES_ROW = eINSTANCE.getMergeInsertSpecification_SourceValuesRow();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.MergeOperationSpecificationImpl <em>Merge Operation Specification</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.MergeOperationSpecificationImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getMergeOperationSpecification()
         * @generated
         */
        EClass MERGE_OPERATION_SPECIFICATION = eINSTANCE.getMergeOperationSpecification();

        /**
         * The meta object literal for the '<em><b>Merge Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference MERGE_OPERATION_SPECIFICATION__MERGE_STATEMENT = eINSTANCE.getMergeOperationSpecification_MergeStatement();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdateOfColumnImpl <em>Update Of Column</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdateOfColumnImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdateOfColumn()
         * @generated
         */
        EClass UPDATE_OF_COLUMN = eINSTANCE.getUpdateOfColumn();

        /**
         * The meta object literal for the '<em><b>Updatability Expr</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference UPDATE_OF_COLUMN__UPDATABILITY_EXPR = eINSTANCE.getUpdateOfColumn_UpdatabilityExpr();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.UpdatabilityExpressionImpl <em>Updatability Expression</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.UpdatabilityExpressionImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdatabilityExpression()
         * @generated
         */
        EClass UPDATABILITY_EXPRESSION = eINSTANCE.getUpdatabilityExpression();

        /**
         * The meta object literal for the '<em><b>Updatability Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute UPDATABILITY_EXPRESSION__UPDATABILITY_TYPE = eINSTANCE.getUpdatabilityExpression_UpdatabilityType();

        /**
         * The meta object literal for the '<em><b>Update Of Column List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference UPDATABILITY_EXPRESSION__UPDATE_OF_COLUMN_LIST = eINSTANCE.getUpdatabilityExpression_UpdateOfColumnList();

        /**
         * The meta object literal for the '<em><b>Select Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference UPDATABILITY_EXPRESSION__SELECT_STATEMENT = eINSTANCE.getUpdatabilityExpression_SelectStatement();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.CallStatementImpl <em>Call Statement</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.CallStatementImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getCallStatement()
         * @generated
         */
        EClass CALL_STATEMENT = eINSTANCE.getCallStatement();

        /**
         * The meta object literal for the '<em><b>Argument List</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CALL_STATEMENT__ARGUMENT_LIST = eINSTANCE.getCallStatement_ArgumentList();

        /**
         * The meta object literal for the '<em><b>Procedure Ref</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference CALL_STATEMENT__PROCEDURE_REF = eINSTANCE.getCallStatement_ProcedureRef();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.ProcedureReferenceImpl <em>Procedure Reference</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.ProcedureReferenceImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getProcedureReference()
         * @generated
         */
        EClass PROCEDURE_REFERENCE = eINSTANCE.getProcedureReference();

        /**
         * The meta object literal for the '<em><b>Call Statement</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROCEDURE_REFERENCE__CALL_STATEMENT = eINSTANCE.getProcedureReference_CallStatement();

        /**
         * The meta object literal for the '<em><b>Procedure</b></em>' reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference PROCEDURE_REFERENCE__PROCEDURE = eINSTANCE.getProcedureReference_Procedure();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.impl.TableQueryLateralImpl <em>Table Query Lateral</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.impl.TableQueryLateralImpl
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableQueryLateral()
         * @generated
         */
        EClass TABLE_QUERY_LATERAL = eINSTANCE.getTableQueryLateral();

        /**
         * The meta object literal for the '<em><b>Query</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference TABLE_QUERY_LATERAL__QUERY = eINSTANCE.getTableQueryLateral_Query();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupType <em>Super Group Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupType
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSuperGroupType()
         * @generated
         */
		EEnum SUPER_GROUP_TYPE = eINSTANCE.getSuperGroupType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType <em>Predicate Quantified Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateQuantifiedType()
         * @generated
         */
		EEnum PREDICATE_QUANTIFIED_TYPE = eINSTANCE.getPredicateQuantifiedType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator <em>Predicate Comparison Operator</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getPredicateComparisonOperator()
         * @generated
         */
		EEnum PREDICATE_COMPARISON_OPERATOR = eINSTANCE.getPredicateComparisonOperator();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator <em>Search Condition Combined Operator</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.SearchConditionCombinedOperator
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getSearchConditionCombinedOperator()
         * @generated
         */
		EEnum SEARCH_CONDITION_COMBINED_OPERATOR = eINSTANCE.getSearchConditionCombinedOperator();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator <em>Table Joined Operator</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.TableJoinedOperator
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getTableJoinedOperator()
         * @generated
         */
		EEnum TABLE_JOINED_OPERATOR = eINSTANCE.getTableJoinedOperator();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator <em>Query Combined Operator</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.QueryCombinedOperator
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getQueryCombinedOperator()
         * @generated
         */
		EEnum QUERY_COMBINED_OPERATOR = eINSTANCE.getQueryCombinedOperator();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator <em>Value Expression Unary Operator</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionUnaryOperator
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionUnaryOperator()
         * @generated
         */
		EEnum VALUE_EXPRESSION_UNARY_OPERATOR = eINSTANCE.getValueExpressionUnaryOperator();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator <em>Value Expression Combined Operator</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionCombinedOperator()
         * @generated
         */
		EEnum VALUE_EXPRESSION_COMBINED_OPERATOR = eINSTANCE.getValueExpressionCombinedOperator();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType <em>Value Expression Labeled Duration Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getValueExpressionLabeledDurationType()
         * @generated
         */
		EEnum VALUE_EXPRESSION_LABELED_DURATION_TYPE = eINSTANCE.getValueExpressionLabeledDurationType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.NullOrderingType <em>Null Ordering Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.NullOrderingType
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getNullOrderingType()
         * @generated
         */
		EEnum NULL_ORDERING_TYPE = eINSTANCE.getNullOrderingType();

		/**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.OrderingSpecType <em>Ordering Spec Type</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.OrderingSpecType
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getOrderingSpecType()
         * @generated
         */
		EEnum ORDERING_SPEC_TYPE = eINSTANCE.getOrderingSpecType();

        /**
         * The meta object literal for the '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityType <em>Updatability Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityType
         * @see org.eclipse.datatools.modelbase.sql.query.impl.SQLQueryModelPackageImpl#getUpdatabilityType()
         * @generated
         */
        EEnum UPDATABILITY_TYPE = eINSTANCE.getUpdatabilityType();

	}

} //SQLQueryModelPackage
