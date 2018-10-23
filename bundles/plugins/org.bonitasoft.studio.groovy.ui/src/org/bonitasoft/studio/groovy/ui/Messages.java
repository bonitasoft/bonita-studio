/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.ui;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages";//$NON-NLS-1$

    public static String testButtonLabel;

    public static String functionDocTitle;

    public static String functionTitle;

    public static String categoriesTitle;

    public static String noProcessVariableAvailable;

    public static String processVariableLabel;

    public static String selectData;

    public static String groovyDocumentationLink;

    public static String browseJava;

    public static String scriptEvaluation;

    public static String returnType;

    public static String add;

    public static String remove;

    public static String automaticResolution;

    public static String SelectProcessVariableLabel;

    public static String SelectBonitasVariableLabel;

    public static String unknownVariableLabel;

    public static String groovyUnresolved;

    public static String evaluationResults;

    public static String testGroovyScriptDialogDescription;

    public static String nameHelp;

    public static String warningAssigningAVariableWithSameNameAsProcessVariable;

    public static String maxScriptLengthTitle;

    public static String maxScriptLength;

    public static String onlyReadOnly;

    private Messages() {
        // Do not instantiate
    }

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}
