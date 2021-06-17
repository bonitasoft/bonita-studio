/*******************************************************************************
 * Copyright (c) 2004, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * The <code>SQLQuerySourceFormat</code> can be used to provide settings regarding the SQL source 
 * text for parsing or source generation from a <code>SQLQueryObject</code> model representation.
 * The SQLQuerySourceFormat will be maintained during the life cycle of a <code>QueryStatement</code> 
 * instance (via reference to SQLQuerySourceInfo, see 
 * {@link org.eclipse.datatools.modelbase.sql.query.SQLQueryObject#getSourceInfo()},
 * {@link org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceInfo#getSqlFormat()}),
 * where all <code>SQLQueryObject</code> elements of one <code>QueryStatement</code> share one instance 
 * of a <code>SQLQuerySourceFormat</code>.  That way the settings you specify for the parse are still 
 * available for generating the SQL source text for a <code>QueryStatement</code>.
 * Most source format options can be modified during the life cycle of a <code>QueryStatement</code>, 
 * exceptions are marked with a * below.
 * <p>
 * Source format options:
 * <ul>
 *     <li> <code>preserveSourceFormat</code> = the option to preserve the input source formating when 
 *     SQL source text is generated (default: false)
 *     <li> <code>statementTerminator</code> = the character separating multiple SQL statements (default: '')
 *     <li> <code>hostVariablePrefix</code> = the character that precedes a	host language variable (default: ':')
 *     <li> <code>parameterMarker</code> = the character that identifies a host language parameter (default: '?')
 *     <li> <code>delimitedIdentifierQuote</code>* = the character that encloses delimited identifiers whose
 *     writing in case will be preserved (default: '"')
 *     <li> <code>omitSchema</code> = the current schema (omitted in SQL source, implicit to unqualified 
 *     table references) (default: null)
 *     <li> <code>qualifyIdentifiers</code> = the flag describing how identifiers in the SQL source will 
 *     be qualified.  See the QUALIFY_IDENTIFIER_xxx constants defined in this class 
 *     (default: qualify identifiers in context)
 *     <li> <code>preserveComments</code> = the option to preserve comments in the parsed SQL source or/and 
 *     the generated SQL source (default: true)
 *     <li> <code>generateCommentsForStatementOnly</code> = the option to generate comments for the SQL source 
 *     only in the context of the complete statement, or if set to <code>false</code>, for single SQL Query 
 *     objects outside the context of a statement as well (default: true)
 *     <li><code>instanceIsFinal</code> = indicates whether or not the instance can be modified (default: false)
 *     <li><code>generateAsKeywordForTableCorrID</code> = option to include (or not) the AS keyword when 
 *     generating SQL for a table correlation ID.  (The AS is optional for DB2.  Oracle does not allow it at all.)
 *     (default: true)
 * </ul> 
 *    
 * <br><br>
 *  * Source format options that can not be modified during the life cycle of a
 * <code>QueryStatement</code>
 * <br><br>
 * See {@link #copyDefaultFormat()} to see how to get a
 * <code>SQLQuerySourceFormat</code> with the default settings.
 * <br>
 * See {@link #copyFields(SQLQuerySourceFormat)} to see how to copy the values
 * from another <code>SQLQuerySourceFormat</code>.
 * 
 * @author ckadner
 */
public class SQLQuerySourceFormat
{   
    /**
     * Default value for <code>preserveSourceFourmat</code> setting: <code>false</code>
     */
    public static final boolean PRESERVE_SOURCE_FORMAT_DEFAULT = false;
    
    /**
     *  Default value for the <code>statementTerminator</code> setting: ';' 
     */
    public static final char STATEMENT_TERMINATOR_DEFAULT = ';';
    
    /**
     *  Default value for the <code>hostVariablePrefix</code> setting: ':' 
     */
    public static final char HOSTVARIABLE_PREFIX_DEFAULT = ':';
    
    /**
     *  Default value for the <code>parameterMarker</code> setting: '?' 
     */
    public static final char PARAMETER_MARKER_DEFAULT = '?';
    
    /**
     *  Default value for the <code>delimitedIdentifierQuote</code> setting: '"' 
     */
    public static final char DELIMITED_IDENTIFIER_QUOTE_DEFAULT = '\"';
    
