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

import org.eclipse.core.runtime.Plugin;



/**
 * The <code>SQLQueryLogger</code> is a slim wrapper around the eclipse
 * logging, with the intention to be refactored in future to adopt the
 * common logging strategy, preferably {@link java.util.logging.Logger}.
 * 
 * @author <a href="mailto:ckadner@us.ibm.com">ckadner</a>
 */
public class SQLQueryLogger extends SQLLogUtil {

    private static SQLLogUtil logger = null;
    

    /**
     * @param plugin
     */
    public SQLQueryLogger(Plugin plugin) {
        super(plugin);
    }

    
    public static SQLLogUtil getLogger() {
        if (SQLQueryLogger.logger == null) {
            SQLQueryLogger.logger = new SQLQueryLogger(SQLQueryModelPlugin.getDefault());
        }
        return SQLQueryLogger.logger;
    }

    public static void setLogger(SQLLogUtil logger) {
        SQLQueryLogger.logger = logger;
    }


}
