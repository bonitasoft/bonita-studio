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

import org.apache.maven.model.Model;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.jface.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.jface.databinding.validator.RegExpValidator;
import org.bonitasoft.studio.common.repository.RepositoryNameValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class EditProjectMetadataPage implements ControlSupplier {

    private IObservableValue<String> name = new WritableValue<>();
    private IObservableValue<String> description = new WritableValue<>();
    private IObservableValue<String> groupId = new WritableValue<>();
    private IObservableValue<String> artifactId = new WritableValue<>();
    private IObservableValue<String> version = new WritableValue<>();

    public EditProjectMetadataPage(Model model) {
        name.setValue(model.getName());
        description.setValue(model.getDescription());
        groupId.setValue(model.getGroupId());
        artifactId.setValue(model.getArtifactId());
        version.setValue(model.getVersion());
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).spacing(20, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        new TextWidget.Builder()
                .withLabel(Messages.name)
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(name)
                .withValidator(new RepositoryNameValidator(false))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.version)
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(version)
                .withValidator(new EmptyInputValidator(Messages.version))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel("Group ID")
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(groupId)
                .withValidator(new MultiValidator.Builder()
                        .havingValidators(new EmptyInputValidator("Group ID"),
                                new RegExpValidator(Messages.invalidFormat, "[A-Za-z0-9_\\-.]+"))
                        .create())
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel("Artifact ID")
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(artifactId)
                .withValidator(new MultiValidator.Builder()
                        .havingValidators(new EmptyInputValidator("Artifact ID"),
                                new RegExpValidator(Messages.invalidFormat, "[A-Za-z0-9_\\-.]+"))
                        .create())
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextAreaWidget.Builder()
                .withLabel(Messages.description)
                .labelAbove()
                .heightHint(100)
                .widthHint(500)
                .grabHorizontalSpace()
                .fill()
                .bindTo(description)
                .inContext(ctx)
                .horizontalSpan(2)
                .useNativeRender()
                .createIn(composite);

        return composite;
    }

    public String getName() {
        return name.getValue();
    }

    public String getGroupId() {
        return groupId.getValue();
    }

    public String getArtifactId() {
        return artifactId.getValue();
    }

    public String getVersion() {
        return version.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }
}
