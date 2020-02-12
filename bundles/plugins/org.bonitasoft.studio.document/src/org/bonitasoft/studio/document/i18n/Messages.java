/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.document.i18n;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$

    public static String newDocument;
    public static String documentWizardPageTitle;
    public static String newDocumentWizardDescription;
    public static String name;
    public static String description;
    public static String mimeType;
    public static String hintMimeTypeDocument;
    public static String External;
    public static String explanationExternalDocument;
    public static String Internal;
    public static String explanationInternalDocument;
    public static String hintExternalUrl;
    public static String Browse;
    public static String selectDocumentDescription;
    public static String importEtc;
    public static String remove;
    public static String selectDocumentDialogTitle;
    public static String error_documentAllreadyexist;
    public static String error_documentURLEmpty;
    public static String error_documentURLTooLong;
    public static String error_documentDefaultIDEmpty;
    public static String explanationMimeTypeDocument;
    public static String initialValueLabel;
    public static String initialValueButtonNone;
    public static String initialValueButtonInternal;
    public static String initialValueButtonInternalToolTip;
    public static String initialValueButtonExternal;
    public static String initialValueButtonExternalToolTip;
    public static String initialValueButtonContract;
    public static String documentExternalLabel;
    public static String documentInternalLabel;
    public static String editDocumentTitle;
    public static String editDocumentDescription;
    public static String hideMimeType;
    public static String manageMimeType;
    public static String editDocument;
    public static String documentInitialContentInternalErrorMessage;
    public static String documentInitialContentExternalErrorMessage;
    public static String initialMultipleContent;
    public static String error_documentInitialContentsEmpty;
    public static String multipleInitialContentsLabel;
    public static String radioButtonMultiple;
    public static String radioButtonMultipleToolTip;
    public static String radioButtonSingle;
    public static String areYouSureMessage;
    public static String documentPropertySectionDescription;
    public static String AddSimple;
    public static String documentRepository;
    public static String returnType;
    public static String documentReferenceType;
    public static String documentType;
    public static String fileContractInput;
    public static String contractInputNotSet;
    public static String initialValueButtonScript;

    public static String documentListScriptToolTip;

    public static String additionalResources;
    public static String additionalResourcesDesc;
    public static String resourceName;
    public static String file;
    public static String unknownResource;
    public static String deleteAdditionalResourceConfirmTitle;
    public static String deleteAdditionalResourceConfirm;
    public static String barPathMissing;
    public static String projectPathInvalid;

    static {
        // initialize resource bundle
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