    /**
     * Default value for <code>omitSchema</code> setting: <code>null</code>
     */
    public static final String OMIT_SCHEMA_DEFAULT = null;
    
    /**
     * Default constant for {@link #setQualifyIdentifiers(int)}, and
     * {@link #getQualifyIdentifiers()}, used for SQL source generation. Column
     * and table names will be qualified depending on the context of the SQL
     * statement.
     */
    public static final int QUALIFY_IDENTIFIERS_IN_CONTEXT = 0;

    /**
     * Constant for {@link #setQualifyIdentifiers(int)}, and
     * {@link #getQualifyIdentifiers()}, used for SQL source generation. Table
     * names will always be qualified with schema names and column names with
     * table and schema names.
     */
    public static final int QUALIFY_IDENTIFIERS_WITH_SCHEMA_NAMES = 1;

    /**
     * Constant for {@link #setQualifyIdentifiers(int)}, and
     * {@link #getQualifyIdentifiers()}, used for SQL source generation. Table
     * names will never be qualified with schema names and column names will
     * only be qualified with table names.
     * <p>
     * <b>Note: </b> this configuration can lead to incorrectly generated source
     * for SQL statements!
     */
    public static final int QUALIFY_IDENTIFIERS_WITH_TABLE_NAMES = 2;

    /**
     * Constant for {@link #setQualifyIdentifiers(int)}, and
     * {@link #getQualifyIdentifiers()}, used for SQL source generation. Table
     * names will never be qualified with schema names and column names will
     * only be qualified with table names.
     * <p>
     * <b>Note: </b> this configuration can lead to incorrectly generated source
     * for SQL statements!
     */
    public static final int QUALIFY_IDENTIFIERS_NEVER = 3;

    /**
     * Default value for the <code>qualifyIdentifiers</code> setting: <code>QUALIFY_IDENTIFIERS_IN_CONTEXT</code>
     */
    public static final int QUALIFY_IDENTIFIERS_DEFAULT = QUALIFY_IDENTIFIERS_IN_CONTEXT;
    
    /**
     * Default value for the <code>preserveComments</code> setting: <code>true</code>
     */
    public static final boolean PRESERVE_COMMENTS_DEFAULT = true;
    
    /**
     * Default value for the <code>generateCommentsForStatementOnly</code> setting: <code>true</code>
     */
    public static final boolean GENERATE_COMMENTS_FOR_STATEMENT_DEFAULT = true;
    
    /**
     * Default value for the <code>instanceIsFinal</code> setting: <code>false</code>
     */
    public static final boolean INSTANCE_IS_FINAL_DEFAULT = false;
    
    /**
     * Default value for the <code>generateAsKeywordForTableCorrID</code> setting: <code>true</code>
     */
    public static final boolean GENERATE_AS_KEYWORD_FOR_TABLE_CORR_ID_DEFAULT = true;
    
    /**
     * Define the default <code>SQLQuerySourceFormat</code> object.
     * <b>Note:</b> this <code>SQLQuerySourceFormat</code>'s <code>instanceIsFinal</code> setting
     * means modifications on its members are not allowed.  Every attempt to do so will raise an
     * <code>UnsupportedOperationException</code>. Use
     * <code>{@link #copyDefaultFormat()}</code> to get a copy of 
     * <code>{@link #SQL_SOURCE_FORMAT_DEFAULT}</code>
     * </p>
     * Default <code>SQLQuerySourceFormat</code> properties:
     * <ul>
     *     <li> <code>preserveSourceFormat</code> = <code>false</code>
     *     <li> <code>statementTerminator</code> = {@link #STATEMENT_TERMINATOR_DEFAULT}
     *     <li> <code>hostVariablePrefix</code> = {@link #HOSTVARIABLE_PREFIX_DEFAULT}
     *     <li> <code>parameterMarker</code> = {@link #PARAMETER_MARKER_DEFAULT}
     *     <li> <code>delimitedIdentifierQuote</code> = {@link #DELIMITED_IDENTIFIER_QUOTE_DEFAULT}
     *     <li> <code>omitSchema</code> = <code>null</code>;
     *     <li> <code>qualifyIdentifiers</code> = {@link #QUALIFY_IDENTIFIERS_IN_CONTEXT}
     *     <li> <code>preserveComments</code> = <code>true</code>
     *     <li> <code>generateCommentsForStatementOnly</code> = <code>true</code>
     * </ul>
     * @see #copyDefaultFormat()
     */
    public static final SQLQuerySourceFormat SQL_SOURCE_FORMAT_DEFAULT          
            = new SQLQuerySourceFormat( PRESERVE_SOURCE_FORMAT_DEFAULT,
                                        STATEMENT_TERMINATOR_DEFAULT,
                                        HOSTVARIABLE_PREFIX_DEFAULT,
                                        PARAMETER_MARKER_DEFAULT,
                                        DELIMITED_IDENTIFIER_QUOTE_DEFAULT,
                                        OMIT_SCHEMA_DEFAULT, 
                                        QUALIFY_IDENTIFIERS_DEFAULT,
                                        PRESERVE_COMMENTS_DEFAULT,
                                        GENERATE_COMMENTS_FOR_STATEMENT_DEFAULT,
                                        true,
                                        GENERATE_AS_KEYWORD_FOR_TABLE_CORR_ID_DEFAULT);
        
