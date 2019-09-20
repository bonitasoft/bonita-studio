/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.ui.wizard;

import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.businessobject.ui.wizard.validator.ImportBdmContentValidator;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.ui.page.AbstractImportPage;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.SelectObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class ImportAndMergeBdmPage extends AbstractImportPage {

    private SelectObservableValue importActionObservable;

    public ImportAndMergeBdmPage(RepositoryAccessor repositoryAccessor) {
        super(repositoryAccessor);
        importActionObservable = new SelectObservableValue(ImportAction.class);
    }

    @Override
    public Control createControl(Composite parent, IWizardContainer wizardContainer, DataBindingContext ctx) {
        if (bdmExists()) {
            Composite composite = new Composite(parent, SWT.NONE);
            composite.setLayout(GridLayoutFactory.fillDefaults().create());
            composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

            super.createControl(composite, wizardContainer, ctx);
            createImportActionGroup(composite);
            importActionObservable.setValue(ImportAction.MERGE);

            return composite;
        }
        importActionObservable.setValue(ImportAction.OVERWRITE);
        return super.createControl(parent, wizardContainer, ctx);
    }

    private void createImportActionGroup(Composite parent) {
        Group importActionGroup = new Group(parent, SWT.None);
        importActionGroup.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        importActionGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).indent(0, 10).create());
        importActionGroup.setText(Messages.importAction);

        Button mergeButton = new Button(importActionGroup, SWT.RADIO);
        mergeButton.setLayoutData(GridDataFactory.swtDefaults().create());
        mergeButton.setText(Messages.mergeOption);

        Button overwriteButton = new Button(importActionGroup, SWT.RADIO);
        overwriteButton.setLayoutData(GridDataFactory.swtDefaults().create());
        overwriteButton.setText(Messages.overwriteOption);

        Composite stackComposite = new Composite(importActionGroup, SWT.NONE);
        stackComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
        StackLayout stackLayout = new StackLayout();
        stackComposite.setLayout(stackLayout);

        Composite mergeComposite = createLabelComposite(stackComposite, Messages.mergeBdmHelpMessage);
        Composite overwriteComposite = createLabelComposite(stackComposite, Messages.overwriteBdmHelpMessage);

        importActionObservable.addOption(ImportAction.MERGE, WidgetProperties.selection().observe(mergeButton));
        importActionObservable.addOption(ImportAction.OVERWRITE, WidgetProperties.selection().observe(overwriteButton));
        importActionObservable.addValueChangeListener(e -> {
            stackLayout.topControl = importActionObservable.getValue() == ImportAction.MERGE
                    ? mergeComposite
                    : overwriteComposite;
            stackComposite.layout();
        });

    }

    private Composite createLabelComposite(Composite parent, String message) {
        Composite composite = new Composite(parent, SWT.None);
        composite.setLayout(GridLayoutFactory.fillDefaults().margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        Label helpLabel = new Label(composite, SWT.WRAP);
        helpLabel.setLayoutData(GridDataFactory.fillDefaults().create());
        helpLabel.setText(message);

        return composite;
    }

    @Override
    protected List<IValidator> getValidators() {
        List<IValidator> validators = super.getValidators();
        validators.add(new ImportBdmContentValidator());
        return validators;
    }

    @Override
    protected String[] getExtensions() {
        return new String[] { "*.zip" };
    }

    private boolean bdmExists() {
        BusinessObjectModelRepositoryStore<BusinessObjectModelFileStore> repositoryStore = repositoryAccessor
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore fileStore = repositoryStore.getChild(BusinessObjectModelFileStore.BOM_FILENAME, true);
        return fileStore != null;
    }

    public boolean mergeOnImport() {
        return importActionObservable.getValue() == ImportAction.MERGE;
    }

}

enum ImportAction {
    MERGE, OVERWRITE
}
