/**
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.document.ui.control;

import static com.google.common.base.Preconditions.checkArgument;

import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class DocumentDescriptionComposite {

    private final Text documentDescriptionText;

    public DocumentDescriptionComposite(final Composite parent) {
        final Label description = new Label(parent, SWT.NONE);
        description.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).create());
        description.setText(Messages.description);

        documentDescriptionText = new Text(parent, SWT.BORDER | SWT.V_SCROLL);
        documentDescriptionText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 60).create());
    }

    protected Text getText() {
        return documentDescriptionText;
    }

    public void bindControl(final Document document, final EMFDataBindingContext emfDataBindingContext) {
        checkArgument(document != null);
        checkArgument(emfDataBindingContext != null);
        emfDataBindingContext.bindValue(SWTObservables.observeText(getText(), SWT.Modify),
                EMFObservables.observeValue(document, ProcessPackage.Literals.ELEMENT__DOCUMENTATION));
    }

}
