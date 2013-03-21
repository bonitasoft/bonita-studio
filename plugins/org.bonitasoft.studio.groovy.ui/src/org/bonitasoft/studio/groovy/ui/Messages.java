/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.groovy.ui;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages";//$NON-NLS-1$

    public static String functionsTitleLabel;
    public static String testGroupLabel ;
    public static String testButtonLabel;
    public static String initVariable;
    public static String operatorTitleLabel;

    public static String functionDocTitle;

    public static String functionTitle;
    public static String categoriesTitle;

    public static String saveButtonLabel;
    public static String loadButtonLabel;

    public static String equalsTooltip;
    public static String multiplyTooltip;
    public static String divideTooltip;
    public static String lessThanTooltip;
    public static String lessThanOrEqualTooltip;
    public static String notEqualTooltip;
    public static String greaterThanOrEqualsTooltip;
    public static String greaterThanTooltip;
    public static String dataTitleLabel;
    public static String evaluationErrorTitle;
    public static String saveWizardPageTitle;
    public static String saveWizardPageMessage;
    public static String saveWizardPageDescription;
    public static String nameLabel;
    public static String saveMethodLabel;
    public static String noMethodLabel;
    public static String overwriteQuestionTitle;
    public static String overwriteQuestionMessage;
    public static String openScriptWizardPage_title;
    public static String openScriptWizardPage_desc;
    public static String removeScriptLabel;
    public static String confirmScriptDeleteTitle;
    public static String confirmScriptDeleteMessage;
    public static String openScriptLabel;
    public static String warningLooseCurrentWorkTitle;
    public static String warningLooseCurrentWorkMessage;
    public static String noProcessVariableAvailable;
    public static String classesNotSupportedTitle;
    public static String classesNotSupportedMessage;
    public static String groovyEditorTitle;
    public static String noVariableToSetTitle;
    public static String noVariableToSetMessage;
    public static String browseButtonLabel;
    public static String unsupportesTypeTitle;
    public static String unsupportesTypeMessage;
    public static String saveSuccesfullTitle;
    public static String saveSuccesfullMessage;
    public static String clearLabel;
    public static String clearContentTitle;
    public static String clearContentMessage;
    public static String processVariableLabel;
    public static String bonitaKeyWord;
    public static String userDefinedCatLabel;
    public static String stringCatLabel;
    public static String numberCatLabel;
    public static String collectionCatLabel;
    public static String otherCatLabel;
    public static String bonitaCatLabel;
    public static String selectData;
    public static String groovyDocumentationLink;
    public static String _UI_TOOLTIP_RESTORE_PANE;
    public static String _UI_TOOLTIP_HIDE_PANE;

    public static String editorButtonLabel;
    public static String showPassword;
    public static String textAreaSelectDataLabel;

    public static String browseJava;
    public static String openExpressionEditor;

    public static String selectWriteToMultipleData_label;
    public static String writeToMultipleData_append;
    public static String writeToMultipleData_replace;

    public static String readMultipleData_label;
    public static String readMultipleData_wholeList;
    public static String readMultipleData_index;
    public static String readMultipleData_size;

    public static String impossibleToOpenEditorTitle;
    public static String impossibleToOpenEditorMsg;

    public static String wrongName;
    public static String documentationMandatory;
    public static String scriptNameMustBeValid;

    public static String createScript;
    public static String scriptNameDialogTitle;
    public static String scriptNameDialogMessage;

    public static String warning;
    public static String warningMessageSystemExit;

    public static String editInFullEditor;

    public static String showDependencies;
    public static String scriptEvaluation ;
    public static String dependencies;
    public static String returnType;
    public static String browse;
    public static String add;
    public static String remove;

    public static String groovyScript;
    public static String selectGroovyScriptToExport;

    public static String automaticResolution;

    public static String SelectProcessVariableLabel;
    public static String SelectBonitasVariableLabel;

    public static String unknownVariableLabel;
    public static String groovyUnresolved;

    public static String manageGroovyScripts;

    public static String evaluationResults;

	public static String testGroovyScriptDialogDescription;

	public static String nameHelp;

	public static String scriptNameAlreadyExists;

    private Messages() {
        // Do not instantiate
    }

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}