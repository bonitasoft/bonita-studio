/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.util;

import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;

/**
 * A <code>SQLSourceWriter</code> provides a generic <code>getSQL()</code>
 * method to generate the SQL source of a given <code>queryObject</code>.
 * {@link org.eclipse.datatools.modelbase.sql.query.util.SQLQuerySourceWriter} provides the
 * base implementation of this interface, implementing the <code>getSQL()</code>
 * method for the Package <code>org.eclipse.datatools.modelbase.sql.query</code>.
 * 
 * @author ckadner
 */
public interface SQLSourceWriter
{
    public String getSQL(SQLQueryObject queryObject);
}