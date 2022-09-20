/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.expression.editor.i18n;

import org.eclipse.osgi.util.NLS;

/**
 * @author Romain Bioteau
 */
public class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages";//$NON-NLS-1$

    public static String expressionTypeLabel;

    public static String removeRow;

    public static String addRow;

    public static String addColumn;

    public static String removeColumn;

    public static String editAsExpression;

    public static String editAsTable;

    public static String contstantTypeLabel;

    public static String value;

    public static String returnType;

    public static String editExpression;

    public static String addAction;

    public static String assignment;

    public static String upRowSort;

    public static String downRowSort;

    public static String operatorType;

    public static String invalidReturnTypeFor;

    public static String xpathUpdateOperator;

    public static String javaMethodOperator;

    public static String setDocumentOperator;

    public static String switchEditor;
    public static String switchEditorCondition;

    public static String eraseExpressionTitle;

    public static String eraseExpressionMsg;

    public static String patternViewerHint;

    public static String returnTypeIsMandatory;

    public static String editScriptExpressionTooltip;

    public static String editConnectorExpressionTooltip;

    public static String editAndContinue;

    public static String invalidReturnTypeBetween;

    public static String eraseExpression;

    public static String messageIdTypeLabel;

    public static String messageDataId;

    public static String availableIds;

    public static String expressionTypeToolTip;

    public static String changeOperator;

    public static String incompatibleExpressionTypeForOperator;

    public static String cleanExpressionTitle;

    public static String cleanExpressionMsg;

    public static String selectTarget;

    public static String incompatibleStorageReturnType;

    public static String handleShellCloseEventTitle;

    public static String handleShellCloseEventMessage;

    public static String editXpathExpressionTooltip;

    public static String editJavaExpressionTooltip;

    public static String expressionValueNotCompatibleWithReturnType;

    public static String formFieldTypeLabel;

    public static String name;

    public static String processVariable;

    public static String stepVariable;

    public static String formVariable;

    public static String createBusinessData;

    public static String businessDataNotCompatibleForOperator;

    public static String AtLeastOneRowShouldBeAddedFor;

    public static String editQueryExpressionTooltip;

    public static String transientDataWarning;

    public static String deleteOperatorLabel;

    public static String multiInstanceIterator;

    public static String browse;

    public static String messageOperationWithDocumentInForm;

    public static String messageOperationWithDocumentInTask;

    public static String incompatibleType;

    public static String dialogTitleSelectOperator;
    public static String setDocumentListOperator;

    public static String messageOperationWithListDocumentInForm;
    public static String messageOperationWithListDocumentInFormInCommunity;

    public static String messageOperationWithListDocumentInTask;

    public static String cannotStoreBusinessObjectInProcessVariable;

    public static String richTextHelpMessage;
    public static String insertVariableButton;
    public static String selectVariableTitle;

    private Messages() {
        // Do not instantiate
    }

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}
