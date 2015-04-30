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
package org.bonitasoft.studio.contract.ui.wizard;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author aurelie
 */
public class CreateContractInputFromBusinessObjectWizardPage extends WizardPage implements ICheckStateListener {

    private final WritableValue selectedDataObservable;
    private final BusinessObjectModelRepositoryStore businessObjectStore;

    /**
     * @param selectedDataObservable
     * @param businessObjectStore
     * @param pageName
     */
    protected CreateContractInputFromBusinessObjectWizardPage(final WritableValue selectedDataObservable,
            final BusinessObjectModelRepositoryStore businessObjectStore) {
        super(CreateContractInputFromBusinessObjectWizardPage.class.getName());
        this.selectedDataObservable = selectedDataObservable;
        this.businessObjectStore = businessObjectStore;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        final EMFDataBindingContext dbc = new EMFDataBindingContext();
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
        composite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        createPrefixText(dbc, composite);
        createProcessDataMappingTreeViewer(composite);
        setControl(composite);
    }

    /**
     * @param dbc
     * @param composite
     */
    private void createPrefixText(final EMFDataBindingContext dbc, final Composite composite) {
        final Composite prefixComposite = new Composite(composite, SWT.NONE);
        prefixComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
        prefixComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        final Label label = new Label(prefixComposite, SWT.NONE);
        label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).create());
        final Object value = selectedDataObservable.getValue();
        final BusinessObjectData d = (BusinessObjectData) value;
        label.setText(Messages.prefix);
        final Text prefixText = new Text(prefixComposite, SWT.BORDER);
        prefixText.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        dbc.bindValue(SWTObservables.observeText(prefixText),
                EMFObservables.observeDetailValue(Realm.getDefault(), selectedDataObservable, ProcessPackage.Literals.ELEMENT__NAME));

    }

    private void createProcessDataMappingTreeViewer(final Composite composite) {
        final CheckboxTreeViewer treeViewer = new CheckboxTreeViewer(composite, SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL);
        treeViewer.getTree().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 200).create());
        treeViewer.getTree().setHeaderVisible(true);
        treeViewer.addCheckStateListener(this);
        treeViewer.setCheckStateProvider(getCheckStateProvider(treeViewer));

    }

    /**
     * @param treeViewer
     * @return
     */
    private ICheckStateProvider getCheckStateProvider(final CheckboxTreeViewer treeViewer) {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
     */
    @Override
    public void checkStateChanged(final CheckStateChangedEvent event) {

    }

}
