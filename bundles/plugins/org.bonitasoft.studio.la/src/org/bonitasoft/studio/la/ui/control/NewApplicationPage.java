/**
 * Copyright (C) 2017 BonitaSoft S.A.
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
package org.bonitasoft.studio.la.ui.control;

import static org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory.updateValueStrategy;

import org.bonitasoft.engine.business.application.xml.ApplicationNode;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.ui.validator.ApplicationDescriptorFileNameValidator;
import org.bonitasoft.studio.la.ui.validator.ApplicationTokenUnicityValidator;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.RegExpValidator;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class NewApplicationPage implements ControlSupplier {

    public static final String DEFAULT_FILE_NAME = "applicationDescriptor";
    private final ApplicationNode applicationNode;
    private final RepositoryAccessor repositoryAccessor;
    private String filename = DEFAULT_FILE_NAME;

    public NewApplicationPage(ApplicationNode applicationNode, RepositoryAccessor repositoryAccessor) {
        this.applicationNode = applicationNode;
        this.repositoryAccessor = repositoryAccessor;
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer container, DataBindingContext ctx) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.swtDefaults().create());

        final IObservableValue filenameObservable = PojoObservables.observeValue(this, "filename");

        new TextWidget.Builder()
                .withLabel(Messages.fileName)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(filenameObservable)
                .inContext(ctx)
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new ApplicationDescriptorFileNameValidator(
                                repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class))))
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.applicationToken)
                .withMessage(Messages.applicationTokenMessage)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoObservables.observeValue(applicationNode, "token"))
                .inContext(ctx)
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new MultiValidator.Builder().havingValidators(
                                new EmptyInputValidator.Builder().withMessage(Messages.required),
                                new RegExpValidator.Builder().matches("^[a-zA-Z0-9]+$")
                                        .withMessage(Messages.alphaNumericOnly),
                                new ApplicationTokenUnicityValidator.Builder(repositoryAccessor))))
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.displayName)
                .withMessage(Messages.displayNameMessage)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .bindTo(PojoObservables.observeValue(applicationNode, "displayName"))
                .inContext(ctx)
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(
                                new EmptyInputValidator.Builder().withMessage(Messages.required).create()))
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.version)
                .labelAbove()
                .widthHint(150)
                .grabHorizontalSpace()
                .bindTo(PojoObservables.observeValue(applicationNode, "version"))
                .inContext(ctx)
                .withTargetToModelStrategy(updateValueStrategy()
                        .withValidator(new EmptyInputValidator.Builder().withMessage(Messages.required).create()))
                .createIn(composite);

        return composite;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
