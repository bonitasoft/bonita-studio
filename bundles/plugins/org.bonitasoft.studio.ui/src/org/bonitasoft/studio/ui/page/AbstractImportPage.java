/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.ui.page;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.ui.UIPlugin;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.bonitasoft.studio.ui.databinding.UpdateValueStrategyFactory;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.bonitasoft.studio.ui.validator.EmptyInputValidator;
import org.bonitasoft.studio.ui.validator.MultiValidator;
import org.bonitasoft.studio.ui.validator.PathValidator;
import org.bonitasoft.studio.ui.widget.TextWidget;
import org.bonitasoft.studio.ui.wizard.ControlSupplier;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.typed.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractImportPage implements ControlSupplier {

    private static final String LAST_IMPORT_PATH = null;

    protected final RepositoryAccessor repositoryAccessor;
    protected TextWidget importerWidget;
    protected String filePath;
    protected IObservableValue<String> filePathObservable;

    public AbstractImportPage(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        filePathObservable = PojoProperties.<AbstractImportPage, String> value("filePath").observe(this);
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        final Composite importerComposite = new Composite(parent, SWT.NONE);
        importerComposite.setLayout(GridLayoutFactory.fillDefaults().extendedMargins(10, 0, 10, 0).create());
        importerComposite.setLayoutData(GridDataFactory.fillDefaults().create());
        importerWidget = new TextWidget.Builder()
                .withLabel(Messages.importLabel)
                .labelAbove()
                .fill()
                .widthHint(500)
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
                .withValidator(new MultiValidator.Builder().havingValidators(getValidators().toArray(new IValidator[0])));
    }

    protected List<IValidator> getValidators() {
        List<IValidator> validators = new ArrayList<>();
        validators.add(new EmptyInputValidator.Builder().withMessage(Messages.filePathNotEmpty).create());
        validators.add(new PathValidator.Builder().withMessage(Messages.fileDoesntExist).create());
        return validators;
    }

    private Optional<String> openFileDialog(Shell shell) {
        final FileDialog fd = new FileDialog(shell, SWT.OPEN | SWT.SINGLE);
        fd.setText(Messages.importLabel);
        fd.setFilterPath(getLastPath());
        fd.setFilterExtensions(getExtensions());
        return Optional.ofNullable(fd.open());
    }

    protected abstract String[] getExtensions();

    private String getLastPath() {
        String path = UIPlugin.getDefault().getDialogSettings().get(LAST_IMPORT_PATH);
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