    private boolean preserveSourceFormat = PRESERVE_SOURCE_FORMAT_DEFAULT;
    private char statementTerminator = STATEMENT_TERMINATOR_DEFAULT;
    private char hostVariablePrefix = HOSTVARIABLE_PREFIX_DEFAULT;
    private char parameterMarker = PARAMETER_MARKER_DEFAULT;
    private char delimitedIdentifierQuote = DELIMITED_IDENTIFIER_QUOTE_DEFAULT;
    private String omitSchema = OMIT_SCHEMA_DEFAULT;
    private int qualifyIdentifiers = QUALIFY_IDENTIFIERS_IN_CONTEXT;
    private boolean preserveComments = PRESERVE_COMMENTS_DEFAULT;
    private boolean generateCommentsForStatementOnly = GENERATE_COMMENTS_FOR_STATEMENT_DEFAULT;
    private boolean instanceIsFinal = INSTANCE_IS_FINAL_DEFAULT;
    private boolean generateAsKeywordForTableCorrID = GENERATE_AS_KEYWORD_FOR_TABLE_CORR_ID_DEFAULT;
    
    protected SQLQuerySourceFormat(boolean preserveSourceFormat,
                                char statementTerminator,
                                char hostVariablePrefix,
                                char parameterMarker,
                                char delimitedIdentifierQuote,
                                String omitSchema,
                                int qualifyIdentifiers,
                                boolean preserveComments,
                                boolean generateCommentsForStatementOnly)  {
        this(preserveSourceFormat,statementTerminator,hostVariablePrefix,parameterMarker,delimitedIdentifierQuote,omitSchema,qualifyIdentifiers,preserveComments,generateCommentsForStatementOnly,INSTANCE_IS_FINAL_DEFAULT, GENERATE_AS_KEYWORD_FOR_TABLE_CORR_ID_DEFAULT);
    }
        
    /**
     * @param preserveSourceFormat
     * @param statementTerminator
     * @param hostVariablePrefix
     * @param parameterMarker
     * @param delimitedIdentifierQuote
     * @param omitSchema
     * @param qualifyIdentifiers
     * @param preserveComments
     * @param generateCommentsForStatementOnly
     * @param finalInstance indicates that the consructed instance will not be modifyable
     */
    private SQLQuerySourceFormat(boolean preserveSourceFormat,
                                char statementTerminator,
                                char hostVariablePrefix,
                                char parameterMarker,
                                char delimitedIdentifierQuote,
                                String omitSchema,
                                int qualifyIdentifiers,
                                boolean preserveComments,
                                boolean generateCommentsForStatementOnly,
                                boolean finalInstance,
                                boolean generateAsKeywordForTableCorrID)
    {
        this.preserveSourceFormat = preserveSourceFormat;
        this.statementTerminator = statementTerminator;
        this.hostVariablePrefix = hostVariablePrefix;
        this.parameterMarker = parameterMarker;
        this.delimitedIdentifierQuote = delimitedIdentifierQuote;
        this.omitSchema = omitSchema;
        this.qualifyIdentifiers = qualifyIdentifiers;
        this.preserveComments = preserveComments;
        this.generateCommentsForStatementOnly = generateCommentsForStatementOnly;
        this.instanceIsFinal = finalInstance;
        this.generateAsKeywordForTableCorrID = generateAsKeywordForTableCorrID;
    }
    
