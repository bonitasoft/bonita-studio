/*******************************************************************************
 * Copyright (c) 2009 Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.util;

import org.eclipse.datatools.modelbase.sql.datatypes.impl.ArrayDataTypeImpl;

/**
 * The SQL Model SQLDataTypesFactory class does not provide a method to create an ArrayDataType,
 * and the constructor of ArrayDataTypeImpl is protected, so we need to provide our own interface 
 * Impl class so that the SQL Query Parser Factor can create them and the SQL Query Source Writer
 * can generate SQL for them.
 * <p>
 * This interface and Impl class can be removed if the SQL Model is changed to provide the
 * factory method.
 */
public class SQLQueryArrayDataTypeImpl extends ArrayDataTypeImpl implements SQLQueryArrayDataType {
    public SQLQueryArrayDataTypeImpl() {
        super();
    }
}
