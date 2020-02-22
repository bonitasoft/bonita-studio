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
package org.bonitasoft.studio.data.ui.property.section;

import javax.inject.Inject;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.data.ui.property.section.control.BusinessDataViewer;
import org.bonitasoft.studio.data.ui.property.section.control.DataWizardFactory;
import org.bonitasoft.studio.data.ui.property.section.control.ProcessDataViewer;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

public class PoolDataSection extends AbstractBonitaDescriptionSection {

    private final RepositoryAccessor repositoryAccessor;
    private final PoolAdaptableSelectionProvider selectionProvider;
    private EMFDataBindingContext context;
    private BusinessDataViewer businessDataComposite;

    @Inject
    public PoolDataSection(final RepositoryAccessor repositoryAccessor, final PoolAdaptableSelectionProvider selectionProvider) {
        this.repositoryAccessor = repositoryAccessor;
        this.selectionProvider = selectionProvider;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#createContent(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createContent(final Composite parent) {
        final Composite mainComposite = getWidgetFactory().createComposite(parent);
        mainComposite.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(true).create());
        mainComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

        context = new EMFDataBindingContext();
        BusinessObjectModelRepositoryStore businessObjectModelRepositoryStore = repositoryAccessor.getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        businessDataComposite = new BusinessDataViewer(mainComposite, getWidgetFactory(), ProcessPackage.Literals.DATA_AWARE__DATA,
                businessObjectModelRepositoryStore);
        repositoryAccessor.getWorkspace().addResourceChangeListener(businessDataComposite);
        businessDataComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        businessDataComposite.bindControl(context, ViewersObservables.observeSingleSelection(selectionProvider));

        final ProcessDataViewer processDataViewer = new ProcessDataViewer(mainComposite, getWidgetFactory(), ProcessPackage.Literals.DATA_AWARE__DATA,
                new DataWizardFactory(ProcessPackage.Literals.DATA_AWARE__DATA));
        processDataViewer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        processDataViewer.bindControl(context, ViewersObservables.observeSingleSelection(selectionProvider));
    }
    
    @Override
    public void dispose() {
        if(businessDataComposite != null) {
            repositoryAccessor.getWorkspace().removeResourceChangeListener(businessDataComposite);
        }
        super.dispose();
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        selectionProvider.setSelection(selection);
        if(businessDataComposite != null) {
            businessDataComposite.updateTopControl();
        }
    }

    protected DataBindingContext getContext() {
        return context;
    }

    protected PoolAdaptableSelectionProvider getSelectionProvider() {
        return selectionProvider;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.properties.AbstractBonitaDescriptionSection#getSectionDescription()
     */
    @Override
    public String getSectionDescription() {
        return Messages.dataSectionWithBusinessData;
    }

}
