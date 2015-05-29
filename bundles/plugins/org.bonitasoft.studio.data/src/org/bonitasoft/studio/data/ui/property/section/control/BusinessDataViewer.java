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
package org.bonitasoft.studio.data.ui.property.section.control;

import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.ui.BusinessObjectDataStyledLabelProvider;
import org.bonitasoft.studio.businessobject.ui.wizard.AddBusinessObjectDataWizard;
import org.bonitasoft.studio.businessobject.ui.wizard.EditBusinessObjectDataWizard;
import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.data.i18n.Messages;
import org.bonitasoft.studio.data.ui.property.section.RemoveDataHandler;
import org.bonitasoft.studio.model.process.BusinessObjectData;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class BusinessDataViewer extends DataViewer {

    private final BusinessObjectModelRepositoryStore store;

    public BusinessDataViewer(final Composite parent, final TabbedPropertySheetWidgetFactory widgetFactory, final EStructuralFeature dataFeature,
            final BusinessObjectModelRepositoryStore store) {
        super(parent, widgetFactory, dataFeature);
        this.store = store;
    }

    @Override
    protected IBaseLabelProvider createLabelProvider(final IObservableMap[] labelMaps) {
        return new BusinessObjectDataStyledLabelProvider(store, labelMaps);
    }

    @Override
    protected void addData() {
        final Pool pool = (Pool) getDataContainerObservable().getValue();
        createWizardDialog(new AddBusinessObjectDataWizard(pool, store, TransactionUtil.getEditingDomain(pool)), IDialogConstants.FINISH_LABEL).open();
    }

    private ViewerFilter acceptBusinessObjectData() {
        return new ViewerFilter() {

            @Override
            public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
                return element instanceof BusinessObjectData;
            }
        };
    }

    @Override
    protected void removeData(final IObservableValue observable, final EStructuralFeature dataFeature) {
        new RemoveDataHandler().execute(getStructuredSelection(), (EObject) observable.getValue(), dataFeature);
    }

    @Override
    protected void editData() {
        final IStructuredSelection selection = getStructuredSelection();
        if (onlyOneElementSelected(selection)) {
            final BusinessObjectData selectedData = (BusinessObjectData) selection.getFirstElement();
            createWizardDialog(new EditBusinessObjectDataWizard(selectedData, store, TransactionUtil.getEditingDomain(selectedData)), IDialogConstants.OK_LABEL)
                    .open();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewerComposite#addFilters(org.eclipse.jface.viewers.StructuredViewer)
     */
    @Override
    protected void addFilters(final StructuredViewer viewer) {
        viewer.addFilter(acceptBusinessObjectData());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewerComposite#getTitle()
     */
    @Override
    protected String getTitle() {
        return Messages.businessData;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewerComposite#getTitleDescripiton()
     */
    @Override
    protected String getTitleDescripiton() {
        return Messages.businessDataHint;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewer#getAddButtonId()
     */
    @Override
    protected String getAddButtonId() {
        return SWTBotConstants.SWTBOT_ID_ADD_BUSINESS_DATA;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewer#getRemoveButtonId()
     */
    @Override
    protected String getRemoveButtonId() {
        return SWTBotConstants.SWTBOT_ID_REMOVE_BUSINESS_DATA;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewer#getEditDataId()
     */
    @Override
    protected String getEditDataId() {
        return SWTBotConstants.SWTBOT_ID_EDIT_BUSINESS_DATA;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.data.ui.property.section.control.DataViewer#getTableId()
     */
    @Override
    protected String getTableId() {
        return SWTBotConstants.SWTBOT_ID_BUSINESS_DATA_LIST;
    }

}
