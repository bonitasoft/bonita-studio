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

import java.util.List;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.ui.BusinessObjectDataStyledLabelProvider;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.contract.i18n.Messages;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * @author aurelie
 */
public class SelectBusinessDataWizardPage extends WizardPage {

    List<Data> availableBusinessData;

    public SelectBusinessDataWizardPage(final List<Data> availableBusinessData) {
        super(SelectBusinessDataWizardPage.class.getName());
        setTitle(Messages.SelectBusinessDataWizardPageTitle);
        setDescription(Messages.selectBusinessDataWizardPageDescription);
        this.availableBusinessData = availableBusinessData;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        createTableViewer(parent);
        setControl(parent);
    }

    public void createTableViewer(final Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(GridLayoutFactory.fillDefaults().create());
        composite.setLayoutData(GridDataFactory.fillDefaults().create());
        final TableViewer businessDataTableViewer = new TableViewer(composite, SWT.BORDER | SWT.MULTI | SWT.NO_FOCUS | SWT.H_SCROLL | SWT.V_SCROLL);
        businessDataTableViewer.getTable().setLayout(GridLayoutFactory.fillDefaults().create());
        businessDataTableViewer.getTable().setLayoutData(GridDataFactory.fillDefaults().grab(true, true).hint(200, 100).create());
        final ObservableListContentProvider contentProvider = new ObservableListContentProvider();
        businessDataTableViewer.setContentProvider(contentProvider);
        final IObservableSet knownElements = contentProvider.getKnownElements();
        final IObservableMap[] labelMaps = EMFObservables.observeMaps(knownElements, new EStructuralFeature[] { ProcessPackage.Literals.ELEMENT__NAME,
                ProcessPackage.Literals.JAVA_OBJECT_DATA__CLASS_NAME });
        businessDataTableViewer.setLabelProvider(new BusinessObjectDataStyledLabelProvider(RepositoryManager.getInstance().getRepositoryStore(
                BusinessObjectModelRepositoryStore.class), labelMaps));
        businessDataTableViewer.setInput(new WritableList(availableBusinessData, ProcessPackage.Literals.BUSINESS_OBJECT_DATA));
    }

}
