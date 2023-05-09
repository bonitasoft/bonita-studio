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

import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.RedirectURLBuilder;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.databinding.validator.EmptyInputValidator;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.ProjectIdValidator;
import org.bonitasoft.studio.common.repository.core.maven.BonitaProjectBuilder.BonitaRuntimeVersionValidator;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.maven.repository.MavenRepositories;
import org.bonitasoft.studio.common.repository.ui.validator.MavenIdValidator;
import org.bonitasoft.studio.engine.BOSWebServerManager;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.widget.ComboWidget;
import org.bonitasoft.studio.ui.widget.TextAreaWidget;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;

public class ProjectMetadataPage implements ControlSupplier {

    private static final String STUDIO_MAINTENANCE_UPDATE_REDIRECT_ID = "735";
    private IObservableValue<ProjectMetadata> metadataObservale;
    private boolean createProject;

    public ProjectMetadataPage(ProjectMetadata metadata, boolean createProject) {
        this.createProject = createProject;
        this.metadataObservale = new WritableValue<>(metadata, ProjectMetadata.class);
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2)
                .margins(10, 10)
                .spacing(20, 10)
                .extendedMargins(0, 0, 0, 20).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        var nameObservable = PojoProperties.value("name", String.class)
                .observeDetail(metadataObservale);
        var artifactIdObservable = PojoProperties.value("artifactId", String.class).observeDetail(metadataObservale);
        var originalId = artifactIdObservable.getValue();
        new TextWidget.Builder()
                .withLabel(Messages.name + " *")
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .widthHint(130)
                .bindTo(nameObservable)
                .withValidator(new EmptyInputValidator(Messages.name))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        var projectIdWidget = new TextWidget.Builder()
                .withLabel("Project ID")
                .withTootltip(Messages.projectIdTootltip)
                .labelAbove()
                .grabHorizontalSpace()
                .widthHint(130)
                .fill()
                .bindTo(artifactIdObservable)
                .withValidator(new MultiValidator.Builder()
                        .havingValidators(new MavenIdValidator("Project ID", false),
                                new ProjectIdValidator(() -> nameObservable.getValue(),
                                       null, () -> createProject),
                                engineRestartWarning(() -> nameObservable.getValue(), originalId))
                        .create())
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel(Messages.version + " *")
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(PojoProperties.value("version").observeDetail(metadataObservale))
                .withValidator(new EmptyInputValidator(Messages.version))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        new TextWidget.Builder()
                .withLabel("Group ID *")
                .withTootltip(Messages.groupIdTootltip)
                .labelAbove()
                .grabHorizontalSpace()
                .fill()
                .bindTo(PojoProperties.value("groupId").observeDetail(metadataObservale))
                .withValidator(new MavenIdValidator("Group ID"))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        var messageObservable = WidgetProperties.message().observe(projectIdWidget.getTextControl());
        ctx.bindValue(messageObservable,
                nameObservable,
                UpdateStrategyFactory.neverUpdateValueStrategy().create(),
                UpdateStrategyFactory.updateValueStrategy()
                        .withConverter(IConverter.<String, String> create(ProjectMetadata::toArtifactId))
                        .create());
        
       var projectIdValidator = new org.eclipse.core.databinding.validation.MultiValidator() {
            
            @Override
            protected IStatus validate() {
                var validator = new MultiValidator.Builder()
                        .havingValidators(
                                new ProjectIdValidator(() -> nameObservable.getValue(),
                                        () -> artifactIdObservable.getValue(), () -> createProject),
                                engineRestartWarning(originalId,
                                        () -> artifactIdObservable.getValue()))
                        .create();
                return validator.validate(messageObservable.getValue());
            }
        };
        ctx.addValidationStatusProvider(projectIdValidator);

        projectIdValidator.getValidationStatus()
                .addValueChangeListener(e -> projectIdWidget.statusChanged(e.diff.getNewValue()));

        new ComboWidget.Builder()
                .withLabel(Messages.targetRuntimeVersion + " *")
                .labelAbove()
                .horizontalSpan(2)
                .grabHorizontalSpace()
                .fill()
                .widthHint(750)
                .withItems(availableCompatibleVersions())
                .withValidator(new MultiValidator.Builder()
                        .havingValidators(new EmptyInputValidator(Messages.targetRuntimeVersion),
                                new BonitaRuntimeVersionValidator())
                        .create())
                .bindTo(PojoProperties.value("bonitaRuntimeVersion", String.class).observeDetail(metadataObservale))
                .inContext(ctx)
                .useNativeRender()
                .createIn(composite);

        var targetVersionMessage = new Link(composite, SWT.NONE);
        targetVersionMessage
                .setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(0, -10).span(2, 1).create());
        targetVersionMessage.setText(Messages.studioMaintenanceUpdateMessage);
        targetVersionMessage.addListener(SWT.Selection, e -> {
            try {
                java.awt.Desktop.getDesktop()
                        .browse(RedirectURLBuilder.createURI(STUDIO_MAINTENANCE_UPDATE_REDIRECT_ID));
            } catch (final IOException ioe) {
                BonitaStudioLog.error(ioe);
            }
        });

        var textArea = new TextAreaWidget.Builder()
                .withLabel(Messages.description)
                .labelAbove()
                .heightHint(100)
                .widthHint(500)
                .grabVerticalSpace()
                .grabHorizontalSpace()
                .fill()
                .bindTo(PojoProperties.value("description").observeDetail(metadataObservale))
                .inContext(ctx)
                .horizontalSpan(2)
                .useNativeRender()
                .createIn(composite);

        textArea.getTextControl().addTraverseListener(event -> {
            if (event.detail == SWT.TRAVERSE_TAB_NEXT
                    || event.detail == SWT.TRAVERSE_TAB_PREVIOUS) {
                event.doit = true;
            }
        });

        return composite;
    }

    private String[] availableCompatibleVersions() {
        try {
            return MavenRepositories.listBonitaRuntimeBomVersions();
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return new String[0];
        }
    }

    private IValidator<String> engineRestartWarning(Supplier<String> nameSuppiler, String originalId) {
        return id -> !BOSWebServerManager.getInstance().isLazyModeEnabled() && !Objects.equals(originalId,
                id == null || id.isEmpty() ? ProjectMetadata.toArtifactId(nameSuppiler.get()) : id)
                        ? ValidationStatus.warning(displayWarningServerMessage())
                        : ValidationStatus.ok();
    }

    private IValidator<String> engineRestartWarning(String originalId, Supplier<String> idSupplier) {
        return name -> !BOSWebServerManager.getInstance().isLazyModeEnabled() && Strings.isNullOrEmpty(idSupplier.get())
                && !Objects.equals(originalId, ProjectMetadata.toArtifactId(name))
                        ? ValidationStatus.warning(displayWarningServerMessage())
                        : ValidationStatus.ok();
    }

    private String displayWarningServerMessage() {
        return BOSWebServerManager.getInstance().serverIsStarted() ? Messages.engineRestartWarning
                : Messages.engineStartWarning;
    }

    public ProjectMetadata getMetadata() {
        return metadataObservale.getValue();
    }
}
