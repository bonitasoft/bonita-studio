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
import static com.google.common.collect.Iterables.removeIf;
import static com.google.common.collect.Lists.newArrayList;
import static org.bonitasoft.studio.common.jface.databinding.UpdateStrategyFactory.updateValueStrategy;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.groovyReferenceValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.maxLengthValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.multiValidator;
import static org.bonitasoft.studio.common.jface.databinding.validator.ValidatorFactory.uniqueValidator;

import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.document.i18n.Messages;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.google.common.base.Predicate;

public class DocumentNameComposite {

    private static final int NAME_MAX_LENGTH = 50;
    private final Text documentNameText;

    public DocumentNameComposite(final Composite parent) {
        final Label nameLabel = new Label(parent, SWT.NONE);
        nameLabel.setText(Messages.name + " *");
        nameLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());

        documentNameText = new Text(parent, SWT.BORDER);
        documentNameText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
    }

    protected Text getText() {
        return documentNameText;
    }

    public void bindControl(final Document document, final EObject context, final EMFDataBindingContext emfDataBindingContext) {
        checkArgument(document != null);
        checkArgument(context != null);
        checkArgument(emfDataBindingContext != null);
        emfDataBindingContext.bindValue(
                SWTObservables.observeText(documentNameText, SWT.Modify),
                EMFObservables.observeValue(document, ProcessPackage.Literals.ELEMENT__NAME),
                updateValueStrategy().withValidator(
                        multiValidator()
                                .addValidator(maxLengthValidator(Messages.name, NAME_MAX_LENGTH))
                                .addValidator(groovyReferenceValidator(Messages.name))
                                .addValidator(
                                        uniqueValidator().in(processDocuments(context, document.getName())).onProperty(
                                                ProcessPackage.Literals.ELEMENT__NAME.getName()))).create(),
                null);
    }

    private Iterable<?> processDocuments(final EObject context, final String documentName) {
        final List<Document> documents = newArrayList(ModelHelper.getParentPool(context).getDocuments());
        removeIf(documents, hasSameCurrentDocumentName(documentName));
        return documents;
    }

    private Predicate<Document> hasSameCurrentDocumentName(final String documentName) {
        return new Predicate<Document>() {

            @Override
            public boolean apply(final Document input) {
                return Objects.equals(input.getName(), documentName);
            }
        };
    }

}
