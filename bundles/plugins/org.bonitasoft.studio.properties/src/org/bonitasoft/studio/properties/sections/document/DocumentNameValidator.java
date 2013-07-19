/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.properties.sections.document;

import java.util.ArrayList;
import java.util.Arrays;

import org.bonitasoft.studio.properties.i18n.Messages;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ListViewer;

/**
 * @author Maxence Raoux
 * 
 */
public class DocumentNameValidator implements IValidator {

	private ListViewer documentListViewer;

	public DocumentNameValidator(ListViewer documentListViewer) {
		this.documentListViewer = documentListViewer;
	}

	@Override
	public IStatus validate(Object value) {
		final ArrayList<String> documentList = new ArrayList<String>(Arrays.asList(documentListViewer.getList().getItems()));
		final String[] selection = documentListViewer.getList().getSelection();
		if (documentList.contains(value) && ((selection.length>0 && !selection[0].equals(value)) || selection.length==0)) {
			return ValidationStatus.error(Messages.error_documentAllreadyexist);
		}
		return ValidationStatus.ok();
	}

}