    /*
     * The default constructor is private to make sure instances can't be created this way.
     */
    private SQLQuerySourceFormat() {}
    
    /**
     * Creates and returns a copy of the default <code>SQLQuerySourceFormat</code> object.
     * <b>Note:</b> the copy will have the <code>instanceIsfinal</code> setting set to false.
     * 
     * @return a new copy of the {@link #SQL_SOURCE_FORMAT_DEFAULT}
     */
    public static SQLQuerySourceFormat copyDefaultFormat() {
        return copySourceFormat(SQL_SOURCE_FORMAT_DEFAULT);
    }
    
    /**
     * Creates and returns a copy of the given <code>SQLQuerySourceFormat</code> object.
     * 
     * @param sourceFormat the <code>SQLQuerySourceFormat</code> to copy
     * @return the copy
     */
    public static SQLQuerySourceFormat copySourceFormat(SQLQuerySourceFormat sourceFormat) {
        SQLQuerySourceFormat copy = new SQLQuerySourceFormat();
        copyFields(sourceFormat, copy);
        return copy;
    }
    
    /**
     * Copies the fields from the given <code>SQLQuerySourceFormat</code>
     * <code>donor</code>
     * to the <code>SQLQuerySourceFormat</code>
     * <code>recipient</code>.
     * 
     * @param source the <code>SQLQuerySourceFormat</code> object containing the fields to copy
     * @param target the <code>SQLQuerySourceFormat</code> object to update
     */
    public static void copyFields(SQLQuerySourceFormat source, SQLQuerySourceFormat target) {
        Field[] fields = SQLQuerySourceFormat.class.getDeclaredFields();
        
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            
            if (!Modifier.isFinal(field.getModifiers()) && !field.getName().equals("instanceIsFinal")) { //$NON-NLS-1$
	            try {
	                field.set(target, field.get(source));
	            }
	            catch (IllegalArgumentException e) {
	                e.printStackTrace();
	            }
	            catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
            }
        }
        
