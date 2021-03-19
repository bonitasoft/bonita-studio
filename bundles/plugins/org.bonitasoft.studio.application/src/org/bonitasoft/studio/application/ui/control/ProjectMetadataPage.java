/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.ui.control;

import java.util.Objects;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.repository.RepositoryNameValidator;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.ui.validator.MavenIdValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class ProjectMetadataPage implements ControlSupplier {

    private IObservableValue<ProjectMetadata> metadataObservale;
    private boolean createProject;

    public ProjectMetadataPage(ProjectMetadata metadata, boolean createProject) {
        this.createProject = createProject;
        this.metadataObservale = new WritableValue<ProjectMetadata>(metadata, ProjectMetadata.class);
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(10, 10)
                .spacing(20, 10)
                .extendedMargins(0, 0, 0, 20).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        IObservableValue<String> nameObservable = PojoProperties.value("name", String.class)
                .observeDetail(metadataObservale);
        new TextWidget.Builder()
                .withLabel(Messages.name)
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(nameObservable)
                .withValidator(new MultiValidator.Builder()
                        .havingValidators(new RepositoryNameValidator(() -> createProject),
                                engineRestartWarning(nameObservable.getValue()))
                        .create())
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.version)
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(PojoProperties.value("version").observeDetail(metadataObservale))
                .withValidator(new EmptyInputValidator(Messages.version))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel("Group ID")
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(PojoProperties.value("groupId").observeDetail(metadataObservale))
                .withValidator(new MavenIdValidator("Group ID"))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel("Artifact ID")
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(PojoProperties.value("artifactId").observeDetail(metadataObservale))
                .withValidator(new MavenIdValidator("Artifact ID"))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        TextWidget textArea = new TextAreaWidget.Builder()
                .withLabel(Messages.description)
                .labelAbove()
                .heightHint(100)
                .widthHint(500)
                .grabHorizontalSpace()
                .fill()
                .bindTo(PojoProperties.value("description").observeDetail(metadataObservale))
                .inContext(ctx)
                .horizontalSpan(2)
                .useNativeRender()
                .createIn(composite);
        textArea.getTextControl().addTraverseListener(new TraverseListener() {

            @Override
            public void keyTraversed(final TraverseEvent event) {
                if (event.detail == SWT.TRAVERSE_TAB_NEXT
                        || event.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
                    event.doit = true;
                }
            }
        });

        return composite;
    }

    private IValidator<String> engineRestartWarning(String originalName) {
        return name -> !Objects.equals(originalName, name)
                ? ValidationStatus.warning(Messages.engineRestartWarning)
                : ValidationStatus.ok();
    }

    public ProjectMetadata getMetadata() {
        return metadataObservale.getValue();
    }
}
