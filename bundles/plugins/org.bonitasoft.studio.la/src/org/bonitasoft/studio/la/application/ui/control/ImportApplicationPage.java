/**
 * Copyright (C) 2016 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui.control;

import java.io.File;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.la.LivingApplicationPlugin;
import org.bonitasoft.studio.la.application.repository.ApplicationRepositoryStore;
import org.bonitasoft.studio.la.application.ui.validator.ApplicationXMLContentValidator;
import org.bonitasoft.studio.la.i18n.Messages;
import org.bonitasoft.studio.la.ui.validator.ImportFileStoreConflictsValidator;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.databinding.UpdateValueStrategyFactory;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.PathValidator;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class ImportApplicationPage implements ControlSupplier {

    public static final String XML_EXTENSION = "*.xml";
    private static final String LAST_IMPORT_PATH = null;

    private final RepositoryAccessor repositoryAccessor;
    private TextWidget importerWidget;
    private String filePath;

    public ImportApplicationPage(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        final Composite importerComposite = new Composite(parent, SWT.NONE);
        importerComposite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 11).create());
        importerComposite.setLayoutData(GridDataFactory.fillDefaults().create());

        final IObservableValue filePathObservable = PojoObservables.observeValue(this, "filePath");

        importerWidget = new TextWidget.Builder()
                .withLabel(Messages.importApplicationDescriptorLabel)
                .labelAbove()
                .fill()
                .grabHorizontalSpace()
                .readOnly()
                .withButton(Messages.browse)
                .onClickButton(e -> openFileDialog(parent.getShell()).ifPresent(importerWidget::setText))
                .bindTo(filePathObservable)
                .withTargetToModelStrategy(fileUpdateStrategy())
                .inContext(ctx)
                .createIn(importerComposite);

        return importerComposite;
    }

    private UpdateValueStrategyFactory fileUpdateStrategy() {
        return UpdateStrategyFactory.updateValueStrategy()
                .withValidator(new MultiValidator.Builder().havingValidators(
                        new EmptyInputValidator.Builder().withMessage(Messages.filePathNotEmpty).create(),
                        new PathValidator.Builder().withMessage(Messages.fileDoesntExist).create(),
                        new ApplicationXMLContentValidator(Messages.notAnApplicationError),
                        new ImportFileStoreConflictsValidator(
                                repositoryAccessor.getRepositoryStore(ApplicationRepositoryStore.class))));
    }

    private Optional<String> openFileDialog(Shell shell) {
        final FileDialog fd = new FileDialog(shell, SWT.OPEN | SWT.SINGLE);
        fd.setText(Messages.importApplicationDescriptorLabel);
        fd.setFilterPath(getLastPath());
        fd.setFilterExtensions(new String[] { XML_EXTENSION });
        return Optional.ofNullable(fd.open());
    }

    private String getLastPath() {
        String path = LivingApplicationPlugin.getDefault().getDialogSettings().get(LAST_IMPORT_PATH);
        if (path == null || !new File(path).exists()) {
            path = System.getProperty("user.home");
        }
        return path;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