        /* Make sure that the copy is modifiable. */
        target.instanceIsFinal = false;
    }
    
    /**
     * Copies the fields from the given <code>SQLQuerySourceFormat</code> to this object.
     * 
     * @param sourceFormat the source <code>SQLQuerySourceFormat</code> object
     */
    public void copyFields(SQLQuerySourceFormat sourceFormat) {
        SQLQuerySourceFormat.copyFields(sourceFormat, this);
    }
    
    
    /** 
     * Checks whether the <code>instanceIsFinal</code> setting is true, and if so, throws an exception.  This is called
     * from each setter method in this class.
     * 
     * @throws UnsupportedOperationException when the instance can not be modified
     */
    private void checkThisForFinalInstance() throws UnsupportedOperationException {
        if (this.instanceIsFinal) {
            throw new UnsupportedOperationException("This " + this.getClass().getName() +
                            " is final! Property modifications illegal." +
                            " Use "+ this.getClass().getName() + "#copyDefaultFormat() to get a copy of this instance.");
        }
    }
    
    /**
     * Gets the delimited identifier quote character to use for parsing and code generation.
     */
    public char getDelimitedIdentifierQuote() {
        return delimitedIdentifierQuote;
    }
    
    /**
     * Sets the delimited identifier quote character to use for parsing and code generation.
     * <b>Note:</b>
     * No modifications of of this setting are allowed after using this <code>SQLQuerySourceFormat</code> 
     * as a parameter for a parse!  The delimited identifier quote character is stored within the identifiers 
     * of the <code>SQLQueryObject</code>s and further modifications to this setting would result into wrong name
     * comparisons.
     * If you need to modify it, make a copy of the current <code>SQLQuerySourceFormat</code> by using
     * {@link #copySourceFormat(SQLQuerySourceFormat)} to keep the previously parsed <code>SQLQueryObject</code>s 
     * references to this <code>SQLQuerySourceFormat</code> and its <code>delimitedIdentifierQuote</code> valid.
     */
    public void setDelimitedIdentifierQuote(char delimitedIdentifierQuote) {
        checkThisForFinalInstance();
        this.delimitedIdentifierQuote = delimitedIdentifierQuote;
    }
    
    /**
     * Gets the host variable prefix character to use.
     */
    public char getHostVariablePrefix() {
        return hostVariablePrefix;
    }
    /**
     * Sets the host variable prefix character to use to the given character.
     */
    public void setHostVariablePrefix(char hostVariablePrefix) {
        checkThisForFinalInstance();
        this.hostVariablePrefix = hostVariablePrefix;
    }
    
    /**
     * Gets the parameter marker character to use.
     */
    public char getParameterMarker() {
        return parameterMarker;
    }
    
    /**
     * Sets the parameter marker character to use to the given character.
     */
    public void setParameterMarker(char parameterMarker) {
        checkThisForFinalInstance();
        this.parameterMarker = parameterMarker;
    }
    
    /**
     * Gets the omit schema name to use.  If non-null, this schema name is omitted from generated SQL.
     */
    public String getOmitSchema() {
        return omitSchema;
    }
    
    /**
     * Sets the omit schema name to use.  If non-null, this schema name is omitted from generated SQL.
     */
    public void setOmitSchema(String omitSchema) {
        checkThisForFinalInstance();
        this.omitSchema = omitSchema;
    }
    
    /**
     * Gets the preserve source format setting.
     */
    public boolean isPreserveSourceFormat() {
        return preserveSourceFormat;
    }
    
    /**
     * Sets the preserve source format setting to the given value.
     */
    public void setPreserveSourceFormat(boolean preserveSourceFormat) {
        checkThisForFinalInstance();
        this.preserveSourceFormat = preserveSourceFormat;
    }
    
    /**
     * Gets the statement terminator character to use.
     */
    public char getStatementTerminator() {
        return statementTerminator;
    }
    
    /**
     * Sets the statement terminator character to use to the given character.
     */
    public void setStatementTerminator(char statementTerminator) {
        checkThisForFinalInstance();
        this.statementTerminator = statementTerminator;
    }
    
    /**
     * Gets the policy setting on how identifiers in the SQL source will be qualified.  See the
     * <cod>QUALIFY_IDENTIFIER_xxx</code> constants defined in this class.  The default is
     * {@link #QUALIFY_IDENTIFIERS_IN_CONTEXT}.
     */
    public int getQualifyIdentifiers() {
        return qualifyIdentifiers;
    }
    
    /**
     * Sets the policy on how identifiers in the SQL source will be qualified.
     */
    public void setQualifyIdentifiers(int qualifyIdentifiers) {
        checkThisForFinalInstance();
        this.qualifyIdentifiers = qualifyIdentifiers;
    }
    
    /**
     * Gets whether or not to preserve comments in the generated SQL.
     */
    public boolean getPreserveComments() {
        return preserveComments;
    }
    
    /**
     * Gets whether or not to preserve comments in the generated SQL is set true.
     */
    public boolean isPreserveComments() {
        return preserveComments;
    }
    
    /**
     * Sets whether or not to preserve comments in the generated SQL.
     */
    public void setPreserveComments(boolean preserveComments) {
        checkThisForFinalInstance();
        this.preserveComments = preserveComments;
    }
    
    /** 
     * Gets whether or not to generate comments in the context of a complete statement only.  When false, comments will
     * be generated in the SQL objects outside the context of a complete statement.
     */
    public boolean isGenerateCommentsForStatementOnly() {
        return generateCommentsForStatementOnly;
    }
    
    /** 
     * Sets whether or not to generate comments in the context of a complete statement only.  When false, comments will
     * be generated in the SQL objects outside the context of a complete statement.
     */
    public void setGenerateCommentsForStatementOnly(boolean generateCommentsForStatementOnly) {
        checkThisForFinalInstance();
        this.generateCommentsForStatementOnly = generateCommentsForStatementOnly;
    }
    
    /**
     * Gets whether or not to include the AS keyword when generating SQL for a table correlation ID.
     */
    public boolean getGenerateAsKeywordForTableCorrID() {
        return this.generateAsKeywordForTableCorrID;
    }
    
    /**
     * Sets whether or not to include the AS keyword when generating SQL for a table correlation ID. 
     */
    public void setGenerateAsKeywordForTableCorrID(boolean genAsKeywordForTableCorrID) {
        checkThisForFinalInstance();
        this.generateAsKeywordForTableCorrID = genAsKeywordForTableCorrID;
    }
}
